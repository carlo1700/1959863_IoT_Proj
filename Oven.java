public class Oven implements Device {
    private String name;
    private boolean on;

    public Oven(String name) {
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
        System.out.println(name + " is heating up.");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + " is turned off.");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
