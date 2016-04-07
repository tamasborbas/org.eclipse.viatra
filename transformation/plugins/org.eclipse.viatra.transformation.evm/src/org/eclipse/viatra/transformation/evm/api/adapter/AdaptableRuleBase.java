/*******************************************************************************
 * Copyright (c) 2010-2013, Abel Hegedus, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Peter Lunk - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.transformation.evm.api.adapter;

import org.eclipse.viatra.transformation.evm.api.Agenda;
import org.eclipse.viatra.transformation.evm.api.RuleBase;
import org.eclipse.viatra.transformation.evm.api.RuleInstance;
import org.eclipse.viatra.transformation.evm.api.RuleSpecification;
import org.eclipse.viatra.transformation.evm.api.event.EventFilter;
import org.eclipse.viatra.transformation.evm.api.event.EventRealm;

/**
 * A {@link RuleBase} that allows {@link IEVMListener}s to listen to the creation and removal of EVM
 * {@link RuleSpecification}s
 * 
 * @author Peter Lunk
 *
 */
public class AdaptableRuleBase extends RuleBase {
    protected final AdaptableEVM vm;

    public AdaptableRuleBase(EventRealm eventRealm, Agenda agenda, AdaptableEVM adapterContainer) {
        super(eventRealm, agenda);
        this.vm = adapterContainer;
    }

    @Override
    protected <EventAtom> RuleInstance<EventAtom> instantiateRule(final RuleSpecification<EventAtom> specification,
            final EventFilter<? super EventAtom> filter) {
        RuleInstance<EventAtom> instance = super.instantiateRule(specification, filter);
        vm.addedRule(specification);
        return instance;
    }

    protected <EventAtom> boolean removeRule(final RuleSpecification<EventAtom> specification,
            final EventFilter<? super EventAtom> filter) {
        boolean result = super.removeRule(specification, filter);
        vm.removedRule(specification);
        return result;
    }

}
