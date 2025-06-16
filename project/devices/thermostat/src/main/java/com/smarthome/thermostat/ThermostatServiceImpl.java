package com.smarthome.thermostat;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class ThermostatServiceImpl extends ThermostatServiceGrpc.ThermostatServiceImplBase {
    
    @Override
    public void turnOn(ThermostatTurnOnRequest request, StreamObserver<ThermostatTurnOnResponse> responseObserver) {
        ThermostatTurnOnResponse response = ThermostatTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Thermostat turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void turnOff(ThermostatTurnOffRequest request, StreamObserver<ThermostatTurnOffResponse> responseObserver) {
        ThermostatTurnOffResponse response = ThermostatTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Thermostat turned off")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void setTargetTemperature(SetTargetTemperatureRequest request, StreamObserver<SetTargetTemperatureResponse> responseObserver) {
        SetTargetTemperatureResponse response = SetTargetTemperatureResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Target temperature set to " + request.getTargetTemperature() + "°C")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void setMode(SetModeRequest request, StreamObserver<SetModeResponse> responseObserver) {
        SetModeResponse response = SetModeResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Mode set to " + request.getMode())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getStatus(ThermostatGetStatusRequest request, StreamObserver<ThermostatGetStatusResponse> responseObserver) {
        ThermostatGetStatusResponse response = ThermostatGetStatusResponse.newBuilder()
                .setCurrentTemperature(22.5)
                .setTargetTemperature(24.0)
                .setMode(ThermostatMode.AUTO)
                .setHeating(false)
                .setCooling(false)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}