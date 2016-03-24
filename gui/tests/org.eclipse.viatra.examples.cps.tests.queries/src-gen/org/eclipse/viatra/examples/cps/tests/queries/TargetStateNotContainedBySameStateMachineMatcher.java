package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition;
import org.eclipse.viatra.examples.cps.tests.queries.TargetStateNotContainedBySameStateMachineMatch;
import org.eclipse.viatra.examples.cps.tests.queries.util.TargetStateNotContainedBySameStateMachineQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * Generated pattern matcher API of the org.eclipse.viatra.examples.cps.tests.queries.targetStateNotContainedBySameStateMachine pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link TargetStateNotContainedBySameStateMachineMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * {@literal @}Constraint(
 * 	key = {"transition"},
 * 	message = "The target state $target.identifier$ of $transition.identifier$ is not in the same state machine",
 * 	severity = "error"
 * )
 * pattern targetStateNotContainedBySameStateMachine(transition : Transition, target : State) {
 * 	find stateTransition(source, transition, target);
 * 	find statemachineState(statemachine, source);
 * 	neg find statemachineState(statemachine, target);
 * }
 * </pre></code>
 * 
 * @see TargetStateNotContainedBySameStateMachineMatch
 * @see TargetStateNotContainedBySameStateMachineProcessor
 * @see TargetStateNotContainedBySameStateMachineQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class TargetStateNotContainedBySameStateMachineMatcher extends BaseMatcher<TargetStateNotContainedBySameStateMachineMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static TargetStateNotContainedBySameStateMachineMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    TargetStateNotContainedBySameStateMachineMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new TargetStateNotContainedBySameStateMachineMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_TRANSITION = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(TargetStateNotContainedBySameStateMachineMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private TargetStateNotContainedBySameStateMachineMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a TargetStateNotContainedBySameStateMachineMatch object.
   * 
   */
  public Collection<TargetStateNotContainedBySameStateMachineMatch> getAllMatches(final Transition pTransition, final State pTarget) {
    return rawGetAllMatches(new Object[]{pTransition, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a TargetStateNotContainedBySameStateMachineMatch object, or null if no match is found.
   * 
   */
  public TargetStateNotContainedBySameStateMachineMatch getOneArbitraryMatch(final Transition pTransition, final State pTarget) {
    return rawGetOneArbitraryMatch(new Object[]{pTransition, pTarget});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Transition pTransition, final State pTarget) {
    return rawHasMatch(new Object[]{pTransition, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Transition pTransition, final State pTarget) {
    return rawCountMatches(new Object[]{pTransition, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Transition pTransition, final State pTarget, final IMatchProcessor<? super TargetStateNotContainedBySameStateMachineMatch> processor) {
    rawForEachMatch(new Object[]{pTransition, pTarget}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Transition pTransition, final State pTarget, final IMatchProcessor<? super TargetStateNotContainedBySameStateMachineMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pTransition, pTarget}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public TargetStateNotContainedBySameStateMachineMatch newMatch(final Transition pTransition, final State pTarget) {
    return TargetStateNotContainedBySameStateMachineMatch.newMatch(pTransition, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for transition.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Transition> rawAccumulateAllValuesOftransition(final Object[] parameters) {
    Set<Transition> results = new HashSet<Transition>();
    rawAccumulateAllValues(POSITION_TRANSITION, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for transition.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Transition> getAllValuesOftransition() {
    return rawAccumulateAllValuesOftransition(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for transition.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Transition> getAllValuesOftransition(final TargetStateNotContainedBySameStateMachineMatch partialMatch) {
    return rawAccumulateAllValuesOftransition(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for transition.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Transition> getAllValuesOftransition(final State pTarget) {
    return rawAccumulateAllValuesOftransition(new Object[]{
    null, 
    pTarget
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<State> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<State> results = new HashSet<State>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<State> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<State> getAllValuesOftarget(final TargetStateNotContainedBySameStateMachineMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<State> getAllValuesOftarget(final Transition pTransition) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pTransition, 
    null
    });
  }
  
  @Override
  protected TargetStateNotContainedBySameStateMachineMatch tupleToMatch(final Tuple t) {
    try {
    	return TargetStateNotContainedBySameStateMachineMatch.newMatch((Transition) t.get(POSITION_TRANSITION), (State) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TargetStateNotContainedBySameStateMachineMatch arrayToMatch(final Object[] match) {
    try {
    	return TargetStateNotContainedBySameStateMachineMatch.newMatch((Transition) match[POSITION_TRANSITION], (State) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TargetStateNotContainedBySameStateMachineMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return TargetStateNotContainedBySameStateMachineMatch.newMutableMatch((Transition) match[POSITION_TRANSITION], (State) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<TargetStateNotContainedBySameStateMachineMatcher> querySpecification() throws ViatraQueryException {
    return TargetStateNotContainedBySameStateMachineQuerySpecification.instance();
  }
}
