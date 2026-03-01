import java.util.*;

public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        Switchable pjPower = reg.getFirst(Switchable.class);
        pjPower.powerOn();
        InputConnectable pjInput = reg.getFirst(InputConnectable.class);
        pjInput.connectInput("HDMI-1");

        BrightnessControl lights = reg.getFirst(BrightnessControl.class);
        lights.setBrightness(60);

        TemperatureControl ac = reg.getFirst(TemperatureControl.class);
        ac.setTemperatureC(24);

        Scanner scan = reg.getFirst(Scanner.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        List<Switchable> switchables = reg.getAll(Switchable.class);
        for (Switchable s : switchables) {
            s.powerOff();
        }
    }
}
