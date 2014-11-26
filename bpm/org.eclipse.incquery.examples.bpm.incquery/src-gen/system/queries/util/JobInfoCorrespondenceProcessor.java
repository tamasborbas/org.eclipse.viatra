package system.queries.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.incquery.runtime.api.IMatchProcessor;
import system.queries.JobInfoCorrespondenceMatch;

/**
 * A match processor tailored for the system.queries.JobInfoCorrespondence pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class JobInfoCorrespondenceProcessor implements IMatchProcessor<JobInfoCorrespondenceMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pJob the value of pattern parameter Job in the currently processed match
   * @param pInfo the value of pattern parameter Info in the currently processed match
   * 
   */
  public abstract void process(final EObject pJob, final EObject pInfo);
  
  @Override
  public void process(final JobInfoCorrespondenceMatch match) {
    process(match.getJob(), match.getInfo());
  }
}
