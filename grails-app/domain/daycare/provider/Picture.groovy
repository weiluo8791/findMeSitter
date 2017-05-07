package daycare.provider

class Picture {
    String pictureName
    String pictureDescription
    String imageUrl
    byte[] image
    String  getImageBase64() {image.encodeBase64().toString()}

    static searchable = {
        except = 'image'
        center component:true
    }
    static belongsTo = [center:DayCareCenter]
    static transients = ['imageBase64']
    static mapping = {
        image type: "blob"
    }
    static constraints = {
        pictureDescription nullable: true
        image nullable: true
        imageUrl  nullable: true
    }
}
