package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Light Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: light.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class LightServiceGrpc {

  private LightServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.LightService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.TurnOnRequest,
      com.smarthome.proto.TurnOnResponse> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = com.smarthome.proto.TurnOnRequest.class,
      responseType = com.smarthome.proto.TurnOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.TurnOnRequest,
      com.smarthome.proto.TurnOnResponse> getTurnOnMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.TurnOnRequest, com.smarthome.proto.TurnOnResponse> getTurnOnMethod;
    if ((getTurnOnMethod = LightServiceGrpc.getTurnOnMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getTurnOnMethod = LightServiceGrpc.getTurnOnMethod) == null) {
          LightServiceGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.TurnOnRequest, com.smarthome.proto.TurnOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.TurnOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.TurnOnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.TurnOffRequest,
      com.smarthome.proto.TurnOffResponse> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = com.smarthome.proto.TurnOffRequest.class,
      responseType = com.smarthome.proto.TurnOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.TurnOffRequest,
      com.smarthome.proto.TurnOffResponse> getTurnOffMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.TurnOffRequest, com.smarthome.proto.TurnOffResponse> getTurnOffMethod;
    if ((getTurnOffMethod = LightServiceGrpc.getTurnOffMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getTurnOffMethod = LightServiceGrpc.getTurnOffMethod) == null) {
          LightServiceGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.TurnOffRequest, com.smarthome.proto.TurnOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.TurnOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.TurnOffResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetBrightnessRequest,
      com.smarthome.proto.SetBrightnessResponse> getSetBrightnessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetBrightness",
      requestType = com.smarthome.proto.SetBrightnessRequest.class,
      responseType = com.smarthome.proto.SetBrightnessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetBrightnessRequest,
      com.smarthome.proto.SetBrightnessResponse> getSetBrightnessMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetBrightnessRequest, com.smarthome.proto.SetBrightnessResponse> getSetBrightnessMethod;
    if ((getSetBrightnessMethod = LightServiceGrpc.getSetBrightnessMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getSetBrightnessMethod = LightServiceGrpc.getSetBrightnessMethod) == null) {
          LightServiceGrpc.getSetBrightnessMethod = getSetBrightnessMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetBrightnessRequest, com.smarthome.proto.SetBrightnessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetBrightness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetBrightnessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetBrightnessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("SetBrightness"))
              .build();
        }
      }
    }
    return getSetBrightnessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetColorRequest,
      com.smarthome.proto.SetColorResponse> getSetColorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetColor",
      requestType = com.smarthome.proto.SetColorRequest.class,
      responseType = com.smarthome.proto.SetColorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetColorRequest,
      com.smarthome.proto.SetColorResponse> getSetColorMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetColorRequest, com.smarthome.proto.SetColorResponse> getSetColorMethod;
    if ((getSetColorMethod = LightServiceGrpc.getSetColorMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getSetColorMethod = LightServiceGrpc.getSetColorMethod) == null) {
          LightServiceGrpc.getSetColorMethod = getSetColorMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetColorRequest, com.smarthome.proto.SetColorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetColor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetColorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetColorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("SetColor"))
              .build();
        }
      }
    }
    return getSetColorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.GetStatusRequest,
      com.smarthome.proto.GetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.GetStatusRequest.class,
      responseType = com.smarthome.proto.GetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.GetStatusRequest,
      com.smarthome.proto.GetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.GetStatusRequest, com.smarthome.proto.GetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = LightServiceGrpc.getGetStatusMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getGetStatusMethod = LightServiceGrpc.getGetStatusMethod) == null) {
          LightServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.GetStatusRequest, com.smarthome.proto.GetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.GetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.GetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LightServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LightServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LightServiceStub>() {
        @java.lang.Override
        public LightServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LightServiceStub(channel, callOptions);
        }
      };
    return LightServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LightServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LightServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LightServiceBlockingStub>() {
        @java.lang.Override
        public LightServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LightServiceBlockingStub(channel, callOptions);
        }
      };
    return LightServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LightServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LightServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LightServiceFutureStub>() {
        @java.lang.Override
        public LightServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LightServiceFutureStub(channel, callOptions);
        }
      };
    return LightServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Light Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(com.smarthome.proto.TurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.TurnOnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(com.smarthome.proto.TurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.TurnOffResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void setBrightness(com.smarthome.proto.SetBrightnessRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetBrightnessResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetBrightnessMethod(), responseObserver);
    }

    /**
     */
    default void setColor(com.smarthome.proto.SetColorRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetColorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetColorMethod(), responseObserver);
    }

    /**
     */
    default void getStatus(com.smarthome.proto.GetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.GetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service LightService.
   * <pre>
   * Light Service
   * </pre>
   */
  public static abstract class LightServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return LightServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service LightService.
   * <pre>
   * Light Service
   * </pre>
   */
  public static final class LightServiceStub
      extends io.grpc.stub.AbstractAsyncStub<LightServiceStub> {
    private LightServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LightServiceStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(com.smarthome.proto.TurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.TurnOnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(com.smarthome.proto.TurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.TurnOffResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setBrightness(com.smarthome.proto.SetBrightnessRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetBrightnessResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetBrightnessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setColor(com.smarthome.proto.SetColorRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetColorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetColorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.GetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.GetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service LightService.
   * <pre>
   * Light Service
   * </pre>
   */
  public static final class LightServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<LightServiceBlockingStub> {
    private LightServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LightServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.TurnOnResponse turnOn(com.smarthome.proto.TurnOnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.TurnOffResponse turnOff(com.smarthome.proto.TurnOffRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetBrightnessResponse setBrightness(com.smarthome.proto.SetBrightnessRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetBrightnessMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetColorResponse setColor(com.smarthome.proto.SetColorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetColorMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.GetStatusResponse getStatus(com.smarthome.proto.GetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service LightService.
   * <pre>
   * Light Service
   * </pre>
   */
  public static final class LightServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<LightServiceFutureStub> {
    private LightServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LightServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.TurnOnResponse> turnOn(
        com.smarthome.proto.TurnOnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.TurnOffResponse> turnOff(
        com.smarthome.proto.TurnOffRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetBrightnessResponse> setBrightness(
        com.smarthome.proto.SetBrightnessRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetBrightnessMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetColorResponse> setColor(
        com.smarthome.proto.SetColorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetColorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.GetStatusResponse> getStatus(
        com.smarthome.proto.GetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TURN_ON = 0;
  private static final int METHODID_TURN_OFF = 1;
  private static final int METHODID_SET_BRIGHTNESS = 2;
  private static final int METHODID_SET_COLOR = 3;
  private static final int METHODID_GET_STATUS = 4;

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
          serviceImpl.turnOn((com.smarthome.proto.TurnOnRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.TurnOnResponse>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((com.smarthome.proto.TurnOffRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.TurnOffResponse>) responseObserver);
          break;
        case METHODID_SET_BRIGHTNESS:
          serviceImpl.setBrightness((com.smarthome.proto.SetBrightnessRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetBrightnessResponse>) responseObserver);
          break;
        case METHODID_SET_COLOR:
          serviceImpl.setColor((com.smarthome.proto.SetColorRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetColorResponse>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.proto.GetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.GetStatusResponse>) responseObserver);
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
              com.smarthome.proto.TurnOnRequest,
              com.smarthome.proto.TurnOnResponse>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.TurnOffRequest,
              com.smarthome.proto.TurnOffResponse>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getSetBrightnessMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetBrightnessRequest,
              com.smarthome.proto.SetBrightnessResponse>(
                service, METHODID_SET_BRIGHTNESS)))
        .addMethod(
          getSetColorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetColorRequest,
              com.smarthome.proto.SetColorResponse>(
                service, METHODID_SET_COLOR)))
        .addMethod(
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.GetStatusRequest,
              com.smarthome.proto.GetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .build();
  }

  private static abstract class LightServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LightServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.LightProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LightService");
    }
  }

  private static final class LightServiceFileDescriptorSupplier
      extends LightServiceBaseDescriptorSupplier {
    LightServiceFileDescriptorSupplier() {}
  }

  private static final class LightServiceMethodDescriptorSupplier
      extends LightServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    LightServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (LightServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LightServiceFileDescriptorSupplier())
              .addMethod(getTurnOnMethod())
              .addMethod(getTurnOffMethod())
              .addMethod(getSetBrightnessMethod())
              .addMethod(getSetColorMethod())
              .addMethod(getGetStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
