public class Coil implements Device {
    private String name;
    private boolean on;

    public Coil(String name) {
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
        System.out.println(name + " coil is activated.");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + " coil is deactivated.");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
