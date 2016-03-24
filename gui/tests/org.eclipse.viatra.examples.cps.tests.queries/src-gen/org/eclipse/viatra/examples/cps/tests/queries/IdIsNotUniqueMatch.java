package org.eclipse.viatra.examples.cps.tests.queries;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Identifiable;
import org.eclipse.viatra.examples.cps.tests.queries.util.IdIsNotUniqueQuerySpecification;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the org.eclipse.viatra.examples.cps.tests.queries.idIsNotUnique pattern,
 * to be used in conjunction with {@link IdIsNotUniqueMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see IdIsNotUniqueMatcher
 * @see IdIsNotUniqueProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class IdIsNotUniqueMatch extends BasePatternMatch {
  private Identifiable fIdentifiable;
  
  private static List<String> parameterNames = makeImmutableList("identifiable");
  
  private IdIsNotUniqueMatch(final Identifiable pIdentifiable) {
    this.fIdentifiable = pIdentifiable;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("identifiable".equals(parameterName)) return this.fIdentifiable;
    return null;
  }
  
  public Identifiable getIdentifiable() {
    return this.fIdentifiable;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("identifiable".equals(parameterName) ) {
    	this.fIdentifiable = (Identifiable) newValue;
    	return true;
    }
    return false;
  }
  
  public void setIdentifiable(final Identifiable pIdentifiable) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fIdentifiable = pIdentifiable;
  }
  
  @Override
  public String patternName() {
    return "org.eclipse.viatra.examples.cps.tests.queries.idIsNotUnique";
  }
  
  @Override
  public List<String> parameterNames() {
    return IdIsNotUniqueMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fIdentifiable};
  }
  
  @Override
  public IdIsNotUniqueMatch toImmutable() {
    return isMutable() ? newMatch(fIdentifiable) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"identifiable\"=" + prettyPrintValue(fIdentifiable)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fIdentifiable == null) ? 0 : fIdentifiable.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof IdIsNotUniqueMatch)) { // this should be infrequent
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
    IdIsNotUniqueMatch other = (IdIsNotUniqueMatch) obj;
    if (fIdentifiable == null) {if (other.fIdentifiable != null) return false;}
    else if (!fIdentifiable.equals(other.fIdentifiable)) return false;
    return true;
  }
  
  @Override
  public IdIsNotUniqueQuerySpecification specification() {
    try {
    	return IdIsNotUniqueQuerySpecification.instance();
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
  public static IdIsNotUniqueMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pIdentifiable the fixed value of pattern parameter identifiable, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static IdIsNotUniqueMatch newMutableMatch(final Identifiable pIdentifiable) {
    return new Mutable(pIdentifiable);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pIdentifiable the fixed value of pattern parameter identifiable, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static IdIsNotUniqueMatch newMatch(final Identifiable pIdentifiable) {
    return new Immutable(pIdentifiable);
  }
  
  private static final class Mutable extends IdIsNotUniqueMatch {
    Mutable(final Identifiable pIdentifiable) {
      super(pIdentifiable);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends IdIsNotUniqueMatch {
    Immutable(final Identifiable pIdentifiable) {
      super(pIdentifiable);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
