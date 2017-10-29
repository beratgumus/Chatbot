public class Laptop extends ConsumerElectronics {
    private int ramSize;
    private String cpuModel;
    private Double screenSize;
    private int storageSize;

    public Laptop(int id, String brand, String model, Double price, int ramSize, String cpuModel, Double screenSize, int storageSize) {
        super(id, brand, model, price);
        this.ramSize = ramSize;
        this.cpuModel = cpuModel;
        this.screenSize = screenSize;
        this.storageSize = storageSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
    }

    public int getRamSize() {

        return ramSize;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    public int getStorageSize() {

        return storageSize;
    }

    @Override
    public String toString() {
        return super.toString() + " --Laptop [ramSize=" + ramSize + ", cpuModel=" + cpuModel + ", storageSize=" + storageSize + ",screenSize=" + screenSize + "]";
    }
}
