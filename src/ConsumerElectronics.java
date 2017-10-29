public class ConsumerElectronics extends Product{
    private final String machineType="Consumer Electronics";

    public ConsumerElectronics(int id, String brand, String model, Double price) {
        super(id, brand, model, price);
    }
    public String getType() {
        return machineType;
    }

    @Override
    public String toString(){
        return super.toString() +" --ConsumerElectronics [pType=" + machineType +"]";
    }
}
