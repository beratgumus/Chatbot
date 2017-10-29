public class MobilePhone extends ConsumerElectronics {

    private int cameraResolution;
    private String os;
    private int ramSize;

    public MobilePhone(int id, String brand, String model, Double price, Double screenSize, int storageSize, int cameraResolution, String os, int ramSize) {
        super(id, brand, model, price, screenSize ,storageSize);
        this.cameraResolution = cameraResolution;
        this.os = os;
        this.ramSize = ramSize;
    }

    public int getCameraResolution() {
        return cameraResolution;
    }

    public void setCameraResolution(int cameraResolution) {
        this.cameraResolution = cameraResolution;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getRamSize() {
        return ramSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

    @Override
    public String toString(){
        return super.toString() + " --CellPhone [cameraResolution=" + cameraResolution + " MP" + ", os=" + os + ", ramSize=" + ramSize + "GB" + "]";
    }
}