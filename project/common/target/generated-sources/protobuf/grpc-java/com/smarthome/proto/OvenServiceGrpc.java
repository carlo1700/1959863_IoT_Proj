package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Oven Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: oven.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class OvenServiceGrpc {

  private OvenServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.OvenService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.OvenTurnOnRequest,
      com.smarthome.proto.OvenTurnOnResponse> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = com.smarthome.proto.OvenTurnOnRequest.class,
      responseType = com.smarthome.proto.OvenTurnOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.OvenTurnOnRequest,
      com.smarthome.proto.OvenTurnOnResponse> getTurnOnMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.OvenTurnOnRequest, com.smarthome.proto.OvenTurnOnResponse> getTurnOnMethod;
    if ((getTurnOnMethod = OvenServiceGrpc.getTurnOnMethod) == null) {
      synchronized (OvenServiceGrpc.class) {
        if ((getTurnOnMethod = OvenServiceGrpc.getTurnOnMethod) == null) {
          OvenServiceGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.OvenTurnOnRequest, com.smarthome.proto.OvenTurnOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.OvenTurnOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.OvenTurnOnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new OvenServiceMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.OvenTurnOffRequest,
      com.smarthome.proto.OvenTurnOffResponse> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = com.smarthome.proto.OvenTurnOffRequest.class,
      responseType = com.smarthome.proto.OvenTurnOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.OvenTurnOffRequest,
      com.smarthome.proto.OvenTurnOffResponse> getTurnOffMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.OvenTurnOffRequest, com.smarthome.proto.OvenTurnOffResponse> getTurnOffMethod;
    if ((getTurnOffMethod = OvenServiceGrpc.getTurnOffMethod) == null) {
      synchronized (OvenServiceGrpc.class) {
        if ((getTurnOffMethod = OvenServiceGrpc.getTurnOffMethod) == null) {
          OvenServiceGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.OvenTurnOffRequest, com.smarthome.proto.OvenTurnOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.OvenTurnOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.OvenTurnOffResponse.getDefaultInstance()))
              .setSchemaDescriptor(new OvenServiceMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetTemperatureRequest,
      com.smarthome.proto.SetTemperatureResponse> getSetTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetTemperature",
      requestType = com.smarthome.proto.SetTemperatureRequest.class,
      responseType = com.smarthome.proto.SetTemperatureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetTemperatureRequest,
      com.smarthome.proto.SetTemperatureResponse> getSetTemperatureMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetTemperatureRequest, com.smarthome.proto.SetTemperatureResponse> getSetTemperatureMethod;
    if ((getSetTemperatureMethod = OvenServiceGrpc.getSetTemperatureMethod) == null) {
      synchronized (OvenServiceGrpc.class) {
        if ((getSetTemperatureMethod = OvenServiceGrpc.getSetTemperatureMethod) == null) {
          OvenServiceGrpc.getSetTemperatureMethod = getSetTemperatureMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetTemperatureRequest, com.smarthome.proto.SetTemperatureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetTemperatureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetTemperatureResponse.getDefaultInstance()))
              .setSchemaDescriptor(new OvenServiceMethodDescriptorSupplier("SetTemperature"))
              .build();
        }
      }
    }
    return getSetTemperatureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetTimerRequest,
      com.smarthome.proto.SetTimerResponse> getSetTimerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetTimer",
      requestType = com.smarthome.proto.SetTimerRequest.class,
      responseType = com.smarthome.proto.SetTimerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetTimerRequest,
      com.smarthome.proto.SetTimerResponse> getSetTimerMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetTimerRequest, com.smarthome.proto.SetTimerResponse> getSetTimerMethod;
    if ((getSetTimerMethod = OvenServiceGrpc.getSetTimerMethod) == null) {
      synchronized (OvenServiceGrpc.class) {
        if ((getSetTimerMethod = OvenServiceGrpc.getSetTimerMethod) == null) {
          OvenServiceGrpc.getSetTimerMethod = getSetTimerMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetTimerRequest, com.smarthome.proto.SetTimerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetTimer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetTimerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetTimerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new OvenServiceMethodDescriptorSupplier("SetTimer"))
              .build();
        }
      }
    }
    return getSetTimerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.OvenGetStatusRequest,
      com.smarthome.proto.OvenGetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.OvenGetStatusRequest.class,
      responseType = com.smarthome.proto.OvenGetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.OvenGetStatusRequest,
      com.smarthome.proto.OvenGetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.OvenGetStatusRequest, com.smarthome.proto.OvenGetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = OvenServiceGrpc.getGetStatusMethod) == null) {
      synchronized (OvenServiceGrpc.class) {
        if ((getGetStatusMethod = OvenServiceGrpc.getGetStatusMethod) == null) {
          OvenServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.OvenGetStatusRequest, com.smarthome.proto.OvenGetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.OvenGetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.OvenGetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new OvenServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OvenServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OvenServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OvenServiceStub>() {
        @java.lang.Override
        public OvenServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OvenServiceStub(channel, callOptions);
        }
      };
    return OvenServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OvenServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OvenServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OvenServiceBlockingStub>() {
        @java.lang.Override
        public OvenServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OvenServiceBlockingStub(channel, callOptions);
        }
      };
    return OvenServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OvenServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OvenServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OvenServiceFutureStub>() {
        @java.lang.Override
        public OvenServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OvenServiceFutureStub(channel, callOptions);
        }
      };
    return OvenServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Oven Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(com.smarthome.proto.OvenTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.OvenTurnOnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(com.smarthome.proto.OvenTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.OvenTurnOffResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void setTemperature(com.smarthome.proto.SetTemperatureRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetTemperatureResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetTemperatureMethod(), responseObserver);
    }

    /**
     */
    default void setTimer(com.smarthome.proto.SetTimerRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetTimerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetTimerMethod(), responseObserver);
    }

    /**
     */
    default void getStatus(com.smarthome.proto.OvenGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.OvenGetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service OvenService.
   * <pre>
   * Oven Service
   * </pre>
   */
  public static abstract class OvenServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return OvenServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service OvenService.
   * <pre>
   * Oven Service
   * </pre>
   */
  public static final class OvenServiceStub
      extends io.grpc.stub.AbstractAsyncStub<OvenServiceStub> {
    private OvenServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OvenServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OvenServiceStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(com.smarthome.proto.OvenTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.OvenTurnOnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(com.smarthome.proto.OvenTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.OvenTurnOffResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setTemperature(com.smarthome.proto.SetTemperatureRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetTemperatureResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetTemperatureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setTimer(com.smarthome.proto.SetTimerRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetTimerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetTimerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.OvenGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.OvenGetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service OvenService.
   * <pre>
   * Oven Service
   * </pre>
   */
  public static final class OvenServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<OvenServiceBlockingStub> {
    private OvenServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OvenServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OvenServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.OvenTurnOnResponse turnOn(com.smarthome.proto.OvenTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.OvenTurnOffResponse turnOff(com.smarthome.proto.OvenTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetTemperatureResponse setTemperature(com.smarthome.proto.SetTemperatureRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetTemperatureMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetTimerResponse setTimer(com.smarthome.proto.SetTimerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetTimerMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.OvenGetStatusResponse getStatus(com.smarthome.proto.OvenGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service OvenService.
   * <pre>
   * Oven Service
   * </pre>
   */
  public static final class OvenServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<OvenServiceFutureStub> {
    private OvenServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OvenServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OvenServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.OvenTurnOnResponse> turnOn(
        com.smarthome.proto.OvenTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.OvenTurnOffResponse> turnOff(
        com.smarthome.proto.OvenTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetTemperatureResponse> setTemperature(
        com.smarthome.proto.SetTemperatureRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetTemperatureMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetTimerResponse> setTimer(
        com.smarthome.proto.SetTimerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetTimerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.OvenGetStatusResponse> getStatus(
        com.smarthome.proto.OvenGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TURN_ON = 0;
  private static final int METHODID_TURN_OFF = 1;
  private static final int METHODID_SET_TEMPERATURE = 2;
  private static final int METHODID_SET_TIMER = 3;
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
          serviceImpl.turnOn((com.smarthome.proto.OvenTurnOnRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.OvenTurnOnResponse>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((com.smarthome.proto.OvenTurnOffRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.OvenTurnOffResponse>) responseObserver);
          break;
        case METHODID_SET_TEMPERATURE:
          serviceImpl.setTemperature((com.smarthome.proto.SetTemperatureRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetTemperatureResponse>) responseObserver);
          break;
        case METHODID_SET_TIMER:
          serviceImpl.setTimer((com.smarthome.proto.SetTimerRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetTimerResponse>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.proto.OvenGetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.OvenGetStatusResponse>) responseObserver);
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
              com.smarthome.proto.OvenTurnOnRequest,
              com.smarthome.proto.OvenTurnOnResponse>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.OvenTurnOffRequest,
              com.smarthome.proto.OvenTurnOffResponse>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getSetTemperatureMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetTemperatureRequest,
              com.smarthome.proto.SetTemperatureResponse>(
                service, METHODID_SET_TEMPERATURE)))
        .addMethod(
          getSetTimerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetTimerRequest,
              com.smarthome.proto.SetTimerResponse>(
                service, METHODID_SET_TIMER)))
        .addMethod(
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.OvenGetStatusRequest,
              com.smarthome.proto.OvenGetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .build();
  }

  private static abstract class OvenServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OvenServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.OvenProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OvenService");
    }
  }

  private static final class OvenServiceFileDescriptorSupplier
      extends OvenServiceBaseDescriptorSupplier {
    OvenServiceFileDescriptorSupplier() {}
  }

  private static final class OvenServiceMethodDescriptorSupplier
      extends OvenServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    OvenServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (OvenServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OvenServiceFileDescriptorSupplier())
              .addMethod(getTurnOnMethod())
              .addMethod(getTurnOffMethod())
              .addMethod(getSetTemperatureMethod())
              .addMethod(getSetTimerMethod())
              .addMethod(getGetStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
