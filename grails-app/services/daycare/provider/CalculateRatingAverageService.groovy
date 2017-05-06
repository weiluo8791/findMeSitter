package daycare.provider

import grails.transaction.Transactional

@Transactional
class CalculateRatingAverageService {

    def getRatingAverage(DayCareCenter dayCareCenter) {
        if (dayCareCenter.reviews.size() > 0) {
            return  dayCareCenter.reviews.sum { it.stars } / dayCareCenter.reviews.size()
        }
        return  0
    }
}
