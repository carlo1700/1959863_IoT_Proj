package com.smarthome.solarpanel;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.Random;

public class SolarPanelServiceImpl extends SolarPanelServiceGrpc.SolarPanelServiceImplBase {

    private final double MAX_POWER_OUTPUT = 4000.0; // potenza massima in watts
    private double batteryStatus = 75.0;
    private double currentPowerOutput = 2500.0;

    private final Random random = new Random();

    @Override
    public void getStatus(SolarPanelGetStatusRequest request, StreamObserver<SolarPanelGetStatusResponse> responseObserver) {
        // Simula variazione casuale della batteria (±1%)
        int batteryRoll = random.nextInt(3); // 0,1,2
        if (batteryRoll == 0) batteryStatus = Math.min(100.0, batteryStatus + 1.0);
        else if (batteryRoll == 1) batteryStatus = Math.max(0.0, batteryStatus - 1.0);

        // Simula variazione casuale della potenza (±100W)
        int powerRoll = random.nextInt(3);
        if (powerRoll == 0) currentPowerOutput = Math.min(MAX_POWER_OUTPUT, currentPowerOutput + 100.0);
        else if (powerRoll == 1) currentPowerOutput = Math.max(0.0, currentPowerOutput - 100.0);

        double powerProductionPercentage = (currentPowerOutput / MAX_POWER_OUTPUT) * 100.0;

        SolarPanelGetStatusResponse response = SolarPanelGetStatusResponse.newBuilder()
                .setBatteryStatus(batteryStatus)
                .setCurrentPowerOutput(currentPowerOutput)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
