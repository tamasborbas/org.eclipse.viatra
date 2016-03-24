package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.StateMachine;
import org.eclipse.viatra.examples.cps.tests.queries.util.InitialStateNotContainedByStateMachineQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the org.eclipse.viatra.examples.cps.tests.queries.initialStateNotContainedByStateMachine pattern,
 * to be used in conjunction with {@link InitialStateNotContainedByStateMachineMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see InitialStateNotContainedByStateMachineMatcher
 * @see InitialStateNotContainedByStateMachineProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class InitialStateNotContainedByStateMachineMatch extends BasePatternMatch {
  private StateMachine fStatemachine;
  
  private State fState;
  
  private static List<String> parameterNames = makeImmutableList("statemachine", "state");
  
  private InitialStateNotContainedByStateMachineMatch(final StateMachine pStatemachine, final State pState) {
    this.fStatemachine = pStatemachine;
    this.fState = pState;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("statemachine".equals(parameterName)) return this.fStatemachine;
    if ("state".equals(parameterName)) return this.fState;
    return null;
  }
  
  public StateMachine getStatemachine() {
    return this.fStatemachine;
  }
  
  public State getState() {
    return this.fState;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("statemachine".equals(parameterName) ) {
    	this.fStatemachine = (StateMachine) newValue;
    	return true;
    }
    if ("state".equals(parameterName) ) {
    	this.fState = (State) newValue;
    	return true;
    }
    return false;
  }
  
  public void setStatemachine(final StateMachine pStatemachine) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fStatemachine = pStatemachine;
  }
  
  public void setState(final State pState) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fState = pState;
  }
  
  @Override
  public String patternName() {
    return "org.eclipse.viatra.examples.cps.tests.queries.initialStateNotContainedByStateMachine";
  }
  
  @Override
  public List<String> parameterNames() {
    return InitialStateNotContainedByStateMachineMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fStatemachine, fState};
  }
  
  @Override
  public InitialStateNotContainedByStateMachineMatch toImmutable() {
    return isMutable() ? newMatch(fStatemachine, fState) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"statemachine\"=" + prettyPrintValue(fStatemachine) + ", ");
    
    result.append("\"state\"=" + prettyPrintValue(fState)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fStatemachine == null) ? 0 : fStatemachine.hashCode());
    result = prime * result + ((fState == null) ? 0 : fState.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof InitialStateNotContainedByStateMachineMatch)) { // this should be infrequent
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
    InitialStateNotContainedByStateMachineMatch other = (InitialStateNotContainedByStateMachineMatch) obj;
    if (fStatemachine == null) {if (other.fStatemachine != null) return false;}
    else if (!fStatemachine.equals(other.fStatemachine)) return false;
    if (fState == null) {if (other.fState != null) return false;}
    else if (!fState.equals(other.fState)) return false;
    return true;
  }
  
  @Override
  public InitialStateNotContainedByStateMachineQuerySpecification specification() {
    try {
    	return InitialStateNotContainedByStateMachineQuerySpecification.instance();
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
  public static InitialStateNotContainedByStateMachineMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pStatemachine the fixed value of pattern parameter statemachine, or null if not bound.
   * @param pState the fixed value of pattern parameter state, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static InitialStateNotContainedByStateMachineMatch newMutableMatch(final StateMachine pStatemachine, final State pState) {
    return new Mutable(pStatemachine, pState);
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
  public static InitialStateNotContainedByStateMachineMatch newMatch(final StateMachine pStatemachine, final State pState) {
    return new Immutable(pStatemachine, pState);
  }
  
  private static final class Mutable extends InitialStateNotContainedByStateMachineMatch {
    Mutable(final StateMachine pStatemachine, final State pState) {
      super(pStatemachine, pState);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends InitialStateNotContainedByStateMachineMatch {
    Immutable(final StateMachine pStatemachine, final State pState) {
      super(pStatemachine, pState);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
