package org.eclipse.viatra.examples.cps.tests.queries.util;

import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State;
import org.eclipse.viatra.examples.cps.tests.queries.MultipleTransitionsWithSameActionMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the org.eclipse.viatra.examples.cps.tests.queries.multipleTransitionsWithSameAction pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class MultipleTransitionsWithSameActionProcessor implements IMatchProcessor<MultipleTransitionsWithSameActionMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pState the value of pattern parameter state in the currently processed match
   * @param pAction the value of pattern parameter action in the currently processed match
   * 
   */
  public abstract void process(final State pState, final String pAction);
  
  @Override
  public void process(final MultipleTransitionsWithSameActionMatch match) {
    process(match.getState(), match.getAction());
  }
}
