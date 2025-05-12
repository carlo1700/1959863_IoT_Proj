public class SolarPanel implements Device {
    private String name;
    private boolean on;

    public SolarPanel(String name) {
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
        System.out.println(name + " solar panel is collecting energy.");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + " solar panel is idle.");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
