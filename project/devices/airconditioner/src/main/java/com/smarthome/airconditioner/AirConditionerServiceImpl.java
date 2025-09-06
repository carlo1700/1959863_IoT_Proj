package com.smarthome.airconditioner;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class AirConditionerServiceImpl extends AirConditionerServiceGrpc.AirConditionerServiceImplBase {

    private boolean isOn = false;
    private AirConditionerProgram currentProgram = AirConditionerProgram.AIR_PROGRAM_UNSPECIFIED;

    @Override
    public void turnOn(AirConditionerTurnOnRequest request, StreamObserver<AirConditionerTurnOnResponse> responseObserver) {
        isOn = true;
        
        
        AirConditionerTurnOnResponse response = AirConditionerTurnOnResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Air conditioner turned on")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void turnOff(AirConditionerTurnOffRequest request, StreamObserver<AirConditionerTurnOffResponse> responseObserver) {
        isOn = false;
        

        AirConditionerTurnOffResponse response = AirConditionerTurnOffResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Air conditioner turned off")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setProgram(SetAirConditionerProgramRequest request, StreamObserver<SetAirConditionerProgramResponse> responseObserver) {

        if (!isOn) {
            SetAirConditionerProgramResponse response = SetAirConditionerProgramResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Air conditioner is not turned on")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }
        else {
            this.currentProgram = request.getProgram();
            SetAirConditionerProgramResponse response = SetAirConditionerProgramResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Program set to " + currentProgram.name())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }
    }

    @Override
    public void getStatus(AirConditionerGetStatusRequest request, StreamObserver<AirConditionerGetStatusResponse> responseObserver) {
        AirConditionerGetStatusResponse response = AirConditionerGetStatusResponse.newBuilder()
                .setIsOn(isOn)
                .setCurrentProgram(currentProgram)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
