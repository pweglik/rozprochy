// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: student.proto

package sr.proto;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_Student_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_Student_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_Student_Class_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_Student_Class_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_AwardScholarshipArguments_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_AwardScholarshipArguments_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_AwardScholarshipResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_AwardScholarshipResult_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rstudent.proto\022\010tutorial\"\336\001\n\007Student\022\014\n" +
      "\004name\030\001 \001(\t\022\n\n\002id\030\002 \001(\005\022\r\n\005email\030\003 \001(\t\0222" +
      "\n\017scholarshipType\030\004 \001(\0162\031.tutorial.Schol" +
      "arshipType\022\030\n\020scholarshipValue\030\005 \001(\005\022(\n\007" +
      "classes\030\006 \003(\0132\027.tutorial.Student.Class\0322" +
      "\n\005Class\022\014\n\004name\030\001 \001(\t\022\014\n\004ects\030\002 \001(\005\022\r\n\005g" +
      "rade\030\003 \001(\002\"f\n\031AwardScholarshipArguments\022" +
      "\021\n\tstudentId\030\001 \001(\005\022\'\n\004type\030\002 \001(\0162\031.tutor" +
      "ial.ScholarshipType\022\r\n\005value\030\003 \001(\005\"6\n\026Aw" +
      "ardScholarshipResult\022\034\n\024isScholarshipAwa" +
      "rded\030\001 \001(\010*>\n\017ScholarshipType\022\013\n\007SCIENCE" +
      "\020\000\022\n\n\006SOCIAL\020\001\022\t\n\005SPORT\020\002\022\007\n\003ART\020\0032q\n\022Sc" +
      "holarshipManager\022[\n\020AwardScholarship\022#.t" +
      "utorial.AwardScholarshipArguments\032 .tuto" +
      "rial.AwardScholarshipResult\"\000B\032\n\010sr.prot" +
      "oB\014StudentProtoP\001b\006proto3"
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
    internal_static_tutorial_Student_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tutorial_Student_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_Student_descriptor,
        new java.lang.String[] { "Name", "Id", "Email", "ScholarshipType", "ScholarshipValue", "Classes", });
    internal_static_tutorial_Student_Class_descriptor =
      internal_static_tutorial_Student_descriptor.getNestedTypes().get(0);
    internal_static_tutorial_Student_Class_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_Student_Class_descriptor,
        new java.lang.String[] { "Name", "Ects", "Grade", });
    internal_static_tutorial_AwardScholarshipArguments_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_tutorial_AwardScholarshipArguments_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_AwardScholarshipArguments_descriptor,
        new java.lang.String[] { "StudentId", "Type", "Value", });
    internal_static_tutorial_AwardScholarshipResult_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_tutorial_AwardScholarshipResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_AwardScholarshipResult_descriptor,
        new java.lang.String[] { "IsScholarshipAwarded", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}