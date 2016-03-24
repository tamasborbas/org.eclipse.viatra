package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.ApplicationInstance;
import org.eclipse.viatra.examples.cps.tests.queries.util.NotAllocatedButRunningQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the org.eclipse.viatra.examples.cps.tests.queries.notAllocatedButRunning pattern,
 * to be used in conjunction with {@link NotAllocatedButRunningMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see NotAllocatedButRunningMatcher
 * @see NotAllocatedButRunningProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class NotAllocatedButRunningMatch extends BasePatternMatch {
  private ApplicationInstance fApp;
  
  private static List<String> parameterNames = makeImmutableList("app");
  
  private NotAllocatedButRunningMatch(final ApplicationInstance pApp) {
    this.fApp = pApp;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("app".equals(parameterName)) return this.fApp;
    return null;
  }
  
  public ApplicationInstance getApp() {
    return this.fApp;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("app".equals(parameterName) ) {
    	this.fApp = (ApplicationInstance) newValue;
    	return true;
    }
    return false;
  }
  
  public void setApp(final ApplicationInstance pApp) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fApp = pApp;
  }
  
  @Override
  public String patternName() {
    return "org.eclipse.viatra.examples.cps.tests.queries.notAllocatedButRunning";
  }
  
  @Override
  public List<String> parameterNames() {
    return NotAllocatedButRunningMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fApp};
  }
  
  @Override
  public NotAllocatedButRunningMatch toImmutable() {
    return isMutable() ? newMatch(fApp) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"app\"=" + prettyPrintValue(fApp)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fApp == null) ? 0 : fApp.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof NotAllocatedButRunningMatch)) { // this should be infrequent
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
    NotAllocatedButRunningMatch other = (NotAllocatedButRunningMatch) obj;
    if (fApp == null) {if (other.fApp != null) return false;}
    else if (!fApp.equals(other.fApp)) return false;
    return true;
  }
  
  @Override
  public NotAllocatedButRunningQuerySpecification specification() {
    try {
    	return NotAllocatedButRunningQuerySpecification.instance();
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
  public static NotAllocatedButRunningMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pApp the fixed value of pattern parameter app, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static NotAllocatedButRunningMatch newMutableMatch(final ApplicationInstance pApp) {
    return new Mutable(pApp);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pApp the fixed value of pattern parameter app, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static NotAllocatedButRunningMatch newMatch(final ApplicationInstance pApp) {
    return new Immutable(pApp);
  }
  
  private static final class Mutable extends NotAllocatedButRunningMatch {
    Mutable(final ApplicationInstance pApp) {
      super(pApp);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends NotAllocatedButRunningMatch {
    Immutable(final ApplicationInstance pApp) {
      super(pApp);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
