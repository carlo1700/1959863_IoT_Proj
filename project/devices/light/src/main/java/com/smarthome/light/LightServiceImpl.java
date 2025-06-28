package com.smarthome.light;

import com.smarthome.proto.GetStatusRequest;
import com.smarthome.proto.GetStatusResponse;
import com.smarthome.proto.LightServiceGrpc;
import com.smarthome.proto.TurnOffRequest;
import com.smarthome.proto.TurnOffResponse;
import com.smarthome.proto.TurnOnRequest;
import com.smarthome.proto.TurnOnResponse;

import io.grpc.stub.StreamObserver;

public class LightServiceImpl extends LightServiceGrpc.LightServiceImplBase {

    private boolean isOn = false;
    
    @Override
    public synchronized void turnOn(TurnOnRequest request, StreamObserver<TurnOnResponse> responseObserver) {
        
        isOn = true;

        TurnOnResponse response = TurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Light turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public synchronized void turnOff(TurnOffRequest request, StreamObserver<TurnOffResponse> responseObserver) {

        isOn = false;

        TurnOffResponse response = TurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Light turned off")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    
    @Override
    public void getStatus(GetStatusRequest request, StreamObserver<GetStatusResponse> responseObserver) {
        GetStatusResponse response = GetStatusResponse.newBuilder()
                .setIsOn(isOn)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}