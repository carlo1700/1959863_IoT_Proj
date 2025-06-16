package com.smarthome.washingmachine;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class WashingMachineServiceImpl extends WashingMachineServiceGrpc.WashingMachineServiceImplBase {
    
    @Override
    public void turnOn(WashingMachineTurnOnRequest request, StreamObserver<WashingMachineTurnOnResponse> responseObserver) {
        WashingMachineTurnOnResponse response = WashingMachineTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Washing machine turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void turnOff(WashingMachineTurnOffRequest request, StreamObserver<WashingMachineTurnOffResponse> responseObserver) {
        WashingMachineTurnOffResponse response = WashingMachineTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Washing machine turned off")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void start(WashingMachineStartRequest request, StreamObserver<WashingMachineStartResponse> responseObserver) {
        WashingMachineStartResponse response = WashingMachineStartResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Washing machine started")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void stop(WashingMachineStopRequest request, StreamObserver<WashingMachineStopResponse> responseObserver) {
        WashingMachineStopResponse response = WashingMachineStopResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Washing machine stopped")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void setProgram(WashingMachineSetProgramRequest request, StreamObserver<WashingMachineSetProgramResponse> responseObserver) {
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
                .setIsRunning(false)
                .setCurrentProgram(WashingMachineProgram.COTTON)
                .setRemainingTime(0)
                .setDoorLocked(false)
                .setCurrentTemperature(20)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}