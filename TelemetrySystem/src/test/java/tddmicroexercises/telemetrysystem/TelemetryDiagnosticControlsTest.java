package tddmicroexercises.telemetrysystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TelemetryDiagnosticControlsTest {

    @Test
    public void CheckTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response() throws Exception {
        // GIVEN
        var telemetryClient = mock(ITelemetryClient.class);
        when(telemetryClient.getOnlineStatus()).thenReturn(true);

        var diagnosticControls = new TelemetryDiagnosticControls(telemetryClient);

        // WHEN
        diagnosticControls.checkTransmission();

        // THEN
        verify(telemetryClient).send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        verify(telemetryClient, times(1)).receive();
    }

    @Test
    public void CheckTransmission_should_disconnect_and_throw_an_exception_when_getOnlineStatus_is_false() throws Exception {
        // GIVEN
        var telemetryClient = mock(ITelemetryClient.class);
        when(telemetryClient.getOnlineStatus()).thenReturn(false);

        var diagnosticControls = new TelemetryDiagnosticControls(telemetryClient);

        // WHEN
        var result = assertThrows(Exception.class, diagnosticControls::checkTransmission);

        // THEN
        verify(telemetryClient, times(1)).disconnect();
        assertEquals("Unable to connect.", result.getMessage());
    }

    @Test
    public void CheckTransmission_should_disconnect_and_reconnect_when_getOnlineStatus_is_false_then_true() throws Exception {
        // GIVEN
        var telemetryClient = mock(ITelemetryClient.class);
        doReturn(false).doReturn(true).when(telemetryClient).getOnlineStatus();

        var diagnosticControls = new TelemetryDiagnosticControls(telemetryClient);

        // WHEN
        diagnosticControls.checkTransmission();

        // THEN
        verify(telemetryClient, times(1)).disconnect();
        verify(telemetryClient, times(1)).connect("*111#");
    }

}
