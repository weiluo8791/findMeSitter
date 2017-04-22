package user.review

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reviewer)
class ReviewerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects"() {
        when:
        Reviewer RE = new Reviewer()
        then:
        !RE.validate()

        when:
        RE= new Reviewer(firstName: 'Wei',lastName: 'Luo')
        then:
        RE.validate()
    }

    void "test gender inList"() {
        when:
        Reviewer RE= new Reviewer(firstName: 'Wei',lastName: 'Luo',gender: 'X')
        then:
        !RE.validate()

        when:
        RE= new Reviewer(firstName: 'Wei',lastName: 'Luo',gender: 'M')
        then:
        RE.validate()
    }

    void "test state inList"() {
        when:
        Reviewer RE= new Reviewer(firstName: 'Wei',lastName: 'Luo',state: 'XX')
        then:
        !RE.validate()

        when:
        RE= new Reviewer(firstName: 'Wei',lastName: 'Luo',state: 'MA')
        then:
        RE.validate()
    }

    void "first before or equal latest date"() {
        when:
        Reviewer RE= new Reviewer(firstName: 'Wei',lastName: 'Luo',dateOfFirstReview: new Date() + 5, dateOfLatestReview: new Date()+4)
        then:
        !RE.validate()

        when:
        RE= new Reviewer(firstName: 'Wei',lastName: 'Luo',dateOfFirstReview: new Date() + 4, dateOfLatestReview: new Date()+5)
        then:
        RE.validate()
    }

}


