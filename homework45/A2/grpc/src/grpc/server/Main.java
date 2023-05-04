package grpc.server;

import java.io.IOException;
import java.util.concurrent.SubmissionPublisher;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // create notification publisher
        SubmissionPublisher<TickReply> notificationPublisher = new SubmissionPublisher<>();
        Thread publisher = new Thread(new NotificationPublisher(notificationPublisher));
        publisher.start();

        // create server
        final grpcServer server = new grpcServer(new NotificationService(notificationPublisher));
        server.start();
        server.blockUntilShutdown();
    }
}
