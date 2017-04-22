package daycare.provider

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Feature)
class FeatureSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects"() {
        when:
        Feature F1 = new Feature()
        then:
        !F1.validate()

        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',zip: '02135')
        F1 =new Feature(featureName: 'Late Pickup',featureDescription: 'Offer late pickup for parents', featureType: 'external',center: D1)
        then:
        F1.validate()
    }

    void "test featureName inList"() {
        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',zip: '02135')
        Feature F1 =new Feature(featureName: 'Early Release',featureDescription: 'Release your child early', featureType: 'external',center: D1)
        then:
        !F1.validate()

        when:
        D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',zip: '02135')
        F1 =new Feature(featureName: 'Late Pickup',featureDescription: 'Offer late pickup for parents', featureType: 'external',center: D1)
        then:
        F1.validate()
    }

    void "test featureType inList"() {
        when:
        DayCareCenter D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',zip: '02135')
        Feature F1 =new Feature(featureName: 'Late Pickup',featureDescription: 'Offer late pickup for parents', featureType: 'other',center: D1)
        then:
        !F1.validate()

        when:
        D1 = new DayCareCenter(name: 'My Day Care',address: '123 Main st',city: 'Boston',state: 'MA',zip: '02135')
        F1 =new Feature(featureName: 'Late Pickup',featureDescription: 'Offer late pickup for parents', featureType: 'internal',center: D1)
        then:
        F1.validate()
    }
}