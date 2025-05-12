public class Main {
    public static void main(String[] args) {
        DeviceManager manager = new DeviceManager();

        manager.addDevice(new Light("Living Room Light"));
        manager.addDevice(new Oven("Kitchen Oven"));
        manager.addDevice(new WashingMachine("LG Washer"));
        manager.addDevice(new MotionSensor("Entrance Sensor"));
        manager.addDevice(new Thermostat("Nest Thermostat"));
        manager.addDevice(new Dishwasher("Bosch Dishwasher"));
        manager.addDevice(new Coil("Electric Coil"));
        manager.addDevice(new Blind("Bedroom Blinds"));
        manager.addDevice(new SolarPanel("Roof Solar Panel"));

        manager.showStatus();
        manager.turnAllOn();
        manager.showStatus();
        manager.turnAllOff();
        manager.showStatus();
    }
}
