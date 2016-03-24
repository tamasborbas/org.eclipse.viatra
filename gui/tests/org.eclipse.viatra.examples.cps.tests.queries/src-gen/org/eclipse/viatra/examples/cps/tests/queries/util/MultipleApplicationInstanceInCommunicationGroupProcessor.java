package org.eclipse.viatra.examples.cps.tests.queries.util;

import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.ApplicationType;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.HostInstance;
import org.eclipse.viatra.examples.cps.tests.queries.MultipleApplicationInstanceInCommunicationGroupMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the org.eclipse.viatra.examples.cps.tests.queries.multipleApplicationInstanceInCommunicationGroup pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class MultipleApplicationInstanceInCommunicationGroupProcessor implements IMatchProcessor<MultipleApplicationInstanceInCommunicationGroupMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSourceHostInstance the value of pattern parameter sourceHostInstance in the currently processed match
   * @param pApp the value of pattern parameter app in the currently processed match
   * 
   */
  public abstract void process(final HostInstance pSourceHostInstance, final ApplicationType pApp);
  
  @Override
  public void process(final MultipleApplicationInstanceInCommunicationGroupMatch match) {
    process(match.getSourceHostInstance(), match.getApp());
  }
}
