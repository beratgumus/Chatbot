public class MobilePhone extends ConsumerElectronics {

    private int cameraResolution;
    private String os;
    private int ramSize;
    private Double screenSize;
    private int storageSize;

    public MobilePhone(int id, String brand, String model, Double price, int cameraResolution, String os, int ramSize, Double screenSize, int storageSize){
        super(id, brand, model, price);
        this.cameraResolution = cameraResolution;
        this.os = os;
        this.ramSize = ramSize;
        this.screenSize = screenSize;
        this.storageSize = storageSize;
    }

    public int getCameraResolution(){
        return  cameraResolution;
    }

    public void setCameraResolution(int cameraResolution){
        this.cameraResolution = cameraResolution;
    }

    public String getOs(){
        return os;
    }

    public void setOs(String os){
        this.os=os;
    }

    public int getRamSize(){
        return ramSize;
    }

    public void setRamSize(int ramSize){
        this.ramSize=ramSize;
    }

    public Double getScreenSize(){
        return screenSize;
    }

    public void setScreenSize(Double screenSize){
        this.screenSize = screenSize;
    }

    public int getStorageSize(){
        return storageSize;
    }

    public void setStorageSize(int storageSize){
        this.storageSize = storageSize;
    }

    @Override
    public String toString(){
        return super.toString() + " --CellPhone [cameraResolution=" + cameraResolution + ", os=" + os + ", ramSize=" + ramSize + ",screenSize=" + screenSize + ", storageSize" + storageSize + "]";
    }
}
