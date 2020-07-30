package tddmicroexercises.tirepressuremonitoringsystem;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlarm {

    @Test
    public void isAlarmOnShouldReturnFalseAsDefault() {
        // Given
        var mockSensor = Mockito.mock(ISensor.class);
        var alarm = new Alarm(mockSensor);

        // When/Then
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void isAlarmOnShouldReturnTrueWhenValueIsBelowLowerThreshold() {
        // Given
        var mockSensor = Mockito.mock(ISensor.class);
        var alarm = new Alarm(mockSensor);
        Mockito.when(mockSensor.popNextPressurePsiValue()).thenReturn(16.0);

        // When
        var result = alarm.check();

        // Then
        assertTrue( alarm.isAlarmOn());
        assertEquals(true, result);
    }

    @Test
    public void isAlarmOnShouldReturnTrueWhenValueIsAboveUpperThreshold() {
        // Given
        var mockSensor = Mockito.mock(ISensor.class);
        var alarm = new Alarm(mockSensor);
        Mockito.when(mockSensor.popNextPressurePsiValue()).thenReturn(22.0);

        // When
        var result = alarm.check();

        // Then
        assertTrue( alarm.isAlarmOn());
        assertEquals(true, result);
    }

    @Test
    public void isAlarmOnShouldReturnFalseWhenValueIsWithinThreshold() {
        // GIVEN
        var mockSensor = Mockito.mock(ISensor.class);
        var alarm = new Alarm(mockSensor);
        Mockito.when(mockSensor.popNextPressurePsiValue()).thenReturn(19.0);

        // WHEN
        var result = alarm.check();

        // THEN
        assertFalse(alarm.isAlarmOn());
        assertEquals(false, result);
    }
}
