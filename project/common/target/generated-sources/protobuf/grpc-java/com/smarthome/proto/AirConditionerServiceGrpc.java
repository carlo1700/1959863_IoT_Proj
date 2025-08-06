package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Air Conditioner Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: airconditioner.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AirConditionerServiceGrpc {

  private AirConditionerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.AirConditionerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.AirConditionerTurnOnRequest,
      com.smarthome.proto.AirConditionerTurnOnResponse> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = com.smarthome.proto.AirConditionerTurnOnRequest.class,
      responseType = com.smarthome.proto.AirConditionerTurnOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.AirConditionerTurnOnRequest,
      com.smarthome.proto.AirConditionerTurnOnResponse> getTurnOnMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.AirConditionerTurnOnRequest, com.smarthome.proto.AirConditionerTurnOnResponse> getTurnOnMethod;
    if ((getTurnOnMethod = AirConditionerServiceGrpc.getTurnOnMethod) == null) {
      synchronized (AirConditionerServiceGrpc.class) {
        if ((getTurnOnMethod = AirConditionerServiceGrpc.getTurnOnMethod) == null) {
          AirConditionerServiceGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.AirConditionerTurnOnRequest, com.smarthome.proto.AirConditionerTurnOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.AirConditionerTurnOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.AirConditionerTurnOnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AirConditionerServiceMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.AirConditionerTurnOffRequest,
      com.smarthome.proto.AirConditionerTurnOffResponse> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = com.smarthome.proto.AirConditionerTurnOffRequest.class,
      responseType = com.smarthome.proto.AirConditionerTurnOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.AirConditionerTurnOffRequest,
      com.smarthome.proto.AirConditionerTurnOffResponse> getTurnOffMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.AirConditionerTurnOffRequest, com.smarthome.proto.AirConditionerTurnOffResponse> getTurnOffMethod;
    if ((getTurnOffMethod = AirConditionerServiceGrpc.getTurnOffMethod) == null) {
      synchronized (AirConditionerServiceGrpc.class) {
        if ((getTurnOffMethod = AirConditionerServiceGrpc.getTurnOffMethod) == null) {
          AirConditionerServiceGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.AirConditionerTurnOffRequest, com.smarthome.proto.AirConditionerTurnOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.AirConditionerTurnOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.AirConditionerTurnOffResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AirConditionerServiceMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetAirConditionerProgramRequest,
      com.smarthome.proto.SetAirConditionerProgramResponse> getSetProgramMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetProgram",
      requestType = com.smarthome.proto.SetAirConditionerProgramRequest.class,
      responseType = com.smarthome.proto.SetAirConditionerProgramResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetAirConditionerProgramRequest,
      com.smarthome.proto.SetAirConditionerProgramResponse> getSetProgramMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetAirConditionerProgramRequest, com.smarthome.proto.SetAirConditionerProgramResponse> getSetProgramMethod;
    if ((getSetProgramMethod = AirConditionerServiceGrpc.getSetProgramMethod) == null) {
      synchronized (AirConditionerServiceGrpc.class) {
        if ((getSetProgramMethod = AirConditionerServiceGrpc.getSetProgramMethod) == null) {
          AirConditionerServiceGrpc.getSetProgramMethod = getSetProgramMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetAirConditionerProgramRequest, com.smarthome.proto.SetAirConditionerProgramResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetProgram"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetAirConditionerProgramRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetAirConditionerProgramResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AirConditionerServiceMethodDescriptorSupplier("SetProgram"))
              .build();
        }
      }
    }
    return getSetProgramMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.AirConditionerGetStatusRequest,
      com.smarthome.proto.AirConditionerGetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.AirConditionerGetStatusRequest.class,
      responseType = com.smarthome.proto.AirConditionerGetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.AirConditionerGetStatusRequest,
      com.smarthome.proto.AirConditionerGetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.AirConditionerGetStatusRequest, com.smarthome.proto.AirConditionerGetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = AirConditionerServiceGrpc.getGetStatusMethod) == null) {
      synchronized (AirConditionerServiceGrpc.class) {
        if ((getGetStatusMethod = AirConditionerServiceGrpc.getGetStatusMethod) == null) {
          AirConditionerServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.AirConditionerGetStatusRequest, com.smarthome.proto.AirConditionerGetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.AirConditionerGetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.AirConditionerGetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AirConditionerServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AirConditionerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AirConditionerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AirConditionerServiceStub>() {
        @java.lang.Override
        public AirConditionerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AirConditionerServiceStub(channel, callOptions);
        }
      };
    return AirConditionerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AirConditionerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AirConditionerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AirConditionerServiceBlockingStub>() {
        @java.lang.Override
        public AirConditionerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AirConditionerServiceBlockingStub(channel, callOptions);
        }
      };
    return AirConditionerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AirConditionerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AirConditionerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AirConditionerServiceFutureStub>() {
        @java.lang.Override
        public AirConditionerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AirConditionerServiceFutureStub(channel, callOptions);
        }
      };
    return AirConditionerServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Air Conditioner Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(com.smarthome.proto.AirConditionerTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.AirConditionerTurnOnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(com.smarthome.proto.AirConditionerTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.AirConditionerTurnOffResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void setProgram(com.smarthome.proto.SetAirConditionerProgramRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetAirConditionerProgramResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetProgramMethod(), responseObserver);
    }

    /**
     */
    default void getStatus(com.smarthome.proto.AirConditionerGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.AirConditionerGetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AirConditionerService.
   * <pre>
   * Air Conditioner Service
   * </pre>
   */
  public static abstract class AirConditionerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AirConditionerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AirConditionerService.
   * <pre>
   * Air Conditioner Service
   * </pre>
   */
  public static final class AirConditionerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AirConditionerServiceStub> {
    private AirConditionerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirConditionerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AirConditionerServiceStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(com.smarthome.proto.AirConditionerTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.AirConditionerTurnOnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(com.smarthome.proto.AirConditionerTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.AirConditionerTurnOffResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setProgram(com.smarthome.proto.SetAirConditionerProgramRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetAirConditionerProgramResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetProgramMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.AirConditionerGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.AirConditionerGetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AirConditionerService.
   * <pre>
   * Air Conditioner Service
   * </pre>
   */
  public static final class AirConditionerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AirConditionerServiceBlockingStub> {
    private AirConditionerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirConditionerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AirConditionerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.AirConditionerTurnOnResponse turnOn(com.smarthome.proto.AirConditionerTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.AirConditionerTurnOffResponse turnOff(com.smarthome.proto.AirConditionerTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetAirConditionerProgramResponse setProgram(com.smarthome.proto.SetAirConditionerProgramRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetProgramMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.AirConditionerGetStatusResponse getStatus(com.smarthome.proto.AirConditionerGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AirConditionerService.
   * <pre>
   * Air Conditioner Service
   * </pre>
   */
  public static final class AirConditionerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AirConditionerServiceFutureStub> {
    private AirConditionerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirConditionerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AirConditionerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.AirConditionerTurnOnResponse> turnOn(
        com.smarthome.proto.AirConditionerTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.AirConditionerTurnOffResponse> turnOff(
        com.smarthome.proto.AirConditionerTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetAirConditionerProgramResponse> setProgram(
        com.smarthome.proto.SetAirConditionerProgramRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetProgramMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.AirConditionerGetStatusResponse> getStatus(
        com.smarthome.proto.AirConditionerGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TURN_ON = 0;
  private static final int METHODID_TURN_OFF = 1;
  private static final int METHODID_SET_PROGRAM = 2;
  private static final int METHODID_GET_STATUS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TURN_ON:
          serviceImpl.turnOn((com.smarthome.proto.AirConditionerTurnOnRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.AirConditionerTurnOnResponse>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((com.smarthome.proto.AirConditionerTurnOffRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.AirConditionerTurnOffResponse>) responseObserver);
          break;
        case METHODID_SET_PROGRAM:
          serviceImpl.setProgram((com.smarthome.proto.SetAirConditionerProgramRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetAirConditionerProgramResponse>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.proto.AirConditionerGetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.AirConditionerGetStatusResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getTurnOnMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.AirConditionerTurnOnRequest,
              com.smarthome.proto.AirConditionerTurnOnResponse>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.AirConditionerTurnOffRequest,
              com.smarthome.proto.AirConditionerTurnOffResponse>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getSetProgramMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetAirConditionerProgramRequest,
              com.smarthome.proto.SetAirConditionerProgramResponse>(
                service, METHODID_SET_PROGRAM)))
        .addMethod(
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.AirConditionerGetStatusRequest,
              com.smarthome.proto.AirConditionerGetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .build();
  }

  private static abstract class AirConditionerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AirConditionerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.AirConditionerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AirConditionerService");
    }
  }

  private static final class AirConditionerServiceFileDescriptorSupplier
      extends AirConditionerServiceBaseDescriptorSupplier {
    AirConditionerServiceFileDescriptorSupplier() {}
  }

  private static final class AirConditionerServiceMethodDescriptorSupplier
      extends AirConditionerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AirConditionerServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AirConditionerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AirConditionerServiceFileDescriptorSupplier())
              .addMethod(getTurnOnMethod())
              .addMethod(getTurnOffMethod())
              .addMethod(getSetProgramMethod())
              .addMethod(getGetStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
