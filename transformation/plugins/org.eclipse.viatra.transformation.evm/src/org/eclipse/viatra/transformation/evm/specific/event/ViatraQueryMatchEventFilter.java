/*******************************************************************************
 * Copyright (c) 2010-2013, Abel Hegedus, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.transformation.evm.specific.event;

import static com.google.common.base.Preconditions.checkArgument;

import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.transformation.evm.api.event.EventFilter;

import com.google.common.base.Objects;

/**
 * @author Abel Hegedus
 *
 */
public class ViatraQueryMatchEventFilter<Match extends IPatternMatch> implements EventFilter<Match> {

    private Match filterMatch;
    
    public Match getFilterMatch() {
        return filterMatch;
    }

    @Override
    public boolean isProcessable(Match eventAtom) {
        if(filterMatch == null) {
            return true;
        }
        return filterMatch.isCompatibleWith(eventAtom);
    }

    protected ViatraQueryMatchEventFilter(Match filterMatch) {
        checkArgument(filterMatch != null, "Cannot create filter with null match");
        this.filterMatch = filterMatch;
    }
    
    /**
     * Only used internally to create empty filters
     */
    protected ViatraQueryMatchEventFilter() {}
    
    public static <Match extends IPatternMatch> ViatraQueryMatchEventFilter<Match> createFilter(Match eventAtom) {
        checkArgument(eventAtom != null, "Cannot create filter for null match, use createEmptyFilter() instead!");
        checkArgument(!eventAtom.isMutable(), "Cannot create filter for mutable match!");
        return new ViatraQueryMatchEventFilter<Match>(eventAtom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(filterMatch);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("rawtypes")
        ViatraQueryMatchEventFilter other = (ViatraQueryMatchEventFilter) obj;
        return Objects.equal(filterMatch, other.filterMatch);
    }
    
}
