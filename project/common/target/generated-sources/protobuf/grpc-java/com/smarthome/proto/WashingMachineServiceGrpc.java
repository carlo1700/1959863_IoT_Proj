package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Washing Machine Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: washingmachine.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class WashingMachineServiceGrpc {

  private WashingMachineServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.WashingMachineService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineTurnOnRequest,
      com.smarthome.proto.WashingMachineTurnOnResponse> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = com.smarthome.proto.WashingMachineTurnOnRequest.class,
      responseType = com.smarthome.proto.WashingMachineTurnOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineTurnOnRequest,
      com.smarthome.proto.WashingMachineTurnOnResponse> getTurnOnMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineTurnOnRequest, com.smarthome.proto.WashingMachineTurnOnResponse> getTurnOnMethod;
    if ((getTurnOnMethod = WashingMachineServiceGrpc.getTurnOnMethod) == null) {
      synchronized (WashingMachineServiceGrpc.class) {
        if ((getTurnOnMethod = WashingMachineServiceGrpc.getTurnOnMethod) == null) {
          WashingMachineServiceGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.WashingMachineTurnOnRequest, com.smarthome.proto.WashingMachineTurnOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineTurnOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineTurnOnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WashingMachineServiceMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineTurnOffRequest,
      com.smarthome.proto.WashingMachineTurnOffResponse> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = com.smarthome.proto.WashingMachineTurnOffRequest.class,
      responseType = com.smarthome.proto.WashingMachineTurnOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineTurnOffRequest,
      com.smarthome.proto.WashingMachineTurnOffResponse> getTurnOffMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineTurnOffRequest, com.smarthome.proto.WashingMachineTurnOffResponse> getTurnOffMethod;
    if ((getTurnOffMethod = WashingMachineServiceGrpc.getTurnOffMethod) == null) {
      synchronized (WashingMachineServiceGrpc.class) {
        if ((getTurnOffMethod = WashingMachineServiceGrpc.getTurnOffMethod) == null) {
          WashingMachineServiceGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.WashingMachineTurnOffRequest, com.smarthome.proto.WashingMachineTurnOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineTurnOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineTurnOffResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WashingMachineServiceMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineStartRequest,
      com.smarthome.proto.WashingMachineStartResponse> getStartMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Start",
      requestType = com.smarthome.proto.WashingMachineStartRequest.class,
      responseType = com.smarthome.proto.WashingMachineStartResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineStartRequest,
      com.smarthome.proto.WashingMachineStartResponse> getStartMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineStartRequest, com.smarthome.proto.WashingMachineStartResponse> getStartMethod;
    if ((getStartMethod = WashingMachineServiceGrpc.getStartMethod) == null) {
      synchronized (WashingMachineServiceGrpc.class) {
        if ((getStartMethod = WashingMachineServiceGrpc.getStartMethod) == null) {
          WashingMachineServiceGrpc.getStartMethod = getStartMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.WashingMachineStartRequest, com.smarthome.proto.WashingMachineStartResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Start"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineStartRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineStartResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WashingMachineServiceMethodDescriptorSupplier("Start"))
              .build();
        }
      }
    }
    return getStartMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineStopRequest,
      com.smarthome.proto.WashingMachineStopResponse> getStopMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Stop",
      requestType = com.smarthome.proto.WashingMachineStopRequest.class,
      responseType = com.smarthome.proto.WashingMachineStopResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineStopRequest,
      com.smarthome.proto.WashingMachineStopResponse> getStopMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineStopRequest, com.smarthome.proto.WashingMachineStopResponse> getStopMethod;
    if ((getStopMethod = WashingMachineServiceGrpc.getStopMethod) == null) {
      synchronized (WashingMachineServiceGrpc.class) {
        if ((getStopMethod = WashingMachineServiceGrpc.getStopMethod) == null) {
          WashingMachineServiceGrpc.getStopMethod = getStopMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.WashingMachineStopRequest, com.smarthome.proto.WashingMachineStopResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Stop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineStopRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineStopResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WashingMachineServiceMethodDescriptorSupplier("Stop"))
              .build();
        }
      }
    }
    return getStopMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineSetProgramRequest,
      com.smarthome.proto.WashingMachineSetProgramResponse> getSetProgramMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetProgram",
      requestType = com.smarthome.proto.WashingMachineSetProgramRequest.class,
      responseType = com.smarthome.proto.WashingMachineSetProgramResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineSetProgramRequest,
      com.smarthome.proto.WashingMachineSetProgramResponse> getSetProgramMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineSetProgramRequest, com.smarthome.proto.WashingMachineSetProgramResponse> getSetProgramMethod;
    if ((getSetProgramMethod = WashingMachineServiceGrpc.getSetProgramMethod) == null) {
      synchronized (WashingMachineServiceGrpc.class) {
        if ((getSetProgramMethod = WashingMachineServiceGrpc.getSetProgramMethod) == null) {
          WashingMachineServiceGrpc.getSetProgramMethod = getSetProgramMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.WashingMachineSetProgramRequest, com.smarthome.proto.WashingMachineSetProgramResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetProgram"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineSetProgramRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineSetProgramResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WashingMachineServiceMethodDescriptorSupplier("SetProgram"))
              .build();
        }
      }
    }
    return getSetProgramMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineGetStatusRequest,
      com.smarthome.proto.WashingMachineGetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.WashingMachineGetStatusRequest.class,
      responseType = com.smarthome.proto.WashingMachineGetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineGetStatusRequest,
      com.smarthome.proto.WashingMachineGetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.WashingMachineGetStatusRequest, com.smarthome.proto.WashingMachineGetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = WashingMachineServiceGrpc.getGetStatusMethod) == null) {
      synchronized (WashingMachineServiceGrpc.class) {
        if ((getGetStatusMethod = WashingMachineServiceGrpc.getGetStatusMethod) == null) {
          WashingMachineServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.WashingMachineGetStatusRequest, com.smarthome.proto.WashingMachineGetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineGetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.WashingMachineGetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WashingMachineServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WashingMachineServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WashingMachineServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WashingMachineServiceStub>() {
        @java.lang.Override
        public WashingMachineServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WashingMachineServiceStub(channel, callOptions);
        }
      };
    return WashingMachineServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WashingMachineServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WashingMachineServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WashingMachineServiceBlockingStub>() {
        @java.lang.Override
        public WashingMachineServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WashingMachineServiceBlockingStub(channel, callOptions);
        }
      };
    return WashingMachineServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WashingMachineServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WashingMachineServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WashingMachineServiceFutureStub>() {
        @java.lang.Override
        public WashingMachineServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WashingMachineServiceFutureStub(channel, callOptions);
        }
      };
    return WashingMachineServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Washing Machine Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(com.smarthome.proto.WashingMachineTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineTurnOnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(com.smarthome.proto.WashingMachineTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineTurnOffResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void start(com.smarthome.proto.WashingMachineStartRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineStartResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStartMethod(), responseObserver);
    }

    /**
     */
    default void stop(com.smarthome.proto.WashingMachineStopRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineStopResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStopMethod(), responseObserver);
    }

    /**
     */
    default void setProgram(com.smarthome.proto.WashingMachineSetProgramRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineSetProgramResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetProgramMethod(), responseObserver);
    }

    /**
     */
    default void getStatus(com.smarthome.proto.WashingMachineGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineGetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service WashingMachineService.
   * <pre>
   * Washing Machine Service
   * </pre>
   */
  public static abstract class WashingMachineServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return WashingMachineServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service WashingMachineService.
   * <pre>
   * Washing Machine Service
   * </pre>
   */
  public static final class WashingMachineServiceStub
      extends io.grpc.stub.AbstractAsyncStub<WashingMachineServiceStub> {
    private WashingMachineServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WashingMachineServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WashingMachineServiceStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(com.smarthome.proto.WashingMachineTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineTurnOnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(com.smarthome.proto.WashingMachineTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineTurnOffResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void start(com.smarthome.proto.WashingMachineStartRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineStartResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStartMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stop(com.smarthome.proto.WashingMachineStopRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineStopResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStopMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setProgram(com.smarthome.proto.WashingMachineSetProgramRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineSetProgramResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetProgramMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.WashingMachineGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineGetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service WashingMachineService.
   * <pre>
   * Washing Machine Service
   * </pre>
   */
  public static final class WashingMachineServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<WashingMachineServiceBlockingStub> {
    private WashingMachineServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WashingMachineServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WashingMachineServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.WashingMachineTurnOnResponse turnOn(com.smarthome.proto.WashingMachineTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.WashingMachineTurnOffResponse turnOff(com.smarthome.proto.WashingMachineTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.WashingMachineStartResponse start(com.smarthome.proto.WashingMachineStartRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStartMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.WashingMachineStopResponse stop(com.smarthome.proto.WashingMachineStopRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStopMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.WashingMachineSetProgramResponse setProgram(com.smarthome.proto.WashingMachineSetProgramRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetProgramMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.WashingMachineGetStatusResponse getStatus(com.smarthome.proto.WashingMachineGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service WashingMachineService.
   * <pre>
   * Washing Machine Service
   * </pre>
   */
  public static final class WashingMachineServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<WashingMachineServiceFutureStub> {
    private WashingMachineServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WashingMachineServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WashingMachineServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.WashingMachineTurnOnResponse> turnOn(
        com.smarthome.proto.WashingMachineTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.WashingMachineTurnOffResponse> turnOff(
        com.smarthome.proto.WashingMachineTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.WashingMachineStartResponse> start(
        com.smarthome.proto.WashingMachineStartRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStartMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.WashingMachineStopResponse> stop(
        com.smarthome.proto.WashingMachineStopRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStopMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.WashingMachineSetProgramResponse> setProgram(
        com.smarthome.proto.WashingMachineSetProgramRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetProgramMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.WashingMachineGetStatusResponse> getStatus(
        com.smarthome.proto.WashingMachineGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TURN_ON = 0;
  private static final int METHODID_TURN_OFF = 1;
  private static final int METHODID_START = 2;
  private static final int METHODID_STOP = 3;
  private static final int METHODID_SET_PROGRAM = 4;
  private static final int METHODID_GET_STATUS = 5;

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
          serviceImpl.turnOn((com.smarthome.proto.WashingMachineTurnOnRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineTurnOnResponse>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((com.smarthome.proto.WashingMachineTurnOffRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineTurnOffResponse>) responseObserver);
          break;
        case METHODID_START:
          serviceImpl.start((com.smarthome.proto.WashingMachineStartRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineStartResponse>) responseObserver);
          break;
        case METHODID_STOP:
          serviceImpl.stop((com.smarthome.proto.WashingMachineStopRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineStopResponse>) responseObserver);
          break;
        case METHODID_SET_PROGRAM:
          serviceImpl.setProgram((com.smarthome.proto.WashingMachineSetProgramRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineSetProgramResponse>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.proto.WashingMachineGetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.WashingMachineGetStatusResponse>) responseObserver);
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
              com.smarthome.proto.WashingMachineTurnOnRequest,
              com.smarthome.proto.WashingMachineTurnOnResponse>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.WashingMachineTurnOffRequest,
              com.smarthome.proto.WashingMachineTurnOffResponse>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getStartMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.WashingMachineStartRequest,
              com.smarthome.proto.WashingMachineStartResponse>(
                service, METHODID_START)))
        .addMethod(
          getStopMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.WashingMachineStopRequest,
              com.smarthome.proto.WashingMachineStopResponse>(
                service, METHODID_STOP)))
        .addMethod(
          getSetProgramMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.WashingMachineSetProgramRequest,
              com.smarthome.proto.WashingMachineSetProgramResponse>(
                service, METHODID_SET_PROGRAM)))
        .addMethod(
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.WashingMachineGetStatusRequest,
              com.smarthome.proto.WashingMachineGetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .build();
  }

  private static abstract class WashingMachineServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WashingMachineServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.WashingMachineProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WashingMachineService");
    }
  }

  private static final class WashingMachineServiceFileDescriptorSupplier
      extends WashingMachineServiceBaseDescriptorSupplier {
    WashingMachineServiceFileDescriptorSupplier() {}
  }

  private static final class WashingMachineServiceMethodDescriptorSupplier
      extends WashingMachineServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    WashingMachineServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (WashingMachineServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WashingMachineServiceFileDescriptorSupplier())
              .addMethod(getTurnOnMethod())
              .addMethod(getTurnOffMethod())
              .addMethod(getStartMethod())
              .addMethod(getStopMethod())
              .addMethod(getSetProgramMethod())
              .addMethod(getGetStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
