package daycare.provider

import user.review.Review

class DayCareCenter {
    String name
    String address
    String city
    String state
    String zip
    String email
    String phoneNumber
    String otherDetail
    Integer centerCapcity
    BigDecimal dailyRate

    static searchable = true
    static hasMany = [features:Feature,
                                pictures:Picture,
                                calendars:Calendar,
                                reviews:Review]

    static constraints = {
        email nullable: true
        phoneNumber nullable: true, matches: '^\\d{3}-\\d{3}-\\d{4}$'
        otherDetail nullable: true
        centerCapcity min: 1
        dailyRate min: 0.0
        state inList: ['AK', 'AL', 'AR', 'AZ', 'CA', 'CO', 'CT', 'DC', 'DE', 'FL', 'GA', 'HI',
                            'IA', 'ID', 'IL', 'IN', 'KS', 'KY', 'LA', 'MA', 'MD', 'ME', 'MI', 'MN',
                            'MO', 'MS', 'MT', 'NC', 'ND', 'NE', 'NH', 'NJ', 'NM', 'NV', 'NY',
                            'OH', 'OK', 'OR', 'PA', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VA', 'VT',
                            'WA', 'WI', 'WV', 'WY']
    }
}
