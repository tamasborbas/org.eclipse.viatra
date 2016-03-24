package org.eclipse.viatra.examples.cps.tests.queries.util;

import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.ApplicationInstance;
import org.eclipse.viatra.examples.cps.tests.queries.NotAllocatedButRunningMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the org.eclipse.viatra.examples.cps.tests.queries.notAllocatedButRunning pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class NotAllocatedButRunningProcessor implements IMatchProcessor<NotAllocatedButRunningMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pApp the value of pattern parameter app in the currently processed match
   * 
   */
  public abstract void process(final ApplicationInstance pApp);
  
  @Override
  public void process(final NotAllocatedButRunningMatch match) {
    process(match.getApp());
  }
}
