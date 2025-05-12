public class Blind implements Device {
    private String name;
    private boolean on;

    public Blind(String name) {
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
        System.out.println(name + " blinds are opened.");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + " blinds are closed.");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
