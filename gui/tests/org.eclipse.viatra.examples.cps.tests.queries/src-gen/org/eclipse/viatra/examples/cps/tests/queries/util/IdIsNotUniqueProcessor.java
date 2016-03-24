package org.eclipse.viatra.examples.cps.tests.queries.util;

import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.Identifiable;
import org.eclipse.viatra.examples.cps.tests.queries.IdIsNotUniqueMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the org.eclipse.viatra.examples.cps.tests.queries.idIsNotUnique pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class IdIsNotUniqueProcessor implements IMatchProcessor<IdIsNotUniqueMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pIdentifiable the value of pattern parameter identifiable in the currently processed match
   * 
   */
  public abstract void process(final Identifiable pIdentifiable);
  
  @Override
  public void process(final IdIsNotUniqueMatch match) {
    process(match.getIdentifiable());
  }
}
