package jaci.gradle.deploy.sessions;

import java.io.File;
import java.util.Map;

import jaci.gradle.deploy.CommandDeployResult;

public interface SessionController extends AutoCloseable {
    void open();

    CommandDeployResult execute(String command);

    void put(Map<String, File> files);

    String friendlyString();
}
