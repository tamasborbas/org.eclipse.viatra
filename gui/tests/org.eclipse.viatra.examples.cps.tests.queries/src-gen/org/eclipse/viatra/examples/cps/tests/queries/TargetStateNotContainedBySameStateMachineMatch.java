package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Transition;
import org.eclipse.viatra.examples.cps.tests.queries.util.TargetStateNotContainedBySameStateMachineQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the org.eclipse.viatra.examples.cps.tests.queries.targetStateNotContainedBySameStateMachine pattern,
 * to be used in conjunction with {@link TargetStateNotContainedBySameStateMachineMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see TargetStateNotContainedBySameStateMachineMatcher
 * @see TargetStateNotContainedBySameStateMachineProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class TargetStateNotContainedBySameStateMachineMatch extends BasePatternMatch {
  private Transition fTransition;
  
  private State fTarget;
  
  private static List<String> parameterNames = makeImmutableList("transition", "target");
  
  private TargetStateNotContainedBySameStateMachineMatch(final Transition pTransition, final State pTarget) {
    this.fTransition = pTransition;
    this.fTarget = pTarget;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("transition".equals(parameterName)) return this.fTransition;
    if ("target".equals(parameterName)) return this.fTarget;
    return null;
  }
  
  public Transition getTransition() {
    return this.fTransition;
  }
  
  public State getTarget() {
    return this.fTarget;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("transition".equals(parameterName) ) {
    	this.fTransition = (Transition) newValue;
    	return true;
    }
    if ("target".equals(parameterName) ) {
    	this.fTarget = (State) newValue;
    	return true;
    }
    return false;
  }
  
  public void setTransition(final Transition pTransition) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTransition = pTransition;
  }
  
  public void setTarget(final State pTarget) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTarget = pTarget;
  }
  
  @Override
  public String patternName() {
    return "org.eclipse.viatra.examples.cps.tests.queries.targetStateNotContainedBySameStateMachine";
  }
  
  @Override
  public List<String> parameterNames() {
    return TargetStateNotContainedBySameStateMachineMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fTransition, fTarget};
  }
  
  @Override
  public TargetStateNotContainedBySameStateMachineMatch toImmutable() {
    return isMutable() ? newMatch(fTransition, fTarget) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"transition\"=" + prettyPrintValue(fTransition) + ", ");
    
    result.append("\"target\"=" + prettyPrintValue(fTarget)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fTransition == null) ? 0 : fTransition.hashCode());
    result = prime * result + ((fTarget == null) ? 0 : fTarget.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof TargetStateNotContainedBySameStateMachineMatch)) { // this should be infrequent
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
    TargetStateNotContainedBySameStateMachineMatch other = (TargetStateNotContainedBySameStateMachineMatch) obj;
    if (fTransition == null) {if (other.fTransition != null) return false;}
    else if (!fTransition.equals(other.fTransition)) return false;
    if (fTarget == null) {if (other.fTarget != null) return false;}
    else if (!fTarget.equals(other.fTarget)) return false;
    return true;
  }
  
  @Override
  public TargetStateNotContainedBySameStateMachineQuerySpecification specification() {
    try {
    	return TargetStateNotContainedBySameStateMachineQuerySpecification.instance();
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
  public static TargetStateNotContainedBySameStateMachineMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static TargetStateNotContainedBySameStateMachineMatch newMutableMatch(final Transition pTransition, final State pTarget) {
    return new Mutable(pTransition, pTarget);
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
  public static TargetStateNotContainedBySameStateMachineMatch newMatch(final Transition pTransition, final State pTarget) {
    return new Immutable(pTransition, pTarget);
  }
  
  private static final class Mutable extends TargetStateNotContainedBySameStateMachineMatch {
    Mutable(final Transition pTransition, final State pTarget) {
      super(pTransition, pTarget);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends TargetStateNotContainedBySameStateMachineMatch {
    Immutable(final Transition pTransition, final State pTarget) {
      super(pTransition, pTarget);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
