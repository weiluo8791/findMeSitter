package user.review

class ReviewComment {
    String comment
    Date commentDate
    Boolean published

    static searchable = {
        only = 'comment'
    }
    static belongsTo = [review:Review]
    static constraints = {
    }
}
