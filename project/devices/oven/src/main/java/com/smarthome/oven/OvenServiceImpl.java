package com.smarthome.oven;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class OvenServiceImpl extends OvenServiceGrpc.OvenServiceImplBase {
    
    @Override
    public void turnOn(OvenTurnOnRequest request, StreamObserver<OvenTurnOnResponse> responseObserver) {
        OvenTurnOnResponse response = OvenTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Oven turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void turnOff(OvenTurnOffRequest request, StreamObserver<OvenTurnOffResponse> responseObserver) {
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
    public void setTimer(SetTimerRequest request, StreamObserver<SetTimerResponse> responseObserver) {
        SetTimerResponse response = SetTimerResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Timer set to " + request.getMinutes() + " minutes")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getStatus(OvenGetStatusRequest request, StreamObserver<OvenGetStatusResponse> responseObserver) {
        OvenGetStatusResponse response = OvenGetStatusResponse.newBuilder()
                .setIsOn(false)
                .setTemperature(0)
                .setRemainingTime(0)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}