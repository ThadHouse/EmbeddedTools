package jaci.gradle.deploy.target.location;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.internal.CollectionCallbackActionDecorator;
import org.gradle.api.internal.DefaultDomainObjectSet;

import jaci.gradle.deploy.target.RemoteTarget;

public class DeployLocationSet extends DefaultDomainObjectSet<DeployLocation> {
    private final RemoteTarget target;
    private final Project project;

    @Inject
    public DeployLocationSet(Project project, RemoteTarget target) {
        super(DeployLocation.class, CollectionCallbackActionDecorator.NOOP);
        this.target = target;
        this.project = project;
    }

    public <T extends DeployLocation> DeployLocation location(Class<T> type, Action<T> config) {
        T location = project.getObjects().newInstance(type, target);

        if (target.isDry()) {
            //location = new DryDeployLocation(location);
        } else {
            config.execute(location);
        }

        this.add(location);
        return location;
    }

    public DeployLocation ssh(final Action<? extends DeployLocation> config) {
        return null;
        //return location(SshDeployLocation, config);
    }
}
