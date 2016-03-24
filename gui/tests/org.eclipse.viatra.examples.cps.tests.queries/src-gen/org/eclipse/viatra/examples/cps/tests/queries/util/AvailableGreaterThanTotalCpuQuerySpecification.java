package org.eclipse.viatra.examples.cps.tests.queries.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.examples.cps.tests.queries.AvailableGreaterThanTotalCpuMatch;
import org.eclipse.viatra.examples.cps.tests.queries.AvailableGreaterThanTotalCpuMatcher;
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
 * A pattern-specific query specification that can instantiate AvailableGreaterThanTotalCpuMatcher in a type-safe way.
 * 
 * @see AvailableGreaterThanTotalCpuMatcher
 * @see AvailableGreaterThanTotalCpuMatch
 * 
 */
@SuppressWarnings("all")
public final class AvailableGreaterThanTotalCpuQuerySpecification extends BaseGeneratedEMFQuerySpecification<AvailableGreaterThanTotalCpuMatcher> {
  private AvailableGreaterThanTotalCpuQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static AvailableGreaterThanTotalCpuQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected AvailableGreaterThanTotalCpuMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AvailableGreaterThanTotalCpuMatcher.on(engine);
  }
  
  @Override
  public AvailableGreaterThanTotalCpuMatch newEmptyMatch() {
    return AvailableGreaterThanTotalCpuMatch.newEmptyMatch();
  }
  
  @Override
  public AvailableGreaterThanTotalCpuMatch newMatch(final Object... parameters) {
    return AvailableGreaterThanTotalCpuMatch.newMatch((org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link AvailableGreaterThanTotalCpuQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link AvailableGreaterThanTotalCpuQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static AvailableGreaterThanTotalCpuQuerySpecification INSTANCE = new AvailableGreaterThanTotalCpuQuerySpecification();
    
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
    private final static AvailableGreaterThanTotalCpuQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "org.eclipse.viatra.examples.cps.tests.queries.availableGreaterThanTotalCpu";
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
      		PVariable var_aCpu = body.getOrCreateVariableByName("aCpu");
      		PVariable var_tCpu = body.getOrCreateVariableByName("tCpu");
      		new TypeConstraint(body, new FlatTuple(var_host), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "HostInstance")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_host, "host")
      		));
      		// 	HostInstance.availableCpu(host, aCpu)
      		new TypeConstraint(body, new FlatTuple(var_host), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "HostInstance")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_host, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://org.eclipse.viatra/model/cps", "HostInstance", "availableCpu")));
      		new Equality(body, var__virtual_0_, var_aCpu);
      		// 	HostInstance.totalCpu(host, tCpu)
      		new TypeConstraint(body, new FlatTuple(var_host), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "HostInstance")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_host, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://org.eclipse.viatra/model/cps", "HostInstance", "totalCpu")));
      		new Equality(body, var__virtual_1_, var_tCpu);
      		// 	check(aCpu > tCpu)
      		new ExpressionEvaluation(body, new IExpressionEvaluator() {
      		                            
      		                            @Override
      		                            public String getShortDescription() {
      		                                return "Expression evaluation from pattern availableGreaterThanTotalCpu";
      		                            }
      		
      		                            @Override
      		                            public Iterable<String> getInputParameterNames() {
      		                                return Arrays.asList("aCpu", "tCpu");
      		                            }
      		
      		                            @Override
      		                            public Object evaluateExpression(IValueProvider provider) throws Exception {
      		                                    java.lang.Integer aCpu = (java.lang.Integer) provider.getValue("aCpu");
      		                                    java.lang.Integer tCpu = (java.lang.Integer) provider.getValue("tCpu");
      		                                    return evaluateExpression_1_1(aCpu, tCpu);
      		                                }
      		
      		                        },  null); 
      		bodies.add(body);
      	}
      	                {
      		PAnnotation annotation = new PAnnotation("Constraint");
      		annotation.addAttribute("severity", "error");
      		annotation.addAttribute("message", "The available CPU ($host.availableCpu$) of $host.identifier$ is greater than the total ($host.totalCpu$)");
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
  
  private static boolean evaluateExpression_1_1(final Integer aCpu, final Integer tCpu) {
    boolean _greaterThan = (aCpu.compareTo(tCpu) > 0);
    return _greaterThan;
  }
}
