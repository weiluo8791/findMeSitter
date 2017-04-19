package user.review

class Review {
    Boolean recommendated
    Date dateOfReview
    String reviewTitle
    String reviewDetail
    String otherDetail
    Integer stars

    static belongsTo = [reviewer:Reviewer]
    static hasMany = [comments:ReviewComment]
    static constraints = {
    }
}
