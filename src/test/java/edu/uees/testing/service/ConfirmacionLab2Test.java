package edu.uees.testing.service;

import edu.uees.testing.availability.DisponibilidadClient;
import edu.uees.testing.domain.EstadoReserva;
import edu.uees.testing.domain.Reserva;
import edu.uees.testing.notification.Notificador;
import edu.uees.testing.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmacionLab2Test {
    @Mock DisponibilidadClient disponibilidad;
    @Mock ReservaRepository repository;
    @Mock Notificador notificador;

    @Test
    void reservaDisponibleSeConfirmaGuardaYNotifica() {
        // Arrange: respuesta controlada (Stub) y colaboradores observables (Mock).
        Reserva reserva = new Reserva("R-001", "NORMAL");
        when(disponibilidad.estaDisponible(reserva)).thenReturn(true);
        ReservaService servicio = new ReservaService(disponibilidad, repository, notificador);

        // Act
        servicio.confirmar(reserva);

        // Assert: estado y efectos relevantes, sobre la misma reserva y una sola vez.
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        verify(repository, times(1)).guardar(same(reserva));
        verify(notificador, times(1)).enviarConfirmacion(same(reserva));
    }

    @Test
    void reservaNoDisponibleNoSeGuardaNiNotifica() {
        // Arrange: Stub que fuerza el camino de rechazo.
        Reserva reserva = new Reserva("R-002", "NORMAL");
        when(disponibilidad.estaDisponible(reserva)).thenReturn(false);
        ReservaService servicio = new ReservaService(disponibilidad, repository, notificador);

        // Act
        IllegalStateException error = assertThrows(IllegalStateException.class,
                () -> servicio.confirmar(reserva));

        // Assert: excepción, estado intacto y ninguna escritura o notificación.
        assertEquals("Horario no disponible", error.getMessage());
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
        verify(repository, never()).guardar(any());
        verify(notificador, never()).enviarConfirmacion(any());
    }
}
