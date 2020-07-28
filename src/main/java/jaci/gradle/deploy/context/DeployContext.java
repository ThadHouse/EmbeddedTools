package jaci.gradle.deploy.context;

import java.io.File;
import java.util.Map;
import java.util.Set;

import jaci.gradle.deploy.CommandDeployResult;
import jaci.gradle.deploy.cache.CacheMethod;
import jaci.gradle.deploy.sessions.SessionController;
import jaci.gradle.deploy.target.location.DeployLocation;
import jaci.gradle.log.ETLogger;

public interface DeployContext {
    SessionController getController();

    ETLogger getLogger();

    String getWorkingDir();

    DeployLocation getDeployLocation();

    CommandDeployResult execute(String command);

    // Send a batch of files
    void put(Map<String, File> files, CacheMethod cache);

    // Send a single file
    void put(File source, String dest, CacheMethod cache);

    // Send multiple files, and trigger cache checking only once
    void put(Set<File> files, CacheMethod cache);

    String friendlyString();

    DeployContext subContext(String workingDir);
}
