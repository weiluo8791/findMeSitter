package user.review

class Reviewer {
    Date dateOfFirstReview
    Date dateOfLatestReview
    String nickName
    String firstName
    String lastName
    String gender
    String city
    String state
    String otherDetail
    Integer getTotalReviewCount(){
        this.reviews.size()
    }

    static transients = ['totalReviewCount']
    static hasMany = [reviews:Review]

    static constraints = {
    }
}
