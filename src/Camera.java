public class Camera extends ConsumerElectronics {
    private int videoResolution;
    private Double screenSize;
    private int imageResolution;
    private int iso;

    public Camera(int id, String brand, String model, Double price, int videoResolution, Double screenSize, int imageResolution, int iso) {
        super(id, brand, model, price);
        this.videoResolution = videoResolution;
        this.screenSize = screenSize;
        this.imageResolution = imageResolution;
        this.iso = iso;
    }

    public int getVideoResolution() {
        return videoResolution;
    }

    public void setVideoResolution(int videoResolution) {
        this.videoResolution = videoResolution;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
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

    @Override
    public String toString() {
        return super.toString() + " --Camera [iso=" + iso + ", imageResolution=" + imageResolution + ", videoResolution=" + videoResolution + ",screenSize=" + screenSize + "]";
    }
}
