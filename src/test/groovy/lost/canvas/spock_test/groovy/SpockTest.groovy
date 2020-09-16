package lost.canvas.spock_test.groovy

import spock.lang.Specification

class SpockTest extends Specification {

    def setup() {
        println('>>> setup')
    }

    def cleanup() {
        println('>>> cleanup')
    }

    def setupSpec() {
        println('>>>>>> setupSpec')
    }

    def cleanupSpec() {
        println('>>>>>> cleanupSpec')
    }

    def "test given when then"() {
        given:
        def a = 1
        when:
        a++
        then:
        a == 2
    }

    def "test expect where"() {
        expect:
        cal(a, b) == c
        where:
        a | b | c
        1 | 2 | 3
        1 | 0 | 1
        0 | 1 | 1
    }

    def "test expect where 2"() {
        expect:
        cal(a, b) == c
        where:
        a << [1, 1, 0]
        b << [2, 0, 1]
        c << [3, 1, 3]
    }

    def "test times"() {
        def service = Mock(Service) // has start(), stop(), and doWork() methods
        def app = new Application(service) // controls the lifecycle of the service

        when:
        app.run()

        then:
        with(service) {
            1 * start()
            2 * doWork()
            1 * stop()
        }
    }

    class Application {
        Service service

        Application(Service s) {
            service = s
        }

        def run() {
            println('app run')
        }
    }

    class Service {
        def start() {
            println('service start')
        }

        def stop() {
            println('service stop')
        }

        def doWork() {
            println('service doWork')
        }
    }

    def first() {
        println('first')
    }

    def second() {
        println('second')
    }

    def cal(a, b) {
        return a + b
    }
}
