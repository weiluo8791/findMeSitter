package daycare.provider

class Feature {
    String featureName
    String featureDescription
    static belongsTo = [center:DayCareCenter]
    static constraints = {
    }
}
