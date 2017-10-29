public class Camera extends ConsumerElectronics {
    private int videoResolution;
    private int imageResolution;
    private int iso;

    public Camera(int id, String brand, String model, Double price, Double screenSize, int storageSize, int videoResolution, int imageResolution, int iso) {
        super(id, brand, model, price, screenSize, storageSize);
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

    @Override
    public String toString() {
        return super.toString() + " --Camera [iso=" + iso + ", imageResolution=" + imageResolution + ", videoResolution=" + videoResolution + "]";
    }
}
