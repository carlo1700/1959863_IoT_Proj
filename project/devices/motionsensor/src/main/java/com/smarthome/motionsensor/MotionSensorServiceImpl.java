package com.smarthome.motionsensor;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class MotionSensorServiceImpl extends MotionSensorServiceGrpc.MotionSensorServiceImplBase {

    private boolean isOn = false; // 🔑 stato del sensore (spento di default)

    @Override
    public void turnOn(MotionSensorTurnOnRequest request, StreamObserver<MotionSensorTurnOnResponse> responseObserver) {
        isOn = true;

        MotionSensorTurnOnResponse response = MotionSensorTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Motion sensor turned on")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void turnOff(MotionSensorTurnOffRequest request,
                        StreamObserver<MotionSensorTurnOffResponse> responseObserver) {
        isOn = false;

        MotionSensorTurnOffResponse response = MotionSensorTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Motion sensor turned off")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getStatus(MotionSensorGetStatusRequest request,
                          StreamObserver<MotionSensorGetStatusResponse> responseObserver) {
        
        if (isOn == false) {
            MotionSensorGetStatusResponse response = MotionSensorGetStatusResponse.newBuilder()
                    .setMotionDetected(false)
                    .setIsOn(false) // ✅ sensore spento
                    .build();

            System.out.println("📡 MotionSensor status: isOn=false, motionDetected=false");

            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        boolean motionDetected = Math.random() < 0.5;

        MotionSensorGetStatusResponse response = MotionSensorGetStatusResponse.newBuilder()
                .setMotionDetected(motionDetected)
                .setIsOn(isOn)
                .build();

        System.out.println("📡 MotionSensor status: isOn=" + isOn + ", motionDetected=" + motionDetected);

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
