package jaci.gradle.deploy.cache

import groovy.transform.CompileStatic
import jaci.gradle.deploy.DeployExtension
import jaci.gradle.deploy.DeployPlugin
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.Project

class CacheExtendableTest extends Specification {

    def outerName = ''

    def "can extend cache"() {

        outerName = ''
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DeployPlugin)
        def deploy = (DeployExtension)project.extensions.getByType(DeployExtension)
        deploy.cache.extensions.add('testDeploy', { String name, Closure closure ->
            outerName = name
        })

        when:
            def test = deploy.cache.extensions.getByName('testDeploy')
            test('hello', {

            })
        then:
            outerName == 'hello'
    }

    @CompileStatic
    def "can extend cache static"() {
        outerName = ''
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DeployPlugin)
        def deploy = (DeployExtension)project.extensions.getByType(DeployExtension)
        def cache = deploy.cache as ExtensionAware
        cache.extensions.add('testDeploy', { String name, Closure closure ->
            outerName = name
        })

        when:
            def test = cache.extensions.getByName('testDeploy') as Closure
            test('hello', {

            })
        then:
            outerName == 'hello'
    }
}
