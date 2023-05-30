package sr.grpc;

import sr.proto.ScholarshipType;
import sr.proto.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class Serialization {

    public static void main(String[] args)
    {
        try {
            new Serialization().testProto();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void testProto() throws IOException
    {
        Student s1 =
                Student.newBuilder()
                        .setId(123456)
                        .setName("Przemek Węglik")
                        .setEmail("pweglik@o2.pl")
                        .addClasses(
                                Student.Class.newBuilder()
                                        .setName("Systemy rozproszone")
                                        .setEcts(3)
                                        .setGrade(4.5f))
                        .setScholarshipType(ScholarshipType.SOCIAL)
                        .build();

        Student s2 =
                Student.newBuilder()
                        .setId(123456)
                        .setName("Przemek Węglik")
                        .setEmail("pweglik@o2.pl")
                        .addClasses(
                                Student.Class.newBuilder()
                                        .setName("Systemy rozproszone")
                                        .setEcts(5)
                                        .setGrade(4.5f))
                        .setScholarshipType(ScholarshipType.SPORT)
                        .setScholarshipValue(1023)
                        .build();

        //serialize again (only once) and write to a file
        FileOutputStream file = new FileOutputStream("s1.ser");
        file.write(s1.toByteArray());
        file.close();

        file = new FileOutputStream("s2.ser");
        file.write(s2.toByteArray());
        file.close();

    }
}

