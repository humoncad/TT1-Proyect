package com.tt1.trabajo;

import modelo.Destinatario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicios.EnviarEmails;
import utilidades.api.EmailApi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class EnviarEmailsUnitTest {
    private EnviarEmails enviarEmails;
    private EmailApi emailApiMock;

    @BeforeEach
    void setUp() {
        emailApiMock = mock(EmailApi.class);
        enviarEmails = new EnviarEmails(emailApiMock);
    }

    @Test
    void testEnviarEmailCorrecto() throws Exception {
        when(emailApiMock.emailPost(anyString(), anyString())).thenReturn(null);

        boolean result = enviarEmails.enviarEmail(new Destinatario(), "mensaje de prueba");

        assertTrue(result, "El email debería enviarse correctamente con el mock");
        verify(emailApiMock, times(1)).emailPost(anyString(), anyString());
    }

    @Test
    void testEnviarEmailIncorrecto() throws Exception {
        when(emailApiMock.emailPost(anyString(), anyString())).thenThrow(new RuntimeException("Error API"));

        boolean result = enviarEmails.enviarEmail(new Destinatario(), "");

        assertFalse(result, "El servicio debería capturar la excepción y devolver false");
    }
}
