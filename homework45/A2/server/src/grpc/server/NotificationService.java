package grpc.server;

import io.grpc.stub.StreamObserver;

import java.util.concurrent.SubmissionPublisher;

public class NotificationService extends StreamingTickerGrpc.StreamingTickerImplBase {
    private SubmissionPublisher<TickReply> notificationPublisher;

    public NotificationService(SubmissionPublisher<TickReply> notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @Override
    public void subscribeTicker(TickRequest request, StreamObserver<TickReply> responseObserver) {
        NotificationSubscriber notificationSubscriber = new NotificationSubscriber(responseObserver);
        notificationPublisher.subscribe(notificationSubscriber);
    }
}