package jaci.gradle.deploy.target.discovery.action;

import java.util.concurrent.Callable;

import jaci.gradle.deploy.context.DeployContext;
import jaci.gradle.deploy.target.discovery.DiscoveryFailedException;
import jaci.gradle.deploy.target.discovery.DiscoveryState;
import jaci.gradle.deploy.target.location.DeployLocation;

public interface DiscoveryAction extends Callable<DeployContext> {
    DeployContext discover();

    DiscoveryFailedException getException();

    DiscoveryState getState();

    DeployLocation getDeployLocation();
}
