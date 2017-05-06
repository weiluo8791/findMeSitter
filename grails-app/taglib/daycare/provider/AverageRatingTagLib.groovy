package daycare.provider

class AverageRatingTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    CalculateRatingAverageService calculateRatingAverageService

    static namespace = "ratingAverage"
    /**
     * @attr daycarecenter REQUIRED
     */
    def daycarecenter = { attrs ->
        def htmlString = []
        htmlString << '<td><span class="stars">'
        htmlString << calculateRatingAverageService.getRatingAverage(attrs["daycarecenter"])
        htmlString << '</span></td>'
        out << htmlString.join()
    }
}
