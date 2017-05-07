package daycare.provider

class Feature {
    String featureName
    String featureType
    String featureDescription

    static searchable = {
        center component:true
    }
    static belongsTo = [center:DayCareCenter]
    static constraints = {
        featureDescription nullable: true
        featureName inList: ['Other','Late Pickup','Large Backyard','Licensed','Substitute on Call','Food Program']
        featureType inList: ['internal','external']
    }
}
