package jaci.gradle.deploy.target.location;

import jaci.gradle.deploy.target.discovery.action.DiscoveryAction;
import jaci.gradle.deploy.target.discovery.action.DryDiscoveryAction;

public class DryDeployLocation extends AbstractDeployLocation {

    private DeployLocation inner;

    public DryDeployLocation(DeployLocation inner) {
        super(inner.getTarget());
        this.inner = inner;
    }

    @Override
    public DiscoveryAction createAction() {
        return new DryDiscoveryAction(inner);
    }

    @Override
    public String friendlyString() {
        return "DryRun DeployLocation (wrapping " + inner.toString() + ")";
    }
}
