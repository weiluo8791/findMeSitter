package daycare.provider

class DayCareCenter {
    Integer centerCapcity
    BigDecimal dailyRate
    String address
    String city
    String state
    String zip
    String country
    String email
    String  phoneNumber
    String otherDetail

    static hasMany = [features:Feature, pictures:Picture,calendars:Calendar]

    static constraints = {
    }
}
