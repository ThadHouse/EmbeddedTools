package jaci.gradle.deploy.target.discovery

import org.gradle.api.provider.Property;

class TargetDiscoveryWorkerWrapper extends TargetDiscoveryWorker {

    private static class TargetDiscoveryWorkerParametersMock implements TargetDiscoveryWorkerParameters {
        private Property<Integer> provider;

        public TargetDiscoveryWorkerParametersMock(Integer index) {
            provider = Mock(Property)
        }

        Property<Integer> getIndex() {
            return provider
        }
    }

    private TargetDiscoveryWorkerParameters parameters;

    TargetDiscoveryWorkerWrapper() {

    }

    TargetDiscoveryWorkerWrapper(Integer index) {
        parameters = new TargetDiscoveryWorkerParametersMock(index)
    }

    @Override
    TargetDiscoveryWorkerParameters getParameters() {
        return parameters
    }
}
