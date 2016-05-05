package dockusan.com.androidbaseframework.helpers;


import org.greenrobot.eventbus.EventBus;

/**
 * Created by LinhNguyen on 10/15/2015.
 */
public class EventBusHelper {
    public static void register(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(subscriber)) {
            eventBus.register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(subscriber)) {
            eventBus.unregister(subscriber);
        }
    }

    public static void post(Object object) {
        EventBus.getDefault().post(object);
    }

}
