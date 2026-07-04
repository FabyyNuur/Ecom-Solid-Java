package ecom.ioc;

import java.util.HashMap;
import java.util.Map;

public class IoCContainer {
    private final Map<Class<?>, Object> beans = new HashMap<>();

    public void register(Class<?> type, Object instance) {
        beans.put(type, instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> type) {
        Object bean = beans.get(type);
        if (bean == null) {
            throw new IllegalStateException("Aucune dependance enregistree pour : " + type.getName());
        }
        return (T) bean;
    }
}
