package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Coil Service (Heating/Cooling coil)
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: coil.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CoilServiceGrpc {

  private CoilServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.CoilService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.CoilTurnOnRequest,
      com.smarthome.proto.CoilTurnOnResponse> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = com.smarthome.proto.CoilTurnOnRequest.class,
      responseType = com.smarthome.proto.CoilTurnOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.CoilTurnOnRequest,
      com.smarthome.proto.CoilTurnOnResponse> getTurnOnMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.CoilTurnOnRequest, com.smarthome.proto.CoilTurnOnResponse> getTurnOnMethod;
    if ((getTurnOnMethod = CoilServiceGrpc.getTurnOnMethod) == null) {
      synchronized (CoilServiceGrpc.class) {
        if ((getTurnOnMethod = CoilServiceGrpc.getTurnOnMethod) == null) {
          CoilServiceGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.CoilTurnOnRequest, com.smarthome.proto.CoilTurnOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.CoilTurnOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.CoilTurnOnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoilServiceMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.CoilTurnOffRequest,
      com.smarthome.proto.CoilTurnOffResponse> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = com.smarthome.proto.CoilTurnOffRequest.class,
      responseType = com.smarthome.proto.CoilTurnOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.CoilTurnOffRequest,
      com.smarthome.proto.CoilTurnOffResponse> getTurnOffMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.CoilTurnOffRequest, com.smarthome.proto.CoilTurnOffResponse> getTurnOffMethod;
    if ((getTurnOffMethod = CoilServiceGrpc.getTurnOffMethod) == null) {
      synchronized (CoilServiceGrpc.class) {
        if ((getTurnOffMethod = CoilServiceGrpc.getTurnOffMethod) == null) {
          CoilServiceGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.CoilTurnOffRequest, com.smarthome.proto.CoilTurnOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.CoilTurnOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.CoilTurnOffResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoilServiceMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SetPowerLevelRequest,
      com.smarthome.proto.SetPowerLevelResponse> getSetPowerLevelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetPowerLevel",
      requestType = com.smarthome.proto.SetPowerLevelRequest.class,
      responseType = com.smarthome.proto.SetPowerLevelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SetPowerLevelRequest,
      com.smarthome.proto.SetPowerLevelResponse> getSetPowerLevelMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SetPowerLevelRequest, com.smarthome.proto.SetPowerLevelResponse> getSetPowerLevelMethod;
    if ((getSetPowerLevelMethod = CoilServiceGrpc.getSetPowerLevelMethod) == null) {
      synchronized (CoilServiceGrpc.class) {
        if ((getSetPowerLevelMethod = CoilServiceGrpc.getSetPowerLevelMethod) == null) {
          CoilServiceGrpc.getSetPowerLevelMethod = getSetPowerLevelMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SetPowerLevelRequest, com.smarthome.proto.SetPowerLevelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetPowerLevel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetPowerLevelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SetPowerLevelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoilServiceMethodDescriptorSupplier("SetPowerLevel"))
              .build();
        }
      }
    }
    return getSetPowerLevelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.CoilGetStatusRequest,
      com.smarthome.proto.CoilGetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.CoilGetStatusRequest.class,
      responseType = com.smarthome.proto.CoilGetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.CoilGetStatusRequest,
      com.smarthome.proto.CoilGetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.CoilGetStatusRequest, com.smarthome.proto.CoilGetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = CoilServiceGrpc.getGetStatusMethod) == null) {
      synchronized (CoilServiceGrpc.class) {
        if ((getGetStatusMethod = CoilServiceGrpc.getGetStatusMethod) == null) {
          CoilServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.CoilGetStatusRequest, com.smarthome.proto.CoilGetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.CoilGetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.CoilGetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoilServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CoilServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoilServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoilServiceStub>() {
        @java.lang.Override
        public CoilServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoilServiceStub(channel, callOptions);
        }
      };
    return CoilServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CoilServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoilServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoilServiceBlockingStub>() {
        @java.lang.Override
        public CoilServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoilServiceBlockingStub(channel, callOptions);
        }
      };
    return CoilServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CoilServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoilServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoilServiceFutureStub>() {
        @java.lang.Override
        public CoilServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoilServiceFutureStub(channel, callOptions);
        }
      };
    return CoilServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Coil Service (Heating/Cooling coil)
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(com.smarthome.proto.CoilTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.CoilTurnOnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(com.smarthome.proto.CoilTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.CoilTurnOffResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void setPowerLevel(com.smarthome.proto.SetPowerLevelRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetPowerLevelResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetPowerLevelMethod(), responseObserver);
    }

    /**
     */
    default void getStatus(com.smarthome.proto.CoilGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.CoilGetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CoilService.
   * <pre>
   * Coil Service (Heating/Cooling coil)
   * </pre>
   */
  public static abstract class CoilServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CoilServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CoilService.
   * <pre>
   * Coil Service (Heating/Cooling coil)
   * </pre>
   */
  public static final class CoilServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CoilServiceStub> {
    private CoilServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoilServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoilServiceStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(com.smarthome.proto.CoilTurnOnRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.CoilTurnOnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(com.smarthome.proto.CoilTurnOffRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.CoilTurnOffResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setPowerLevel(com.smarthome.proto.SetPowerLevelRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SetPowerLevelResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetPowerLevelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.CoilGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.CoilGetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CoilService.
   * <pre>
   * Coil Service (Heating/Cooling coil)
   * </pre>
   */
  public static final class CoilServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CoilServiceBlockingStub> {
    private CoilServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoilServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoilServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.CoilTurnOnResponse turnOn(com.smarthome.proto.CoilTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.CoilTurnOffResponse turnOff(com.smarthome.proto.CoilTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SetPowerLevelResponse setPowerLevel(com.smarthome.proto.SetPowerLevelRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetPowerLevelMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.CoilGetStatusResponse getStatus(com.smarthome.proto.CoilGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CoilService.
   * <pre>
   * Coil Service (Heating/Cooling coil)
   * </pre>
   */
  public static final class CoilServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CoilServiceFutureStub> {
    private CoilServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoilServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoilServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.CoilTurnOnResponse> turnOn(
        com.smarthome.proto.CoilTurnOnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.CoilTurnOffResponse> turnOff(
        com.smarthome.proto.CoilTurnOffRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SetPowerLevelResponse> setPowerLevel(
        com.smarthome.proto.SetPowerLevelRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetPowerLevelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.CoilGetStatusResponse> getStatus(
        com.smarthome.proto.CoilGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TURN_ON = 0;
  private static final int METHODID_TURN_OFF = 1;
  private static final int METHODID_SET_POWER_LEVEL = 2;
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
          serviceImpl.turnOn((com.smarthome.proto.CoilTurnOnRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.CoilTurnOnResponse>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((com.smarthome.proto.CoilTurnOffRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.CoilTurnOffResponse>) responseObserver);
          break;
        case METHODID_SET_POWER_LEVEL:
          serviceImpl.setPowerLevel((com.smarthome.proto.SetPowerLevelRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SetPowerLevelResponse>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.proto.CoilGetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.CoilGetStatusResponse>) responseObserver);
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
              com.smarthome.proto.CoilTurnOnRequest,
              com.smarthome.proto.CoilTurnOnResponse>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.CoilTurnOffRequest,
              com.smarthome.proto.CoilTurnOffResponse>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getSetPowerLevelMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SetPowerLevelRequest,
              com.smarthome.proto.SetPowerLevelResponse>(
                service, METHODID_SET_POWER_LEVEL)))
        .addMethod(
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.CoilGetStatusRequest,
              com.smarthome.proto.CoilGetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .build();
  }

  private static abstract class CoilServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CoilServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.CoilProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CoilService");
    }
  }

  private static final class CoilServiceFileDescriptorSupplier
      extends CoilServiceBaseDescriptorSupplier {
    CoilServiceFileDescriptorSupplier() {}
  }

  private static final class CoilServiceMethodDescriptorSupplier
      extends CoilServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CoilServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CoilServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CoilServiceFileDescriptorSupplier())
              .addMethod(getTurnOnMethod())
              .addMethod(getTurnOffMethod())
              .addMethod(getSetPowerLevelMethod())
              .addMethod(getGetStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
