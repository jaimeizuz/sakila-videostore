package org.sakila.videostore.processes.usertasks.lifecycle.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jbpm.process.instance.impl.humantask.BaseHumanTaskLifeCycle;
import org.jbpm.process.instance.impl.humantask.InternalHumanTaskWorkItem;
import org.jbpm.process.instance.impl.humantask.phases.Claim;
import org.jbpm.process.instance.impl.humantask.phases.Release;
import org.jbpm.process.instance.impl.humantask.phases.Skip;
import org.jbpm.process.instance.impl.workitem.Abort;
import org.jbpm.process.instance.impl.workitem.Active;
import org.jbpm.process.instance.impl.workitem.Complete;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;
import org.kie.kogito.process.workitem.InvalidLifeCyclePhaseException;
import org.kie.kogito.process.workitem.InvalidTransitionException;
import org.kie.kogito.process.workitem.LifeCycle;
import org.kie.kogito.process.workitem.LifeCyclePhase;
import org.kie.kogito.process.workitem.NotAuthorizedException;
import org.kie.kogito.process.workitem.Policy;
import org.kie.kogito.process.workitem.Transition;
import org.kie.kogito.process.workitems.InternalKogitoWorkItemManager;
import org.sakila.videostore.processes.usertasks.lifecycle.phases.CompleteStartedOnly;
import org.sakila.videostore.processes.usertasks.lifecycle.phases.Start;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom life cycle definition for human tasks. It comes with following phases
 *
 * <ul>
 * <li>Active</li>
 * <li>Claim</li>
 * <li>Release</li>
 * <li>Start</li>
 * <li>Complete - extended one that allows to only complete started tasks</li>
 * <li>Skip</li>
 * <li>Abort</li>
 * </ul>
 * At the beginning human task enters
 * 
 * <pre>
 * Active
 * </pre>
 * 
 * phase. From there it can go to
 *
 * <ul>
 * <li>Claim</li>
 * <li>Skip</li>
 * <li>Abort</li>
 * </ul>
 *
 * at any time. At each phase data can be associated and by that set on work item.
 *
 * Completion can only be performed on started tasks.
 */
public class CustomHumanTaskLifeCycle implements LifeCycle<Map<String, Object>> {

    private static final Logger logger = LoggerFactory.getLogger(BaseHumanTaskLifeCycle.class);

    private Map<String, LifeCyclePhase> phases = new LinkedHashMap<>();

    public CustomHumanTaskLifeCycle() {
        phases.put(Claim.ID, new Claim());
        phases.put(Release.ID, new Release());
        phases.put(Start.ID, new Start());
        phases.put(Complete.ID, new CompleteStartedOnly());
        phases.put(Skip.ID, new Skip());
        phases.put(Active.ID, new Active());
        phases.put(Abort.ID, new Abort());
    }

    @Override
    public LifeCyclePhase phaseById(String phaseId) {
        return phases.get(phaseId);
    }

    @Override
    public Collection<LifeCyclePhase> phases() {
        return phases.values();
    }

    @Override
    public Map<String, Object> transitionTo(KogitoWorkItem workItem, KogitoWorkItemManager manager, Transition<Map<String, Object>> transition) {
        logger.debug("Transition method invoked for work item {} to transition to {}, currently in phase {} and status {}", workItem.getStringId(), transition.phase(), workItem.getPhaseId(),
                workItem.getPhaseStatus());

        InternalHumanTaskWorkItem humanTaskWorkItem = (InternalHumanTaskWorkItem) workItem;

        LifeCyclePhase targetPhase = phases.get(transition.phase());
        if (targetPhase == null) {
            logger.debug("Target life cycle phase '{}' does not exist in {}", transition.phase(), this.getClass().getSimpleName());
            throw new InvalidLifeCyclePhaseException(transition.phase());
        }

        LifeCyclePhase currentPhase = phases.get(humanTaskWorkItem.getPhaseId());

        if (!targetPhase.canTransition(currentPhase)) {
            logger.debug("Target life cycle phase '{}' cannot transition from current state '{}'", targetPhase.id(), currentPhase.id());
            throw new InvalidTransitionException("Cannot transition from " + humanTaskWorkItem.getPhaseId() + " to " + targetPhase.id());
        }

        if (!targetPhase.id().equals(Active.ID) && !targetPhase.id().equals(Abort.ID) && !humanTaskWorkItem.enforce(transition.policies().toArray(new Policy[transition.policies().size()]))) {
            throw new NotAuthorizedException("User is not authorized to access task instance with id " + humanTaskWorkItem.getStringId());
        }

        humanTaskWorkItem.setPhaseId(targetPhase.id());
        humanTaskWorkItem.setPhaseStatus(targetPhase.status());

        targetPhase.apply(humanTaskWorkItem, transition);
        if (transition.data() != null) {
            logger.debug("Updating data for phase {} and work item {}", targetPhase.id(), humanTaskWorkItem.getStringId());
            humanTaskWorkItem.getResults().putAll(transition.data());
        }
        logger.debug("Transition for work item {} to {} done, currently in phase {} and status {}", workItem.getStringId(), transition.phase(), workItem.getPhaseId(), workItem.getPhaseStatus());

        if (targetPhase.isTerminating()) {
            logger.debug("Target life cycle phase '{}' is terminiating, completing work item {}", targetPhase.id(), humanTaskWorkItem.getStringId());
            // since target life cycle phase is terminating completing work item
            ((InternalKogitoWorkItemManager) manager).internalCompleteWorkItem(humanTaskWorkItem);
        }

        return data(humanTaskWorkItem);
    }

    @Override
    public Map<String, Object> data(KogitoWorkItem workItem) {
        return ((InternalHumanTaskWorkItem) workItem).getResults();
    }
}