// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: eventStream.proto

package grpc.server;

public final class TickerProto {
  private TickerProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ticker_TickRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ticker_TickRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ticker_TickReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ticker_TickReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021eventStream.proto\022\006ticker\"\r\n\013TickReque" +
      "st\"\031\n\tTickReply\022\014\n\004tick\030\001 \001(\t2P\n\017Streami" +
      "ngTicker\022=\n\017subscribeTicker\022\023.ticker.Tic" +
      "kRequest\032\021.ticker.TickReply\"\0000\001B\034\n\013grpc." +
      "serverB\013TickerProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_ticker_TickRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ticker_TickRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ticker_TickRequest_descriptor,
        new java.lang.String[] { });
    internal_static_ticker_TickReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ticker_TickReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ticker_TickReply_descriptor,
        new java.lang.String[] { "Tick", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}