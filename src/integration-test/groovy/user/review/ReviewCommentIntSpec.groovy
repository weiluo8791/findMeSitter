package user.review


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class ReviewCommentIntSpec extends Specification {

    void "test that there are 6 review comment in the db" () {
        expect:
        ReviewComment.count() == 6
    }
}
