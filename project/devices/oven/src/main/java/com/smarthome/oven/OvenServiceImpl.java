package com.smarthome.oven;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class OvenServiceImpl extends OvenServiceGrpc.OvenServiceImplBase {

    private boolean isOn = false;
    private int temperature = 0;
    private OvenProgram currentProgram = OvenProgram.OVEN_PROGRAM_UNSPECIFIED;

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

        OvenTurnOffResponse response = OvenTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Oven turned off")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setTemperature(SetTemperatureRequest request, StreamObserver<SetTemperatureResponse> responseObserver) {
        SetTemperatureResponse response = SetTemperatureResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Temperature set to " + request.getTemperature() + "°C")
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

        // Aggiorna il programma interno
        currentProgram = request.getProgram();

        // Crea la risposta
        currentProgram = request.getProgram();


        OvenSetProgramResponse response = OvenSetProgramResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Oven program set to " + currentProgram.name())
                .build();


        // Invia la risposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}