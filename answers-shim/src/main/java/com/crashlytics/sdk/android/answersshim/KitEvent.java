package com.crashlytics.sdk.android.answersshim;

import com.crashlytics.android.answers.CustomEvent;

import java.util.HashMap;
import java.util.Map;

public class KitEvent {
    private final String eventName;
    private final Map<String, Object> attributes = new HashMap<String, Object>();

    public KitEvent(String eventName) {
        this.eventName = eventName;
    }

    public KitEvent putAttribute(String key, String value) {
        attributes.put(key, value);
        return this;
    }

    public KitEvent putAttribute(String key, Number value) {
        attributes.put(key, value);
        return this;
    }

    CustomEvent toCustomEvent() {
        CustomEvent event = new CustomEvent(eventName);
        for (String key : attributes.keySet()) {
            Object value = attributes.get(key);
            if (value instanceof String) {
                event.putCustomAttribute(key, (String) value);
            } else if (value instanceof Number){
                event.putCustomAttribute(key, (Number) value);
            }
        }
        return event;
    }
}
