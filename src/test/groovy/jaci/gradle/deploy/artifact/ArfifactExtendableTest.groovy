package jaci.gradle.deploy.artifact

import groovy.transform.CompileStatic
import jaci.gradle.deploy.DeployExtension
import jaci.gradle.deploy.DeployPlugin
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.Project

class ArtifactExtendableTest extends Specification {

    def outerName = ''

    def "can extend artifact"() {

        outerName = ''
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DeployPlugin)
        def deploy = (DeployExtension)project.extensions.getByType(DeployExtension)
        deploy.artifacts.extensions.add('testDeploy', { String name, Closure closure ->
            outerName = name
        })

        when:
            def test = deploy.artifacts.extensions.getByName('testDeploy')
            test('hello', {

            })
        then:
            outerName == 'hello'
    }

    @CompileStatic
    def "can extend artifact static"() {
        outerName = ''
        Project project = ProjectBuilder.builder().build()
        project.plugins.apply(DeployPlugin)
        def deploy = (DeployExtension)project.extensions.getByType(DeployExtension)
        def artifacts = deploy.artifacts as ExtensionAware
        artifacts.extensions.add('testDeploy', { String name, Closure closure ->
            outerName = name
        })

        when:
            def test = artifacts.extensions.getByName('testDeploy') as Closure
            test('hello', {

            })
        then:
            outerName == 'hello'
    }
}
