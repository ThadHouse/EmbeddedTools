package jaci.gradle.deploy.target.discovery.action;

import jaci.gradle.deploy.context.DefaultDeployContext;
import jaci.gradle.deploy.context.DeployContext;
import jaci.gradle.deploy.sessions.DrySessionController;
import jaci.gradle.deploy.target.discovery.DiscoveryState;
import jaci.gradle.deploy.target.location.DeployLocation;
import jaci.gradle.log.ETLogger;
import jaci.gradle.log.ETLoggerFactory;

public class DryDiscoveryAction extends AbstractDiscoveryAction {

    private ETLogger log;

    public DryDiscoveryAction(DeployLocation loc) {
        super(loc);
        this.log = ETLoggerFactory.INSTANCE.create(toString());
    }

    @Override
    public DeployContext discover() {
        DrySessionController controller = new DrySessionController();
        return new DefaultDeployContext(controller, log, getDeployLocation(), getDeployLocation().getTarget().getDirectory());
    }

    @Override
    public DiscoveryState getState() {
        return DiscoveryState.CONNECTED;
    }
}

