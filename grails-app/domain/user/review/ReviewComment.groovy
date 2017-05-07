package user.review

class ReviewComment {
    String comment
    Date commentDate
    Boolean published

    static searchable = {
        review component:true
    }
    static belongsTo = [review:Review]
    static constraints = {
    }
}
