package org.eclipse.viatra.examples.cps.tests.queries.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.examples.cps.tests.queries.TransitionWithoutTargetStateMatch;
import org.eclipse.viatra.examples.cps.tests.queries.TransitionWithoutTargetStateMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.util.StateTransitionQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate TransitionWithoutTargetStateMatcher in a type-safe way.
 * 
 * @see TransitionWithoutTargetStateMatcher
 * @see TransitionWithoutTargetStateMatch
 * 
 */
@SuppressWarnings("all")
public final class TransitionWithoutTargetStateQuerySpecification extends BaseGeneratedEMFQuerySpecification<TransitionWithoutTargetStateMatcher> {
  private TransitionWithoutTargetStateQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static TransitionWithoutTargetStateQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected TransitionWithoutTargetStateMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return TransitionWithoutTargetStateMatcher.on(engine);
  }
  
  @Override
  public TransitionWithoutTargetStateMatch newEmptyMatch() {
    return TransitionWithoutTargetStateMatch.newEmptyMatch();
  }
  
  @Override
  public TransitionWithoutTargetStateMatch newMatch(final Object... parameters) {
    return TransitionWithoutTargetStateMatch.newMatch((org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link TransitionWithoutTargetStateQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link TransitionWithoutTargetStateQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static TransitionWithoutTargetStateQuerySpecification INSTANCE = new TransitionWithoutTargetStateQuerySpecification();
    
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
    private final static TransitionWithoutTargetStateQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "org.eclipse.viatra.examples.cps.tests.queries.transitionWithoutTargetState";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("transition");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("transition", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_transition = body.getOrCreateVariableByName("transition");
      		PVariable var_source = body.getOrCreateVariableByName("source");
      		PVariable var___0_ = body.getOrCreateVariableByName("_<0>");
      		new TypeConstraint(body, new FlatTuple(var_transition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "Transition")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_transition, "transition")
      		));
      		// 	State.outgoingTransitions(source, transition)
      		new TypeConstraint(body, new FlatTuple(var_source), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "State")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_source, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://org.eclipse.viatra/model/cps", "State", "outgoingTransitions")));
      		new Equality(body, var__virtual_0_, var_transition);
      		// 	neg find stateTransition(source, transition, _)
      		new NegativePatternCall(body, new FlatTuple(var_source, var_transition, var___0_), StateTransitionQuerySpecification.instance().getInternalQueryRepresentation());
      		bodies.add(body);
      	}
      	                {
      		PAnnotation annotation = new PAnnotation("Constraint");
      		annotation.addAttribute("severity", "error");
      		annotation.addAttribute("message", "No target state set for $transition.identifier$");
      		annotation.addAttribute("key", Arrays.asList(new Object[] {
      		                "transition"
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
