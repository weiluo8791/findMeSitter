package user.review


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class ReviewerIntSpec extends Specification {

    void "test that there are 3 reviewer in the db" () {
        expect:
        Reviewer.count() == 3
    }
}
