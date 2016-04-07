/** 
 * Copyright (c) 2010-2015, Denes Harmath, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Denes Harmath - initial API and implementation
 */
package org.eclipse.viatra.query.patternlanguage.emf.jvmmodel

import com.google.common.base.Joiner
import java.util.Arrays
import java.util.List
import org.eclipse.emf.common.util.Enumerator
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EEnumLiteral
import org.eclipse.viatra.query.patternlanguage.emf.specification.XBaseEvaluator
import org.eclipse.viatra.query.patternlanguage.emf.specification.internal.PatternBodyTransformer
import org.eclipse.viatra.query.patternlanguage.emf.util.EMFPatternLanguageJvmModelInferrerUtil
import org.eclipse.viatra.query.patternlanguage.emf.util.IErrorFeedback
import org.eclipse.viatra.query.patternlanguage.patternLanguage.Constraint
import org.eclipse.viatra.query.patternlanguage.patternLanguage.Pattern
import org.eclipse.viatra.query.patternlanguage.patternLanguage.PatternBody
import org.eclipse.viatra.query.patternlanguage.patternLanguage.Variable
import org.eclipse.viatra.query.runtime.api.IQuerySpecification
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey
import org.eclipse.viatra.query.runtime.emf.types.EDataTypeInSlotsKey
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey
import org.eclipse.viatra.query.runtime.matchers.context.IInputKey
import org.eclipse.viatra.query.runtime.matchers.psystem.IExpressionEvaluator
import org.eclipse.viatra.query.runtime.matchers.psystem.IValueProvider
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExpressionEvaluation
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Inequality
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.NegativePatternCall
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.PatternMatchCounter
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.BinaryTransitiveClosure
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.ConstantValue
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple
import org.eclipse.xtend2.lib.StringConcatenationClient
import org.eclipse.xtend2.lib.StringConcatenationClient.TargetStringConcatenation
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.serializer.impl.Serializer
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.compiler.output.ImportingStringConcatenation

import static extension org.eclipse.viatra.query.patternlanguage.emf.jvmmodel.BodyCodeGenerator.*
import org.eclipse.viatra.query.patternlanguage.emf.specification.internal.PatternModelAcceptor

/** 
 * {@link PatternModelAcceptor} implementation that generates body code for {@link IQuerySpecification} classes.
 * Implementation note: it extends {@link StringConcatenationClient} so that it can be used with {@link ImportingStringConcatenation}.
 * @since 1.1
 */
class BodyCodeGenerator extends StringConcatenationClient {

    val Pattern pattern
    val PatternBody body
    extension val EMFPatternLanguageJvmModelInferrerUtil util
    val IErrorFeedback feedback
    val Serializer serializer

    new(Pattern pattern, PatternBody body, EMFPatternLanguageJvmModelInferrerUtil util, IErrorFeedback feedback, Serializer serializer) {
        this.pattern = pattern
        this.body = body
        this.util = util
        this.feedback = feedback
        this.serializer = serializer
    }

