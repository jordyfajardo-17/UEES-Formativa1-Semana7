package edu.uees.testing.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservaServiceTest {
    // Solo los dos métodos puros se prueban aquí: no utilizan colaboradores.
    private final ReservaService servicio = new ReservaService(null, null, null);
    private static final double TOLERANCIA = 0.001;

    @Test
    void entornoJUnitFunciona() {
        assertTrue(true);
    }

    @Test
    void cincoHorasPermitenCancelar() {
        // Arrange
        int horas = 5;
        // Act
        boolean resultado = servicio.puedeCancelar(horas);
        // Assert
        assertTrue(resultado);
    }

    @Test
    void dosHorasEsElLimitePermitido() {
        // Arrange
        int horas = 2;
        // Act
        boolean resultado = servicio.puedeCancelar(horas);
        // Assert
        assertTrue(resultado);
    }

    @Test
    void unaHoraNoPermiteCancelar() {
        // Arrange
        int horas = 1;
        // Act
        boolean resultado = servicio.puedeCancelar(horas);
        // Assert
        assertFalse(resultado);
    }

    @Test
    void ceroHorasNoPermitenCancelar() {
        // Arrange
        int horas = 0;
        // Act
        boolean resultado = servicio.puedeCancelar(horas);
        // Assert
        assertFalse(resultado);
    }

    @Test
    void normalNoRecibeDescuento() {
        // Arrange
        String tipo = "NORMAL";
        double base = 100;
        // Act
        double resultado = servicio.calcularTotal(tipo, base);
        // Assert
        assertEquals(100.0, resultado, TOLERANCIA);
    }

    @Test
    void vipRecibeQuincePorCiento() {
        // Arrange
        String tipo = "VIP";
        double base = 100;
        // Act
        double resultado = servicio.calcularTotal(tipo, base);
        // Assert
        assertEquals(85.0, resultado, TOLERANCIA);
    }

    @Test
    void estudianteRecibeDiezPorCiento() {
        // Arrange
        String tipo = "ESTUDIANTE";
        double base = 100;
        // Act
        double resultado = servicio.calcularTotal(tipo, base);
        // Assert
        assertEquals(90.0, resultado, TOLERANCIA);
    }

    @Test
    void totalCeroEsValido() {
        // Arrange
        String tipo = "VIP";
        double base = 0;
        // Act
        double resultado = servicio.calcularTotal(tipo, base);
        // Assert
        assertEquals(0.0, resultado, TOLERANCIA);
    }

    @Test
    void totalNegativoEsInvalido() {
        // Arrange
        String tipo = "NORMAL";
        double base = -1;
        // Act: assertThrows captura la excepción producida por la operación.
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularTotal(tipo, base));
        // Assert
        assertEquals("Total base inválido", error.getMessage());
    }
}
