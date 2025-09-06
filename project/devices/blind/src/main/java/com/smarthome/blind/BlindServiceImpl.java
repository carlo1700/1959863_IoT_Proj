package com.smarthome.blind;

import com.smarthome.proto.*;
import io.grpc.stub.StreamObserver;

public class BlindServiceImpl extends BlindServiceGrpc.BlindServiceImplBase {

    // Stato attuale delle tapparelle: true = su, false = giù
    private boolean isUp = false;

    @Override
    public void setUp(BlindSetUpRequest request, StreamObserver<BlindSetUpResponse> responseObserver) {
        isUp = true;

        BlindSetUpResponse response = BlindSetUpResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Blind moved up")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setDown(BlindSetDownRequest request, StreamObserver<BlindSetDownResponse> responseObserver) {
        isUp = false;

        BlindSetDownResponse response = BlindSetDownResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Blind moved down")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getStatus(BlindGetStatusRequest request, StreamObserver<BlindGetStatusResponse> responseObserver) {
        BlindGetStatusResponse response = BlindGetStatusResponse.newBuilder()
                .setIsUp(isUp)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
