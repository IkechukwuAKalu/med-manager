package com.ikechukwuakalu.med_manager.utils.espresso;

import android.support.test.espresso.IdlingResource;

/**
 * This class is used for testing using Espresso
 */
public class EspressoIdlingResource {

    private static SimpleCountingIdlingResource idlingResource =
            new SimpleCountingIdlingResource("GLOBAL_RESOURCE");

    public static void increment() {
        idlingResource.increment();
    }

    public static void decrement() {
        idlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return idlingResource;
    }
}
