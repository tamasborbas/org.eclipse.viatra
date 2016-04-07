/*******************************************************************************
 * Copyright (c) 2004-2015, Peter Lunk, Zoltan Ujhelyi and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Peter Lunk - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.transformation.evm.api.adapter;

import java.util.List;

/**
 * Interface that defines the methods of VIATRA transformation adapter configurations. Adapter configurations can
 * contain {@link IEVMListener} and {@link IEVMAdapter} objects
 * 
 * @author Peter Lunk
 */
public interface IAdapterConfiguration {
    public List<IEVMListener> getListeners();

    public List<IEVMAdapter> getAdapters();
}
