package user.review

import daycare.provider.DayCareCenter

class Review {

    Boolean recommended
    Date dateOfReview
    String reviewTitle
    String reviewDetail
    String otherDetail
    Integer stars

    static searchable = {
        only = ['reviewTitle','reviewDetail','otherDetail']
    }
    static belongsTo = [reviewer:Reviewer,dayCareCenter:DayCareCenter]
    static hasMany = [comments:ReviewComment]
    static constraints = {
        recommended nullable: true
        otherDetail nullable: true
        stars nullable: true,  min: 0, max: 5
    }
}
