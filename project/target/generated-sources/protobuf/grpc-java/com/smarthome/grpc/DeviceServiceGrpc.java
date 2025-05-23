package com.smarthome.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Common service for all devices
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.0)",
    comments = "Source: device.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DeviceServiceGrpc {

  private DeviceServiceGrpc() {}

  public static final String SERVICE_NAME = "device.DeviceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.grpc.StatusRequest,
      com.smarthome.grpc.StatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.grpc.StatusRequest.class,
      responseType = com.smarthome.grpc.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.grpc.StatusRequest,
      com.smarthome.grpc.StatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.grpc.StatusRequest, com.smarthome.grpc.StatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = DeviceServiceGrpc.getGetStatusMethod) == null) {
      synchronized (DeviceServiceGrpc.class) {
        if ((getGetStatusMethod = DeviceServiceGrpc.getGetStatusMethod) == null) {
          DeviceServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.grpc.StatusRequest, com.smarthome.grpc.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.grpc.StatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.grpc.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeviceServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.grpc.StateUpdateRequest,
      com.smarthome.grpc.StateUpdateResponse> getUpdateStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateState",
      requestType = com.smarthome.grpc.StateUpdateRequest.class,
      responseType = com.smarthome.grpc.StateUpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.grpc.StateUpdateRequest,
      com.smarthome.grpc.StateUpdateResponse> getUpdateStateMethod() {
    io.grpc.MethodDescriptor<com.smarthome.grpc.StateUpdateRequest, com.smarthome.grpc.StateUpdateResponse> getUpdateStateMethod;
    if ((getUpdateStateMethod = DeviceServiceGrpc.getUpdateStateMethod) == null) {
      synchronized (DeviceServiceGrpc.class) {
        if ((getUpdateStateMethod = DeviceServiceGrpc.getUpdateStateMethod) == null) {
          DeviceServiceGrpc.getUpdateStateMethod = getUpdateStateMethod =
              io.grpc.MethodDescriptor.<com.smarthome.grpc.StateUpdateRequest, com.smarthome.grpc.StateUpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.grpc.StateUpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.grpc.StateUpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeviceServiceMethodDescriptorSupplier("UpdateState"))
              .build();
        }
      }
    }
    return getUpdateStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.grpc.RegistrationRequest,
      com.smarthome.grpc.RegistrationResponse> getRegisterDeviceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterDevice",
      requestType = com.smarthome.grpc.RegistrationRequest.class,
      responseType = com.smarthome.grpc.RegistrationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.grpc.RegistrationRequest,
      com.smarthome.grpc.RegistrationResponse> getRegisterDeviceMethod() {
    io.grpc.MethodDescriptor<com.smarthome.grpc.RegistrationRequest, com.smarthome.grpc.RegistrationResponse> getRegisterDeviceMethod;
    if ((getRegisterDeviceMethod = DeviceServiceGrpc.getRegisterDeviceMethod) == null) {
      synchronized (DeviceServiceGrpc.class) {
        if ((getRegisterDeviceMethod = DeviceServiceGrpc.getRegisterDeviceMethod) == null) {
          DeviceServiceGrpc.getRegisterDeviceMethod = getRegisterDeviceMethod =
              io.grpc.MethodDescriptor.<com.smarthome.grpc.RegistrationRequest, com.smarthome.grpc.RegistrationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterDevice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.grpc.RegistrationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.grpc.RegistrationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeviceServiceMethodDescriptorSupplier("RegisterDevice"))
              .build();
        }
      }
    }
    return getRegisterDeviceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.grpc.CommandRequest,
      com.smarthome.grpc.CommandResponse> getExecuteCommandMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecuteCommand",
      requestType = com.smarthome.grpc.CommandRequest.class,
      responseType = com.smarthome.grpc.CommandResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.grpc.CommandRequest,
      com.smarthome.grpc.CommandResponse> getExecuteCommandMethod() {
    io.grpc.MethodDescriptor<com.smarthome.grpc.CommandRequest, com.smarthome.grpc.CommandResponse> getExecuteCommandMethod;
    if ((getExecuteCommandMethod = DeviceServiceGrpc.getExecuteCommandMethod) == null) {
      synchronized (DeviceServiceGrpc.class) {
        if ((getExecuteCommandMethod = DeviceServiceGrpc.getExecuteCommandMethod) == null) {
          DeviceServiceGrpc.getExecuteCommandMethod = getExecuteCommandMethod =
              io.grpc.MethodDescriptor.<com.smarthome.grpc.CommandRequest, com.smarthome.grpc.CommandResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecuteCommand"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.grpc.CommandRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.grpc.CommandResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeviceServiceMethodDescriptorSupplier("ExecuteCommand"))
              .build();
        }
      }
    }
    return getExecuteCommandMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DeviceServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeviceServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeviceServiceStub>() {
        @java.lang.Override
        public DeviceServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeviceServiceStub(channel, callOptions);
        }
      };
    return DeviceServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DeviceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeviceServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeviceServiceBlockingStub>() {
        @java.lang.Override
        public DeviceServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeviceServiceBlockingStub(channel, callOptions);
        }
      };
    return DeviceServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DeviceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeviceServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeviceServiceFutureStub>() {
        @java.lang.Override
        public DeviceServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeviceServiceFutureStub(channel, callOptions);
        }
      };
    return DeviceServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Common service for all devices
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Generic device operations
     * </pre>
     */
    default void getStatus(com.smarthome.grpc.StatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.grpc.StatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }

    /**
     */
    default void updateState(com.smarthome.grpc.StateUpdateRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.grpc.StateUpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateStateMethod(), responseObserver);
    }

    /**
     */
    default void registerDevice(com.smarthome.grpc.RegistrationRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.grpc.RegistrationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterDeviceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Device-specific operations
     * </pre>
     */
    default void executeCommand(com.smarthome.grpc.CommandRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.grpc.CommandResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecuteCommandMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DeviceService.
   * <pre>
   * Common service for all devices
   * </pre>
   */
  public static abstract class DeviceServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DeviceServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DeviceService.
   * <pre>
   * Common service for all devices
   * </pre>
   */
  public static final class DeviceServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DeviceServiceStub> {
    private DeviceServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeviceServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Generic device operations
     * </pre>
     */
    public void getStatus(com.smarthome.grpc.StatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.grpc.StatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateState(com.smarthome.grpc.StateUpdateRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.grpc.StateUpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registerDevice(com.smarthome.grpc.RegistrationRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.grpc.RegistrationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterDeviceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Device-specific operations
     * </pre>
     */
    public void executeCommand(com.smarthome.grpc.CommandRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.grpc.CommandResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecuteCommandMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DeviceService.
   * <pre>
   * Common service for all devices
   * </pre>
   */
  public static final class DeviceServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DeviceServiceBlockingStub> {
    private DeviceServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeviceServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Generic device operations
     * </pre>
     */
    public com.smarthome.grpc.StatusResponse getStatus(com.smarthome.grpc.StatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.grpc.StateUpdateResponse updateState(com.smarthome.grpc.StateUpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateStateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.grpc.RegistrationResponse registerDevice(com.smarthome.grpc.RegistrationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterDeviceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Device-specific operations
     * </pre>
     */
    public com.smarthome.grpc.CommandResponse executeCommand(com.smarthome.grpc.CommandRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecuteCommandMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DeviceService.
   * <pre>
   * Common service for all devices
   * </pre>
   */
  public static final class DeviceServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DeviceServiceFutureStub> {
    private DeviceServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeviceServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Generic device operations
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.grpc.StatusResponse> getStatus(
        com.smarthome.grpc.StatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.grpc.StateUpdateResponse> updateState(
        com.smarthome.grpc.StateUpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateStateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.grpc.RegistrationResponse> registerDevice(
        com.smarthome.grpc.RegistrationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterDeviceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Device-specific operations
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.grpc.CommandResponse> executeCommand(
        com.smarthome.grpc.CommandRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecuteCommandMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_STATUS = 0;
  private static final int METHODID_UPDATE_STATE = 1;
  private static final int METHODID_REGISTER_DEVICE = 2;
  private static final int METHODID_EXECUTE_COMMAND = 3;

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
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.smarthome.grpc.StatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.grpc.StatusResponse>) responseObserver);
          break;
        case METHODID_UPDATE_STATE:
          serviceImpl.updateState((com.smarthome.grpc.StateUpdateRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.grpc.StateUpdateResponse>) responseObserver);
          break;
        case METHODID_REGISTER_DEVICE:
          serviceImpl.registerDevice((com.smarthome.grpc.RegistrationRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.grpc.RegistrationResponse>) responseObserver);
          break;
        case METHODID_EXECUTE_COMMAND:
          serviceImpl.executeCommand((com.smarthome.grpc.CommandRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.grpc.CommandResponse>) responseObserver);
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
          getGetStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.grpc.StatusRequest,
              com.smarthome.grpc.StatusResponse>(
                service, METHODID_GET_STATUS)))
        .addMethod(
          getUpdateStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.grpc.StateUpdateRequest,
              com.smarthome.grpc.StateUpdateResponse>(
                service, METHODID_UPDATE_STATE)))
        .addMethod(
          getRegisterDeviceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.grpc.RegistrationRequest,
              com.smarthome.grpc.RegistrationResponse>(
                service, METHODID_REGISTER_DEVICE)))
        .addMethod(
          getExecuteCommandMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.grpc.CommandRequest,
              com.smarthome.grpc.CommandResponse>(
                service, METHODID_EXECUTE_COMMAND)))
        .build();
  }

  private static abstract class DeviceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DeviceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.grpc.DeviceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DeviceService");
    }
  }

  private static final class DeviceServiceFileDescriptorSupplier
      extends DeviceServiceBaseDescriptorSupplier {
    DeviceServiceFileDescriptorSupplier() {}
  }

  private static final class DeviceServiceMethodDescriptorSupplier
      extends DeviceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DeviceServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (DeviceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DeviceServiceFileDescriptorSupplier())
              .addMethod(getGetStatusMethod())
              .addMethod(getUpdateStateMethod())
              .addMethod(getRegisterDeviceMethod())
              .addMethod(getExecuteCommandMethod())
              .build();
        }
      }
    }
    return result;
  }
}
