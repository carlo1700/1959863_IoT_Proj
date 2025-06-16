package com.smarthome.solarpanel;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class SolarPanelServiceImpl extends SolarPanelServiceGrpc.SolarPanelServiceImplBase {
    
    @Override
    public void turnOn(SolarPanelTurnOnRequest request, StreamObserver<SolarPanelTurnOnResponse> responseObserver) {
        SolarPanelTurnOnResponse response = SolarPanelTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Solar panel system turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void turnOff(SolarPanelTurnOffRequest request, StreamObserver<SolarPanelTurnOffResponse> responseObserver) {
        SolarPanelTurnOffResponse response = SolarPanelTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Solar panel system turned off")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getStatus(SolarPanelGetStatusRequest request, StreamObserver<SolarPanelGetStatusResponse> responseObserver) {
        SolarPanelGetStatusResponse response = SolarPanelGetStatusResponse.newBuilder()
                .setIsOnline(true)
                .setCurrentPowerOutput(2500.0)
                .setDailyEnergyProduction(15.5)
                .setEfficiency(85.2)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getEnergyProduction(GetEnergyProductionRequest request, StreamObserver<GetEnergyProductionResponse> responseObserver) {
        GetEnergyProductionResponse response = GetEnergyProductionResponse.newBuilder()
                .setTotalEnergy(45.2)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}