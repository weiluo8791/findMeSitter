package user.review

import daycare.provider.DayCareCenter
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Review)
class ReviewSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects"() {
        when:
        Review R1 = new Review()
        then:
        !R1.validate()

        when:
        R1 =new Review( reviewTitle: 'Best day care',reviewDetail: 'I love this place',
                                    dateOfReview: new Date(),reviewer: new Reviewer(),
                                    dayCareCenter: new DayCareCenter() )
        then:
        R1.validate()
    }

    //test start range
    void "test stars range" () {
        when:
        Review R1 =new Review( reviewTitle: 'Best day care',reviewDetail: 'I love this place',
                dateOfReview: new Date(),stars: 6, reviewer: new Reviewer(),
                dayCareCenter: new DayCareCenter() )
        then:
        !R1.validate()

        when:
        R1 =new Review( reviewTitle: 'Best day care',reviewDetail: 'I love this place',
                dateOfReview: new Date(),stars: 5, reviewer: new Reviewer(),
                dayCareCenter: new DayCareCenter() )
        then:
        R1.validate()
    }
}

