public class Laptop extends ConsumerElectronics {
    private int ramSize;
    private String cpuModel;

    public Laptop(int id, String brand, String model, Double price, Double screenSize, int storageSize, int ramSize, String cpuModel) {
        super(id, brand, model, price, screenSize ,storageSize);
        this.ramSize = ramSize;
        this.cpuModel = cpuModel;
    }
    public int getRamSize() {
        return ramSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    @Override
    public String toString() {
        return super.toString() + " --Laptop [ramSize=" + ramSize + ", cpuModel=" + cpuModel + "]";
    }
}
