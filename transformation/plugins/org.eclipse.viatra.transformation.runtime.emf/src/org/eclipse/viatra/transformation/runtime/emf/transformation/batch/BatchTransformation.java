/*******************************************************************************
 * Copyright (c) 2004-2013, Zoltan Ujhelyi, Istvan David and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zoltan Ujhelyi - initial API and implementation
 *   Peter Lunk - revised Transformation API structure for adapter support
 *******************************************************************************/
package org.eclipse.viatra.transformation.runtime.emf.transformation.batch;

import java.util.List;
import java.util.Set;

import org.eclipse.viatra.query.runtime.api.GenericQueryGroup;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.transformation.evm.api.Context;
import org.eclipse.viatra.transformation.evm.api.Executor;
import org.eclipse.viatra.transformation.evm.api.IExecutor;
import org.eclipse.viatra.transformation.evm.api.RuleEngine;
import org.eclipse.viatra.transformation.evm.api.adapter.AdaptableEVM;
import org.eclipse.viatra.transformation.evm.api.adapter.AdaptableExecutor;
import org.eclipse.viatra.transformation.evm.api.adapter.IAdapterConfiguration;
import org.eclipse.viatra.transformation.evm.api.adapter.IEVMAdapter;
import org.eclipse.viatra.transformation.evm.api.adapter.IEVMListener;
import org.eclipse.viatra.transformation.evm.specific.RuleEngines;
import org.eclipse.viatra.transformation.runtime.emf.rules.BatchTransformationRuleGroup;
import org.eclipse.viatra.transformation.runtime.emf.rules.TransformationRuleGroup;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A base class for batch transformations.
 * 
 * @author Zoltan Ujhelyi, Peter Lunk
 * 
 */
public class BatchTransformation {

    protected final RuleEngine ruleEngine;
    protected final ViatraQueryEngine queryEngine;
    protected final IExecutor executor;
    protected final Context context;
    protected Set<BatchTransformationRule<?, ?>> rules = Sets.newHashSet();

    public static class BatchTransformationBuilder {
        private ViatraQueryEngine engine;
        private Set<BatchTransformationRule<?, ?>> rules = Sets.newHashSet();
        private List<IEVMListener> listeners = Lists.newArrayList();
        private List<IEVMAdapter> adapters = Lists.newArrayList();

        public BatchTransformationBuilder setQueryEngine(ViatraQueryEngine engine) {
            this.engine = engine;
            return this;
        }

        public BatchTransformationBuilder addRule(BatchTransformationRule<?, ?> rule) {
            rules.add(rule);
            return this;
        }

        public BatchTransformationBuilder addAdapter(IEVMAdapter adapter) {
            this.adapters.add(adapter);
            return this;
        }

        public BatchTransformationBuilder addListener(IEVMListener listener) {
            this.listeners.add(listener);
            return this;
        }

        public BatchTransformationBuilder addAdapterConfiguration(IAdapterConfiguration config) {
            this.listeners.addAll(config.getListeners());
            this.adapters.addAll(config.getAdapters());
            return this;
        }

        public BatchTransformationBuilder addRules(BatchTransformationRuleGroup ruleGroup) {
            for (BatchTransformationRule<?, ?> rule : ruleGroup) {
                rules.add(rule);
            }
            return this;
        }

        public BatchTransformation build() throws ViatraQueryException {
            Preconditions.checkState(engine != null, "ViatraQueryEngine must be set.");
            BatchTransformation transformation = (listeners.size() > 0 || adapters.size() > 0) ? debugBuild()
                    : doBuild();
            initializeIndexes(engine);
            transformation.rules = rules;
            return transformation;
        }

        private BatchTransformation doBuild() throws ViatraQueryException {
            final IExecutor executor = new Executor();
            RuleEngine ruleEngine = RuleEngines.createViatraQueryRuleEngine(engine);
            return new BatchTransformation(ruleEngine, engine, executor);
        }

        private BatchTransformation debugBuild() throws ViatraQueryException {
            AdaptableEVM vm = new AdaptableEVM();
            vm.addAdapters(adapters);
            vm.addListeners(listeners);

            final IExecutor executor = new AdaptableExecutor(new Executor(), vm);
            RuleEngine ruleEngine = vm.createAdaptableRuleEngine(engine);
            return new BatchTransformation(ruleEngine, engine, executor);

        }

        private void initializeIndexes(ViatraQueryEngine queryEngine) throws ViatraQueryException {
            GenericQueryGroup.of(Iterables.toArray(
                    Iterables.transform(rules, new Function<BatchTransformationRule<?, ?>, IQuerySpecification<?>>() {
                        @Override
                        public IQuerySpecification<?> apply(BatchTransformationRule<?, ?> rule) {
                            return rule.getPrecondition();
                        }
                    }), IQuerySpecification.class)).prepare(queryEngine);
        }

    }

    public static BatchTransformationBuilder forScope(EMFScope scope) throws ViatraQueryException {
        return forEngine(ViatraQueryEngine.on(scope));
    }

    public static BatchTransformationBuilder forEngine(ViatraQueryEngine engine) throws ViatraQueryException {
        return new BatchTransformationBuilder().setQueryEngine(engine);
    }

    public BatchTransformationStatements getTransformationStatements() {
        return new BatchTransformationStatements(this, executor);
    }

    private BatchTransformation(RuleEngine ruleEngine, ViatraQueryEngine queryEngine, IExecutor executor) {
        this.ruleEngine = ruleEngine;
        this.queryEngine = queryEngine;
        this.executor = executor;
        context = executor.getContext();
        Preconditions.checkState(context != null, "Executor must return a non-null context.");

    }

    public void addRule(@SuppressWarnings("rawtypes") BatchTransformationRule rule) {
        rules.add(rule);
    }

    @SuppressWarnings("rawtypes")
    public void addRules(TransformationRuleGroup<BatchTransformationRule> ruleGroup) {
        for (BatchTransformationRule rule : ruleGroup) {
            rules.add(rule);
        }
    }

    public void addRules(BatchTransformationRuleGroup ruleGroup) {
        for (BatchTransformationRule<?, ?> rule : ruleGroup) {
            rules.add(rule);
        }
    }

    public RuleEngine getRuleEngine() {
        return ruleEngine;
    }

    public ViatraQueryEngine getQueryEngine() {
        return queryEngine;
    }

    public Context getContext() {
        return context;
    }

    public void dispose() {
        ruleEngine.dispose();
    }

}
