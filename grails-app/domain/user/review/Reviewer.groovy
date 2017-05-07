package user.review

import findMeSitter.user.*

class Reviewer {
    Date dateOfFirstReview
    Date dateOfLatestReview
    User userDetail
    String  getGender() {userDetail.gender}
    String getState(){userDetail.state}
    String getCity(){userDetail.city}
    String getFullName(){userDetail.firstName + ' ' + userDetail.lastName}

    Date dateCreated
    Date lastUpdated

    Integer getTotalReviewCount() {
        this.reviews.size()
    }

    static searchable = {
        except = ['dateCreated','lastUpdated']
        userDetail component:true
    }
    static transients = ['totalReviewCount','gender','state','city','fullName']
    static hasMany = [reviews: Review]
    static constraints = {
        dateOfLatestReview validator: { val, obj ->
            val?.after(obj.dateOfFirstReview) || (val == obj.dateOfFirstReview)
        }
        gender nullable: true, inList: ['M', 'F']
        state nullable: true, inList: ['AK', 'AL', 'AR', 'AZ', 'CA', 'CO', 'CT', 'DC', 'DE', 'FL', 'GA', 'HI',
                                       'IA', 'ID', 'IL', 'IN', 'KS', 'KY', 'LA', 'MA', 'MD', 'ME', 'MI', 'MN',
                                       'MO', 'MS', 'MT', 'NC', 'ND', 'NE', 'NH', 'NJ', 'NM', 'NV', 'NY',
                                       'OH', 'OK', 'OR', 'PA', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VA', 'VT',
                                       'WA', 'WI', 'WV', 'WY']
    }
}

