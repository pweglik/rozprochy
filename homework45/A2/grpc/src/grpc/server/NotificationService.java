package grpc.server;

import io.grpc.stub.StreamObserver;

import java.util.concurrent.SubmissionPublisher;

public class NotificationService extends StreamingEventsGrpc.StreamingEventsImplBase {
    private SubmissionPublisher<NotificationReply> notificationPublisher;

    public NotificationService(SubmissionPublisher<NotificationReply> notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @Override
    public void subscribe(NotificationRequest request, StreamObserver<NotificationReply> responseObserver) {
        NotificationSubscriber notificationSubscriber = new NotificationSubscriber(responseObserver);
        notificationPublisher.subscribe(notificationSubscriber);
    }
}