package com.smarthome.dishwasher;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class DishwasherServiceImpl extends DishwasherServiceGrpc.DishwasherServiceImplBase {

    private boolean isOn = false;
    private boolean isRunning = false;
    private DishwasherProgram currentProgram = DishwasherProgram.NORMAL;
    private int remainingTime = 0; // in minutes

    @Override
    public void turnOn(DishwasherTurnOnRequest request, StreamObserver<DishwasherTurnOnResponse> responseObserver) {
        isOn = true;

        DishwasherTurnOnResponse response = DishwasherTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Dishwasher turned on")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void turnOff(DishwasherTurnOffRequest request, StreamObserver<DishwasherTurnOffResponse> responseObserver) {
        isOn = false;
        isRunning = false;
        remainingTime = 0;

        DishwasherTurnOffResponse response = DishwasherTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Dishwasher turned off")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void start(DishwasherStartRequest request, StreamObserver<DishwasherStartResponse> responseObserver) {
        if (!isOn) {
            DishwasherStartResponse response = DishwasherStartResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Dishwasher is off. Turn it on first.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        isRunning = true;
        remainingTime = 120; // simuliamo un ciclo di 2 ore

        DishwasherStartResponse response = DishwasherStartResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Dishwasher started with program " + currentProgram.name())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void stop(DishwasherStopRequest request, StreamObserver<DishwasherStopResponse> responseObserver) {
        isRunning = false;
        remainingTime = 0;

        DishwasherStopResponse response = DishwasherStopResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Dishwasher stopped")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setProgram(SetProgramRequest request, StreamObserver<SetProgramResponse> responseObserver) {
        if (!isOn) {
            SetProgramResponse response = SetProgramResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Dishwasher is off. Turn it on first.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        this.currentProgram = request.getProgram();

        SetProgramResponse response = SetProgramResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Program set to " + currentProgram.name())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getStatus(DishwasherGetStatusRequest request, StreamObserver<DishwasherGetStatusResponse> responseObserver) {
        DishwasherGetStatusResponse response = DishwasherGetStatusResponse.newBuilder()
                .setIsRunning(isRunning)
                .setCurrentProgram(currentProgram)
                .setRemainingTime(remainingTime)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
