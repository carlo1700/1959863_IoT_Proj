package com.smarthome.thermostat;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.Random;

public class ThermostatServiceImpl extends ThermostatServiceGrpc.ThermostatServiceImplBase {

    private double currentTemperature = 22.5; // valore iniziale
    private final Random random = new Random();

    @Override
    public void getStatus(ThermostatGetStatusRequest request, StreamObserver<ThermostatGetStatusResponse> responseObserver) {
        int roll = random.nextInt(3); // 0, 1, o 2

        if (roll == 0) {
            currentTemperature += 0.5;
        } else if (roll == 1) {
            currentTemperature -= 0.5;
        }
        // se roll == 2, non cambia

        ThermostatGetStatusResponse response = ThermostatGetStatusResponse.newBuilder()
                .setCurrentTemperature(currentTemperature)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}