package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Dishwasher Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: dishwasher.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DishwasherServiceGrpc {

  private DishwasherServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.DishwasherService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherTurnOnRequest,
      com.smarthome.proto.DishwasherTurnOnResponse> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = com.smarthome.proto.DishwasherTurnOnRequest.class,
      responseType = com.smarthome.proto.DishwasherTurnOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherTurnOnRequest,
      com.smarthome.proto.DishwasherTurnOnResponse> getTurnOnMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherTurnOnRequest, com.smarthome.proto.DishwasherTurnOnResponse> getTurnOnMethod;
    if ((getTurnOnMethod = DishwasherServiceGrpc.getTurnOnMethod) == null) {
      synchronized (DishwasherServiceGrpc.class) {
        if ((getTurnOnMethod = DishwasherServiceGrpc.getTurnOnMethod) == null) {
          DishwasherServiceGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.DishwasherTurnOnRequest, com.smarthome.proto.DishwasherTurnOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherTurnOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherTurnOnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DishwasherServiceMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherTurnOffRequest,
      com.smarthome.proto.DishwasherTurnOffResponse> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = com.smarthome.proto.DishwasherTurnOffRequest.class,
      responseType = com.smarthome.proto.DishwasherTurnOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherTurnOffRequest,
      com.smarthome.proto.DishwasherTurnOffResponse> getTurnOffMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherTurnOffRequest, com.smarthome.proto.DishwasherTurnOffResponse> getTurnOffMethod;
    if ((getTurnOffMethod = DishwasherServiceGrpc.getTurnOffMethod) == null) {
      synchronized (DishwasherServiceGrpc.class) {
        if ((getTurnOffMethod = DishwasherServiceGrpc.getTurnOffMethod) == null) {
          DishwasherServiceGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.DishwasherTurnOffRequest, com.smarthome.proto.DishwasherTurnOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherTurnOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherTurnOffResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DishwasherServiceMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherStartRequest,
      com.smarthome.proto.DishwasherStartResponse> getStartMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Start",
      requestType = com.smarthome.proto.DishwasherStartRequest.class,
      responseType = com.smarthome.proto.DishwasherStartResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherStartRequest,
      com.smarthome.proto.DishwasherStartResponse> getStartMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherStartRequest, com.smarthome.proto.DishwasherStartResponse> getStartMethod;
    if ((getStartMethod = DishwasherServiceGrpc.getStartMethod) == null) {
      synchronized (DishwasherServiceGrpc.class) {
        if ((getStartMethod = DishwasherServiceGrpc.getStartMethod) == null) {
          DishwasherServiceGrpc.getStartMethod = getStartMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.DishwasherStartRequest, com.smarthome.proto.DishwasherStartResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Start"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherStartRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherStartResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DishwasherServiceMethodDescriptorSupplier("Start"))
              .build();
        }
      }
    }
    return getStartMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherStopRequest,
      com.smarthome.proto.DishwasherStopResponse> getStopMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Stop",
      requestType = com.smarthome.proto.DishwasherStopRequest.class,
      responseType = com.smarthome.proto.DishwasherStopResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherStopRequest,
      com.smarthome.proto.DishwasherStopResponse> getStopMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherStopRequest, com.smarthome.proto.DishwasherStopResponse> getStopMethod;
    if ((getStopMethod = DishwasherServiceGrpc.getStopMethod) == null) {
      synchronized (DishwasherServiceGrpc.class) {
        if ((getStopMethod = DishwasherServiceGrpc.getStopMethod) == null) {
          DishwasherServiceGrpc.getStopMethod = getStopMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.DishwasherStopRequest, com.smarthome.proto.DishwasherStopResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Stop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherStopRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherStopResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DishwasherServiceMethodDescriptorSupplier("Stop"))
              .build();
        }
      }
    }
    return getStopMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetProgramRequest,
      com.smarthome.proto.SetProgramResponse> getSetProgramMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetProgram",
      requestType = com.smarthome.proto.SetProgramRequest.class,
      responseType = com.smarthome.proto.SetProgramResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetProgramRequest,
      com.smarthome.proto.SetProgramResponse> getSetProgramMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetProgramRequest, com.smarthome.proto.SetProgramResponse> getSetProgramMethod;
    if ((getSetProgramMethod = DishwasherServiceGrpc.getSetProgramMethod) == null) {
      synchronized (DishwasherServiceGrpc.class) {
        if ((getSetProgramMethod = DishwasherServiceGrpc.getSetProgramMethod) == null) {
          DishwasherServiceGrpc.getSetProgramMethod = getSetProgramMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetProgramRequest, com.smarthome.proto.SetProgramResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetProgram"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetProgramRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetProgramResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DishwasherServiceMethodDescriptorSupplier("SetProgram"))
              .build();
        }
      }
    }
    return getSetProgramMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherGetStatusRequest,
      com.smarthome.proto.DishwasherGetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.DishwasherGetStatusRequest.class,
      responseType = com.smarthome.proto.DishwasherGetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherGetStatusRequest,
      com.smarthome.proto.DishwasherGetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.DishwasherGetStatusRequest, com.smarthome.proto.DishwasherGetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = DishwasherServiceGrpc.getGetStatusMethod) == null) {
      synchronized (DishwasherServiceGrpc.class) {
        if ((getGetStatusMethod = DishwasherServiceGrpc.getGetStatusMethod) == null) {
          DishwasherServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.DishwasherGetStatusRequest, com.smarthome.proto.DishwasherGetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherGetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.DishwasherGetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DishwasherServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DishwasherServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DishwasherServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DishwasherServiceStub>() {
        @java.lang.Override
        public DishwasherServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DishwasherServiceStub(channel, callOptions);
        }
      };
    return DishwasherServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DishwasherServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DishwasherServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DishwasherServiceBlockingStub>() {
        @java.lang.Override
        public DishwasherServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DishwasherServiceBlockingStub(channel, callOptions);
        }
      };
    return DishwasherServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DishwasherServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DishwasherServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DishwasherServiceFutureStub>() {
        @java.lang.Override
        public DishwasherServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DishwasherServiceFutureStub(channel, callOptions);
        }
      };
    return DishwasherServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Dishwasher Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(com.smarthome.proto.DishwasherTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherTurnOnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(com.smarthome.proto.DishwasherTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherTurnOffResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void start(com.smarthome.proto.DishwasherStartRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherStartResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStartMethod(), responseObserver);
    }

    /**
     */
    default void stop(com.smarthome.proto.DishwasherStopRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherStopResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStopMethod(), responseObserver);
    }

    /**
     */
    default void setProgram(com.smarthome.proto.SetProgramRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetProgramResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetProgramMethod(), responseObserver);
    }

    /**
     */
    default void getStatus(com.smarthome.proto.DishwasherGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherGetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DishwasherService.
   * <pre>
   * Dishwasher Service
   * </pre>
   */
  public static abstract class DishwasherServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DishwasherServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DishwasherService.
   * <pre>
   * Dishwasher Service
   * </pre>
   */
  public static final class DishwasherServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DishwasherServiceStub> {
    private DishwasherServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DishwasherServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DishwasherServiceStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(com.smarthome.proto.DishwasherTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherTurnOnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(com.smarthome.proto.DishwasherTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherTurnOffResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void start(com.smarthome.proto.DishwasherStartRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherStartResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStartMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stop(com.smarthome.proto.DishwasherStopRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherStopResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStopMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setProgram(com.smarthome.proto.SetProgramRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetProgramResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetProgramMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.DishwasherGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherGetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DishwasherService.
   * <pre>
   * Dishwasher Service
   * </pre>
   */
  public static final class DishwasherServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DishwasherServiceBlockingStub> {
    private DishwasherServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DishwasherServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DishwasherServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.DishwasherTurnOnResponse turnOn(com.smarthome.proto.DishwasherTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.DishwasherTurnOffResponse turnOff(com.smarthome.proto.DishwasherTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.DishwasherStartResponse start(com.smarthome.proto.DishwasherStartRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStartMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.DishwasherStopResponse stop(com.smarthome.proto.DishwasherStopRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStopMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetProgramResponse setProgram(com.smarthome.proto.SetProgramRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetProgramMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.DishwasherGetStatusResponse getStatus(com.smarthome.proto.DishwasherGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DishwasherService.
   * <pre>
   * Dishwasher Service
   * </pre>
   */
  public static final class DishwasherServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DishwasherServiceFutureStub> {
    private DishwasherServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DishwasherServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DishwasherServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.DishwasherTurnOnResponse> turnOn(
        com.smarthome.proto.DishwasherTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.DishwasherTurnOffResponse> turnOff(
        com.smarthome.proto.DishwasherTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.DishwasherStartResponse> start(
        com.smarthome.proto.DishwasherStartRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStartMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.DishwasherStopResponse> stop(
        com.smarthome.proto.DishwasherStopRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStopMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetProgramResponse> setProgram(
        com.smarthome.proto.SetProgramRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetProgramMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.DishwasherGetStatusResponse> getStatus(
        com.smarthome.proto.DishwasherGetStatusRequest request) {
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
          serviceImpl.turnOn((com.smarthome.proto.DishwasherTurnOnRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherTurnOnResponse>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((com.smarthome.proto.DishwasherTurnOffRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherTurnOffResponse>) responseObserver);
          break;
        case METHODID_START:
          serviceImpl.start((com.smarthome.proto.DishwasherStartRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherStartResponse>) responseObserver);
          break;
        case METHODID_STOP:
          serviceImpl.stop((com.smarthome.proto.DishwasherStopRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherStopResponse>) responseObserver);
          break;
        case METHODID_SET_PROGRAM:
          serviceImpl.setProgram((com.smarthome.proto.SetProgramRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetProgramResponse>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.proto.DishwasherGetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.DishwasherGetStatusResponse>) responseObserver);
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
              com.smarthome.proto.DishwasherTurnOnRequest,
              com.smarthome.proto.DishwasherTurnOnResponse>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.DishwasherTurnOffRequest,
              com.smarthome.proto.DishwasherTurnOffResponse>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getStartMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.DishwasherStartRequest,
              com.smarthome.proto.DishwasherStartResponse>(
                service, METHODID_START)))
        .addMethod(
          getStopMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.DishwasherStopRequest,
              com.smarthome.proto.DishwasherStopResponse>(
                service, METHODID_STOP)))
        .addMethod(
          getSetProgramMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetProgramRequest,
              com.smarthome.proto.SetProgramResponse>(
                service, METHODID_SET_PROGRAM)))
        .addMethod(
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.DishwasherGetStatusRequest,
              com.smarthome.proto.DishwasherGetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .build();
  }

  private static abstract class DishwasherServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DishwasherServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.DishwasherProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DishwasherService");
    }
  }

  private static final class DishwasherServiceFileDescriptorSupplier
      extends DishwasherServiceBaseDescriptorSupplier {
    DishwasherServiceFileDescriptorSupplier() {}
  }

  private static final class DishwasherServiceMethodDescriptorSupplier
      extends DishwasherServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    DishwasherServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (DishwasherServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DishwasherServiceFileDescriptorSupplier())
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
