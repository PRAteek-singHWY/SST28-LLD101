import java.util.*;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) {
        devices.add(d);
    }

    @SuppressWarnings("unchecked")
    public <T> T getFirst(Class<T> capability) {
        for (Object d : devices) {
            if (capability.isInstance(d))
                return (T) d;
        }
        throw new IllegalStateException("No device with capability: " + capability.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> capability) {
        List<T> result = new ArrayList<>();
        for (Object d : devices) {
            if (capability.isInstance(d))
                result.add((T) d);
        }
        return result;
    }
}
