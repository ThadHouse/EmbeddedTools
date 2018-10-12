package jaci.gradle.deploy.target

import groovy.transform.CompileStatic
import jaci.gradle.deploy.DeployExtension
import jaci.gradle.deploy.DeployPlugin
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.Project

class TargetExtendableTest extends Specification {

    def outerName = ''

    def "can extend target"() {

        outerName = ''
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DeployPlugin)
        def deploy = (DeployExtension)project.extensions.getByType(DeployExtension)
        deploy.targets.extensions.add('testDeploy', { String name, Closure closure ->
            outerName = name
        })

        when:
            def test = deploy.targets.extensions.getByName('testDeploy')
            test('hello', {

            })
        then:
            outerName == 'hello'
    }

    @CompileStatic
    def "can extend target static"() {
        outerName = ''
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DeployPlugin)
        def deploy = (DeployExtension)project.extensions.getByType(DeployExtension)
        def target = deploy.targets as ExtensionAware
        target.extensions.add('testDeploy', { String name, Closure closure ->
            outerName = name
        })

        when:
            def test = target.extensions.getByName('testDeploy') as Closure
            test('hello', {

            })
        then:
            outerName == 'hello'
    }
}
