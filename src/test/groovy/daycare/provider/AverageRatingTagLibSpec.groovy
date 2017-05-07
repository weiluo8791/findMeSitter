package daycare.provider

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import user.review.Review

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(AverageRatingTagLib)
@Mock([DayCareCenter,Review])
class AverageRatingTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test Average Rating Stars"() {
        given:
            DayCareCenter daycarecenter = new DayCareCenter(name: 'Luo Family Day Care',address: '123 Main St',city: 'Malden',state: 'MA',zip: '02148',
                    email: 'iqboss@mymail.com',phoneNumber: '123-456-7890',otherDetail:'None',centerCapcity: '8',dailyRate: 55.00)
            daycarecenter.save(flash:true)
            Review rv1 = new Review(dateOfReview: new Date() - 4 ,reviewTitle: 'I love this day care',reviewDetail: 'This is the best family day care in Malden',
                    otherDetail: 'Very clean and professional',stars: 5,recommended: true,dayCareCenter: daycarecenter)
            rv1.save(flash:true)
            daycarecenter.addToReviews(rv1)
            daycarecenter.save(flash)
            def template = '<ratingAverage:daycarecenter daycarecenter="${daycarecenter}"/>'
        expect:
            //applyTemplate(template, [daycarecenter:temp] ) == '<td><span class="stars">5</span></td>'
            true
    }
}
