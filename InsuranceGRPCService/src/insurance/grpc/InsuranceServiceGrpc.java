package insurance.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: insurance.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class InsuranceServiceGrpc {

  private InsuranceServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "InsuranceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<insurance.grpc.CoverageRequest,
      insurance.grpc.CoverageResponse> getCheckCoverageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckCoverage",
      requestType = insurance.grpc.CoverageRequest.class,
      responseType = insurance.grpc.CoverageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<insurance.grpc.CoverageRequest,
      insurance.grpc.CoverageResponse> getCheckCoverageMethod() {
    io.grpc.MethodDescriptor<insurance.grpc.CoverageRequest, insurance.grpc.CoverageResponse> getCheckCoverageMethod;
    if ((getCheckCoverageMethod = InsuranceServiceGrpc.getCheckCoverageMethod) == null) {
      synchronized (InsuranceServiceGrpc.class) {
        if ((getCheckCoverageMethod = InsuranceServiceGrpc.getCheckCoverageMethod) == null) {
          InsuranceServiceGrpc.getCheckCoverageMethod = getCheckCoverageMethod =
              io.grpc.MethodDescriptor.<insurance.grpc.CoverageRequest, insurance.grpc.CoverageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckCoverage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  insurance.grpc.CoverageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  insurance.grpc.CoverageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new InsuranceServiceMethodDescriptorSupplier("CheckCoverage"))
              .build();
        }
      }
    }
    return getCheckCoverageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InsuranceServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InsuranceServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InsuranceServiceStub>() {
        @java.lang.Override
        public InsuranceServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InsuranceServiceStub(channel, callOptions);
        }
      };
    return InsuranceServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InsuranceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InsuranceServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InsuranceServiceBlockingStub>() {
        @java.lang.Override
        public InsuranceServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InsuranceServiceBlockingStub(channel, callOptions);
        }
      };
    return InsuranceServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static InsuranceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InsuranceServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InsuranceServiceFutureStub>() {
        @java.lang.Override
        public InsuranceServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InsuranceServiceFutureStub(channel, callOptions);
        }
      };
    return InsuranceServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkCoverage(insurance.grpc.CoverageRequest request,
        io.grpc.stub.StreamObserver<insurance.grpc.CoverageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckCoverageMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service InsuranceService.
   */
  public static abstract class InsuranceServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return InsuranceServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service InsuranceService.
   */
  public static final class InsuranceServiceStub
      extends io.grpc.stub.AbstractAsyncStub<InsuranceServiceStub> {
    private InsuranceServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InsuranceServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InsuranceServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkCoverage(insurance.grpc.CoverageRequest request,
        io.grpc.stub.StreamObserver<insurance.grpc.CoverageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckCoverageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service InsuranceService.
   */
  public static final class InsuranceServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<InsuranceServiceBlockingStub> {
    private InsuranceServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InsuranceServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InsuranceServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public insurance.grpc.CoverageResponse checkCoverage(insurance.grpc.CoverageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckCoverageMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service InsuranceService.
   */
  public static final class InsuranceServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<InsuranceServiceFutureStub> {
    private InsuranceServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InsuranceServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InsuranceServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<insurance.grpc.CoverageResponse> checkCoverage(
        insurance.grpc.CoverageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckCoverageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_COVERAGE = 0;

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
        case METHODID_CHECK_COVERAGE:
          serviceImpl.checkCoverage((insurance.grpc.CoverageRequest) request,
              (io.grpc.stub.StreamObserver<insurance.grpc.CoverageResponse>) responseObserver);
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
          getCheckCoverageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              insurance.grpc.CoverageRequest,
              insurance.grpc.CoverageResponse>(
                service, METHODID_CHECK_COVERAGE)))
        .build();
  }

  private static abstract class InsuranceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    InsuranceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return insurance.grpc.InsuranceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("InsuranceService");
    }
  }

  private static final class InsuranceServiceFileDescriptorSupplier
      extends InsuranceServiceBaseDescriptorSupplier {
    InsuranceServiceFileDescriptorSupplier() {}
  }

  private static final class InsuranceServiceMethodDescriptorSupplier
      extends InsuranceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    InsuranceServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (InsuranceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InsuranceServiceFileDescriptorSupplier())
              .addMethod(getCheckCoverageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
