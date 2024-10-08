package org.sakila.videostore.processes.usertasks.lifecycle.phases;

import java.util.Arrays;
import java.util.List;

import org.jbpm.process.instance.impl.humantask.phases.Claim;
import org.jbpm.process.instance.impl.humantask.phases.Release;
import org.jbpm.process.instance.impl.workitem.Active;
import org.kie.kogito.process.workitem.LifeCyclePhase;

/**
 * Start life cycle phase that applies to any human task.
 * It will set the status to "Started"
 *
 * It can transition from
 * <ul>
 * <li>Active</li>
 * <li>Claim</li>
 * <li>Release</li>
 * </ul>
 * 
 */
public class Start implements LifeCyclePhase {

    public static final String ID = "start";
    public static final String STATUS = "Started";

    private List<String> allowedTransitions = Arrays.asList(Active.ID, Claim.ID, Release.ID);

    @Override
    public String id() {
        return ID;
    }

    @Override
    public String status() {
        return STATUS;
    }

    @Override
    public boolean isTerminating() {
        return false;
    }

    @Override
    public boolean canTransition(LifeCyclePhase phase) {
        return allowedTransitions.contains(phase.id());
    }
}