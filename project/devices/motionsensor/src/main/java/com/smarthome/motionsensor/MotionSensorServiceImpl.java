package com.smarthome.motionsensor;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class MotionSensorServiceImpl extends MotionSensorServiceGrpc.MotionSensorServiceImplBase {
    
    @Override
    public void turnOn(MotionSensorTurnOnRequest request, StreamObserver<MotionSensorTurnOnResponse> responseObserver) {
        MotionSensorTurnOnResponse response = MotionSensorTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Motion sensor turned on")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void turnOff(MotionSensorTurnOffRequest request, StreamObserver<MotionSensorTurnOffResponse> responseObserver) {
        MotionSensorTurnOffResponse response = MotionSensorTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Motion sensor turned off")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void getStatus(MotionSensorGetStatusRequest request, StreamObserver<MotionSensorGetStatusResponse> responseObserver) {
        boolean motionDetected = Math.random() < 0.5;
        MotionSensorGetStatusResponse response = MotionSensorGetStatusResponse.newBuilder()
                .setMotionDetected(motionDetected)
                .setLastMotionTime(System.currentTimeMillis())
                .setSensitivity(5)
                .build();
        
        System.out.println("📡 MotionSensor status: motionDetected=" + motionDetected);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void setSensitivity(SetSensitivityRequest request, StreamObserver<SetSensitivityResponse> responseObserver) {
        SetSensitivityResponse response = SetSensitivityResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Sensitivity set to " + request.getSensitivity())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}