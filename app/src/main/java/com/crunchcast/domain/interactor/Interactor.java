package com.crunchcast.domain.interactor;

import com.crunchcast.domain.executor.BackgroundThreadExecutor;
import com.crunchcast.domain.executor.PostExecutionThread;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class Interactor<T> {

    private final BackgroundThreadExecutor mBackgroundThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;

    private Subscription mSubscription = Subscriptions.empty();

    Interactor(BackgroundThreadExecutor backgroundThreadExecutor, PostExecutionThread postExecutionThread) {
        mBackgroundThreadExecutor = backgroundThreadExecutor;
        mPostExecutionThread = postExecutionThread;
    }

    abstract Observable<T> getObservable();

    public void execute(Subscriber subscriber) {
        this.mSubscription = getObservable()
                .subscribeOn(Schedulers.from(mBackgroundThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
