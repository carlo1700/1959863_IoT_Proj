public class MotionSensor implements Device {
    private String name;
    private boolean on;

    public MotionSensor(String name) {
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
        System.out.println(name + " motion sensor is active.");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + " motion sensor is inactive.");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
