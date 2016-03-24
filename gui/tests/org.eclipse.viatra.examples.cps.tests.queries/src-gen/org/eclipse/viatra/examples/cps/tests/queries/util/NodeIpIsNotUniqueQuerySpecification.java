package org.eclipse.viatra.examples.cps.tests.queries.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.examples.cps.tests.queries.NodeIpIsNotUniqueMatch;
import org.eclipse.viatra.examples.cps.tests.queries.NodeIpIsNotUniqueMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.util.NodeIpOfHostQuerySpecification;
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
 * A pattern-specific query specification that can instantiate NodeIpIsNotUniqueMatcher in a type-safe way.
 * 
 * @see NodeIpIsNotUniqueMatcher
 * @see NodeIpIsNotUniqueMatch
 * 
 */
@SuppressWarnings("all")
public final class NodeIpIsNotUniqueQuerySpecification extends BaseGeneratedEMFQuerySpecification<NodeIpIsNotUniqueMatcher> {
  private NodeIpIsNotUniqueQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static NodeIpIsNotUniqueQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected NodeIpIsNotUniqueMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return NodeIpIsNotUniqueMatcher.on(engine);
  }
  
  @Override
  public NodeIpIsNotUniqueMatch newEmptyMatch() {
    return NodeIpIsNotUniqueMatch.newEmptyMatch();
  }
  
  @Override
  public NodeIpIsNotUniqueMatch newMatch(final Object... parameters) {
    return NodeIpIsNotUniqueMatch.newMatch((org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link NodeIpIsNotUniqueQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link NodeIpIsNotUniqueQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static NodeIpIsNotUniqueQuerySpecification INSTANCE = new NodeIpIsNotUniqueQuerySpecification();
    
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
    private final static NodeIpIsNotUniqueQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "org.eclipse.viatra.examples.cps.tests.queries.nodeIpIsNotUnique";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("host");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("host", "org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_host = body.getOrCreateVariableByName("host");
      		PVariable var_ip = body.getOrCreateVariableByName("ip");
      		PVariable var_otherHost = body.getOrCreateVariableByName("otherHost");
      		new TypeConstraint(body, new FlatTuple(var_host), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://org.eclipse.viatra/model/cps", "HostInstance")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_host, "host")
      		));
      		// 	find nodeIpOfHost(host, ip)
      		new PositivePatternCall(body, new FlatTuple(var_host, var_ip), NodeIpOfHostQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	find nodeIpOfHost(otherHost, ip)
      		new PositivePatternCall(body, new FlatTuple(var_otherHost, var_ip), NodeIpOfHostQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	host != otherHost
      		new Inequality(body, var_host, var_otherHost);
      		bodies.add(body);
      	}
      	                {
      		PAnnotation annotation = new PAnnotation("Constraint");
      		annotation.addAttribute("severity", "error");
      		annotation.addAttribute("message", "The IP address $host.nodeIp$ of $host.identifier$ is not unique");
      		annotation.addAttribute("key", Arrays.asList(new Object[] {
      		                "host"
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
