package com.crashlytics.android.answers.shim;

import android.util.Log;

import com.crashlytics.android.answers.Answers;

class AnswersKitEventLogger implements KitEventLogger {
    private static final String TAG = "AnswersKitEventLogger";
    private final Answers answers;

    private AnswersKitEventLogger(Answers answers) {
        this.answers = answers;
    }

    public static AnswersKitEventLogger create() throws NoClassDefFoundError, IllegalStateException {
        // This will throw NoClassDefFoundError if the app doesn't include Answers
        Answers answers = Answers.getInstance();

        return create(answers);
    }

    static AnswersKitEventLogger create(Answers answers) throws IllegalStateException {
        // If Answers is on the classpath, but hasn't been initialized by Fabric.with()
        if (answers == null) {
            throw new IllegalStateException("Answers must be initialized before logging kit events");
        }

        return new AnswersKitEventLogger(answers);
    }

    @Override
    public void logKitEvent(KitEvent kitEvent) {
        try {
            answers.logCustom(kitEvent.toCustomEvent());
        } catch (Throwable t) {
            Log.w(TAG, "Unexpected error sending Answers event", t);
        }
    }
}
