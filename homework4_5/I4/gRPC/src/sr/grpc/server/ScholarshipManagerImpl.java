package sr.grpc.server;


import sr.proto.AwardScholarshipResult;
import sr.proto.ScholarshipManagerGrpc.ScholarshipManagerImplBase;
import sr.proto.Student;

import java.util.HashMap;

public class ScholarshipManagerImpl extends ScholarshipManagerImplBase {
    HashMap<Integer, Student> students;

    public ScholarshipManagerImpl(HashMap<Integer, Student> students) {
        this.students = students;
    }

    @Override
    public void awardScholarship(sr.proto.AwardScholarshipArguments request,
                                 io.grpc.stub.StreamObserver<sr.proto.AwardScholarshipResult> responseObserver) {
        Student student = this.students.get(request.getStudentId());

        AwardScholarshipResult result;
        if (student == null) {
            System.out.println("student not found");
            result = AwardScholarshipResult.newBuilder().setIsScholarshipAwarded(false).build();
        } else {
            Student newStudent = student.toBuilder().setScholarshipType(request.getType()).setScholarshipValue(request.getValue()).build();
            this.students.put(student.getId(), newStudent);

            result = AwardScholarshipResult.newBuilder().setIsScholarshipAwarded(true).build();
        }
        System.out.println("processes scholarship request");
        System.out.println(this.students);

        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }


}