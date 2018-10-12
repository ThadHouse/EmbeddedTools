package jaci.gradle.deploy.artifact

import groovy.transform.CompileStatic
import jaci.gradle.Resolver
import org.gradle.api.Project
import org.gradle.api.internal.DefaultNamedDomainObjectSet
import org.gradle.internal.reflect.DirectInstantiator

import javax.inject.Inject

// DefaultNamedDomainObjectSet applies the withType, matching, all and other methods
// that are incredibly useful
@CompileStatic
class ArtifactsExtension extends DefaultNamedDomainObjectSet<Artifact> implements Resolver<Artifact> {
    final Project project

    @Inject
    ArtifactsExtension(Project project) {
        super(Artifact.class, DirectInstantiator.INSTANCE)
        this.project = project
    }

    Artifact artifact(String name, Class<? extends Artifact> type, final Closure config) {
        def artifact = type.newInstance(name, project)
        project.configure(artifact, config)
        this << (artifact)
        return artifact
    }

    Artifact fileArtifact(String name, final Closure config) {
        return artifact(name, FileArtifact, config)
    }

    Artifact fileCollectionArtifact(String name, final Closure config) {
        return artifact(name, FileCollectionArtifact, config)
    }

    Artifact fileTreeArtifact(String name, final Closure config) {
        return artifact(name, FileTreeArtifact, config)
    }

    Artifact commandArtifact(String name, final Closure config) {
        return artifact(name, CommandArtifact, config)
    }

    Artifact javaArtifact(String name, final Closure config) {
        return artifact(name, JavaArtifact, config)
    }

    Artifact nativeArtifact(String name, final Closure config) {
        return artifact(name, NativeArtifact, config)
    }

    Artifact nativeLibraryArtifact(String name, final Closure config) {
        return artifact(name, NativeLibraryArtifact, config)
    }

    Artifact binaryLibraryArtifact(String name, final Closure config) {
        return artifact(name, BinaryLibraryArtifact, config)
    }

    Artifact mavenArtifact(String name, final Closure config) {
        return artifact(name, MavenArtifact, config)
    }

    @Override
    Artifact resolve(Object o) {
        Artifact result = null
        if (o instanceof String)
            result = this.findByName(o.toString())
        else if (o instanceof Artifact)
            result = (Artifact)o
        // TODO more resolution methods

        if (result == null)
            throw new ResolveFailedException("Could not find artifact " + o.toString() + " (" + o.class.name + ")")

        return result
    }
}
