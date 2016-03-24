package org.eclipse.viatra.examples.cps.tests.queries;

import org.eclipse.viatra.examples.cps.tests.queries.AvailableGreaterThanTotalCpuMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.AvailableGreaterThanTotalHddMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.AvailableGreaterThanTotalRamMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.IdIsNotUniqueMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.InitialStateNotContainedByStateMachineMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.MultipleApplicationInstanceInCommunicationGroupMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.MultipleTransitionsWithSameActionMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.NodeIpIsNotUniqueMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.NotAllocatedButRunningMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.TargetStateNotContainedBySameStateMachineMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.TransitionWithoutTargetStateMatcher;
import org.eclipse.viatra.examples.cps.tests.queries.util.AvailableGreaterThanTotalCpuQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.AvailableGreaterThanTotalHddQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.AvailableGreaterThanTotalRamQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.IdIsNotUniqueQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.InitialStateNotContainedByStateMachineQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.MultipleApplicationInstanceInCommunicationGroupQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.MultipleTransitionsWithSameActionQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.NodeIpIsNotUniqueQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.NotAllocatedButRunningQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.TargetStateNotContainedBySameStateMachineQuerySpecification;
import org.eclipse.viatra.examples.cps.tests.queries.util.TransitionWithoutTargetStateQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all patterns defined in validationRules.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file validationRules.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.eclipse.viatra.examples.cps.tests.queries, the group contains the definition of the following patterns: <ul>
 * <li>notAllocatedButRunning</li>
 * <li>allocatedApplication</li>
 * <li>availableGreaterThanTotalCpu</li>
 * <li>availableGreaterThanTotalHdd</li>
 * <li>availableGreaterThanTotalRam</li>
 * <li>nodeIpIsNotUnique</li>
 * <li>nodeIpOfHost</li>
 * <li>idIsNotUnique</li>
 * <li>identifiableId</li>
 * <li>initialStateNotContainedByStateMachine</li>
 * <li>statemachineState</li>
 * <li>transitionWithoutTargetState</li>
 * <li>stateTransition</li>
 * <li>targetStateNotContainedBySameStateMachine</li>
 * <li>multipleTransitionsWithSameAction</li>
 * <li>actionOfTransition</li>
 * <li>multipleApplicationInstanceInCommunicationGroup</li>
 * <li>appTypeInstanceAndHost</li>
 * <li>hostCommunication</li>
 * <li>reachableHosts</li>
 * <li>reachableAppInstance</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ValidationRules extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ValidationRules instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new ValidationRules();
    }
    return INSTANCE;
  }
  
  private static ValidationRules INSTANCE;
  
  private ValidationRules() throws ViatraQueryException {
    querySpecifications.add(NotAllocatedButRunningQuerySpecification.instance());
    querySpecifications.add(AvailableGreaterThanTotalCpuQuerySpecification.instance());
    querySpecifications.add(AvailableGreaterThanTotalHddQuerySpecification.instance());
    querySpecifications.add(AvailableGreaterThanTotalRamQuerySpecification.instance());
    querySpecifications.add(NodeIpIsNotUniqueQuerySpecification.instance());
    querySpecifications.add(IdIsNotUniqueQuerySpecification.instance());
    querySpecifications.add(InitialStateNotContainedByStateMachineQuerySpecification.instance());
    querySpecifications.add(TransitionWithoutTargetStateQuerySpecification.instance());
    querySpecifications.add(TargetStateNotContainedBySameStateMachineQuerySpecification.instance());
    querySpecifications.add(MultipleTransitionsWithSameActionQuerySpecification.instance());
    querySpecifications.add(MultipleApplicationInstanceInCommunicationGroupQuerySpecification.instance());
  }
  
  public NotAllocatedButRunningQuerySpecification getNotAllocatedButRunning() throws ViatraQueryException {
    return NotAllocatedButRunningQuerySpecification.instance();
  }
  
  public NotAllocatedButRunningMatcher getNotAllocatedButRunning(final ViatraQueryEngine engine) throws ViatraQueryException {
    return NotAllocatedButRunningMatcher.on(engine);
  }
  
  public AvailableGreaterThanTotalCpuQuerySpecification getAvailableGreaterThanTotalCpu() throws ViatraQueryException {
    return AvailableGreaterThanTotalCpuQuerySpecification.instance();
  }
  
  public AvailableGreaterThanTotalCpuMatcher getAvailableGreaterThanTotalCpu(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AvailableGreaterThanTotalCpuMatcher.on(engine);
  }
  
  public AvailableGreaterThanTotalHddQuerySpecification getAvailableGreaterThanTotalHdd() throws ViatraQueryException {
    return AvailableGreaterThanTotalHddQuerySpecification.instance();
  }
  
  public AvailableGreaterThanTotalHddMatcher getAvailableGreaterThanTotalHdd(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AvailableGreaterThanTotalHddMatcher.on(engine);
  }
  
  public AvailableGreaterThanTotalRamQuerySpecification getAvailableGreaterThanTotalRam() throws ViatraQueryException {
    return AvailableGreaterThanTotalRamQuerySpecification.instance();
  }
  
  public AvailableGreaterThanTotalRamMatcher getAvailableGreaterThanTotalRam(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AvailableGreaterThanTotalRamMatcher.on(engine);
  }
  
  public NodeIpIsNotUniqueQuerySpecification getNodeIpIsNotUnique() throws ViatraQueryException {
    return NodeIpIsNotUniqueQuerySpecification.instance();
  }
  
  public NodeIpIsNotUniqueMatcher getNodeIpIsNotUnique(final ViatraQueryEngine engine) throws ViatraQueryException {
    return NodeIpIsNotUniqueMatcher.on(engine);
  }
  
  public IdIsNotUniqueQuerySpecification getIdIsNotUnique() throws ViatraQueryException {
    return IdIsNotUniqueQuerySpecification.instance();
  }
  
  public IdIsNotUniqueMatcher getIdIsNotUnique(final ViatraQueryEngine engine) throws ViatraQueryException {
    return IdIsNotUniqueMatcher.on(engine);
  }
  
  public InitialStateNotContainedByStateMachineQuerySpecification getInitialStateNotContainedByStateMachine() throws ViatraQueryException {
    return InitialStateNotContainedByStateMachineQuerySpecification.instance();
  }
  
  public InitialStateNotContainedByStateMachineMatcher getInitialStateNotContainedByStateMachine(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InitialStateNotContainedByStateMachineMatcher.on(engine);
  }
  
  public TransitionWithoutTargetStateQuerySpecification getTransitionWithoutTargetState() throws ViatraQueryException {
    return TransitionWithoutTargetStateQuerySpecification.instance();
  }
  
  public TransitionWithoutTargetStateMatcher getTransitionWithoutTargetState(final ViatraQueryEngine engine) throws ViatraQueryException {
    return TransitionWithoutTargetStateMatcher.on(engine);
  }
  
  public TargetStateNotContainedBySameStateMachineQuerySpecification getTargetStateNotContainedBySameStateMachine() throws ViatraQueryException {
    return TargetStateNotContainedBySameStateMachineQuerySpecification.instance();
  }
  
  public TargetStateNotContainedBySameStateMachineMatcher getTargetStateNotContainedBySameStateMachine(final ViatraQueryEngine engine) throws ViatraQueryException {
    return TargetStateNotContainedBySameStateMachineMatcher.on(engine);
  }
  
  public MultipleTransitionsWithSameActionQuerySpecification getMultipleTransitionsWithSameAction() throws ViatraQueryException {
    return MultipleTransitionsWithSameActionQuerySpecification.instance();
  }
  
  public MultipleTransitionsWithSameActionMatcher getMultipleTransitionsWithSameAction(final ViatraQueryEngine engine) throws ViatraQueryException {
    return MultipleTransitionsWithSameActionMatcher.on(engine);
  }
  
  public MultipleApplicationInstanceInCommunicationGroupQuerySpecification getMultipleApplicationInstanceInCommunicationGroup() throws ViatraQueryException {
    return MultipleApplicationInstanceInCommunicationGroupQuerySpecification.instance();
  }
  
  public MultipleApplicationInstanceInCommunicationGroupMatcher getMultipleApplicationInstanceInCommunicationGroup(final ViatraQueryEngine engine) throws ViatraQueryException {
    return MultipleApplicationInstanceInCommunicationGroupMatcher.on(engine);
  }
}
