package user.review

import findMeSitter.user.*

class Reviewer {
    Date dateOfFirstReview
    Date dateOfLatestReview
    User userDetail

    Integer getTotalReviewCount() {
        this.reviews.size()
    }

    static transients = ['totalReviewCount']
    static hasMany = [reviews: Review]
    static constraints = {
        dateOfFirstReview nullable: true
        dateOfLatestReview nullable: true, validator: { val, obj ->
            val?.after(obj.dateOfFirstReview) || (val == obj.dateOfFirstReview)
        }
    }
}

