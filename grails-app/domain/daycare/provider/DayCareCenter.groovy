package daycare.provider

class DayCareCenter {
    Integer centerCapcity
    BigDecimal dailyRate
    String name
    String address
    String city
    String state
    String zip
    String email
    String  phoneNumber
    String otherDetail

    static hasMany = [features:Feature, pictures:Picture,calendars:Calendar]

    static constraints = {
        email nullable: true
        phoneNumber nullable: true
        phoneNumber(matches: '^\\d{3}-\\d{3}-\\d{4}$')
        dailyRate nullable: true
        state inList: ['AK', 'AL', 'AR', 'AZ', 'CA', 'CO', 'CT', 'DC', 'DE', 'FL', 'GA', 'HI',
                            'IA', 'ID', 'IL', 'IN', 'KS', 'KY', 'LA', 'MA', 'MD', 'ME', 'MI', 'MN',
                            'MO', 'MS', 'MT', 'NC', 'ND', 'NE', 'NH', 'NJ', 'NM', 'NV', 'NY',
                            'OH', 'OK', 'OR', 'PA', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VA', 'VT',
                            'WA', 'WI', 'WV', 'WY']
    }
}
