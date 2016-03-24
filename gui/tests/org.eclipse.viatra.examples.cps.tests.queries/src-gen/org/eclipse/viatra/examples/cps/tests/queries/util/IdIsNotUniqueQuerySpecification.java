package org.eclipse.viatra.examples.cps.tests.queries.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.examples.cps.tests.queries.IdIsNotUniqueMatch;
import org.eclipse.viatra.examples.cps.tests.queries.IdIsNotUniqueMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.util.IdentifiableIdQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Inequality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate IdIsNotUniqueMatcher in a type-safe way.
 * 
 * @see IdIsNotUniqueMatcher
 * @see IdIsNotUniqueMatch
 * 
 */
@SuppressWarnings("all")
public final class IdIsNotUniqueQuerySpecification extends BaseGeneratedEMFQuerySpecification<IdIsNotUniqueMatcher> {
  private IdIsNotUniqueQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static IdIsNotUniqueQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected IdIsNotUniqueMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return IdIsNotUniqueMatcher.on(engine);
  }
  
  @Override
  public IdIsNotUniqueMatch newEmptyMatch() {
    return IdIsNotUniqueMatch.newEmptyMatch();
  }
  
  @Override
  public IdIsNotUniqueMatch newMatch(final Object... parameters) {
    return IdIsNotUniqueMatch.newMatch((org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Identifiable) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link IdIsNotUniqueQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link IdIsNotUniqueQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static IdIsNotUniqueQuerySpecification INSTANCE = new IdIsNotUniqueQuerySpecification();
    
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
    private final static IdIsNotUniqueQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "org.eclipse.viatra.examples.cps.tests.queries.idIsNotUnique";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("identifiable");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("identifiable", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Identifiable"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_identifiable = body.getOrCreateVariableByName("identifiable");
      		PVariable var_id = body.getOrCreateVariableByName("id");
      		PVariable var_otherIdentifiable = body.getOrCreateVariableByName("otherIdentifiable");
      		new TypeConstraint(body, new FlatTuple(var_identifiable), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "Identifiable")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_identifiable, "identifiable")
      		));
      		// 	find identifiableId(identifiable, id)
      		new PositivePatternCall(body, new FlatTuple(var_identifiable, var_id), IdentifiableIdQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	find identifiableId(otherIdentifiable, id)
      		new PositivePatternCall(body, new FlatTuple(var_otherIdentifiable, var_id), IdentifiableIdQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	identifiable != otherIdentifiable
      		new Inequality(body, var_identifiable, var_otherIdentifiable);
      		bodies.add(body);
      	}
      	                {
      		PAnnotation annotation = new PAnnotation("Constraint");
      		annotation.addAttribute("severity", "error");
      		annotation.addAttribute("message", "The ID of $identifiable.identifier$ is not unique");
      		annotation.addAttribute("key", Arrays.asList(new Object[] {
      		                "identifiable"
      		                }));
      		addAnnotation(annotation);
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
