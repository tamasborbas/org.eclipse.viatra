package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance;
import org.eclipse.viatra.examples.cps.tests.queries.AvailableGreaterThanTotalHddMatch;
import org.eclipse.viatra.examples.cps.tests.queries.util.AvailableGreaterThanTotalHddQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * Generated pattern matcher API of the org.eclipse.viatra.examples.cps.tests.queries.availableGreaterThanTotalHdd pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link AvailableGreaterThanTotalHddMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * {@literal @}Constraint(
 * 	key = {"host"},
 * 	message = "The available HDD ($host.availableHdd$) of $host.identifier$ is greater than the total ($host.totalHdd$)",
 * 	severity = "error"
 * )
 * pattern availableGreaterThanTotalHdd(host : HostInstance) {
 * 	HostInstance.availableHdd(host, aHdd);
 * 	HostInstance.totalHdd(host, tHdd);
 * 
 * 	check(aHdd {@literal >} tHdd);
 * }
 * </pre></code>
 * 
 * @see AvailableGreaterThanTotalHddMatch
 * @see AvailableGreaterThanTotalHddProcessor
 * @see AvailableGreaterThanTotalHddQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class AvailableGreaterThanTotalHddMatcher extends BaseMatcher<AvailableGreaterThanTotalHddMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static AvailableGreaterThanTotalHddMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    AvailableGreaterThanTotalHddMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new AvailableGreaterThanTotalHddMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_HOST = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(AvailableGreaterThanTotalHddMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private AvailableGreaterThanTotalHddMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pHost the fixed value of pattern parameter host, or null if not bound.
   * @return matches represented as a AvailableGreaterThanTotalHddMatch object.
   * 
   */
  public Collection<AvailableGreaterThanTotalHddMatch> getAllMatches(final HostInstance pHost) {
    return rawGetAllMatches(new Object[]{pHost});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pHost the fixed value of pattern parameter host, or null if not bound.
   * @return a match represented as a AvailableGreaterThanTotalHddMatch object, or null if no match is found.
   * 
   */
  public AvailableGreaterThanTotalHddMatch getOneArbitraryMatch(final HostInstance pHost) {
    return rawGetOneArbitraryMatch(new Object[]{pHost});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pHost the fixed value of pattern parameter host, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final HostInstance pHost) {
    return rawHasMatch(new Object[]{pHost});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pHost the fixed value of pattern parameter host, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final HostInstance pHost) {
    return rawCountMatches(new Object[]{pHost});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pHost the fixed value of pattern parameter host, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final HostInstance pHost, final IMatchProcessor<? super AvailableGreaterThanTotalHddMatch> processor) {
    rawForEachMatch(new Object[]{pHost}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pHost the fixed value of pattern parameter host, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final HostInstance pHost, final IMatchProcessor<? super AvailableGreaterThanTotalHddMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pHost}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pHost the fixed value of pattern parameter host, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public AvailableGreaterThanTotalHddMatch newMatch(final HostInstance pHost) {
    return AvailableGreaterThanTotalHddMatch.newMatch(pHost);
  }
  
  /**
   * Retrieve the set of values that occur in matches for host.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<HostInstance> rawAccumulateAllValuesOfhost(final Object[] parameters) {
    Set<HostInstance> results = new HashSet<HostInstance>();
    rawAccumulateAllValues(POSITION_HOST, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for host.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<HostInstance> getAllValuesOfhost() {
    return rawAccumulateAllValuesOfhost(emptyArray());
  }
  
  @Override
  protected AvailableGreaterThanTotalHddMatch tupleToMatch(final Tuple t) {
    try {
    	return AvailableGreaterThanTotalHddMatch.newMatch((HostInstance) t.get(POSITION_HOST));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected AvailableGreaterThanTotalHddMatch arrayToMatch(final Object[] match) {
    try {
    	return AvailableGreaterThanTotalHddMatch.newMatch((HostInstance) match[POSITION_HOST]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected AvailableGreaterThanTotalHddMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return AvailableGreaterThanTotalHddMatch.newMutableMatch((HostInstance) match[POSITION_HOST]);
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
  public static IQuerySpecification<AvailableGreaterThanTotalHddMatcher> querySpecification() throws ViatraQueryException {
    return AvailableGreaterThanTotalHddQuerySpecification.instance();
  }
}
