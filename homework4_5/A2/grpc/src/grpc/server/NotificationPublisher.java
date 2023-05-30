package grpc.server;

import java.util.concurrent.SubmissionPublisher;

public class NotificationPublisher implements Runnable {
    SubmissionPublisher<NotificationReply> notificationPublisher;


    public NotificationPublisher(SubmissionPublisher<NotificationReply> notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            float randomTemp = (float)(5 + Math.random() * (25));
            String city = "Cracow";
            NotificationReply reply = NotificationReply.newBuilder().setCity(city).setTemperature(randomTemp).build();

            Integer n = notificationPublisher.getNumberOfSubscribers();
            System.out.println("Publishing city " + city + " temperature " + randomTemp + "C to " + n + " subscribers.");
            notificationPublisher.submit(reply);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                return;
            }

            randomTemp = (float)(5 + Math.random() * (25));
            city = "Warsaw";
            reply = NotificationReply.newBuilder().setCity(city).setTemperature(randomTemp).build();

            n = notificationPublisher.getNumberOfSubscribers();
            System.out.println("Publishing city " + city + " temperature " + randomTemp + "C to " + n + " subscribers.");
            notificationPublisher.submit(reply);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                return;
            }

            randomTemp = (float)(5 + Math.random() * (25));
            city = "Berlin";
            reply = NotificationReply.newBuilder().setCity(city).setTemperature(randomTemp).build();

            n = notificationPublisher.getNumberOfSubscribers();
            System.out.println("Publishing city " + city + " temperature " + randomTemp + "C to " + n + " subscribers.");
            notificationPublisher.submit(reply);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}
