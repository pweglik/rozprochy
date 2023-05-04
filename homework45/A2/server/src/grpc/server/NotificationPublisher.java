package grpc.server;

import java.util.concurrent.SubmissionPublisher;

public class NotificationPublisher implements Runnable {
    SubmissionPublisher<TickReply> notificationPublisher;

    public NotificationPublisher(SubmissionPublisher<TickReply> notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @Override
    public void run() {
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            i++;
            TickReply reply = TickReply.newBuilder().setTick("Blarz " + i).build();
            Integer n = notificationPublisher.getNumberOfSubscribers();
            System.out.println("Publishing new tick " + i + " to " + n + " subscribers.");
            notificationPublisher.submit(reply);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}
