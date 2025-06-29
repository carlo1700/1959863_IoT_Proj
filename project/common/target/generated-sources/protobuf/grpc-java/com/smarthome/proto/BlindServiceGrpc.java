package com.smarthome.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Blind Service - SOLO SetUp e SetDown
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
  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.BlindSetUpRequest,
      com.smarthome.proto.BlindSetUpResponse> getSetUpMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetUp",
      requestType = com.smarthome.proto.BlindSetUpRequest.class,
      responseType = com.smarthome.proto.BlindSetUpResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.BlindSetUpRequest,
      com.smarthome.proto.BlindSetUpResponse> getSetUpMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.BlindSetUpRequest, com.smarthome.proto.BlindSetUpResponse> getSetUpMethod;
    if ((getSetUpMethod = BlindServiceGrpc.getSetUpMethod) == null) {
      synchronized (BlindServiceGrpc.class) {
        if ((getSetUpMethod = BlindServiceGrpc.getSetUpMethod) == null) {
          BlindServiceGrpc.getSetUpMethod = getSetUpMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.BlindSetUpRequest, com.smarthome.proto.BlindSetUpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetUp"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindSetUpRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindSetUpResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlindServiceMethodDescriptorSupplier("SetUp"))
              .build();
        }
      }
    }
    return getSetUpMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smarthome.proto.BlindSetDownRequest,
      com.smarthome.proto.BlindSetDownResponse> getSetDownMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDown",
      requestType = com.smarthome.proto.BlindSetDownRequest.class,
      responseType = com.smarthome.proto.BlindSetDownResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smarthome.proto.BlindSetDownRequest,
      com.smarthome.proto.BlindSetDownResponse> getSetDownMethod() {
    io.grpc.MethodDescriptor<com.smarthome.proto.BlindSetDownRequest, com.smarthome.proto.BlindSetDownResponse> getSetDownMethod;
    if ((getSetDownMethod = BlindServiceGrpc.getSetDownMethod) == null) {
      synchronized (BlindServiceGrpc.class) {
        if ((getSetDownMethod = BlindServiceGrpc.getSetDownMethod) == null) {
          BlindServiceGrpc.getSetDownMethod = getSetDownMethod =
              io.grpc.MethodDescriptor.<com.smarthome.proto.BlindSetDownRequest, com.smarthome.proto.BlindSetDownResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDown"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindSetDownRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smarthome.proto.BlindSetDownResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlindServiceMethodDescriptorSupplier("SetDown"))
              .build();
        }
      }
    }
    return getSetDownMethod;
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
   * Blind Service - SOLO SetUp e SetDown
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void setUp(com.smarthome.proto.BlindSetUpRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindSetUpResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetUpMethod(), responseObserver);
    }

    /**
     */
    default void setDown(com.smarthome.proto.BlindSetDownRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindSetDownResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetDownMethod(), responseObserver);
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
   * Blind Service - SOLO SetUp e SetDown
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
   * Blind Service - SOLO SetUp e SetDown
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
    public void setUp(com.smarthome.proto.BlindSetUpRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindSetUpResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetUpMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setDown(com.smarthome.proto.BlindSetDownRequest request,
        io.grpc.stub.StreamObserver<com.smarthome.proto.BlindSetDownResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetDownMethod(), getCallOptions()), request, responseObserver);
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
   * Blind Service - SOLO SetUp e SetDown
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
    public com.smarthome.proto.BlindSetUpResponse setUp(com.smarthome.proto.BlindSetUpRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetUpMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smarthome.proto.BlindSetDownResponse setDown(com.smarthome.proto.BlindSetDownRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetDownMethod(), getCallOptions(), request);
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
   * Blind Service - SOLO SetUp e SetDown
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
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.BlindSetUpResponse> setUp(
        com.smarthome.proto.BlindSetUpRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetUpMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.BlindSetDownResponse> setDown(
        com.smarthome.proto.BlindSetDownRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetDownMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smarthome.proto.BlindGetStatusResponse> getStatus(
        com.smarthome.proto.BlindGetStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_UP = 0;
  private static final int METHODID_SET_DOWN = 1;
  private static final int METHODID_GET_STATUS = 2;

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
        case METHODID_SET_UP:
          serviceImpl.setUp((com.smarthome.proto.BlindSetUpRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.BlindSetUpResponse>) responseObserver);
          break;
        case METHODID_SET_DOWN:
          serviceImpl.setDown((com.smarthome.proto.BlindSetDownRequest) request,
              (io.grpc.stub.StreamObserver<com.smarthome.proto.BlindSetDownResponse>) responseObserver);
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
          getSetUpMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.BlindSetUpRequest,
              com.smarthome.proto.BlindSetUpResponse>(
                service, METHODID_SET_UP)))
        .addMethod(
          getSetDownMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.smarthome.proto.BlindSetDownRequest,
              com.smarthome.proto.BlindSetDownResponse>(
                service, METHODID_SET_DOWN)))
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
              .addMethod(getSetUpMethod())
              .addMethod(getSetDownMethod())
              .addMethod(getGetStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
