package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm {

    private final ISensor sensor;

    private boolean alarmOn = false;

    public Alarm(ISensor sensor) {
        this.sensor = sensor;
    }

    public boolean check() {
        var psiPressureValue = sensor.popNextPressurePsiValue();

        alarmOn = PressureValidator.validatePressure(psiPressureValue);

        return alarmOn;
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
