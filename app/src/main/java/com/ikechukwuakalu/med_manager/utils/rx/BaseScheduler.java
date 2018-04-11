package com.ikechukwuakalu.med_manager.utils.rx;

import io.reactivex.Scheduler;

public interface BaseScheduler {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
