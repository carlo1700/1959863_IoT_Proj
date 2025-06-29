package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Motion Sensor Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: motionsensor.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MotionSensorServiceGrpc {

  private MotionSensorServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.MotionSensorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.MotionSensorTurnOnRequest,
      com.smarthome.proto.MotionSensorTurnOnResponse> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = com.smarthome.proto.MotionSensorTurnOnRequest.class,
      responseType = com.smarthome.proto.MotionSensorTurnOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.MotionSensorTurnOnRequest,
      com.smarthome.proto.MotionSensorTurnOnResponse> getTurnOnMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.MotionSensorTurnOnRequest, com.smarthome.proto.MotionSensorTurnOnResponse> getTurnOnMethod;
    if ((getTurnOnMethod = MotionSensorServiceGrpc.getTurnOnMethod) == null) {
      synchronized (MotionSensorServiceGrpc.class) {
        if ((getTurnOnMethod = MotionSensorServiceGrpc.getTurnOnMethod) == null) {
          MotionSensorServiceGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.MotionSensorTurnOnRequest, com.smarthome.proto.MotionSensorTurnOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.MotionSensorTurnOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.MotionSensorTurnOnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MotionSensorServiceMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.MotionSensorTurnOffRequest,
      com.smarthome.proto.MotionSensorTurnOffResponse> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = com.smarthome.proto.MotionSensorTurnOffRequest.class,
      responseType = com.smarthome.proto.MotionSensorTurnOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.MotionSensorTurnOffRequest,
      com.smarthome.proto.MotionSensorTurnOffResponse> getTurnOffMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.MotionSensorTurnOffRequest, com.smarthome.proto.MotionSensorTurnOffResponse> getTurnOffMethod;
    if ((getTurnOffMethod = MotionSensorServiceGrpc.getTurnOffMethod) == null) {
      synchronized (MotionSensorServiceGrpc.class) {
        if ((getTurnOffMethod = MotionSensorServiceGrpc.getTurnOffMethod) == null) {
          MotionSensorServiceGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.MotionSensorTurnOffRequest, com.smarthome.proto.MotionSensorTurnOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.MotionSensorTurnOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.MotionSensorTurnOffResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MotionSensorServiceMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.MotionSensorGetStatusRequest,
      com.smarthome.proto.MotionSensorGetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.MotionSensorGetStatusRequest.class,
      responseType = com.smarthome.proto.MotionSensorGetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.MotionSensorGetStatusRequest,
      com.smarthome.proto.MotionSensorGetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.MotionSensorGetStatusRequest, com.smarthome.proto.MotionSensorGetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = MotionSensorServiceGrpc.getGetStatusMethod) == null) {
      synchronized (MotionSensorServiceGrpc.class) {
        if ((getGetStatusMethod = MotionSensorServiceGrpc.getGetStatusMethod) == null) {
          MotionSensorServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.MotionSensorGetStatusRequest, com.smarthome.proto.MotionSensorGetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.MotionSensorGetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.MotionSensorGetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MotionSensorServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetSensitivityRequest,
      com.smarthome.proto.SetSensitivityResponse> getSetSensitivityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetSensitivity",
      requestType = com.smarthome.proto.SetSensitivityRequest.class,
      responseType = com.smarthome.proto.SetSensitivityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetSensitivityRequest,
      com.smarthome.proto.SetSensitivityResponse> getSetSensitivityMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetSensitivityRequest, com.smarthome.proto.SetSensitivityResponse> getSetSensitivityMethod;
    if ((getSetSensitivityMethod = MotionSensorServiceGrpc.getSetSensitivityMethod) == null) {
      synchronized (MotionSensorServiceGrpc.class) {
        if ((getSetSensitivityMethod = MotionSensorServiceGrpc.getSetSensitivityMethod) == null) {
          MotionSensorServiceGrpc.getSetSensitivityMethod = getSetSensitivityMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetSensitivityRequest, com.smarthome.proto.SetSensitivityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetSensitivity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetSensitivityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetSensitivityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MotionSensorServiceMethodDescriptorSupplier("SetSensitivity"))
              .build();
        }
      }
    }
    return getSetSensitivityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MotionSensorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MotionSensorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MotionSensorServiceStub>() {
        @java.lang.Override
        public MotionSensorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MotionSensorServiceStub(channel, callOptions);
        }
      };
    return MotionSensorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MotionSensorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MotionSensorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MotionSensorServiceBlockingStub>() {
        @java.lang.Override
        public MotionSensorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MotionSensorServiceBlockingStub(channel, callOptions);
        }
      };
    return MotionSensorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MotionSensorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MotionSensorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MotionSensorServiceFutureStub>() {
        @java.lang.Override
        public MotionSensorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MotionSensorServiceFutureStub(channel, callOptions);
        }
      };
    return MotionSensorServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Motion Sensor Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(com.smarthome.proto.MotionSensorTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.MotionSensorTurnOnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(com.smarthome.proto.MotionSensorTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.MotionSensorTurnOffResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void getStatus(com.smarthome.proto.MotionSensorGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.MotionSensorGetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }

    /**
     */
    default void setSensitivity(com.smarthome.proto.SetSensitivityRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetSensitivityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetSensitivityMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MotionSensorService.
   * <pre>
   * Motion Sensor Service
   * </pre>
   */
  public static abstract class MotionSensorServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MotionSensorServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MotionSensorService.
   * <pre>
   * Motion Sensor Service
   * </pre>
   */
  public static final class MotionSensorServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MotionSensorServiceStub> {
    private MotionSensorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MotionSensorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MotionSensorServiceStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(com.smarthome.proto.MotionSensorTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.MotionSensorTurnOnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(com.smarthome.proto.MotionSensorTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.MotionSensorTurnOffResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.MotionSensorGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.MotionSensorGetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setSensitivity(com.smarthome.proto.SetSensitivityRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetSensitivityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetSensitivityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MotionSensorService.
   * <pre>
   * Motion Sensor Service
   * </pre>
   */
  public static final class MotionSensorServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MotionSensorServiceBlockingStub> {
    private MotionSensorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MotionSensorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MotionSensorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.MotionSensorTurnOnResponse turnOn(com.smarthome.proto.MotionSensorTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.MotionSensorTurnOffResponse turnOff(com.smarthome.proto.MotionSensorTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.MotionSensorGetStatusResponse getStatus(com.smarthome.proto.MotionSensorGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetSensitivityResponse setSensitivity(com.smarthome.proto.SetSensitivityRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetSensitivityMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MotionSensorService.
   * <pre>
   * Motion Sensor Service
   * </pre>
   */
  public static final class MotionSensorServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MotionSensorServiceFutureStub> {
    private MotionSensorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MotionSensorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MotionSensorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.MotionSensorTurnOnResponse> turnOn(
        com.smarthome.proto.MotionSensorTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.MotionSensorTurnOffResponse> turnOff(
        com.smarthome.proto.MotionSensorTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.MotionSensorGetStatusResponse> getStatus(
        com.smarthome.proto.MotionSensorGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetSensitivityResponse> setSensitivity(
        com.smarthome.proto.SetSensitivityRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetSensitivityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TURN_ON = 0;
  private static final int METHODID_TURN_OFF = 1;
  private static final int METHODID_GET_STATUS = 2;
  private static final int METHODID_SET_SENSITIVITY = 3;

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
          serviceImpl.turnOn((com.smarthome.proto.MotionSensorTurnOnRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.MotionSensorTurnOnResponse>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((com.smarthome.proto.MotionSensorTurnOffRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.MotionSensorTurnOffResponse>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.proto.MotionSensorGetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.MotionSensorGetStatusResponse>) responseObserver);
          break;
        case METHODID_SET_SENSITIVITY:
          serviceImpl.setSensitivity((com.smarthome.proto.SetSensitivityRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetSensitivityResponse>) responseObserver);
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
              com.smarthome.proto.MotionSensorTurnOnRequest,
              com.smarthome.proto.MotionSensorTurnOnResponse>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.MotionSensorTurnOffRequest,
              com.smarthome.proto.MotionSensorTurnOffResponse>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.MotionSensorGetStatusRequest,
              com.smarthome.proto.MotionSensorGetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .addMethod(
          getSetSensitivityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetSensitivityRequest,
              com.smarthome.proto.SetSensitivityResponse>(
                service, METHODID_SET_SENSITIVITY)))
        .build();
  }

  private static abstract class MotionSensorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MotionSensorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.MotionSensorProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MotionSensorService");
    }
  }

  private static final class MotionSensorServiceFileDescriptorSupplier
      extends MotionSensorServiceBaseDescriptorSupplier {
    MotionSensorServiceFileDescriptorSupplier() {}
  }

  private static final class MotionSensorServiceMethodDescriptorSupplier
      extends MotionSensorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MotionSensorServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (MotionSensorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MotionSensorServiceFileDescriptorSupplier())
              .addMethod(getTurnOnMethod())
              .addMethod(getTurnOffMethod())
              .addMethod(getGetStatusMethod())
              .addMethod(getSetSensitivityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
