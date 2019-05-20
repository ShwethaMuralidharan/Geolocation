package v1.post;

/**
 * Resource for the API.  This is a presentation class for frontend work.
 */
public class PostResource {
    private String id;
    private String link;
    private String name;
    private String address;
    private String latitude;
    private String longitude;

    public PostResource() {
    }

    public PostResource(String id, String link, String name, String address) {
        this.id = id;
        this.link = link;
        this.name = name;
        this.address = address;
    }

    public PostResource(PostData data, String link) {
        this.id = data.id.toString();
        this.link = link;
        this.name = data.name;
        this.address = data.address;
        this.latitude = data.latitude;
        this.longitude = data.longitude;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
