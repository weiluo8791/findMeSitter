package daycare.provider

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Picture)
class PictureSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects"() {
        when:
        Picture P1 = new Picture()
        then:
        !P1.validate()

        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',zip: '02135')
        P1 =new Picture(pictureName: 'front door',pictureDescription: 'this is a image of our front door',center: D1)
        then:
        P1.validate()
    }

}
