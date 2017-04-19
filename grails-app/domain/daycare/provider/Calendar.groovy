package daycare.provider

class Calendar {
    Integer calendarYear
    String hours
    String holidays

    static belongsTo = [center:DayCareCenter]
    static constraints = {
    }
}
