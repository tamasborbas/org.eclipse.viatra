package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance;
import org.eclipse.viatra.examples.cps.tests.queries.util.AvailableGreaterThanTotalHddQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the org.eclipse.viatra.examples.cps.tests.queries.availableGreaterThanTotalHdd pattern,
 * to be used in conjunction with {@link AvailableGreaterThanTotalHddMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see AvailableGreaterThanTotalHddMatcher
 * @see AvailableGreaterThanTotalHddProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class AvailableGreaterThanTotalHddMatch extends BasePatternMatch {
  private HostInstance fHost;
  
  private static List<String> parameterNames = makeImmutableList("host");
  
  private AvailableGreaterThanTotalHddMatch(final HostInstance pHost) {
    this.fHost = pHost;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("host".equals(parameterName)) return this.fHost;
    return null;
  }
  
  public HostInstance getHost() {
    return this.fHost;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("host".equals(parameterName) ) {
    	this.fHost = (HostInstance) newValue;
    	return true;
    }
    return false;
  }
  
  public void setHost(final HostInstance pHost) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fHost = pHost;
  }
  
  @Override
  public String patternName() {
    return "org.eclipse.viatra.examples.cps.tests.queries.availableGreaterThanTotalHdd";
  }
  
  @Override
  public List<String> parameterNames() {
    return AvailableGreaterThanTotalHddMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fHost};
  }
  
  @Override
  public AvailableGreaterThanTotalHddMatch toImmutable() {
    return isMutable() ? newMatch(fHost) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"host\"=" + prettyPrintValue(fHost)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fHost == null) ? 0 : fHost.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof AvailableGreaterThanTotalHddMatch)) { // this should be infrequent
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
    AvailableGreaterThanTotalHddMatch other = (AvailableGreaterThanTotalHddMatch) obj;
    if (fHost == null) {if (other.fHost != null) return false;}
    else if (!fHost.equals(other.fHost)) return false;
    return true;
  }
  
  @Override
  public AvailableGreaterThanTotalHddQuerySpecification specification() {
    try {
    	return AvailableGreaterThanTotalHddQuerySpecification.instance();
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
  public static AvailableGreaterThanTotalHddMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pHost the fixed value of pattern parameter host, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static AvailableGreaterThanTotalHddMatch newMutableMatch(final HostInstance pHost) {
    return new Mutable(pHost);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pHost the fixed value of pattern parameter host, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static AvailableGreaterThanTotalHddMatch newMatch(final HostInstance pHost) {
    return new Immutable(pHost);
  }
  
  private static final class Mutable extends AvailableGreaterThanTotalHddMatch {
    Mutable(final HostInstance pHost) {
      super(pHost);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends AvailableGreaterThanTotalHddMatch {
    Immutable(final HostInstance pHost) {
      super(pHost);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
