package user.review


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class ReviewIntSpec extends Specification {

    void "test that there are 3 review in the db" () {
        expect:
        Review.count() == 4
    }
}
