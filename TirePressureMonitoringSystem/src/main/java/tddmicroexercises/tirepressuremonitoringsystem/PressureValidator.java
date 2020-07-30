package tddmicroexercises.tirepressuremonitoringsystem;

public class PressureValidator {
    private static final double LOW_PRESSURE_THRESHOLD = 17.0;
    private static final double HIGH_PRESSURE_THRESHOLD = 21.0;

    public static boolean validatePressure(Double pressureValue) {
        return pressureValue < LOW_PRESSURE_THRESHOLD || pressureValue > HIGH_PRESSURE_THRESHOLD;
    }
}
