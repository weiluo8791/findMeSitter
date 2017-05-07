package user.review

import grails.transaction.Transactional

@Transactional
class SearchReviewService {

    def elasticSearchService

    def searchReview(String searchString) {
        //def res = elasticSearchService.search(searchString)
        def res = elasticSearchService.search("${params.query}")
        [query:params.query, total:res.total, searchResults:res.searchResults]
        res
    }
}
