package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Global metrics registry — proper, thread-safe, lazy-initialized Singleton.
 *
 * Thread safety: Bill Pugh static holder idiom (class loading guarantee).
 * Reflection protection: constructor throws if instance already exists.
 * Serialization protection: readResolve() returns the singleton.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();

    // Static flag to detect reflection attacks
    private static volatile boolean instanceCreated = false;

    // Private constructor — blocks reflection-based multiple construction
    private MetricsRegistry() {
        if (instanceCreated) {
            throw new RuntimeException(
                    "Singleton violation: use MetricsRegistry.getInstance()");
        }
        instanceCreated = true;
    }

    // Bill Pugh static holder — lazy, thread-safe, no synchronization overhead
    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    // Preserve singleton across serialization/deserialization
    @Serial
    private Object readResolve() {
        return getInstance();
    }
}
