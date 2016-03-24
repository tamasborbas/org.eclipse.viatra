package org.eclipse.viatra.examples.cps.tests.queries.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.examples.cps.tests.queries.MultipleApplicationInstanceInCommunicationGroupMatch;
import org.eclipse.viatra.examples.cps.tests.queries.MultipleApplicationInstanceInCommunicationGroupMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.util.ReachableAppInstanceQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Inequality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate MultipleApplicationInstanceInCommunicationGroupMatcher in a type-safe way.
 * 
 * @see MultipleApplicationInstanceInCommunicationGroupMatcher
 * @see MultipleApplicationInstanceInCommunicationGroupMatch
 * 
 */
@SuppressWarnings("all")
public final class MultipleApplicationInstanceInCommunicationGroupQuerySpecification extends BaseGeneratedEMFQuerySpecification<MultipleApplicationInstanceInCommunicationGroupMatcher> {
  private MultipleApplicationInstanceInCommunicationGroupQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static MultipleApplicationInstanceInCommunicationGroupQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected MultipleApplicationInstanceInCommunicationGroupMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return MultipleApplicationInstanceInCommunicationGroupMatcher.on(engine);
  }
  
  @Override
  public MultipleApplicationInstanceInCommunicationGroupMatch newEmptyMatch() {
    return MultipleApplicationInstanceInCommunicationGroupMatch.newEmptyMatch();
  }
  
  @Override
  public MultipleApplicationInstanceInCommunicationGroupMatch newMatch(final Object... parameters) {
    return MultipleApplicationInstanceInCommunicationGroupMatch.newMatch((org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance) parameters[0], (org.eclipse.viatra.examples.cps.cyberPhysicalSystem.ApplicationType) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link MultipleApplicationInstanceInCommunicationGroupQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link MultipleApplicationInstanceInCommunicationGroupQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static MultipleApplicationInstanceInCommunicationGroupQuerySpecification INSTANCE = new MultipleApplicationInstanceInCommunicationGroupQuerySpecification();
    
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
    private final static MultipleApplicationInstanceInCommunicationGroupQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "org.eclipse.viatra.examples.cps.tests.queries.multipleApplicationInstanceInCommunicationGroup";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("sourceHostInstance","app");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("sourceHostInstance", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance"),new PParameter("app", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.ApplicationType"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_sourceHostInstance = body.getOrCreateVariableByName("sourceHostInstance");
      		PVariable var_app = body.getOrCreateVariableByName("app");
      		PVariable var_appInstance = body.getOrCreateVariableByName("appInstance");
      		PVariable var_otherAppInstance = body.getOrCreateVariableByName("otherAppInstance");
      		new TypeConstraint(body, new FlatTuple(var_sourceHostInstance), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "HostInstance")));
      		new TypeConstraint(body, new FlatTuple(var_app), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "ApplicationType")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_sourceHostInstance, "sourceHostInstance"),
      		   new ExportedParameter(body, var_app, "app")
      		));
      		// 	find reachableAppInstance(sourceHostInstance, app, appInstance)
      		new PositivePatternCall(body, new FlatTuple(var_sourceHostInstance, var_app, var_appInstance), ReachableAppInstanceQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	find reachableAppInstance(sourceHostInstance, app, otherAppInstance)
      		new PositivePatternCall(body, new FlatTuple(var_sourceHostInstance, var_app, var_otherAppInstance), ReachableAppInstanceQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	appInstance != otherAppInstance
      		new Inequality(body, var_appInstance, var_otherAppInstance);
      		bodies.add(body);
      	}
      	                {
      		PAnnotation annotation = new PAnnotation("Constraint");
      		annotation.addAttribute("severity", "error");
      		annotation.addAttribute("message", "Multiple instances of $app.identifier$ are reachable from $sourceHostInstance.identifier$");
      		annotation.addAttribute("key", Arrays.asList(new Object[] {
      		                "sourceHostInstance"
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
}
