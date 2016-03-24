package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.StateMachine;
import org.eclipse.viatra.examples.cps.tests.queries.InitialStateNotContainedByStateMachineMatch;
import org.eclipse.viatra.examples.cps.tests.queries.util.InitialStateNotContainedByStateMachineQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * Generated pattern matcher API of the org.eclipse.viatra.examples.cps.tests.queries.initialStateNotContainedByStateMachine pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link InitialStateNotContainedByStateMachineMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * {@literal @}Constraint(
 * 	key = {"statemachine"},
 * 	message = "The initial state $state.identifier$ of $statemachine.identifier$ is not included in its states",
 * 	severity = "error"
 * )
 * pattern initialStateNotContainedByStateMachine(statemachine : StateMachine, state : State) {
 * 	StateMachine.initial(statemachine, state);
 * 	neg find statemachineState(statemachine, state);
 * }
 * </pre></code>
 * 
 * @see InitialStateNotContainedByStateMachineMatch
 * @see InitialStateNotContainedByStateMachineProcessor
 * @see InitialStateNotContainedByStateMachineQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class InitialStateNotContainedByStateMachineMatcher extends BaseMatcher<InitialStateNotContainedByStateMachineMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static InitialStateNotContainedByStateMachineMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    InitialStateNotContainedByStateMachineMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new InitialStateNotContainedByStateMachineMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_STATEMACHINE = 0;
  
  private final static int POSITION_STATE = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(InitialStateNotContainedByStateMachineMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private InitialStateNotContainedByStateMachineMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pStatemachine the fixed value of pattern parameter statemachine, or null if not bound.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @return matches represented as a InitialStateNotContainedByStateMachineMatch object.
   * 
   */
  public Collection<InitialStateNotContainedByStateMachineMatch> getAllMatches(final StateMachine pStatemachine, final State pState) {
    return rawGetAllMatches(new Object[]{pStatemachine, pState});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pStatemachine the fixed value of pattern parameter statemachine, or null if not bound.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @return a match represented as a InitialStateNotContainedByStateMachineMatch object, or null if no match is found.
   * 
   */
  public InitialStateNotContainedByStateMachineMatch getOneArbitraryMatch(final StateMachine pStatemachine, final State pState) {
    return rawGetOneArbitraryMatch(new Object[]{pStatemachine, pState});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pStatemachine the fixed value of pattern parameter statemachine, or null if not bound.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final StateMachine pStatemachine, final State pState) {
    return rawHasMatch(new Object[]{pStatemachine, pState});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pStatemachine the fixed value of pattern parameter statemachine, or null if not bound.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final StateMachine pStatemachine, final State pState) {
    return rawCountMatches(new Object[]{pStatemachine, pState});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pStatemachine the fixed value of pattern parameter statemachine, or null if not bound.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final StateMachine pStatemachine, final State pState, final IMatchProcessor<? super InitialStateNotContainedByStateMachineMatch> processor) {
    rawForEachMatch(new Object[]{pStatemachine, pState}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pStatemachine the fixed value of pattern parameter statemachine, or null if not bound.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final StateMachine pStatemachine, final State pState, final IMatchProcessor<? super InitialStateNotContainedByStateMachineMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pStatemachine, pState}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pStatemachine the fixed value of pattern parameter statemachine, or null if not bound.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public InitialStateNotContainedByStateMachineMatch newMatch(final StateMachine pStatemachine, final State pState) {
    return InitialStateNotContainedByStateMachineMatch.newMatch(pStatemachine, pState);
  }
  
  /**
   * Retrieve the set of values that occur in matches for statemachine.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<StateMachine> rawAccumulateAllValuesOfstatemachine(final Object[] parameters) {
    Set<StateMachine> results = new HashSet<StateMachine>();
    rawAccumulateAllValues(POSITION_STATEMACHINE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for statemachine.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<StateMachine> getAllValuesOfstatemachine() {
    return rawAccumulateAllValuesOfstatemachine(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for statemachine.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<StateMachine> getAllValuesOfstatemachine(final InitialStateNotContainedByStateMachineMatch partialMatch) {
    return rawAccumulateAllValuesOfstatemachine(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for statemachine.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<StateMachine> getAllValuesOfstatemachine(final State pState) {
    return rawAccumulateAllValuesOfstatemachine(new Object[]{
    null, 
    pState
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for state.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<State> rawAccumulateAllValuesOfstate(final Object[] parameters) {
    Set<State> results = new HashSet<State>();
    rawAccumulateAllValues(POSITION_STATE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for state.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<State> getAllValuesOfstate() {
    return rawAccumulateAllValuesOfstate(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for state.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<State> getAllValuesOfstate(final InitialStateNotContainedByStateMachineMatch partialMatch) {
    return rawAccumulateAllValuesOfstate(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for state.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<State> getAllValuesOfstate(final StateMachine pStatemachine) {
    return rawAccumulateAllValuesOfstate(new Object[]{
    pStatemachine, 
    null
    });
  }
  
  @Override
  protected InitialStateNotContainedByStateMachineMatch tupleToMatch(final Tuple t) {
    try {
    	return InitialStateNotContainedByStateMachineMatch.newMatch((StateMachine) t.get(POSITION_STATEMACHINE), (State) t.get(POSITION_STATE));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected InitialStateNotContainedByStateMachineMatch arrayToMatch(final Object[] match) {
    try {
    	return InitialStateNotContainedByStateMachineMatch.newMatch((StateMachine) match[POSITION_STATEMACHINE], (State) match[POSITION_STATE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected InitialStateNotContainedByStateMachineMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return InitialStateNotContainedByStateMachineMatch.newMutableMatch((StateMachine) match[POSITION_STATEMACHINE], (State) match[POSITION_STATE]);
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
  public static IQuerySpecification<InitialStateNotContainedByStateMachineMatcher> querySpecification() throws ViatraQueryException {
    return InitialStateNotContainedByStateMachineQuerySpecification.instance();
  }
}
