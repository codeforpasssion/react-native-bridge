package com.nativebridge;

import android.util.Log;

import androidx.annotation.NonNull;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class CalendarModule extends ReactContextBaseJavaModule {
    private int eventCount = 0;

    CalendarModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "CalendarModule";
    }

    @ReactMethod
    public void createCalenderEvent(Callback callback) {
        Log.d("Calender Module", "Logged from module");
        callback.invoke("data ruturned ");
    }

    @ReactMethod
    public void createCalenderPromise(Promise promise) {
        try {
            promise.resolve("data reciwved from promise");
            eventCount += 1;
            sendEvent(getReactApplicationContext(), "EventCount", eventCount);

        } catch (Exception e) {
            promise.reject("error exception", e);
        }
    }

    private void sendEvent(ReactContext reactContext,
            String eventName,
            int params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

}