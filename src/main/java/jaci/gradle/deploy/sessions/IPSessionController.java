package jaci.gradle.deploy.sessions;

public interface IPSessionController extends SessionController {
    String getHost();
    int getPort();
}
