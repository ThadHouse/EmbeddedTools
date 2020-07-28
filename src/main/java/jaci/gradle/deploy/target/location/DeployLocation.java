package jaci.gradle.deploy.target.location;

import jaci.gradle.deploy.target.RemoteTarget;
import jaci.gradle.deploy.target.discovery.action.DiscoveryAction;

public interface DeployLocation {
    DiscoveryAction createAction();

    RemoteTarget getTarget();

    String friendlyString();
}
