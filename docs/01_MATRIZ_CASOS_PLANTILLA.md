# Matriz de casos de la actividad formativa 1

Estudiante: Jordy Alessandro Fajardo Figueroa  
Carrera: Ingeniería en Ciencias de la Computación  
Materia: Diseño de Software

La matriz se diseñó antes de ampliar JUnit. Se prueban los métodos puros puedeCancelar y calcularTotal; confirmar se reserva para el laboratorio 2.

| ID | Método | Escenario | Entrada | Esperado | Tipo | Justificación | Prueba JUnit |
|---|---|---|---|---|---|---|---|
| CP-01 | puedeCancelar | Anticipación habitual | 5 | true | Normal | Proteger la regla general | cincoHorasPermitenCancelar |
| CP-02 | puedeCancelar | Límite permitido | 2 | true | Límite | Detectar el cambio incorrecto de >= a > | dosHorasEsElLimitePermitido |
| CP-03 | puedeCancelar | Debajo del límite | 1 | false | Límite | Rechazar anticipación insuficiente | unaHoraNoPermiteCancelar |
| CP-04 | puedeCancelar | Sin anticipación | 0 | false | Extremo | Rechazar cancelación inmediata | ceroHorasNoPermitenCancelar |
| CP-05 | calcularTotal | NORMAL | NORMAL, 100 | 100 | Normal | No aplicar descuentos indebidos | normalNoRecibeDescuento |
| CP-06 | calcularTotal | VIP | VIP, 100 | 85 | Alternativo | Validar descuento del 15 % | vipRecibeQuincePorCiento |
| CP-07 | calcularTotal | ESTUDIANTE | ESTUDIANTE, 100 | 90 | Alternativo | Validar descuento del 10 % | estudianteRecibeDiezPorCiento |
| CP-08 | calcularTotal | Total cero | VIP, 0 | 0 | Límite | Aceptar el mínimo no negativo | totalCeroEsValido |
| CP-09 | calcularTotal | Total negativo | NORMAL, -1 | IllegalArgumentException con mensaje Total base inválido | Inválido | Rechazar valores monetarios negativos | totalNegativoEsInvalido |

La prueba inicial entornoJUnitFunciona se conserva: verifica el arranque de JUnit, pero no cubre una regla de negocio.
