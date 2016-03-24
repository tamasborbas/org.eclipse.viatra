package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State;
import org.eclipse.viatra.examples.cps.tests.queries.MultipleTransitionsWithSameActionMatch;
import org.eclipse.viatra.examples.cps.tests.queries.util.MultipleTransitionsWithSameActionQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * Generated pattern matcher API of the org.eclipse.viatra.examples.cps.tests.queries.multipleTransitionsWithSameAction pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link MultipleTransitionsWithSameActionMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * {@literal @}Constraint(
 * 	key = {"state"},
 * 	message = "Multiple outgoing transitions of $state.identifier$ define the same action ($action$)",
 * 	severity = "error"
 * )
 * pattern multipleTransitionsWithSameAction(state : State, action) {
 * 	State.outgoingTransitions(state, transition);
 * 	State.outgoingTransitions(state, otherTransition);
 * 	find actionOfTransition(transition, action);
 * 	find actionOfTransition(otherTransition, action);
 * 	transition != otherTransition;
 * }
 * </pre></code>
 * 
 * @see MultipleTransitionsWithSameActionMatch
 * @see MultipleTransitionsWithSameActionProcessor
 * @see MultipleTransitionsWithSameActionQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class MultipleTransitionsWithSameActionMatcher extends BaseMatcher<MultipleTransitionsWithSameActionMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static MultipleTransitionsWithSameActionMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    MultipleTransitionsWithSameActionMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new MultipleTransitionsWithSameActionMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_STATE = 0;
  
  private final static int POSITION_ACTION = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(MultipleTransitionsWithSameActionMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private MultipleTransitionsWithSameActionMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param pAction the fixed value of pattern parameter action, or null if not bound.
   * @return matches represented as a MultipleTransitionsWithSameActionMatch object.
   * 
   */
  public Collection<MultipleTransitionsWithSameActionMatch> getAllMatches(final State pState, final String pAction) {
    return rawGetAllMatches(new Object[]{pState, pAction});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param pAction the fixed value of pattern parameter action, or null if not bound.
   * @return a match represented as a MultipleTransitionsWithSameActionMatch object, or null if no match is found.
   * 
   */
  public MultipleTransitionsWithSameActionMatch getOneArbitraryMatch(final State pState, final String pAction) {
    return rawGetOneArbitraryMatch(new Object[]{pState, pAction});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param pAction the fixed value of pattern parameter action, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final State pState, final String pAction) {
    return rawHasMatch(new Object[]{pState, pAction});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param pAction the fixed value of pattern parameter action, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final State pState, final String pAction) {
    return rawCountMatches(new Object[]{pState, pAction});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param pAction the fixed value of pattern parameter action, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final State pState, final String pAction, final IMatchProcessor<? super MultipleTransitionsWithSameActionMatch> processor) {
    rawForEachMatch(new Object[]{pState, pAction}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param pAction the fixed value of pattern parameter action, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final State pState, final String pAction, final IMatchProcessor<? super MultipleTransitionsWithSameActionMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pState, pAction}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param pAction the fixed value of pattern parameter action, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public MultipleTransitionsWithSameActionMatch newMatch(final State pState, final String pAction) {
    return MultipleTransitionsWithSameActionMatch.newMatch(pState, pAction);
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
  public Set<State> getAllValuesOfstate(final MultipleTransitionsWithSameActionMatch partialMatch) {
    return rawAccumulateAllValuesOfstate(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for state.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<State> getAllValuesOfstate(final String pAction) {
    return rawAccumulateAllValuesOfstate(new Object[]{
    null, 
    pAction
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for action.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<String> rawAccumulateAllValuesOfaction(final Object[] parameters) {
    Set<String> results = new HashSet<String>();
    rawAccumulateAllValues(POSITION_ACTION, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for action.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<String> getAllValuesOfaction() {
    return rawAccumulateAllValuesOfaction(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for action.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<String> getAllValuesOfaction(final MultipleTransitionsWithSameActionMatch partialMatch) {
    return rawAccumulateAllValuesOfaction(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for action.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<String> getAllValuesOfaction(final State pState) {
    return rawAccumulateAllValuesOfaction(new Object[]{
    pState, 
    null
    });
  }
  
  @Override
  protected MultipleTransitionsWithSameActionMatch tupleToMatch(final Tuple t) {
    try {
    	return MultipleTransitionsWithSameActionMatch.newMatch((State) t.get(POSITION_STATE), (String) t.get(POSITION_ACTION));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected MultipleTransitionsWithSameActionMatch arrayToMatch(final Object[] match) {
    try {
    	return MultipleTransitionsWithSameActionMatch.newMatch((State) match[POSITION_STATE], (String) match[POSITION_ACTION]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected MultipleTransitionsWithSameActionMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return MultipleTransitionsWithSameActionMatch.newMutableMatch((State) match[POSITION_STATE], (String) match[POSITION_ACTION]);
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
  public static IQuerySpecification<MultipleTransitionsWithSameActionMatcher> querySpecification() throws ViatraQueryException {
    return MultipleTransitionsWithSameActionQuerySpecification.instance();
  }
}
