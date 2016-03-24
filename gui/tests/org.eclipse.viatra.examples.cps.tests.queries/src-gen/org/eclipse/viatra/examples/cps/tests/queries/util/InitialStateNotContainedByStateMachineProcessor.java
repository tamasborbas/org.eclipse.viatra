package org.eclipse.viatra.examples.cps.tests.queries.util;

import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.State;
import org.eclipse.viatra.examples.cps.cyberPhysicalSystem.StateMachine;
import org.eclipse.viatra.examples.cps.tests.queries.InitialStateNotContainedByStateMachineMatch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the org.eclipse.viatra.examples.cps.tests.queries.initialStateNotContainedByStateMachine pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class InitialStateNotContainedByStateMachineProcessor implements IMatchProcessor<InitialStateNotContainedByStateMachineMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pStatemachine the value of pattern parameter statemachine in the currently processed match
   * @param pState the value of pattern parameter state in the currently processed match
   * 
   */
  public abstract void process(final StateMachine pStatemachine, final State pState);
  
  @Override
  public void process(final InitialStateNotContainedByStateMachineMatch match) {
    process(match.getStatemachine(), match.getState());
  }
}
