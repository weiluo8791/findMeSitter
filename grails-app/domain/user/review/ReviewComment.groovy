package user.review

class ReviewComment {
    String comment
    Date commentDate
    Boolean published
    static belongsTo = [review:Review]
    static constraints = {
    }
}
