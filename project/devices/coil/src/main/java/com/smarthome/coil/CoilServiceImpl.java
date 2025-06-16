package com.smarthome.coil;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class CoilServiceImpl extends CoilServiceGrpc.CoilServiceImplBase {
    
    @Override
    public void turnOn(CoilTurnOnRequest request, StreamObserver<CoilTurnOnResponse> responseObserver) {
        CoilTurnOnResponse response = CoilTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Coil turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void turnOff(CoilTurnOffRequest request, StreamObserver<CoilTurnOffResponse> responseObserver) {
        CoilTurnOffResponse response = CoilTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Coil turned off")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void setPowerLevel(SetPowerLevelRequest request, StreamObserver<SetPowerLevelResponse> responseObserver) {
        SetPowerLevelResponse response = SetPowerLevelResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Power level set to " + request.getPowerLevel())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getStatus(CoilGetStatusRequest request, StreamObserver<CoilGetStatusResponse> responseObserver) {
        CoilGetStatusResponse response = CoilGetStatusResponse.newBuilder()
                .setIsOn(false)
                .setPowerLevel(0)
                .setCurrentTemperature(20.0)
                .setPowerConsumption(0.0)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}