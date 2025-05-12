public class WashingMachine implements Device {
    private String name;
    private boolean on;

    public WashingMachine(String name) {
        this.name = name;
        this.on = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println(name + " washing machine is running.");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + " washing machine is stopped.");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
