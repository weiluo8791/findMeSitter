package daycare.provider

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DayCareCenter)
class DayCareCenterSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects"() {
        when:
        DayCareCenter D1 = new DayCareCenter()
        then:
        !D1.validate()

        when:
        D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',
                                                zip: '02135',phoneNumber: '123-456-7890',centerCapcity: 6,dailyRate: 25.45)
        then:
        D1.validate()
    }

    void "test phone format"() {
        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',
                zip: '02135',phoneNumber: '1-123-456-7890',centerCapcity: 6,dailyRate: 25.45)
        then:
        !D1.validate()

        when:
        D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',
                zip: '02135',phoneNumber: '123-456-7890',centerCapcity: 6,dailyRate: 25.45)
        then:
        D1.validate()
    }

    void "test state inList"() {
        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'XX',
                zip: '02135',phoneNumber: '123-456-7890',centerCapcity: 6,dailyRate: 25.45)
        then:
        !D1.validate()

        when:
        D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',
                zip: '02135',phoneNumber: '123-456-7890',centerCapcity: 6,dailyRate: 25.45)
        then:
        D1.validate()
    }

    void "test centerCapcity"() {
        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'XX',
                zip: '02135',phoneNumber: '123-456-7890',centerCapcity: 0,dailyRate: 25.45)
        then:
        !D1.validate()

        when:
        D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',
                zip: '02135',phoneNumber: '123-456-7890',centerCapcity: 6,dailyRate: 25.45)
        then:
        D1.validate()
    }

    void "test dailyRate"() {
        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'XX',
                zip: '02135',phoneNumber: '123-456-7890',centerCapcity: 6,dailyRate: -25.45)
        then:
        !D1.validate()

        when:
        D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',
                zip: '02135',phoneNumber: '123-456-7890',centerCapcity: 6,dailyRate: 25.45)
        then:
        D1.validate()
    }
}
