package com.smarthome.dishwasher;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class DishwasherServiceImpl extends DishwasherServiceGrpc.DishwasherServiceImplBase {
    
    @Override
    public void turnOn(DishwasherTurnOnRequest request, StreamObserver<DishwasherTurnOnResponse> responseObserver) {
        DishwasherTurnOnResponse response = DishwasherTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Dishwasher turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void turnOff(DishwasherTurnOffRequest request, StreamObserver<DishwasherTurnOffResponse> responseObserver) {
        DishwasherTurnOffResponse response = DishwasherTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Dishwasher turned off")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void start(DishwasherStartRequest request, StreamObserver<DishwasherStartResponse> responseObserver) {
        DishwasherStartResponse response = DishwasherStartResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Dishwasher started")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void stop(DishwasherStopRequest request, StreamObserver<DishwasherStopResponse> responseObserver) {
        DishwasherStopResponse response = DishwasherStopResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Dishwasher stopped")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void setProgram(SetProgramRequest request, StreamObserver<SetProgramResponse> responseObserver) {
        SetProgramResponse response = SetProgramResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Program set to " + request.getProgram())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getStatus(DishwasherGetStatusRequest request, StreamObserver<DishwasherGetStatusResponse> responseObserver) {
        DishwasherGetStatusResponse response = DishwasherGetStatusResponse.newBuilder()
                .setIsRunning(false)
                .setCurrentProgram(DishwasherProgram.NORMAL)
                .setRemainingTime(0)
                .setDoorOpen(false)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}