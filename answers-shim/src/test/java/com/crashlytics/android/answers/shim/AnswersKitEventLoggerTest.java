package com.crashlytics.android.answers.shim;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AnswersKitEventLoggerTest {
    @Test
    public void create() throws Exception {
        Answers answers = Mockito.mock(Answers.class);
        assertNotNull(AnswersKitEventLogger.create(answers));
    }

    @Test(expected = IllegalStateException.class)
    public void create_fabricNotInitialized() throws Exception {
        AnswersKitEventLogger.create(null);
    }

    @Test(expected = RuntimeException.class)
    public void logKitEvent_unexpectedError() throws Exception {
        Answers answers = Mockito.mock(Answers.class);

        doThrow(RuntimeException.class).when(answers).logCustom(any(CustomEvent.class));

        AnswersKitEventLogger.create(answers).logKitEvent(new KitEvent("test"));
    }
}