package daycare.provider

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Calendar)
class CalendarSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects"() {
        when:
        Calendar C1 = new Calendar()
        then:
        !C1.validate()

        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',zip: '02135')
        C1 =new Calendar(calendarYear: 2016,hours: 'M-F 07-18',holidays: '0101,0118,0215,0530,0704,0905,1010,1111,1124,1226',center: D1)
        then:
        C1.validate()
    }

    void "test year"() {
        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',zip: '02135')
        Calendar C1 =new Calendar(calendarYear: 1996,hours: 'M-F 07-18',holidays: '0101,0118,0215,0530,0704,0905,1010,1111,1124,1226',center: D1)
        then:
        !C1.validate()


        when:
        D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',zip: '02135')
        C1 =new Calendar(calendarYear: 2016,hours: 'M-F 07-18',holidays: '0101,0118,0215,0530,0704,0905,1010,1111,1124,1226',center: D1)
        then:
        C1.validate()
    }

}
