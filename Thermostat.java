public class Thermostat implements Device {
    private String name;
    private boolean on;

    public Thermostat(String name) {
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
        System.out.println(name + " thermostat is heating.");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + " thermostat is off.");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
