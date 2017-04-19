package daycare.provider

class Picture {
    String pictureName
    String pictureDescription
    String imageUrl
    byte[] image
    String  getImageBase64() {image.encodeBase64().toString()}

    static belongsTo = [center:DayCareCenter]
    static transients = ['imageBase64']
    static mappings = {
        image type: "blob"
    }
    static constraints = {
    }
}
