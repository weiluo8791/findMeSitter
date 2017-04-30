package user.review

import findMeSitter.user.User
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
        User user1 = new User()
        Reviewer RE = new Reviewer(userDetail: user1)
        then:
        !RE.validate()

        when:
        user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas' )
        RE= new Reviewer(dateOfFirstReview: new Date() - 20 , dateOfLatestReview: new Date() - 2,userDetail: user1)
        then:
        RE.validate()
    }

    void "test gender inList"() {
        when:
        User user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas',gender: 'X' )
        Reviewer RE= new Reviewer(dateOfFirstReview: new Date() - 20 , dateOfLatestReview: new Date() - 2,userDetail: user1)
        then:
        !RE.validate()

        when:
        user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas',gender: 'M' )
        RE= new Reviewer(dateOfFirstReview: new Date() - 20 , dateOfLatestReview: new Date() - 2,userDetail: user1)
        then:
        RE.validate()
    }

    void "test state inList"() {
        when:
        User user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas',state: 'XX' )
        Reviewer RE= new Reviewer(dateOfFirstReview: new Date() - 20 , dateOfLatestReview: new Date() - 2,userDetail: user1)
        then:
        !RE.validate()

        when:
        user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas',state: 'MA' )
        RE= new Reviewer(dateOfFirstReview: new Date() - 20 , dateOfLatestReview: new Date() - 2,userDetail: user1)
        then:
        RE.validate()
    }

    void "first before or equal latest date"() {
        when:
        User user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas' )
        Reviewer RE= new Reviewer(dateOfFirstReview: new Date() + 5, dateOfLatestReview: new Date()+4,userDetail: user1)
        then:
        !RE.validate()

        when:
        user1 = new User (username: 'ithomas',password: 'ithomas',firstName: 'Isaiah',lastName:'Thomas' )
        RE= new Reviewer(dateOfFirstReview: new Date() + 4, dateOfLatestReview: new Date()+5,userDetail: user1)
        then:
        RE.validate()
    }

}


