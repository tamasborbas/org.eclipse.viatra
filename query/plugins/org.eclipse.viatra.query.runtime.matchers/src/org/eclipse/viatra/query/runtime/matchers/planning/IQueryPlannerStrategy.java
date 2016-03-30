/*******************************************************************************
 * Copyright (c) 2004-2010 Gabor Bergmann and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gabor Bergmann - initial API and implementation
 *******************************************************************************/

package org.eclipse.viatra.query.runtime.matchers.planning;

import org.apache.log4j.Logger;
import org.eclipse.viatra.query.runtime.matchers.context.IQueryMetaContext;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;

/**
 * An algorithm that builds a query plan based on a PSystem representation of a body of constraints. This interface is
 * for internal use of the various query backends.
 * 
 * @author Gabor Bergmann
 */
public interface IQueryPlannerStrategy {
    public SubPlan plan(PBody pSystem, Logger logger, IQueryMetaContext context) throws QueryProcessingException;
}
