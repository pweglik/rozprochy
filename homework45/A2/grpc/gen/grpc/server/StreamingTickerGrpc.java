package grpc.server;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: eventStream.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class StreamingTickerGrpc {

  private StreamingTickerGrpc() {}

  public static final String SERVICE_NAME = "ticker.StreamingTicker";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.server.TickRequest,
      grpc.server.TickReply> getSubscribeTickerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribeTicker",
      requestType = grpc.server.TickRequest.class,
      responseType = grpc.server.TickReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.server.TickRequest,
      grpc.server.TickReply> getSubscribeTickerMethod() {
    io.grpc.MethodDescriptor<grpc.server.TickRequest, grpc.server.TickReply> getSubscribeTickerMethod;
    if ((getSubscribeTickerMethod = StreamingTickerGrpc.getSubscribeTickerMethod) == null) {
      synchronized (StreamingTickerGrpc.class) {
        if ((getSubscribeTickerMethod = StreamingTickerGrpc.getSubscribeTickerMethod) == null) {
          StreamingTickerGrpc.getSubscribeTickerMethod = getSubscribeTickerMethod =
              io.grpc.MethodDescriptor.<grpc.server.TickRequest, grpc.server.TickReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "subscribeTicker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.server.TickRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.server.TickReply.getDefaultInstance()))
              .setSchemaDescriptor(new StreamingTickerMethodDescriptorSupplier("subscribeTicker"))
              .build();
        }
      }
    }
    return getSubscribeTickerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StreamingTickerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingTickerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingTickerStub>() {
        @java.lang.Override
        public StreamingTickerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingTickerStub(channel, callOptions);
        }
      };
    return StreamingTickerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StreamingTickerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingTickerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingTickerBlockingStub>() {
        @java.lang.Override
        public StreamingTickerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingTickerBlockingStub(channel, callOptions);
        }
      };
    return StreamingTickerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StreamingTickerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingTickerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingTickerFutureStub>() {
        @java.lang.Override
        public StreamingTickerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingTickerFutureStub(channel, callOptions);
        }
      };
    return StreamingTickerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void subscribeTicker(grpc.server.TickRequest request,
        io.grpc.stub.StreamObserver<grpc.server.TickReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeTickerMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StreamingTicker.
   */
  public static abstract class StreamingTickerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return StreamingTickerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StreamingTicker.
   */
  public static final class StreamingTickerStub
      extends io.grpc.stub.AbstractAsyncStub<StreamingTickerStub> {
    private StreamingTickerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingTickerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingTickerStub(channel, callOptions);
    }

    /**
     */
    public void subscribeTicker(grpc.server.TickRequest request,
        io.grpc.stub.StreamObserver<grpc.server.TickReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeTickerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StreamingTicker.
   */
  public static final class StreamingTickerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StreamingTickerBlockingStub> {
    private StreamingTickerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingTickerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingTickerBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<grpc.server.TickReply> subscribeTicker(
        grpc.server.TickRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeTickerMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StreamingTicker.
   */
  public static final class StreamingTickerFutureStub
      extends io.grpc.stub.AbstractFutureStub<StreamingTickerFutureStub> {
    private StreamingTickerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingTickerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingTickerFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE_TICKER = 0;

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
        case METHODID_SUBSCRIBE_TICKER:
          serviceImpl.subscribeTicker((grpc.server.TickRequest) request,
              (io.grpc.stub.StreamObserver<grpc.server.TickReply>) responseObserver);
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
          getSubscribeTickerMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              grpc.server.TickRequest,
              grpc.server.TickReply>(
                service, METHODID_SUBSCRIBE_TICKER)))
        .build();
  }

  private static abstract class StreamingTickerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StreamingTickerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.server.TickerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StreamingTicker");
    }
  }

  private static final class StreamingTickerFileDescriptorSupplier
      extends StreamingTickerBaseDescriptorSupplier {
    StreamingTickerFileDescriptorSupplier() {}
  }

  private static final class StreamingTickerMethodDescriptorSupplier
      extends StreamingTickerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StreamingTickerMethodDescriptorSupplier(String methodName) {
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
      synchronized (StreamingTickerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StreamingTickerFileDescriptorSupplier())
              .addMethod(getSubscribeTickerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
