import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeviceManager {
    private List<Device> devices;

    public void saveDeviceToDB(Device device) {
        String sql = "INSERT INTO devices (name, type, status) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, device.getName());
            stmt.setString(2, device.getClass().getSimpleName());
            stmt.setBoolean(3, device.isOn()); // oppure false se inizialmente spento
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore salvataggio DB: " + e.getMessage());
        }
    }

    public DeviceManager() {
        devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
        System.out.println(device.getName() + " added to manager.");
        saveDeviceToDB(device); // salva anche nel database
    }

    public void turnAllOn() {
        System.out.println("\nTurning all devices ON:");
        for (Device device : devices) {
            device.turnOn();
        }
    }

    public void turnAllOff() {
        System.out.println("\nTurning all devices OFF:");
        for (Device device : devices) {
            device.turnOff();
        }
    }

    public void showStatus() {
        System.out.println("\nDevice Status:");
        for (Device device : devices) {
            String status = device.isOn() ? "ON" : "OFF";
            System.out.println("- " + device.getName() + ": " + status);
        }
    }
}
