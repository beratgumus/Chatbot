import org.bson.Document;

public class Camera extends ConsumerElectronics {
    private int videoResolution;
    private int imageResolution;
    private int iso;

    public Camera(int id, String brand, String model, Double price, Double height, Double width, Double depth, int weight, Double screenSize, int storageSize, int videoResolution, int imageResolution, int iso) {
        super(id, brand, model, price, "Camera", height, width, depth, weight, screenSize, storageSize);
        this.videoResolution = videoResolution;
        this.imageResolution = imageResolution;
        this.iso = iso;
    }

    public int getVideoResolution() {
        return videoResolution;
    }

    public void setVideoResolution(int videoResolution) {
        this.videoResolution = videoResolution;
    }

    public int getImageResolution() {
        return imageResolution;
    }

    public void setImageResolution(int imageResolution) {
        this.imageResolution = imageResolution;
    }

    public int getIso() {
        return iso;
    }

    public void setIso(int iso) {
        this.iso = iso;
    }

    public Document toDocument(){
        return super.toDocument()
                .append("Video Resolution", getVideoResolution())
                .append("Image Resolution", getImageResolution())
                .append("ISO", getIso());
    }

    @Override
    public String toString() {
        return super.toString() + " --Camera [iso=" + iso + ", imageResolution=" + imageResolution + ", videoResolution=" + videoResolution + "]";
    }
}
