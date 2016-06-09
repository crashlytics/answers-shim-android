package com.crashlytics.android.answers.shim;

import android.util.Log;

public class AnswersOptionalLogger {
    private static final String TAG = "AnswersOptionalLogger";
    private static final KitEventLogger logger;

    static {
        KitEventLogger theLogger = null;
        try {
            theLogger = AnswersKitEventLogger.create();
        } catch (NoClassDefFoundError e) {
            // expected when the Answers isn't available at runtime
        }
        catch (IllegalStateException e) {
            // expected when Fabric hasn't been initialized
        } catch (Throwable t) {
            Log.w(TAG, "Unexpected error creating AnswersKitEventLogger", t);
        }

        logger = theLogger != null ? theLogger : NoopKitEventLogger.create();
    }

    public static KitEventLogger get() {
        return logger;
    }
}