    override protected appendTo(TargetStringConcatenation target) {
        val acceptor = new PatternModelAcceptor<Void>() {
               
            var int lastId = 0
        
            override getResult() {
            }
        
            override acceptVariable(Variable variable) {
                declareVariable(variable.name, target)
                variable.name
            }
            
            override createVirtualVariable() {
                val virtualVariableName = '''.virtual{«lastId»}'''
                lastId++
                declareVariable(virtualVariableName, target)
                virtualVariableName
            }
            
            override createConstantVariable(Object value) {
                val virtualVariable = createVirtualVariable
                target.append('''new ''')
                target.append(ConstantValue)
                target.append('''(body, «virtualVariable.escape», «value.outputConstant»);
                ''')
                virtualVariable
            }
        
            private def StringConcatenationClient outputConstant(Object constant) {
                switch constant {
                    EEnumLiteral: {
                        val enumeration = constant.EEnum
                        val ePackage = enumeration.EPackage
                        '''getEnumLiteral("«ePackage.nsURI»", "«enumeration.name»", "«constant.name»").getInstance()'''
                    }
                    Enumerator : {
                        '''«constant.class.canonicalName».get("«constant.literal»")'''
                    }
                    String :
                        '''"«constant»"'''
                    default :
                        '''«constant»'''
                }
            }
        
            override acceptExportedParameters(List<Variable> parameters) {
                target.append('''
                        body.setSymbolicParameters(''')
                target.append(Arrays)
                target.append('''.<''')
                target.append(ExportedParameter)
                target.append('''>asList(
                            ''')
                parameters.forEach[parameter, index |
                    target.append('''   new ''')
                    target.append(ExportedParameter)
                    target.append('''(body, «parameter.name.escape», "«parameter.name»")''')
                    if (index < parameters.length - 1) { // XXX separator logic
                        target.append(',')
                    }
                    target.append('\n')
                ]
                target.append('''));
                ''')
            }
        
            override acceptConstraint(Constraint constraint) {
                target.append('''// «serializer.serialize(constraint).replaceAll('\r\n?|\n','')»
                ''')
            }
        
            override acceptTypeConstraint(List<String> variableNames, IInputKey key) {
                target.append('''new ''')
                target.append(TypeConstraint)
                target.append('''(body, new ''')
                target.append(FlatTuple)
                target.append('''(«variableNames.output»), ''')
                target.appendInputKey(key, false)
                target.append(''');
                ''')
            }
        
            private def output(List<String> variableNames) {
                Joiner.on(", ").join(variableNames.map[escape])
            }
        
            override acceptPositivePatternCall(List<String> argumentVariableNames, Pattern calledPattern) {
                target.append('''new ''')
                target.append(PositivePatternCall)
                target.append('''(body, new ''')
                target.append(FlatTuple)
                target.append('''(«argumentVariableNames.output»), ''')
                referPQuery(calledPattern, pattern, target)
                target.append(''');
                ''')
            }
        
            private def referPQuery(Pattern calledPattern, Pattern callerPattern, TargetStringConcatenation target) {
                if (calledPattern == callerPattern) {
                    target.append('''this''')
                } else {
                    target.append(calledPattern.findInferredSpecification)
                    target.append('''.instance().getInternalQueryRepresentation()''')
                }
            }
        
            override acceptNegativePatternCall(List<String> argumentVariableNames, Pattern calledPattern) {
                target.append('''new ''')
                target.append(NegativePatternCall)
                target.append('''(body, new ''')
                target.append(FlatTuple)
                target.append('''(«argumentVariableNames.output»), ''')
                referPQuery(calledPattern, pattern, target)
                target.append(''');
                ''')
            }
        
            override acceptBinaryTransitiveClosure(List<String> argumentVariableNames, Pattern calledPattern) {
                target.append('''new ''')
                target.append(BinaryTransitiveClosure)
                target.append('''(body, new ''')
                target.append(FlatTuple)
                target.append('''(«argumentVariableNames.output»), ''')
                referPQuery(calledPattern, pattern, target)
                target.append(''');
                ''')
            }
            
            override acceptEquality(String leftOperandVariableName, String rightOperandVariableName) {
                target.append('''new ''')
                target.append(Equality)
                target.append('''(body, «leftOperandVariableName.escape», «rightOperandVariableName.escape»);
                ''')
            }
            
            override acceptInequality(String leftOperandVariableName, String rightOperandVariableName) {
                target.append('''new ''')
                target.append(Inequality)
                target.append('''(body, «leftOperandVariableName.escape», «rightOperandVariableName.escape»);
                ''')
            }
            
            override acceptExpressionEvaluation(XExpression expression, String outputVariableName) {
                val xBaseEvaluator = new XBaseEvaluator(expression, pattern)
                target.append(
                        '''
                        «if (xBaseEvaluator.inputParameterNames.empty) {
                            feedback.reportError(xBaseEvaluator.expression, "No parameters defined", EMFPatternLanguageJvmModelInferrer.SPECIFICATION_BUILDER_CODE, Severity.WARNING, IErrorFeedback.JVMINFERENCE_ERROR_TYPE)
                        }»
                        new ''')
                target.append(ExpressionEvaluation)
                target.append('''(body, new ''')
                target.append(IExpressionEvaluator)
                target.append('''() {
                            
                            @Override
                            public String getShortDescription() {
                                return "Expression evaluation from pattern «pattern.name»";
                            }

                            @Override
                            public Iterable<String> getInputParameterNames() {
                                return ''')
                target.append(Arrays)
                target.append('''.asList(«FOR name : xBaseEvaluator.inputParameterNames SEPARATOR ", "»"«name»"«ENDFOR»);
                            }

                            @Override
                            public Object evaluateExpression(''')
                target.append(IValueProvider)
                target.append(''' provider) throws Exception {
                                    «val variables = variables(xBaseEvaluator.expression)»
                                    «FOR variable : variables»
                                        «variable.calculateType.qualifiedName» «variable.name» = («variable.calculateType.qualifiedName») provider.getValue("«variable.name»");
                                    «ENDFOR»
                                    return «expressionMethodName(xBaseEvaluator.expression)»(«FOR variable : variables SEPARATOR ', '»«variable.name»«ENDFOR»);
                                }

                        }, «IF outputVariableName != null » «outputVariableName.escape» «ELSE» null«ENDIF»); '''
                )
            }
            
            override acceptPatternMatchCounter(List<String> argumentVariableNames, Pattern calledPattern, String resultVariableName) {
                target.append('''new ''')
                target.append(PatternMatchCounter)
                target.append('''(body, new ''')
                target.append(FlatTuple)
                target.append('''(«argumentVariableNames.output»), ''')
                referPQuery(calledPattern, pattern, target)
                target.append(''', «resultVariableName.escape»);
                ''')
            }
                    
        } // PatternModelAcceptor
        new PatternBodyTransformer(pattern).transform(body, acceptor)
    }
        

    /**
     * Generates a {@link PVariable} declaration with the given name.
     */
    private static def declareVariable(String variableName, TargetStringConcatenation target) {
        target.append(PVariable)
        target.append(''' «variableName.escape» = body.getOrCreateVariableByName("«variableName»");
        ''')
    }

    private static def escape(String name) {
        "var_" + name.replaceAll("[\\.\\{\\}<>]","_")
    }

}
