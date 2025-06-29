package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Thermostat Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: thermostat.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ThermostatServiceGrpc {

  private ThermostatServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.ThermostatService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.ThermostatTurnOnRequest,
      com.smarthome.proto.ThermostatTurnOnResponse> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = com.smarthome.proto.ThermostatTurnOnRequest.class,
      responseType = com.smarthome.proto.ThermostatTurnOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.ThermostatTurnOnRequest,
      com.smarthome.proto.ThermostatTurnOnResponse> getTurnOnMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.ThermostatTurnOnRequest, com.smarthome.proto.ThermostatTurnOnResponse> getTurnOnMethod;
    if ((getTurnOnMethod = ThermostatServiceGrpc.getTurnOnMethod) == null) {
      synchronized (ThermostatServiceGrpc.class) {
        if ((getTurnOnMethod = ThermostatServiceGrpc.getTurnOnMethod) == null) {
          ThermostatServiceGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.ThermostatTurnOnRequest, com.smarthome.proto.ThermostatTurnOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.ThermostatTurnOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.ThermostatTurnOnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ThermostatServiceMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.ThermostatTurnOffRequest,
      com.smarthome.proto.ThermostatTurnOffResponse> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = com.smarthome.proto.ThermostatTurnOffRequest.class,
      responseType = com.smarthome.proto.ThermostatTurnOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.ThermostatTurnOffRequest,
      com.smarthome.proto.ThermostatTurnOffResponse> getTurnOffMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.ThermostatTurnOffRequest, com.smarthome.proto.ThermostatTurnOffResponse> getTurnOffMethod;
    if ((getTurnOffMethod = ThermostatServiceGrpc.getTurnOffMethod) == null) {
      synchronized (ThermostatServiceGrpc.class) {
        if ((getTurnOffMethod = ThermostatServiceGrpc.getTurnOffMethod) == null) {
          ThermostatServiceGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.ThermostatTurnOffRequest, com.smarthome.proto.ThermostatTurnOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.ThermostatTurnOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.ThermostatTurnOffResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ThermostatServiceMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetTargetTemperatureRequest,
      com.smarthome.proto.SetTargetTemperatureResponse> getSetTargetTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetTargetTemperature",
      requestType = com.smarthome.proto.SetTargetTemperatureRequest.class,
      responseType = com.smarthome.proto.SetTargetTemperatureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetTargetTemperatureRequest,
      com.smarthome.proto.SetTargetTemperatureResponse> getSetTargetTemperatureMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetTargetTemperatureRequest, com.smarthome.proto.SetTargetTemperatureResponse> getSetTargetTemperatureMethod;
    if ((getSetTargetTemperatureMethod = ThermostatServiceGrpc.getSetTargetTemperatureMethod) == null) {
      synchronized (ThermostatServiceGrpc.class) {
        if ((getSetTargetTemperatureMethod = ThermostatServiceGrpc.getSetTargetTemperatureMethod) == null) {
          ThermostatServiceGrpc.getSetTargetTemperatureMethod = getSetTargetTemperatureMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetTargetTemperatureRequest, com.smarthome.proto.SetTargetTemperatureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetTargetTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetTargetTemperatureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetTargetTemperatureResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ThermostatServiceMethodDescriptorSupplier("SetTargetTemperature"))
              .build();
        }
      }
    }
    return getSetTargetTemperatureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetModeRequest,
      com.smarthome.proto.SetModeResponse> getSetModeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetMode",
      requestType = com.smarthome.proto.SetModeRequest.class,
      responseType = com.smarthome.proto.SetModeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetModeRequest,
      com.smarthome.proto.SetModeResponse> getSetModeMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetModeRequest, com.smarthome.proto.SetModeResponse> getSetModeMethod;
    if ((getSetModeMethod = ThermostatServiceGrpc.getSetModeMethod) == null) {
      synchronized (ThermostatServiceGrpc.class) {
        if ((getSetModeMethod = ThermostatServiceGrpc.getSetModeMethod) == null) {
          ThermostatServiceGrpc.getSetModeMethod = getSetModeMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetModeRequest, com.smarthome.proto.SetModeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetMode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetModeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetModeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ThermostatServiceMethodDescriptorSupplier("SetMode"))
              .build();
        }
      }
    }
    return getSetModeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.ThermostatGetStatusRequest,
      com.smarthome.proto.ThermostatGetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.ThermostatGetStatusRequest.class,
      responseType = com.smarthome.proto.ThermostatGetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.ThermostatGetStatusRequest,
      com.smarthome.proto.ThermostatGetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.ThermostatGetStatusRequest, com.smarthome.proto.ThermostatGetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = ThermostatServiceGrpc.getGetStatusMethod) == null) {
      synchronized (ThermostatServiceGrpc.class) {
        if ((getGetStatusMethod = ThermostatServiceGrpc.getGetStatusMethod) == null) {
          ThermostatServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.ThermostatGetStatusRequest, com.smarthome.proto.ThermostatGetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.ThermostatGetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.ThermostatGetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ThermostatServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ThermostatServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ThermostatServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ThermostatServiceStub>() {
        @java.lang.Override
        public ThermostatServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ThermostatServiceStub(channel, callOptions);
        }
      };
    return ThermostatServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ThermostatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ThermostatServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ThermostatServiceBlockingStub>() {
        @java.lang.Override
        public ThermostatServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ThermostatServiceBlockingStub(channel, callOptions);
        }
      };
    return ThermostatServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ThermostatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ThermostatServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ThermostatServiceFutureStub>() {
        @java.lang.Override
        public ThermostatServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ThermostatServiceFutureStub(channel, callOptions);
        }
      };
    return ThermostatServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Thermostat Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(com.smarthome.proto.ThermostatTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.ThermostatTurnOnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(com.smarthome.proto.ThermostatTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.ThermostatTurnOffResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void setTargetTemperature(com.smarthome.proto.SetTargetTemperatureRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetTargetTemperatureResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetTargetTemperatureMethod(), responseObserver);
    }

    /**
     */
    default void setMode(com.smarthome.proto.SetModeRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetModeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetModeMethod(), responseObserver);
    }

    /**
     */
    default void getStatus(com.smarthome.proto.ThermostatGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.ThermostatGetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ThermostatService.
   * <pre>
   * Thermostat Service
   * </pre>
   */
  public static abstract class ThermostatServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ThermostatServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ThermostatService.
   * <pre>
   * Thermostat Service
   * </pre>
   */
  public static final class ThermostatServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ThermostatServiceStub> {
    private ThermostatServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ThermostatServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ThermostatServiceStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(com.smarthome.proto.ThermostatTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.ThermostatTurnOnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(com.smarthome.proto.ThermostatTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.ThermostatTurnOffResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setTargetTemperature(com.smarthome.proto.SetTargetTemperatureRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetTargetTemperatureResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetTargetTemperatureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setMode(com.smarthome.proto.SetModeRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetModeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetModeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.ThermostatGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.ThermostatGetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ThermostatService.
   * <pre>
   * Thermostat Service
   * </pre>
   */
  public static final class ThermostatServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ThermostatServiceBlockingStub> {
    private ThermostatServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ThermostatServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ThermostatServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.ThermostatTurnOnResponse turnOn(com.smarthome.proto.ThermostatTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.ThermostatTurnOffResponse turnOff(com.smarthome.proto.ThermostatTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetTargetTemperatureResponse setTargetTemperature(com.smarthome.proto.SetTargetTemperatureRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetTargetTemperatureMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetModeResponse setMode(com.smarthome.proto.SetModeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetModeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.ThermostatGetStatusResponse getStatus(com.smarthome.proto.ThermostatGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ThermostatService.
   * <pre>
   * Thermostat Service
   * </pre>
   */
  public static final class ThermostatServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ThermostatServiceFutureStub> {
    private ThermostatServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ThermostatServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ThermostatServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.ThermostatTurnOnResponse> turnOn(
        com.smarthome.proto.ThermostatTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.ThermostatTurnOffResponse> turnOff(
        com.smarthome.proto.ThermostatTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetTargetTemperatureResponse> setTargetTemperature(
        com.smarthome.proto.SetTargetTemperatureRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetTargetTemperatureMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetModeResponse> setMode(
        com.smarthome.proto.SetModeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetModeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.ThermostatGetStatusResponse> getStatus(
        com.smarthome.proto.ThermostatGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TURN_ON = 0;
  private static final int METHODID_TURN_OFF = 1;
  private static final int METHODID_SET_TARGET_TEMPERATURE = 2;
  private static final int METHODID_SET_MODE = 3;
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
          serviceImpl.turnOn((com.smarthome.proto.ThermostatTurnOnRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.ThermostatTurnOnResponse>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((com.smarthome.proto.ThermostatTurnOffRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.ThermostatTurnOffResponse>) responseObserver);
          break;
        case METHODID_SET_TARGET_TEMPERATURE:
          serviceImpl.setTargetTemperature((com.smarthome.proto.SetTargetTemperatureRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetTargetTemperatureResponse>) responseObserver);
          break;
        case METHODID_SET_MODE:
          serviceImpl.setMode((com.smarthome.proto.SetModeRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetModeResponse>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.proto.ThermostatGetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.ThermostatGetStatusResponse>) responseObserver);
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
              com.smarthome.proto.ThermostatTurnOnRequest,
              com.smarthome.proto.ThermostatTurnOnResponse>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.ThermostatTurnOffRequest,
              com.smarthome.proto.ThermostatTurnOffResponse>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getSetTargetTemperatureMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetTargetTemperatureRequest,
              com.smarthome.proto.SetTargetTemperatureResponse>(
                service, METHODID_SET_TARGET_TEMPERATURE)))
        .addMethod(
          getSetModeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetModeRequest,
              com.smarthome.proto.SetModeResponse>(
                service, METHODID_SET_MODE)))
        .addMethod(
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.ThermostatGetStatusRequest,
              com.smarthome.proto.ThermostatGetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .build();
  }

  private static abstract class ThermostatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ThermostatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.ThermostatProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ThermostatService");
    }
  }

  private static final class ThermostatServiceFileDescriptorSupplier
      extends ThermostatServiceBaseDescriptorSupplier {
    ThermostatServiceFileDescriptorSupplier() {}
  }

  private static final class ThermostatServiceMethodDescriptorSupplier
      extends ThermostatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ThermostatServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ThermostatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ThermostatServiceFileDescriptorSupplier())
              .addMethod(getTurnOnMethod())
              .addMethod(getTurnOffMethod())
              .addMethod(getSetTargetTemperatureMethod())
              .addMethod(getSetModeMethod())
              .addMethod(getGetStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
