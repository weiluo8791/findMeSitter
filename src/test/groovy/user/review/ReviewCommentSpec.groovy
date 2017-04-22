package user.review

import daycare.provider.DayCareCenter
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReviewComment)
class ReviewCommentSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects"() {
        when:
        ReviewComment RC = new ReviewComment()
        then:
        !RC.validate()

        when:
        Review R1= new Review()
        RC =new ReviewComment(comment: 'Thank you for the review',commentDate:  new Date(),published: false,review: R1)
        then:
        RC.validate()
    }

}
