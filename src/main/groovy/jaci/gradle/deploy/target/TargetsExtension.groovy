package jaci.gradle.deploy.target

import groovy.transform.CompileStatic
import jaci.gradle.Resolver
import org.gradle.api.Project
import org.gradle.api.internal.DefaultNamedDomainObjectSet
import org.gradle.internal.reflect.DirectInstantiator

import javax.inject.Inject

// DefaultNamedDomainObjectSet applies the withType, matching, all and other methods
// that are incredibly useful
@CompileStatic
class TargetsExtension extends DefaultNamedDomainObjectSet<RemoteTarget> implements Resolver<RemoteTarget> {
    final Project project

    @Inject
    TargetsExtension(Project project) {
        super(RemoteTarget, DirectInstantiator.INSTANCE)
        this.project = project
    }

    def target(String name, Class<? extends RemoteTarget> type, final Closure config) {
        def target = type.newInstance(name, project)
        project.configure(target, config)
        this << (target)
    }

    def target(String name, final Closure config) {
        target(name, RemoteTarget, config)
    }

    @Override
    RemoteTarget resolve(Object o) {
        RemoteTarget result = null
        if (o instanceof String)
            result = this.findByName(o.toString())
        else if (o instanceof RemoteTarget)
            result = (RemoteTarget)o
        // TODO more resolution methods

        if (result == null)
            throw new ResolveFailedException("Could not find target " + o.toString() + " (" + o.class.name + ")")

        return result
    }
}
