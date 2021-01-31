package jaci.gradle.deploy.target.location;

import javax.inject.Inject;

import jaci.gradle.deploy.target.RemoteTarget;

public abstract class AbstractDeployLocation implements DeployLocation {
    private final RemoteTarget target;

    @Inject
    public AbstractDeployLocation(RemoteTarget target) {
        this.target = target;
    }

    @Override
    public RemoteTarget getTarget() {
        return this.target;
    }
}
