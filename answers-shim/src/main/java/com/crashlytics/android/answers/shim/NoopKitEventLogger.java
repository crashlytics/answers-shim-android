package com.crashlytics.android.answers.shim;

class NoopKitEventLogger implements KitEventLogger {
    public static KitEventLogger create() {
        return new NoopKitEventLogger();
    }

    @Override
    public void logKitEvent(KitEvent kitEvent) {
    }
}
