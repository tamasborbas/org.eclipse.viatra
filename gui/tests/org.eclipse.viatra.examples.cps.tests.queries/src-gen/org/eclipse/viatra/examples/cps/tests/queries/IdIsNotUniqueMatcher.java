package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Identifiable;
import org.eclipse.viatra.examples.cps.tests.queries.IdIsNotUniqueMatch;
import org.eclipse.viatra.examples.cps.tests.queries.util.IdIsNotUniqueQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * Generated pattern matcher API of the org.eclipse.viatra.examples.cps.tests.queries.idIsNotUnique pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link IdIsNotUniqueMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * {@literal @}Constraint(
 * 	key = {"identifiable"},
 * 	message = "The ID of $identifiable.identifier$ is not unique",
 * 	severity = "error"
 * )
 * pattern idIsNotUnique(identifiable : Identifiable) {
 * 	find identifiableId(identifiable, id);
 * 	find identifiableId(otherIdentifiable, id);
 * 	identifiable != otherIdentifiable; 
 * }
 * </pre></code>
 * 
 * @see IdIsNotUniqueMatch
 * @see IdIsNotUniqueProcessor
 * @see IdIsNotUniqueQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class IdIsNotUniqueMatcher extends BaseMatcher<IdIsNotUniqueMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static IdIsNotUniqueMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    IdIsNotUniqueMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new IdIsNotUniqueMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_IDENTIFIABLE = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(IdIsNotUniqueMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private IdIsNotUniqueMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pIdentifiable the fixed value of pattern parameter identifiable, or null if not bound.
   * @return matches represented as a IdIsNotUniqueMatch object.
   * 
   */
  public Collection<IdIsNotUniqueMatch> getAllMatches(final Identifiable pIdentifiable) {
    return rawGetAllMatches(new Object[]{pIdentifiable});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pIdentifiable the fixed value of pattern parameter identifiable, or null if not bound.
   * @return a match represented as a IdIsNotUniqueMatch object, or null if no match is found.
   * 
   */
  public IdIsNotUniqueMatch getOneArbitraryMatch(final Identifiable pIdentifiable) {
    return rawGetOneArbitraryMatch(new Object[]{pIdentifiable});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pIdentifiable the fixed value of pattern parameter identifiable, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Identifiable pIdentifiable) {
    return rawHasMatch(new Object[]{pIdentifiable});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pIdentifiable the fixed value of pattern parameter identifiable, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Identifiable pIdentifiable) {
    return rawCountMatches(new Object[]{pIdentifiable});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pIdentifiable the fixed value of pattern parameter identifiable, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Identifiable pIdentifiable, final IMatchProcessor<? super IdIsNotUniqueMatch> processor) {
    rawForEachMatch(new Object[]{pIdentifiable}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pIdentifiable the fixed value of pattern parameter identifiable, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Identifiable pIdentifiable, final IMatchProcessor<? super IdIsNotUniqueMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pIdentifiable}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pIdentifiable the fixed value of pattern parameter identifiable, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public IdIsNotUniqueMatch newMatch(final Identifiable pIdentifiable) {
    return IdIsNotUniqueMatch.newMatch(pIdentifiable);
  }
  
  /**
   * Retrieve the set of values that occur in matches for identifiable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Identifiable> rawAccumulateAllValuesOfidentifiable(final Object[] parameters) {
    Set<Identifiable> results = new HashSet<Identifiable>();
    rawAccumulateAllValues(POSITION_IDENTIFIABLE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for identifiable.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Identifiable> getAllValuesOfidentifiable() {
    return rawAccumulateAllValuesOfidentifiable(emptyArray());
  }
  
  @Override
  protected IdIsNotUniqueMatch tupleToMatch(final Tuple t) {
    try {
    	return IdIsNotUniqueMatch.newMatch((Identifiable) t.get(POSITION_IDENTIFIABLE));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected IdIsNotUniqueMatch arrayToMatch(final Object[] match) {
    try {
    	return IdIsNotUniqueMatch.newMatch((Identifiable) match[POSITION_IDENTIFIABLE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected IdIsNotUniqueMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return IdIsNotUniqueMatch.newMutableMatch((Identifiable) match[POSITION_IDENTIFIABLE]);
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
  public static IQuerySpecification<IdIsNotUniqueMatcher> querySpecification() throws ViatraQueryException {
    return IdIsNotUniqueQuerySpecification.instance();
  }
}
