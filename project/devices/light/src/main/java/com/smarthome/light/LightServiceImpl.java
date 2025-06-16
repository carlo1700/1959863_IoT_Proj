package com.smarthome.light;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class LightServiceImpl extends LightServiceGrpc.LightServiceImplBase {
    
    @Override
    public void turnOn(TurnOnRequest request, StreamObserver<TurnOnResponse> responseObserver) {
        TurnOnResponse response = TurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Light turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void turnOff(TurnOffRequest request, StreamObserver<TurnOffResponse> responseObserver) {
        TurnOffResponse response = TurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Light turned off")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void setBrightness(SetBrightnessRequest request, StreamObserver<SetBrightnessResponse> responseObserver) {
        SetBrightnessResponse response = SetBrightnessResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Brightness set to " + request.getBrightness())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void setColor(SetColorRequest request, StreamObserver<SetColorResponse> responseObserver) {
        SetColorResponse response = SetColorResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Color set to RGB(" + request.getRed() + "," + request.getGreen() + "," + request.getBlue() + ")")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getStatus(GetStatusRequest request, StreamObserver<GetStatusResponse> responseObserver) {
        GetStatusResponse response = GetStatusResponse.newBuilder()
                .setIsOn(true)
                .setBrightness(80)
                .setRed(255)
                .setGreen(255)
                .setBlue(255)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}