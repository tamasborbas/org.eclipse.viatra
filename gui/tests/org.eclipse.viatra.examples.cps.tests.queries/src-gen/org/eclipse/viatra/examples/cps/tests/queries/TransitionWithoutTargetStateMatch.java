package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition;
import org.eclipse.viatra.examples.cps.tests.queries.util.TransitionWithoutTargetStateQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the org.eclipse.viatra.examples.cps.tests.queries.transitionWithoutTargetState pattern,
 * to be used in conjunction with {@link TransitionWithoutTargetStateMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see TransitionWithoutTargetStateMatcher
 * @see TransitionWithoutTargetStateProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class TransitionWithoutTargetStateMatch extends BasePatternMatch {
  private Transition fTransition;
  
  private static List<String> parameterNames = makeImmutableList("transition");
  
  private TransitionWithoutTargetStateMatch(final Transition pTransition) {
    this.fTransition = pTransition;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("transition".equals(parameterName)) return this.fTransition;
    return null;
  }
  
  public Transition getTransition() {
    return this.fTransition;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("transition".equals(parameterName) ) {
    	this.fTransition = (Transition) newValue;
    	return true;
    }
    return false;
  }
  
  public void setTransition(final Transition pTransition) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTransition = pTransition;
  }
  
  @Override
  public String patternName() {
    return "org.eclipse.viatra.examples.cps.tests.queries.transitionWithoutTargetState";
  }
  
  @Override
  public List<String> parameterNames() {
    return TransitionWithoutTargetStateMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fTransition};
  }
  
  @Override
  public TransitionWithoutTargetStateMatch toImmutable() {
    return isMutable() ? newMatch(fTransition) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"transition\"=" + prettyPrintValue(fTransition)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fTransition == null) ? 0 : fTransition.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof TransitionWithoutTargetStateMatch)) { // this should be infrequent
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
    TransitionWithoutTargetStateMatch other = (TransitionWithoutTargetStateMatch) obj;
    if (fTransition == null) {if (other.fTransition != null) return false;}
    else if (!fTransition.equals(other.fTransition)) return false;
    return true;
  }
  
  @Override
  public TransitionWithoutTargetStateQuerySpecification specification() {
    try {
    	return TransitionWithoutTargetStateQuerySpecification.instance();
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
  public static TransitionWithoutTargetStateMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static TransitionWithoutTargetStateMatch newMutableMatch(final Transition pTransition) {
    return new Mutable(pTransition);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static TransitionWithoutTargetStateMatch newMatch(final Transition pTransition) {
    return new Immutable(pTransition);
  }
  
  private static final class Mutable extends TransitionWithoutTargetStateMatch {
    Mutable(final Transition pTransition) {
      super(pTransition);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends TransitionWithoutTargetStateMatch {
    Immutable(final Transition pTransition) {
      super(pTransition);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
