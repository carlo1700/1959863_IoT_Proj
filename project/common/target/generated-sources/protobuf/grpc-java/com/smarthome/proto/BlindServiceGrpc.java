package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Blind Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: blind.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BlindServiceGrpc {

  private BlindServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.BlindService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.BlindTurnOnRequest,
      com.smarthome.proto.BlindTurnOnResponse> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = com.smarthome.proto.BlindTurnOnRequest.class,
      responseType = com.smarthome.proto.BlindTurnOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.BlindTurnOnRequest,
      com.smarthome.proto.BlindTurnOnResponse> getTurnOnMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.BlindTurnOnRequest, com.smarthome.proto.BlindTurnOnResponse> getTurnOnMethod;
    if ((getTurnOnMethod = BlindServiceGrpc.getTurnOnMethod) == null) {
      synchronized (BlindServiceGrpc.class) {
        if ((getTurnOnMethod = BlindServiceGrpc.getTurnOnMethod) == null) {
          BlindServiceGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.BlindTurnOnRequest, com.smarthome.proto.BlindTurnOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindTurnOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindTurnOnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlindServiceMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.BlindTurnOffRequest,
      com.smarthome.proto.BlindTurnOffResponse> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = com.smarthome.proto.BlindTurnOffRequest.class,
      responseType = com.smarthome.proto.BlindTurnOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.BlindTurnOffRequest,
      com.smarthome.proto.BlindTurnOffResponse> getTurnOffMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.BlindTurnOffRequest, com.smarthome.proto.BlindTurnOffResponse> getTurnOffMethod;
    if ((getTurnOffMethod = BlindServiceGrpc.getTurnOffMethod) == null) {
      synchronized (BlindServiceGrpc.class) {
        if ((getTurnOffMethod = BlindServiceGrpc.getTurnOffMethod) == null) {
          BlindServiceGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.BlindTurnOffRequest, com.smarthome.proto.BlindTurnOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindTurnOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindTurnOffResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlindServiceMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.BlindOpenRequest,
      com.smarthome.proto.BlindOpenResponse> getOpenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Open",
      requestType = com.smarthome.proto.BlindOpenRequest.class,
      responseType = com.smarthome.proto.BlindOpenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.BlindOpenRequest,
      com.smarthome.proto.BlindOpenResponse> getOpenMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.BlindOpenRequest, com.smarthome.proto.BlindOpenResponse> getOpenMethod;
    if ((getOpenMethod = BlindServiceGrpc.getOpenMethod) == null) {
      synchronized (BlindServiceGrpc.class) {
        if ((getOpenMethod = BlindServiceGrpc.getOpenMethod) == null) {
          BlindServiceGrpc.getOpenMethod = getOpenMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.BlindOpenRequest, com.smarthome.proto.BlindOpenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Open"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindOpenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindOpenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlindServiceMethodDescriptorSupplier("Open"))
              .build();
        }
      }
    }
    return getOpenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.BlindCloseRequest,
      com.smarthome.proto.BlindCloseResponse> getCloseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Close",
      requestType = com.smarthome.proto.BlindCloseRequest.class,
      responseType = com.smarthome.proto.BlindCloseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.BlindCloseRequest,
      com.smarthome.proto.BlindCloseResponse> getCloseMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.BlindCloseRequest, com.smarthome.proto.BlindCloseResponse> getCloseMethod;
    if ((getCloseMethod = BlindServiceGrpc.getCloseMethod) == null) {
      synchronized (BlindServiceGrpc.class) {
        if ((getCloseMethod = BlindServiceGrpc.getCloseMethod) == null) {
          BlindServiceGrpc.getCloseMethod = getCloseMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.BlindCloseRequest, com.smarthome.proto.BlindCloseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Close"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindCloseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindCloseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlindServiceMethodDescriptorSupplier("Close"))
              .build();
        }
      }
    }
    return getCloseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetPositionRequest,
      com.smarthome.proto.SetPositionResponse> getSetPositionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetPosition",
      requestType = com.smarthome.proto.SetPositionRequest.class,
      responseType = com.smarthome.proto.SetPositionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetPositionRequest,
      com.smarthome.proto.SetPositionResponse> getSetPositionMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetPositionRequest, com.smarthome.proto.SetPositionResponse> getSetPositionMethod;
    if ((getSetPositionMethod = BlindServiceGrpc.getSetPositionMethod) == null) {
      synchronized (BlindServiceGrpc.class) {
        if ((getSetPositionMethod = BlindServiceGrpc.getSetPositionMethod) == null) {
          BlindServiceGrpc.getSetPositionMethod = getSetPositionMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetPositionRequest, com.smarthome.proto.SetPositionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetPosition"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetPositionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetPositionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlindServiceMethodDescriptorSupplier("SetPosition"))
              .build();
        }
      }
    }
    return getSetPositionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.BlindGetStatusRequest,
      com.smarthome.proto.BlindGetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.BlindGetStatusRequest.class,
      responseType = com.smarthome.proto.BlindGetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.BlindGetStatusRequest,
      com.smarthome.proto.BlindGetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.BlindGetStatusRequest, com.smarthome.proto.BlindGetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = BlindServiceGrpc.getGetStatusMethod) == null) {
      synchronized (BlindServiceGrpc.class) {
        if ((getGetStatusMethod = BlindServiceGrpc.getGetStatusMethod) == null) {
          BlindServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.BlindGetStatusRequest, com.smarthome.proto.BlindGetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindGetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindGetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlindServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BlindServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlindServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlindServiceStub>() {
        @java.lang.Override
        public BlindServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlindServiceStub(channel, callOptions);
        }
      };
    return BlindServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BlindServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlindServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlindServiceBlockingStub>() {
        @java.lang.Override
        public BlindServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlindServiceBlockingStub(channel, callOptions);
        }
      };
    return BlindServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BlindServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlindServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlindServiceFutureStub>() {
        @java.lang.Override
        public BlindServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlindServiceFutureStub(channel, callOptions);
        }
      };
    return BlindServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Blind Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(com.smarthome.proto.BlindTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindTurnOnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(com.smarthome.proto.BlindTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindTurnOffResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void open(com.smarthome.proto.BlindOpenRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindOpenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOpenMethod(), responseObserver);
    }

    /**
     */
    default void close(com.smarthome.proto.BlindCloseRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindCloseResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCloseMethod(), responseObserver);
    }

    /**
     */
    default void setPosition(com.smarthome.proto.SetPositionRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetPositionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetPositionMethod(), responseObserver);
    }

    /**
     */
    default void getStatus(com.smarthome.proto.BlindGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindGetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BlindService.
   * <pre>
   * Blind Service
   * </pre>
   */
  public static abstract class BlindServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BlindServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BlindService.
   * <pre>
   * Blind Service
   * </pre>
   */
  public static final class BlindServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BlindServiceStub> {
    private BlindServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlindServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlindServiceStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(com.smarthome.proto.BlindTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindTurnOnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(com.smarthome.proto.BlindTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindTurnOffResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void open(com.smarthome.proto.BlindOpenRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindOpenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOpenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void close(com.smarthome.proto.BlindCloseRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindCloseResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setPosition(com.smarthome.proto.SetPositionRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetPositionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetPositionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.BlindGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindGetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BlindService.
   * <pre>
   * Blind Service
   * </pre>
   */
  public static final class BlindServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BlindServiceBlockingStub> {
    private BlindServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlindServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlindServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.BlindTurnOnResponse turnOn(com.smarthome.proto.BlindTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.BlindTurnOffResponse turnOff(com.smarthome.proto.BlindTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.BlindOpenResponse open(com.smarthome.proto.BlindOpenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOpenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.BlindCloseResponse close(com.smarthome.proto.BlindCloseRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetPositionResponse setPosition(com.smarthome.proto.SetPositionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetPositionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.BlindGetStatusResponse getStatus(com.smarthome.proto.BlindGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BlindService.
   * <pre>
   * Blind Service
   * </pre>
   */
  public static final class BlindServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BlindServiceFutureStub> {
    private BlindServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlindServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlindServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.BlindTurnOnResponse> turnOn(
        com.smarthome.proto.BlindTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.BlindTurnOffResponse> turnOff(
        com.smarthome.proto.BlindTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.BlindOpenResponse> open(
        com.smarthome.proto.BlindOpenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOpenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.BlindCloseResponse> close(
        com.smarthome.proto.BlindCloseRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetPositionResponse> setPosition(
        com.smarthome.proto.SetPositionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetPositionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.BlindGetStatusResponse> getStatus(
        com.smarthome.proto.BlindGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TURN_ON = 0;
  private static final int METHODID_TURN_OFF = 1;
  private static final int METHODID_OPEN = 2;
  private static final int METHODID_CLOSE = 3;
  private static final int METHODID_SET_POSITION = 4;
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
          serviceImpl.turnOn((com.smarthome.proto.BlindTurnOnRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.BlindTurnOnResponse>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((com.smarthome.proto.BlindTurnOffRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.BlindTurnOffResponse>) responseObserver);
          break;
        case METHODID_OPEN:
          serviceImpl.open((com.smarthome.proto.BlindOpenRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.BlindOpenResponse>) responseObserver);
          break;
        case METHODID_CLOSE:
          serviceImpl.close((com.smarthome.proto.BlindCloseRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.BlindCloseResponse>) responseObserver);
          break;
        case METHODID_SET_POSITION:
          serviceImpl.setPosition((com.smarthome.proto.SetPositionRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetPositionResponse>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.proto.BlindGetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.BlindGetStatusResponse>) responseObserver);
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
              com.smarthome.proto.BlindTurnOnRequest,
              com.smarthome.proto.BlindTurnOnResponse>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.BlindTurnOffRequest,
              com.smarthome.proto.BlindTurnOffResponse>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getOpenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.BlindOpenRequest,
              com.smarthome.proto.BlindOpenResponse>(
                service, METHODID_OPEN)))
        .addMethod(
          getCloseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.BlindCloseRequest,
              com.smarthome.proto.BlindCloseResponse>(
                service, METHODID_CLOSE)))
        .addMethod(
          getSetPositionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetPositionRequest,
              com.smarthome.proto.SetPositionResponse>(
                service, METHODID_SET_POSITION)))
        .addMethod(
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.BlindGetStatusRequest,
              com.smarthome.proto.BlindGetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .build();
  }

  private static abstract class BlindServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BlindServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.BlindProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BlindService");
    }
  }

  private static final class BlindServiceFileDescriptorSupplier
      extends BlindServiceBaseDescriptorSupplier {
    BlindServiceFileDescriptorSupplier() {}
  }

  private static final class BlindServiceMethodDescriptorSupplier
      extends BlindServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BlindServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (BlindServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BlindServiceFileDescriptorSupplier())
              .addMethod(getTurnOnMethod())
              .addMethod(getTurnOffMethod())
              .addMethod(getOpenMethod())
              .addMethod(getCloseMethod())
              .addMethod(getSetPositionMethod())
              .addMethod(getGetStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
