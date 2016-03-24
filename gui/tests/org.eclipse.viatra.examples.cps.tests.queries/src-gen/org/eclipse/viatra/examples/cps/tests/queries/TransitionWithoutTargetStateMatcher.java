package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition;
import org.eclipse.viatra.examples.cps.tests.queries.TransitionWithoutTargetStateMatch;
import org.eclipse.viatra.examples.cps.tests.queries.util.TransitionWithoutTargetStateQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * Generated pattern matcher API of the org.eclipse.viatra.examples.cps.tests.queries.transitionWithoutTargetState pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link TransitionWithoutTargetStateMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * {@literal @}Constraint(
 * 	key = {"transition"},
 * 	message = "No target state set for $transition.identifier$",
 * 	severity = "error"
 * )
 * pattern transitionWithoutTargetState(transition : Transition) {
 * 	State.outgoingTransitions(source, transition);
 * 	neg find stateTransition(source, transition, _);
 * }
 * </pre></code>
 * 
 * @see TransitionWithoutTargetStateMatch
 * @see TransitionWithoutTargetStateProcessor
 * @see TransitionWithoutTargetStateQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class TransitionWithoutTargetStateMatcher extends BaseMatcher<TransitionWithoutTargetStateMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static TransitionWithoutTargetStateMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    TransitionWithoutTargetStateMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new TransitionWithoutTargetStateMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_TRANSITION = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(TransitionWithoutTargetStateMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private TransitionWithoutTargetStateMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @return matches represented as a TransitionWithoutTargetStateMatch object.
   * 
   */
  public Collection<TransitionWithoutTargetStateMatch> getAllMatches(final Transition pTransition) {
    return rawGetAllMatches(new Object[]{pTransition});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @return a match represented as a TransitionWithoutTargetStateMatch object, or null if no match is found.
   * 
   */
  public TransitionWithoutTargetStateMatch getOneArbitraryMatch(final Transition pTransition) {
    return rawGetOneArbitraryMatch(new Object[]{pTransition});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Transition pTransition) {
    return rawHasMatch(new Object[]{pTransition});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Transition pTransition) {
    return rawCountMatches(new Object[]{pTransition});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Transition pTransition, final IMatchProcessor<? super TransitionWithoutTargetStateMatch> processor) {
    rawForEachMatch(new Object[]{pTransition}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Transition pTransition, final IMatchProcessor<? super TransitionWithoutTargetStateMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pTransition}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public TransitionWithoutTargetStateMatch newMatch(final Transition pTransition) {
    return TransitionWithoutTargetStateMatch.newMatch(pTransition);
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
  
  @Override
  protected TransitionWithoutTargetStateMatch tupleToMatch(final Tuple t) {
    try {
    	return TransitionWithoutTargetStateMatch.newMatch((Transition) t.get(POSITION_TRANSITION));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TransitionWithoutTargetStateMatch arrayToMatch(final Object[] match) {
    try {
    	return TransitionWithoutTargetStateMatch.newMatch((Transition) match[POSITION_TRANSITION]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TransitionWithoutTargetStateMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return TransitionWithoutTargetStateMatch.newMutableMatch((Transition) match[POSITION_TRANSITION]);
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
  public static IQuerySpecification<TransitionWithoutTargetStateMatcher> querySpecification() throws ViatraQueryException {
    return TransitionWithoutTargetStateQuerySpecification.instance();
  }
}
