package com.crunchcast.domain.executor;


import rx.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
