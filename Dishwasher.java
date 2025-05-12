public class Dishwasher implements Device {
    private String name;
    private boolean on;

    public Dishwasher(String name) {
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
        System.out.println(name + " dishwasher is washing.");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + " dishwasher is stopped.");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
