package org.eclipse.viatra.examples.cps.tests.queries.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.ViatraQueryMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate IdentifiableIdMatcher in a type-safe way.
 * 
 * @see IdentifiableIdMatcher
 * @see IdentifiableIdMatch
 * 
 */
@SuppressWarnings("all")
final class IdentifiableIdQuerySpecification extends BaseGeneratedEMFQuerySpecification<ViatraQueryMatcher<IPatternMatch>> {
  private IdentifiableIdQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static IdentifiableIdQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ViatraQueryMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    throw new UnsupportedOperationException();
  }
  
  @Override
  public IPatternMatch newEmptyMatch() {
    throw new UnsupportedOperationException();
  }
  
  @Override
  public IPatternMatch newMatch(final Object... parameters) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Inner class allowing the singleton instance of {@link IdentifiableIdQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link IdentifiableIdQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static IdentifiableIdQuerySpecification INSTANCE = new IdentifiableIdQuerySpecification();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private final static Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternalSneaky();
      return null;					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static IdentifiableIdQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "org.eclipse.viatra.examples.cps.tests.queries.identifiableId";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("identifiable","id");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("identifiable", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Identifiable"),new PParameter("id", "java.lang.String"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_identifiable = body.getOrCreateVariableByName("identifiable");
      		PVariable var_id = body.getOrCreateVariableByName("id");
      		new TypeConstraint(body, new FlatTuple(var_identifiable), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "Identifiable")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_identifiable, "identifiable"),
      		   new ExportedParameter(body, var_id, "id")
      		));
      		// 	Identifiable.identifier(identifiable, id)
      		new TypeConstraint(body, new FlatTuple(var_identifiable), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "Identifiable")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_identifiable, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://org.eclipse.viatra/model/cps", "Identifiable", "identifier")));
      		new Equality(body, var__virtual_0_, var_id);
      		bodies.add(body);
      	}
      	// to silence compiler error
      	if (false) throw new ViatraQueryException("Never", "happens");
      } catch (ViatraQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
