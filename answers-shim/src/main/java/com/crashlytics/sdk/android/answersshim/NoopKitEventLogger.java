package com.crashlytics.sdk.android.answersshim;

class NoopKitEventLogger implements KitEventLogger {
    public static KitEventLogger create() {
        return new NoopKitEventLogger();
    }

    @Override
    public void logKitEvent(KitEvent kitEvent) {
    }
}
