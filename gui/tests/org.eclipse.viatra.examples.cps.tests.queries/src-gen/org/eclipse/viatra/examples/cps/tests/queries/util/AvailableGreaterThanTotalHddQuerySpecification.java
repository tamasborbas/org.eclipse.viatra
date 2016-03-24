package org.eclipse.viatra.examples.cps.tests.queries.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.examples.cps.tests.queries.AvailableGreaterThanTotalHddMatch;
import org.eclipse.viatra.examples.cps.tests.queries.AvailableGreaterThanTotalHddMatcher;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.psystem.IExpressionEvaluator;
import org.eclipse.viatra.query.runtime.matchers.psystem.IValueProvider;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExpressionEvaluation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate AvailableGreaterThanTotalHddMatcher in a type-safe way.
 * 
 * @see AvailableGreaterThanTotalHddMatcher
 * @see AvailableGreaterThanTotalHddMatch
 * 
 */
@SuppressWarnings("all")
public final class AvailableGreaterThanTotalHddQuerySpecification extends BaseGeneratedEMFQuerySpecification<AvailableGreaterThanTotalHddMatcher> {
  private AvailableGreaterThanTotalHddQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static AvailableGreaterThanTotalHddQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected AvailableGreaterThanTotalHddMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AvailableGreaterThanTotalHddMatcher.on(engine);
  }
  
  @Override
  public AvailableGreaterThanTotalHddMatch newEmptyMatch() {
    return AvailableGreaterThanTotalHddMatch.newEmptyMatch();
  }
  
  @Override
  public AvailableGreaterThanTotalHddMatch newMatch(final Object... parameters) {
    return AvailableGreaterThanTotalHddMatch.newMatch((org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link AvailableGreaterThanTotalHddQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link AvailableGreaterThanTotalHddQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static AvailableGreaterThanTotalHddQuerySpecification INSTANCE = new AvailableGreaterThanTotalHddQuerySpecification();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private final static Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternalSneaky();
      return null;					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static AvailableGreaterThanTotalHddQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "org.eclipse.viatra.examples.cps.tests.queries.availableGreaterThanTotalHdd";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("host");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("host", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_host = body.getOrCreateVariableByName("host");
      		PVariable var_aHdd = body.getOrCreateVariableByName("aHdd");
      		PVariable var_tHdd = body.getOrCreateVariableByName("tHdd");
      		new TypeConstraint(body, new FlatTuple(var_host), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "HostInstance")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_host, "host")
      		));
      		// 	HostInstance.availableHdd(host, aHdd)
      		new TypeConstraint(body, new FlatTuple(var_host), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "HostInstance")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_host, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://org.eclipse.viatra/model/cps", "HostInstance", "availableHdd")));
      		new Equality(body, var__virtual_0_, var_aHdd);
      		// 	HostInstance.totalHdd(host, tHdd)
      		new TypeConstraint(body, new FlatTuple(var_host), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "HostInstance")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_host, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://org.eclipse.viatra/model/cps", "HostInstance", "totalHdd")));
      		new Equality(body, var__virtual_1_, var_tHdd);
      		// 	check(aHdd > tHdd)
      		new ExpressionEvaluation(body, new IExpressionEvaluator() {
      		                            
      		                            @Override
      		                            public String getShortDescription() {
      		                                return "Expression evaluation from pattern availableGreaterThanTotalHdd";
      		                            }
      		
      		                            @Override
      		                            public Iterable<String> getInputParameterNames() {
      		                                return Arrays.asList("aHdd", "tHdd");
      		                            }
      		
      		                            @Override
      		                            public Object evaluateExpression(IValueProvider provider) throws Exception {
      		                                    java.lang.Integer aHdd = (java.lang.Integer) provider.getValue("aHdd");
      		                                    java.lang.Integer tHdd = (java.lang.Integer) provider.getValue("tHdd");
      		                                    return evaluateExpression_1_1(aHdd, tHdd);
      		                                }
      		
      		                        },  null); 
      		bodies.add(body);
      	}
      	                {
      		PAnnotation annotation = new PAnnotation("Constraint");
      		annotation.addAttribute("severity", "error");
      		annotation.addAttribute("message", "The available HDD ($host.availableHdd$) of $host.identifier$ is greater than the total ($host.totalHdd$)");
      		annotation.addAttribute("key", Arrays.asList(new Object[] {
      		                "host"
      		                }));
      		addAnnotation(annotation);
      	}
      	// to silence compiler error
      	if (false) throw new ViatraQueryException("Never", "happens");
      } catch (ViatraQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
  
  private static boolean evaluateExpression_1_1(final Integer aHdd, final Integer tHdd) {
    boolean _greaterThan = (aHdd.compareTo(tHdd) > 0);
    return _greaterThan;
  }
}
