package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Solar Panel Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: solarpanel.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SolarPanelServiceGrpc {

  private SolarPanelServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "smarthome.SolarPanelService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.SolarPanelGetStatusRequest,
      com.smarthome.proto.SolarPanelGetStatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStatus",
      requestType = com.smarthome.proto.SolarPanelGetStatusRequest.class,
      responseType = com.smarthome.proto.SolarPanelGetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.SolarPanelGetStatusRequest,
      com.smarthome.proto.SolarPanelGetStatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.SolarPanelGetStatusRequest, com.smarthome.proto.SolarPanelGetStatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = SolarPanelServiceGrpc.getGetStatusMethod) == null) {
      synchronized (SolarPanelServiceGrpc.class) {
        if ((getGetStatusMethod = SolarPanelServiceGrpc.getGetStatusMethod) == null) {
          SolarPanelServiceGrpc.getGetStatusMethod = getGetStatusMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.SolarPanelGetStatusRequest, com.smarthome.proto.SolarPanelGetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SolarPanelGetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.SolarPanelGetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SolarPanelServiceMethodDescriptorSupplier("GetStatus"))
              .build();
        }
      }
    }
    return getGetStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SolarPanelServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SolarPanelServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SolarPanelServiceStub>() {
        @java.lang.Override
        public SolarPanelServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SolarPanelServiceStub(channel, callOptions);
        }
      };
    return SolarPanelServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SolarPanelServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SolarPanelServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SolarPanelServiceBlockingStub>() {
        @java.lang.Override
        public SolarPanelServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SolarPanelServiceBlockingStub(channel, callOptions);
        }
      };
    return SolarPanelServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SolarPanelServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SolarPanelServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SolarPanelServiceFutureStub>() {
        @java.lang.Override
        public SolarPanelServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SolarPanelServiceFutureStub(channel, callOptions);
        }
      };
    return SolarPanelServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Solar Panel Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void getStatus(com.smarthome.proto.SolarPanelGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SolarPanelGetStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SolarPanelService.
   * <pre>
   * Solar Panel Service
   * </pre>
   */
  public static abstract class SolarPanelServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SolarPanelServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SolarPanelService.
   * <pre>
   * Solar Panel Service
   * </pre>
   */
  public static final class SolarPanelServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SolarPanelServiceStub> {
    private SolarPanelServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SolarPanelServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SolarPanelServiceStub(channel, callOptions);
    }

    /**
     */
    public void getStatus(com.smarthome.proto.SolarPanelGetStatusRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.SolarPanelGetStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SolarPanelService.
   * <pre>
   * Solar Panel Service
   * </pre>
   */
  public static final class SolarPanelServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SolarPanelServiceBlockingStub> {
    private SolarPanelServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SolarPanelServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SolarPanelServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smarthome.proto.SolarPanelGetStatusResponse getStatus(com.smarthome.proto.SolarPanelGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SolarPanelService.
   * <pre>
   * Solar Panel Service
   * </pre>
   */
  public static final class SolarPanelServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SolarPanelServiceFutureStub> {
    private SolarPanelServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SolarPanelServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SolarPanelServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.SolarPanelGetStatusResponse> getStatus(
        com.smarthome.proto.SolarPanelGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_STATUS = 0;

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
          serviceImpl.getStatus((com.smarthome.proto.SolarPanelGetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.SolarPanelGetStatusResponse>) responseObserver);
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
              com.smarthome.proto.SolarPanelGetStatusRequest,
              com.smarthome.proto.SolarPanelGetStatusResponse>(
                service, METHODID_GET_STATUS)))
        .build();
  }

  private static abstract class SolarPanelServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SolarPanelServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smarthome.proto.SolarPanelProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SolarPanelService");
    }
  }

  private static final class SolarPanelServiceFileDescriptorSupplier
      extends SolarPanelServiceBaseDescriptorSupplier {
    SolarPanelServiceFileDescriptorSupplier() {}
  }

  private static final class SolarPanelServiceMethodDescriptorSupplier
      extends SolarPanelServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SolarPanelServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SolarPanelServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SolarPanelServiceFileDescriptorSupplier())
              .addMethod(getGetStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
