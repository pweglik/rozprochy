package grpc.server;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicInteger;

import io.grpc.stub.StreamObserver;

public class NotificationSubscriber implements Subscriber<NotificationReply> {
    private AtomicInteger count = new AtomicInteger(0);
    private Subscription subscription;

    private StreamObserver<NotificationReply> observer;

    public NotificationSubscriber(StreamObserver<NotificationReply> observer) {
        this.count = new AtomicInteger(0);
        this.observer = observer;
    }

    @Override
    public void onComplete() {
//        logger.debug("Subscriber has been completed.");
    }

    @Override
    public void onError(Throwable t) {
//        logger.debug("A problem occured in subscriber: ", t);
    }

    @Override
    public void onNext(NotificationReply reply) {
//        logger.debug("Subscriber received next tick: " + reply.getTick());
        observer.onNext(reply);
        Integer currentCount = count.incrementAndGet();
        if (currentCount > 100) {
            this.subscription.cancel();
            observer.onCompleted();
        } else {
            this.subscription.request(1);
        }
    }

    @Override
    public void onSubscribe(Subscription subscription) {
//        logger.debug("Subscribing subscriber");
        this.subscription = subscription;
        this.subscription.request(1);
    }
}