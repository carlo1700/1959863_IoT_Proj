package com.smarthome.washingmachine;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class WashingMachineServiceImpl extends WashingMachineServiceGrpc.WashingMachineServiceImplBase {

    private boolean isOn = false;
    private WashingMachineProgram currentProgram = WashingMachineProgram.DELICATE;
    private boolean isRunning = false;

    @Override
    public void turnOn(WashingMachineTurnOnRequest request,
            StreamObserver<WashingMachineTurnOnResponse> responseObserver) {

        isOn = true;

        WashingMachineTurnOnResponse response = WashingMachineTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Washing machine turned on")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void turnOff(WashingMachineTurnOffRequest request,
            StreamObserver<WashingMachineTurnOffResponse> responseObserver) {

        isOn = false;
        isRunning = false;

        WashingMachineTurnOffResponse response = WashingMachineTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Washing machine turned off")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void start(WashingMachineStartRequest request,
            StreamObserver<WashingMachineStartResponse> responseObserver) {

        if (!isOn) {
            isRunning = true;
            WashingMachineStartResponse response = WashingMachineStartResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Washing machine is not turned on")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }
    }

    @Override
    public void stop(WashingMachineStopRequest request, StreamObserver<WashingMachineStopResponse> responseObserver) {

        if (!isRunning) {
            WashingMachineStopResponse response = WashingMachineStopResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Washing machine is not running")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }
    }

    @Override
    public void setProgram(WashingMachineSetProgramRequest request,
            StreamObserver<WashingMachineSetProgramResponse> responseObserver) {
        WashingMachineSetProgramResponse response = WashingMachineSetProgramResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Program set to " + request.getProgram())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getStatus(WashingMachineGetStatusRequest request, StreamObserver<WashingMachineGetStatusResponse> responseObserver) {
        WashingMachineGetStatusResponse response = WashingMachineGetStatusResponse.newBuilder()
                .setIsOn(isOn)
                .setIsRunning(isRunning)
                .setCurrentProgram(currentProgram)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}