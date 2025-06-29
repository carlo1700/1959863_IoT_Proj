package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Device Manager Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: devicemanager.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DeviceManagerServiceGrpc {

  private DeviceManagerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.DeviceManagerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.RegisterDeviceRequest,
      com.smarthome.proto.RegisterDeviceResponse> getRegisterDeviceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterDevice",
      requestType = com.smarthome.proto.RegisterDeviceRequest.class,
      responseType = com.smarthome.proto.RegisterDeviceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.RegisterDeviceRequest,
      com.smarthome.proto.RegisterDeviceResponse> getRegisterDeviceMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.RegisterDeviceRequest, com.smarthome.proto.RegisterDeviceResponse> getRegisterDeviceMethod;
    if ((getRegisterDeviceMethod = DeviceManagerServiceGrpc.getRegisterDeviceMethod) == null) {
      synchronized (DeviceManagerServiceGrpc.class) {
        if ((getRegisterDeviceMethod = DeviceManagerServiceGrpc.getRegisterDeviceMethod) == null) {
          DeviceManagerServiceGrpc.getRegisterDeviceMethod = getRegisterDeviceMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.RegisterDeviceRequest, com.smarthome.proto.RegisterDeviceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterDevice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.RegisterDeviceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.RegisterDeviceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeviceManagerServiceMethodDescriptorSupplier("RegisterDevice"))
              .build();
        }
      }
    }
    return getRegisterDeviceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.UnregisterDeviceRequest,
      com.smarthome.proto.UnregisterDeviceResponse> getUnregisterDeviceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnregisterDevice",
      requestType = com.smarthome.proto.UnregisterDeviceRequest.class,
      responseType = com.smarthome.proto.UnregisterDeviceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.UnregisterDeviceRequest,
      com.smarthome.proto.UnregisterDeviceResponse> getUnregisterDeviceMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.UnregisterDeviceRequest, com.smarthome.proto.UnregisterDeviceResponse> getUnregisterDeviceMethod;
    if ((getUnregisterDeviceMethod = DeviceManagerServiceGrpc.getUnregisterDeviceMethod) == null) {
      synchronized (DeviceManagerServiceGrpc.class) {
        if ((getUnregisterDeviceMethod = DeviceManagerServiceGrpc.getUnregisterDeviceMethod) == null) {
          DeviceManagerServiceGrpc.getUnregisterDeviceMethod = getUnregisterDeviceMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.UnregisterDeviceRequest, com.smarthome.proto.UnregisterDeviceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnregisterDevice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.UnregisterDeviceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.UnregisterDeviceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeviceManagerServiceMethodDescriptorSupplier("UnregisterDevice"))
              .build();
        }
      }
    }
    return getUnregisterDeviceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.ListDevicesRequest,
      com.smarthome.proto.ListDevicesResponse> getListDevicesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListDevices",
      requestType = com.smarthome.proto.ListDevicesRequest.class,
      responseType = com.smarthome.proto.ListDevicesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.ListDevicesRequest,
      com.smarthome.proto.ListDevicesResponse> getListDevicesMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.ListDevicesRequest, com.smarthome.proto.ListDevicesResponse> getListDevicesMethod;
    if ((getListDevicesMethod = DeviceManagerServiceGrpc.getListDevicesMethod) == null) {
      synchronized (DeviceManagerServiceGrpc.class) {
        if ((getListDevicesMethod = DeviceManagerServiceGrpc.getListDevicesMethod) == null) {
          DeviceManagerServiceGrpc.getListDevicesMethod = getListDevicesMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.ListDevicesRequest, com.smarthome.proto.ListDevicesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListDevices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.ListDevicesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.ListDevicesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeviceManagerServiceMethodDescriptorSupplier("ListDevices"))
              .build();
        }
      }
    }
    return getListDevicesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SendCommandRequest,
      com.smarthome.proto.SendCommandResponse> getSendCommandMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendCommand",
      requestType = com.smarthome.proto.SendCommandRequest.class,
      responseType = com.smarthome.proto.SendCommandResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SendCommandRequest,
      com.smarthome.proto.SendCommandResponse> getSendCommandMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SendCommandRequest, com.smarthome.proto.SendCommandResponse> getSendCommandMethod;
    if ((getSendCommandMethod = DeviceManagerServiceGrpc.getSendCommandMethod) == null) {
      synchronized (DeviceManagerServiceGrpc.class) {
        if ((getSendCommandMethod = DeviceManagerServiceGrpc.getSendCommandMethod) == null) {
          DeviceManagerServiceGrpc.getSendCommandMethod = getSendCommandMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SendCommandRequest, com.smarthome.proto.SendCommandResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendCommand"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SendCommandRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SendCommandResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeviceManagerServiceMethodDescriptorSupplier("SendCommand"))
              .build();
        }
      }
    }
    return getSendCommandMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DeviceManagerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeviceManagerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeviceManagerServiceStub>() {
        @java.lang.Override
        public DeviceManagerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeviceManagerServiceStub(channel, callOptions);
        }
      };
    return DeviceManagerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DeviceManagerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeviceManagerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeviceManagerServiceBlockingStub>() {
        @java.lang.Override
        public DeviceManagerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeviceManagerServiceBlockingStub(channel, callOptions);
        }
      };
    return DeviceManagerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DeviceManagerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeviceManagerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeviceManagerServiceFutureStub>() {
        @java.lang.Override
        public DeviceManagerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeviceManagerServiceFutureStub(channel, callOptions);
        }
      };
    return DeviceManagerServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Device Manager Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void registerDevice(com.smarthome.proto.RegisterDeviceRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.RegisterDeviceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterDeviceMethod(), responseObserver);
    }

    /**
     */
    default void unregisterDevice(com.smarthome.proto.UnregisterDeviceRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.UnregisterDeviceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnregisterDeviceMethod(), responseObserver);
    }

    /**
     */
    default void listDevices(com.smarthome.proto.ListDevicesRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.ListDevicesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListDevicesMethod(), responseObserver);
    }

    /**
     */
    default void sendCommand(com.smarthome.proto.SendCommandRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SendCommandResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendCommandMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DeviceManagerService.
   * <pre>
   * Device Manager Service
   * </pre>
   */
  public static abstract class DeviceManagerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DeviceManagerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DeviceManagerService.
   * <pre>
   * Device Manager Service
   * </pre>
   */
  public static final class DeviceManagerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DeviceManagerServiceStub> {
    private DeviceManagerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceManagerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeviceManagerServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerDevice(com.smarthome.proto.RegisterDeviceRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.RegisterDeviceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterDeviceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unregisterDevice(com.smarthome.proto.UnregisterDeviceRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.UnregisterDeviceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnregisterDeviceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listDevices(com.smarthome.proto.ListDevicesRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.ListDevicesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListDevicesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendCommand(com.smarthome.proto.SendCommandRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SendCommandResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendCommandMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DeviceManagerService.
   * <pre>
   * Device Manager Service
   * </pre>
   */
  public static final class DeviceManagerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DeviceManagerServiceBlockingStub> {
    private DeviceManagerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceManagerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeviceManagerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.RegisterDeviceResponse registerDevice(com.smarthome.proto.RegisterDeviceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterDeviceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.UnregisterDeviceResponse unregisterDevice(com.smarthome.proto.UnregisterDeviceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnregisterDeviceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.ListDevicesResponse listDevices(com.smarthome.proto.ListDevicesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListDevicesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.SendCommandResponse sendCommand(com.smarthome.proto.SendCommandRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendCommandMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DeviceManagerService.
   * <pre>
   * Device Manager Service
   * </pre>
   */
  public static final class DeviceManagerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DeviceManagerServiceFutureStub> {
    private DeviceManagerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceManagerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeviceManagerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.RegisterDeviceResponse> registerDevice(
        com.smarthome.proto.RegisterDeviceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterDeviceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.UnregisterDeviceResponse> unregisterDevice(
        com.smarthome.proto.UnregisterDeviceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnregisterDeviceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.ListDevicesResponse> listDevices(
        com.smarthome.proto.ListDevicesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListDevicesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SendCommandResponse> sendCommand(
        com.smarthome.proto.SendCommandRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendCommandMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_DEVICE = 0;
  private static final int METHODID_UNREGISTER_DEVICE = 1;
  private static final int METHODID_LIST_DEVICES = 2;
  private static final int METHODID_SEND_COMMAND = 3;

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
        case METHODID_REGISTER_DEVICE:
          serviceImpl.registerDevice((com.smarthome.proto.RegisterDeviceRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.RegisterDeviceResponse>) responseObserver);
          break;
        case METHODID_UNREGISTER_DEVICE:
          serviceImpl.unregisterDevice((com.smarthome.proto.UnregisterDeviceRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.UnregisterDeviceResponse>) responseObserver);
          break;
        case METHODID_LIST_DEVICES:
          serviceImpl.listDevices((com.smarthome.proto.ListDevicesRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.ListDevicesResponse>) responseObserver);
          break;
        case METHODID_SEND_COMMAND:
          serviceImpl.sendCommand((com.smarthome.proto.SendCommandRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SendCommandResponse>) responseObserver);
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
          getRegisterDeviceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.RegisterDeviceRequest,
              com.smarthome.proto.RegisterDeviceResponse>(
                service, METHODID_REGISTER_DEVICE)))
        .addMethod(
          getUnregisterDeviceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.UnregisterDeviceRequest,
              com.smarthome.proto.UnregisterDeviceResponse>(
                service, METHODID_UNREGISTER_DEVICE)))
        .addMethod(
          getListDevicesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.ListDevicesRequest,
              com.smarthome.proto.ListDevicesResponse>(
                service, METHODID_LIST_DEVICES)))
        .addMethod(
          getSendCommandMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.SendCommandRequest,
              com.smarthome.proto.SendCommandResponse>(
                service, METHODID_SEND_COMMAND)))
        .build();
  }

  private static abstract class DeviceManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DeviceManagerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.DeviceManagerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DeviceManagerService");
    }
  }

  private static final class DeviceManagerServiceFileDescriptorSupplier
      extends DeviceManagerServiceBaseDescriptorSupplier {
    DeviceManagerServiceFileDescriptorSupplier() {}
  }

  private static final class DeviceManagerServiceMethodDescriptorSupplier
      extends DeviceManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    DeviceManagerServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (DeviceManagerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DeviceManagerServiceFileDescriptorSupplier())
              .addMethod(getRegisterDeviceMethod())
              .addMethod(getUnregisterDeviceMethod())
              .addMethod(getListDevicesMethod())
              .addMethod(getSendCommandMethod())
              .build();
        }
      }
    }
    return result;
  }
}
