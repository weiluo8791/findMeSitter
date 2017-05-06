package daycare.provider

import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class DayCareCenterIntSpec extends Specification {

    void "test that there are 1 DayCareCenter in the db" () {
        expect:
        DayCareCenter.count() == 3
    }
}
