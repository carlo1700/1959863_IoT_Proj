package com.smarthome.oven;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class OvenServiceImpl extends OvenServiceGrpc.OvenServiceImplBase {

    private boolean isOn = false;
    private int temperature = 0;         // temperatura attuale
    private int targetTemperature = 0;   // temperatura desiderata
    private OvenProgram currentProgram = OvenProgram.OVEN_PROGRAM_UNSPECIFIED;

    private Thread heatingThread;

    @Override
    public void turnOn(OvenTurnOnRequest request, StreamObserver<OvenTurnOnResponse> responseObserver) {
        isOn = true;

        OvenTurnOnResponse response = OvenTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Oven turned on")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void turnOff(OvenTurnOffRequest request, StreamObserver<OvenTurnOffResponse> responseObserver) {
        isOn = false;

        // ferma eventuale thread di riscaldamento
        if (heatingThread != null && heatingThread.isAlive()) {
            heatingThread.interrupt();
        }

        OvenTurnOffResponse response = OvenTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Oven turned off")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setTemperature(SetTemperatureRequest request, StreamObserver<SetTemperatureResponse> responseObserver) {
        targetTemperature = request.getTemperature();

        // Se c'è un thread precedente in esecuzione, lo interrompiamo
        if (heatingThread != null && heatingThread.isAlive()) {
            heatingThread.interrupt();
        }

        // Thread che aggiorna la temperatura gradualmente
        heatingThread = new Thread(() -> {
            try {
                while (isOn && temperature != targetTemperature) {
                    if (temperature < targetTemperature) temperature++;
                    else if (temperature > targetTemperature) temperature--;
                    Thread.sleep(10000); // 10 secondi per ogni grado
                }
            } catch (InterruptedException e) {
                // Thread interrotto, non fare nulla
            }
        });
        heatingThread.start();

        SetTemperatureResponse response = SetTemperatureResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Heating started towards " + targetTemperature + "°C")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getStatus(OvenGetStatusRequest request, StreamObserver<OvenGetStatusResponse> responseObserver) {
        OvenGetStatusResponse response = OvenGetStatusResponse.newBuilder()
                .setIsOn(isOn)
                .setTemperature(temperature)
                .setCurrentProgram(currentProgram)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setProgram(OvenSetProgramRequest request, StreamObserver<OvenSetProgramResponse> responseObserver) {
        currentProgram = request.getProgram();

        OvenSetProgramResponse response = OvenSetProgramResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Oven program set to " + currentProgram.name())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
