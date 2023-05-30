package grpc.server;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: eventStream.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class StreamingEventsGrpc {

  private StreamingEventsGrpc() {}

  public static final String SERVICE_NAME = "ticker.StreamingEvents";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.server.NotificationRequest,
      grpc.server.NotificationReply> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribe",
      requestType = grpc.server.NotificationRequest.class,
      responseType = grpc.server.NotificationReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.server.NotificationRequest,
      grpc.server.NotificationReply> getSubscribeMethod() {
    io.grpc.MethodDescriptor<grpc.server.NotificationRequest, grpc.server.NotificationReply> getSubscribeMethod;
    if ((getSubscribeMethod = StreamingEventsGrpc.getSubscribeMethod) == null) {
      synchronized (StreamingEventsGrpc.class) {
        if ((getSubscribeMethod = StreamingEventsGrpc.getSubscribeMethod) == null) {
          StreamingEventsGrpc.getSubscribeMethod = getSubscribeMethod =
              io.grpc.MethodDescriptor.<grpc.server.NotificationRequest, grpc.server.NotificationReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.server.NotificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.server.NotificationReply.getDefaultInstance()))
              .setSchemaDescriptor(new StreamingEventsMethodDescriptorSupplier("subscribe"))
              .build();
        }
      }
    }
    return getSubscribeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StreamingEventsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingEventsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingEventsStub>() {
        @java.lang.Override
        public StreamingEventsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingEventsStub(channel, callOptions);
        }
      };
    return StreamingEventsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StreamingEventsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingEventsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingEventsBlockingStub>() {
        @java.lang.Override
        public StreamingEventsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingEventsBlockingStub(channel, callOptions);
        }
      };
    return StreamingEventsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StreamingEventsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingEventsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingEventsFutureStub>() {
        @java.lang.Override
        public StreamingEventsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingEventsFutureStub(channel, callOptions);
        }
      };
    return StreamingEventsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void subscribe(grpc.server.NotificationRequest request,
        io.grpc.stub.StreamObserver<grpc.server.NotificationReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StreamingEvents.
   */
  public static abstract class StreamingEventsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return StreamingEventsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StreamingEvents.
   */
  public static final class StreamingEventsStub
      extends io.grpc.stub.AbstractAsyncStub<StreamingEventsStub> {
    private StreamingEventsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingEventsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingEventsStub(channel, callOptions);
    }

    /**
     */
    public void subscribe(grpc.server.NotificationRequest request,
        io.grpc.stub.StreamObserver<grpc.server.NotificationReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StreamingEvents.
   */
  public static final class StreamingEventsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StreamingEventsBlockingStub> {
    private StreamingEventsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingEventsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingEventsBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<grpc.server.NotificationReply> subscribe(
        grpc.server.NotificationRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StreamingEvents.
   */
  public static final class StreamingEventsFutureStub
      extends io.grpc.stub.AbstractFutureStub<StreamingEventsFutureStub> {
    private StreamingEventsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingEventsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingEventsFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE = 0;

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
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((grpc.server.NotificationRequest) request,
              (io.grpc.stub.StreamObserver<grpc.server.NotificationReply>) responseObserver);
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
          getSubscribeMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              grpc.server.NotificationRequest,
              grpc.server.NotificationReply>(
                service, METHODID_SUBSCRIBE)))
        .build();
  }

  private static abstract class StreamingEventsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StreamingEventsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.server.eventProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StreamingEvents");
    }
  }

  private static final class StreamingEventsFileDescriptorSupplier
      extends StreamingEventsBaseDescriptorSupplier {
    StreamingEventsFileDescriptorSupplier() {}
  }

  private static final class StreamingEventsMethodDescriptorSupplier
      extends StreamingEventsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StreamingEventsMethodDescriptorSupplier(String methodName) {
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
      synchronized (StreamingEventsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StreamingEventsFileDescriptorSupplier())
              .addMethod(getSubscribeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
