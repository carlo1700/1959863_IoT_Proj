package com.smarthome;

import com.smarthome.communication.DeviceManagerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceManagerMain {
    private static final Logger logger = LoggerFactory.getLogger(DeviceManagerMain.class);

    public static void main(String[] args) {
        int port = 9000;

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                logger.error("Invalid port number: {}", args[0]);
                System.exit(1);
            }
        }

        try {
            DeviceManagerServer server = new DeviceManagerServer(port);
            server.start();
            server.blockUntilShutdown();
        } catch (Exception e) {
            logger.error("Error starting Device Manager Server", e);
            System.exit(1);
        }
    }
}
