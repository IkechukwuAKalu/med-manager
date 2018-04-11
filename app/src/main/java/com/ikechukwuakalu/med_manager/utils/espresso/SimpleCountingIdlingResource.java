package com.ikechukwuakalu.med_manager.utils.espresso;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCountingIdlingResource implements IdlingResource {

    private String name;
    private AtomicInteger counter = new AtomicInteger(0);
    private volatile IdlingResource.ResourceCallback resourceCallback;

    SimpleCountingIdlingResource(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isIdleNow() {
        return counter.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }

    public void increment() {
        counter.getAndIncrement();
    }

    public void decrement() {
        int currentValue = counter.decrementAndGet();
        if (currentValue == 0) {
            if (resourceCallback != null) resourceCallback.onTransitionToIdle();
        }
        if (currentValue < 0) throw new IllegalArgumentException("Arguemnt is corrupt");
    }
}
