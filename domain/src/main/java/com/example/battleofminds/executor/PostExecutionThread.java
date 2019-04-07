package com.example.battleofminds.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {

    Scheduler getScheduler();
}
