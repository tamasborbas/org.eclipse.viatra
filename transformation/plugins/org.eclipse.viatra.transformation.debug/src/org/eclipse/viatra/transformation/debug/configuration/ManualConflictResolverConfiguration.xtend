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
package org.eclipse.viatra.transformation.debug.configuration

import com.google.common.collect.Lists
import java.util.List
import org.eclipse.viatra.transformation.debug.ManualConflictResolver
import org.eclipse.viatra.transformation.debug.controller.IDebugController
import org.eclipse.viatra.transformation.debug.controller.impl.ConsoleDebugger
import org.eclipse.viatra.transformation.evm.api.adapter.IAdapterConfiguration
import org.eclipse.viatra.transformation.evm.api.adapter.IEVMAdapter
import org.eclipse.viatra.transformation.evm.api.adapter.IEVMListener

/**
 * Configuration class that defines the manual conflict resolver.
 * 
 * @author Peter Lunk
 */
class ManualConflictResolverConfiguration implements IAdapterConfiguration {
    List<IEVMAdapter> adapters
    List<IEVMListener> listeners

    new(IDebugController usedcontroller) {
        adapters = Lists.newArrayList
        listeners = Lists.newArrayList
        adapters.add(new ManualConflictResolver(usedcontroller))
    }

    new() {
        val usedUI = new ConsoleDebugger
        adapters = Lists.newArrayList
        listeners = Lists.newArrayList
        adapters.add(new ManualConflictResolver(usedUI))
    }

    override getAdapters() {
        return adapters
    }

    override getListeners() {
        return listeners
    }

}
