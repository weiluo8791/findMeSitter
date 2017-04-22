package user.review

class Reviewer {
    String firstName
    String lastName
    String nickName
    String gender
    String city
    String state
    String otherDetail
    Date dateOfFirstReview
    Date dateOfLatestReview

    Integer getTotalReviewCount() {
        this.reviews.size()
    }

    static transients = ['totalReviewCount']
    static hasMany = [reviews: Review]

    static constraints = {
        nickName nullable: true
        gender nullable: true, inList: ['M', 'F']
        city nullable: true
        state nullable: true, inList: ['AK', 'AL', 'AR', 'AZ', 'CA', 'CO', 'CT', 'DC', 'DE', 'FL', 'GA', 'HI',
                                       'IA', 'ID', 'IL', 'IN', 'KS', 'KY', 'LA', 'MA', 'MD', 'ME', 'MI', 'MN',
                                       'MO', 'MS', 'MT', 'NC', 'ND', 'NE', 'NH', 'NJ', 'NM', 'NV', 'NY',
                                       'OH', 'OK', 'OR', 'PA', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VA', 'VT',
                                       'WA', 'WI', 'WV', 'WY']
        otherDetail nullable: true
        dateOfFirstReview nullable: true
        dateOfLatestReview nullable: true, validator: { val, obj ->
            val?.after(obj.dateOfFirstReview) || (val == obj.dateOfFirstReview)
        }
    }
}

