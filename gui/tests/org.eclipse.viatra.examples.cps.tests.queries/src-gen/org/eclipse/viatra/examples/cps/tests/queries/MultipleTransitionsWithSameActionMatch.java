package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State;
import org.eclipse.viatra.examples.cps.tests.queries.util.MultipleTransitionsWithSameActionQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the org.eclipse.viatra.examples.cps.tests.queries.multipleTransitionsWithSameAction pattern,
 * to be used in conjunction with {@link MultipleTransitionsWithSameActionMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see MultipleTransitionsWithSameActionMatcher
 * @see MultipleTransitionsWithSameActionProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class MultipleTransitionsWithSameActionMatch extends BasePatternMatch {
  private State fState;
  
  private String fAction;
  
  private static List<String> parameterNames = makeImmutableList("state", "action");
  
  private MultipleTransitionsWithSameActionMatch(final State pState, final String pAction) {
    this.fState = pState;
    this.fAction = pAction;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("state".equals(parameterName)) return this.fState;
    if ("action".equals(parameterName)) return this.fAction;
    return null;
  }
  
  public State getState() {
    return this.fState;
  }
  
  public String getAction() {
    return this.fAction;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("state".equals(parameterName) ) {
    	this.fState = (State) newValue;
    	return true;
    }
    if ("action".equals(parameterName) ) {
    	this.fAction = (String) newValue;
    	return true;
    }
    return false;
  }
  
  public void setState(final State pState) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fState = pState;
  }
  
  public void setAction(final String pAction) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fAction = pAction;
  }
  
  @Override
  public String patternName() {
    return "org.eclipse.viatra.examples.cps.tests.queries.multipleTransitionsWithSameAction";
  }
  
  @Override
  public List<String> parameterNames() {
    return MultipleTransitionsWithSameActionMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fState, fAction};
  }
  
  @Override
  public MultipleTransitionsWithSameActionMatch toImmutable() {
    return isMutable() ? newMatch(fState, fAction) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"state\"=" + prettyPrintValue(fState) + ", ");
    
    result.append("\"action\"=" + prettyPrintValue(fAction)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fState == null) ? 0 : fState.hashCode());
    result = prime * result + ((fAction == null) ? 0 : fAction.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof MultipleTransitionsWithSameActionMatch)) { // this should be infrequent
    	if (obj == null) {
    		return false;
    	}
    	if (!(obj instanceof IPatternMatch)) {
    		return false;
    	}
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    MultipleTransitionsWithSameActionMatch other = (MultipleTransitionsWithSameActionMatch) obj;
    if (fState == null) {if (other.fState != null) return false;}
    else if (!fState.equals(other.fState)) return false;
    if (fAction == null) {if (other.fAction != null) return false;}
    else if (!fAction.equals(other.fAction)) return false;
    return true;
  }
  
  @Override
  public MultipleTransitionsWithSameActionQuerySpecification specification() {
    try {
    	return MultipleTransitionsWithSameActionQuerySpecification.instance();
    } catch (ViatraQueryException ex) {
     	// This cannot happen, as the match object can only be instantiated if the query specification exists
     	throw new IllegalStateException (ex);
    }
  }
  
  /**
   * Returns an empty, mutable match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @return the empty match.
   * 
   */
  public static MultipleTransitionsWithSameActionMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @param pAction the fixed value of pattern parameter action, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static MultipleTransitionsWithSameActionMatch newMutableMatch(final State pState, final String pAction) {
    return new Mutable(pState, pAction);
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
  public static MultipleTransitionsWithSameActionMatch newMatch(final State pState, final String pAction) {
    return new Immutable(pState, pAction);
  }
  
  private static final class Mutable extends MultipleTransitionsWithSameActionMatch {
    Mutable(final State pState, final String pAction) {
      super(pState, pAction);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends MultipleTransitionsWithSameActionMatch {
    Immutable(final State pState, final String pAction) {
      super(pState, pAction);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
