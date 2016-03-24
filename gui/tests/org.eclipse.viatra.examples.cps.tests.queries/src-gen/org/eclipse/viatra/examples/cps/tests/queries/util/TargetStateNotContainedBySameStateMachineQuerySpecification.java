package org.eclipse.viatra.examples.cps.tests.queries.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.examples.cps.tests.queries.TargetStateNotContainedBySameStateMachineMatch;
import org.eclipse.viatra.examples.cps.tests.queries.TargetStateNotContainedBySameStateMachineMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.util.StateTransitionQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.StatemachineStateQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate TargetStateNotContainedBySameStateMachineMatcher in a type-safe way.
 * 
 * @see TargetStateNotContainedBySameStateMachineMatcher
 * @see TargetStateNotContainedBySameStateMachineMatch
 * 
 */
@SuppressWarnings("all")
public final class TargetStateNotContainedBySameStateMachineQuerySpecification extends BaseGeneratedEMFQuerySpecification<TargetStateNotContainedBySameStateMachineMatcher> {
  private TargetStateNotContainedBySameStateMachineQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static TargetStateNotContainedBySameStateMachineQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected TargetStateNotContainedBySameStateMachineMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return TargetStateNotContainedBySameStateMachineMatcher.on(engine);
  }
  
  @Override
  public TargetStateNotContainedBySameStateMachineMatch newEmptyMatch() {
    return TargetStateNotContainedBySameStateMachineMatch.newEmptyMatch();
  }
  
  @Override
  public TargetStateNotContainedBySameStateMachineMatch newMatch(final Object... parameters) {
    return TargetStateNotContainedBySameStateMachineMatch.newMatch((org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition) parameters[0], (org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link TargetStateNotContainedBySameStateMachineQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link TargetStateNotContainedBySameStateMachineQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static TargetStateNotContainedBySameStateMachineQuerySpecification INSTANCE = new TargetStateNotContainedBySameStateMachineQuerySpecification();
    
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
    private final static TargetStateNotContainedBySameStateMachineQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "org.eclipse.viatra.examples.cps.tests.queries.targetStateNotContainedBySameStateMachine";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("transition","target");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("transition", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition"),new PParameter("target", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_transition = body.getOrCreateVariableByName("transition");
      		PVariable var_target = body.getOrCreateVariableByName("target");
      		PVariable var_source = body.getOrCreateVariableByName("source");
      		PVariable var_statemachine = body.getOrCreateVariableByName("statemachine");
      		new TypeConstraint(body, new FlatTuple(var_transition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "Transition")));
      		new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "State")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_transition, "transition"),
      		   new ExportedParameter(body, var_target, "target")
      		));
      		// 	find stateTransition(source, transition, target)
      		new PositivePatternCall(body, new FlatTuple(var_source, var_transition, var_target), StateTransitionQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	find statemachineState(statemachine, source)
      		new PositivePatternCall(body, new FlatTuple(var_statemachine, var_source), StatemachineStateQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	neg find statemachineState(statemachine, target)
      		new NegativePatternCall(body, new FlatTuple(var_statemachine, var_target), StatemachineStateQuerySpecification.instance().getInternalQueryRepresentation());
      		bodies.add(body);
      	}
      	                {
      		PAnnotation annotation = new PAnnotation("Constraint");
      		annotation.addAttribute("severity", "error");
      		annotation.addAttribute("message", "The target state $target.identifier$ of $transition.identifier$ is not in the same state machine");
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
