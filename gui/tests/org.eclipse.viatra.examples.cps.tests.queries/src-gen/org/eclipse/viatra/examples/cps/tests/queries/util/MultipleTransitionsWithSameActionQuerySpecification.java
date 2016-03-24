package org.eclipse.viatra.examples.cps.tests.queries.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.examples.cps.tests.queries.MultipleTransitionsWithSameActionMatch;
import org.eclipse.viatra.examples.cps.tests.queries.MultipleTransitionsWithSameActionMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.util.ActionOfTransitionQuerySpecification;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Inequality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate MultipleTransitionsWithSameActionMatcher in a type-safe way.
 * 
 * @see MultipleTransitionsWithSameActionMatcher
 * @see MultipleTransitionsWithSameActionMatch
 * 
 */
@SuppressWarnings("all")
public final class MultipleTransitionsWithSameActionQuerySpecification extends BaseGeneratedEMFQuerySpecification<MultipleTransitionsWithSameActionMatcher> {
  private MultipleTransitionsWithSameActionQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static MultipleTransitionsWithSameActionQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected MultipleTransitionsWithSameActionMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return MultipleTransitionsWithSameActionMatcher.on(engine);
  }
  
  @Override
  public MultipleTransitionsWithSameActionMatch newEmptyMatch() {
    return MultipleTransitionsWithSameActionMatch.newEmptyMatch();
  }
  
  @Override
  public MultipleTransitionsWithSameActionMatch newMatch(final Object... parameters) {
    return MultipleTransitionsWithSameActionMatch.newMatch((org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State) parameters[0], (java.lang.String) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link MultipleTransitionsWithSameActionQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link MultipleTransitionsWithSameActionQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static MultipleTransitionsWithSameActionQuerySpecification INSTANCE = new MultipleTransitionsWithSameActionQuerySpecification();
    
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
    private final static MultipleTransitionsWithSameActionQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "org.eclipse.viatra.examples.cps.tests.queries.multipleTransitionsWithSameAction";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("state","action");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("state", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State"),new PParameter("action", "java.lang.String"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_state = body.getOrCreateVariableByName("state");
      		PVariable var_action = body.getOrCreateVariableByName("action");
      		PVariable var_transition = body.getOrCreateVariableByName("transition");
      		PVariable var_otherTransition = body.getOrCreateVariableByName("otherTransition");
      		new TypeConstraint(body, new FlatTuple(var_state), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "State")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_state, "state"),
      		   new ExportedParameter(body, var_action, "action")
      		));
      		// 	State.outgoingTransitions(state, transition)
      		new TypeConstraint(body, new FlatTuple(var_state), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "State")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_state, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://org.eclipse.viatra/model/cps", "State", "outgoingTransitions")));
      		new Equality(body, var__virtual_0_, var_transition);
      		// 	State.outgoingTransitions(state, otherTransition)
      		new TypeConstraint(body, new FlatTuple(var_state), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "State")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_state, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://org.eclipse.viatra/model/cps", "State", "outgoingTransitions")));
      		new Equality(body, var__virtual_1_, var_otherTransition);
      		// 	find actionOfTransition(transition, action)
      		new PositivePatternCall(body, new FlatTuple(var_transition, var_action), ActionOfTransitionQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	find actionOfTransition(otherTransition, action)
      		new PositivePatternCall(body, new FlatTuple(var_otherTransition, var_action), ActionOfTransitionQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	transition != otherTransition
      		new Inequality(body, var_transition, var_otherTransition);
      		bodies.add(body);
      	}
      	                {
      		PAnnotation annotation = new PAnnotation("Constraint");
      		annotation.addAttribute("severity", "error");
      		annotation.addAttribute("message", "Multiple outgoing transitions of $state.identifier$ define the same action ($action$)");
      		annotation.addAttribute("key", Arrays.asList(new Object[] {
      		                "state"
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
