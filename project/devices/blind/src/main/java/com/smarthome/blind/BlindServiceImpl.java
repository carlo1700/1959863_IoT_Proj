package com.smarthome.blind;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class BlindServiceImpl extends BlindServiceGrpc.BlindServiceImplBase {
    
    @Override
    public void turnOn(BlindTurnOnRequest request, StreamObserver<BlindTurnOnResponse> responseObserver) {
        BlindTurnOnResponse response = BlindTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Blind motor turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void turnOff(BlindTurnOffRequest request, StreamObserver<BlindTurnOffResponse> responseObserver) {
        BlindTurnOffResponse response = BlindTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Blind motor turned off")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void open(BlindOpenRequest request, StreamObserver<BlindOpenResponse> responseObserver) {
        BlindOpenResponse response = BlindOpenResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Blind opened")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void close(BlindCloseRequest request, StreamObserver<BlindCloseResponse> responseObserver) {
        BlindCloseResponse response = BlindCloseResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Blind closed")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void setPosition(SetPositionRequest request, StreamObserver<SetPositionResponse> responseObserver) {
        SetPositionResponse response = SetPositionResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Position set to " + request.getPosition() + "%")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getStatus(BlindGetStatusRequest request, StreamObserver<BlindGetStatusResponse> responseObserver) {
        BlindGetStatusResponse response = BlindGetStatusResponse.newBuilder()
                .setPosition(50)
                .setIsMoving(false)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}