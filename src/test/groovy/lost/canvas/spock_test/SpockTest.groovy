package lost.canvas.spock_test

import lost.canvas.spock_test.Calc
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

    def "test where"() {
        expect:
        calc(a, b) == c
        where:
        a | b | c
        1 | 2 | 3
        1 | 0 | 1
        0 | 1 | 1
    }

    def "test where 2"() {
        expect:
        calc(a, b) == c
        where:
        a << [1, 1, 0]
        b << [2, 0, 1]
        c << [3, 1, 1]
    }

    def "test where 3"() {
        expect:
        calc(a, b) == c
        where:
        a << (1..4)
        b << (4..1)
        c << [5] * 4
    }

    def "test where 4"() {
        expect:
        calc(a, b) == c
        where:
        a << (1..4)
        b = 5 - a
        c << [5] * 4
    }

    def "test where 5"() {
        when:
        println "test $filename"
        then:
        filename.toLowerCase() == "abc"
        where:
        filename << GroovyCollections.combinations([["a", "A"], ["b", "B"], ["c", "C"]].iterator())*.join()
    }

    def "test stub"() {

    }

    def "test mock"() {

    }

    def calc(a, b) {
        a + b
    }

    /**
     * Groovy Java 混编
     * 1. 可以通过 new 创建 Java 对象
     * 2. 可以调用 Java 对象的方法
     * 3. 可以 implements Java 接口
     * 4. 可以 extends Java 类
     * @return
     */
    def "test java"() {
        given: "invoke java Calc.add"
        def calc = new Calc()
        expect:
        calc.add(a, b) == c
        calc.div(x, y) == z
        where:
        a << [1, 2, 3]
        b << [3, 2, 1]
        c << [4, 4, 4]

        x << [7, 8, 9]
        y << [1, 2, 3]
        z << [6, 6, 6]
    }

}
