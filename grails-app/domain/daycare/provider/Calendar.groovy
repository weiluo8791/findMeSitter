package daycare.provider

class Calendar {
    Integer calendarYear
    String hours
    String holidays

    static belongsTo = [center:DayCareCenter]
    static constraints = {
        calendarYear min: 1997
        hours nullable: true
        holidays nullable: true
    }
}
