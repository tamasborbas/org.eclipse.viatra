package org.eclipse.viatra.examples.cps.tests.queries.util;

import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance;
import org.eclipse.viatra.examples.cps.tests.queries.AvailableGreaterThanTotalCpuMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the org.eclipse.viatra.examples.cps.tests.queries.availableGreaterThanTotalCpu pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class AvailableGreaterThanTotalCpuProcessor implements IMatchProcessor<AvailableGreaterThanTotalCpuMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pHost the value of pattern parameter host in the currently processed match
   * 
   */
  public abstract void process(final HostInstance pHost);
  
  @Override
  public void process(final AvailableGreaterThanTotalCpuMatch match) {
    process(match.getHost());
  }
}
