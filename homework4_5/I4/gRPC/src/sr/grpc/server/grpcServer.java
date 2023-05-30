package sr.grpc.server;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import sr.proto.Student;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class grpcServer {
    private static final Logger logger = Logger.getLogger(grpcServer.class.getName());

    private final String address = "127.0.0.5";
    private final int port = 50051;
    private Server server;

    private SocketAddress socket;

    private HashMap<Integer, Student> students = new HashMap<>();

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final grpcServer server = new grpcServer();
        server.start();
        server.blockUntilShutdown();
    }

    private void start() throws IOException {
        // filling DB
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

                        .build();

        this.students.put(123456, s1);

        // actual server
        try {
            socket = new InetSocketAddress(InetAddress.getByName(address), port);
        } catch (UnknownHostException e) {
        }

        server = /*ServerBuilder*/NettyServerBuilder.forAddress(socket).executor(Executors.newFixedThreadPool(16))
                .addService(new ScholarshipManagerImpl(this.students))
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                grpcServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            System.out.println("Ending! Final students:");
            System.out.println(this.students);
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
