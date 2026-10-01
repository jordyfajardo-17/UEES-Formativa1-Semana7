# Actividad formativa 2 Guía para demostrar Stub Mock y JaCoCo

Estudiante: Jordy Alessandro Fajardo Figueroa

Carrera: Ingeniería en Ciencias de la Computación

Materia: Diseño de Software UCOM0310

## Repositorio y rama

La consigna exige partir del laboratorio 1 y conservar sus pruebas. Se usa UEES-Formativa1-Semana7, con main intacta y la rama test/lab2-dobles-cobertura. No hace falta otro repositorio ni fusionar esta rama para entregar. La actividad 2 no exige Pull Request. Ae6 es un repositorio independiente.

## Diferencia práctica entre Stub y Mock

Mockito crea objetos de prueba; su papel depende de lo que se hace con ellos. DisponibilidadClient es el Stub porque when(...).thenReturn(true/false) determina qué respuesta recibe el servicio. ReservaRepository y Notificador son Mocks de interacción porque verify comprueba sus llamadas. Reserva es un objeto real: permite observar PENDIENTE y CONFIRMADA sin simular el estado que se pretende comprobar.

```java
when(disponibilidad.estaDisponible(reserva)).thenReturn(true);
servicio.confirmar(reserva);
assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
verify(repository, times(1)).guardar(same(reserva));
verify(notificador, times(1)).enviarConfirmacion(same(reserva));
```

La primera línea configura una respuesta; no comprueba que el servicio funcione. assertEquals y verify son las comprobaciones. times(1) exige una llamada, same(reserva) exige esa instancia. No se usa una API real ni se envía una notificación real: es una prueba unitaria del contrato con los colaboradores, no de sus implementaciones.

## Qué evidencia cada escenario

| Prueba | Respuesta del Stub | Estado o excepción | Interacciones exigidas |
|---|---|---|---|
| reservaDisponibleSeConfirmaGuardaYNotifica | true para la reserva | CONFIRMADA | Guardar una vez y notificar una vez la misma reserva |
| reservaNoDisponibleNoSeGuardaNiNotifica | false para la reserva | IllegalStateException, mensaje y PENDIENTE | never guardar; never notificar |
| reservaNulaNoConsultaDependencias | No se configura: no debe consultarse | IllegalArgumentException y mensaje | never en los tres; verifyNoInteractions |

```java
verify(repository, never()).guardar(any());
verify(notificador, never()).enviarConfirmacion(any());
```

never equivale a cero llamadas. any() es un matcher: interesa impedir la operación con cualquier argumento, no únicamente con R-002. No es un valor del negocio. En el caso null, verifyNoInteractions refuerza la ausencia de toda interacción; no se usa un stubbing innecesario. El servicio de confirmar recibe tres dobles válidos, no dependencias null.

## Orden recomendado para mostrar evidencia

1. Mostrar git branch y la rama test/lab2-dobles-cobertura, luego la suite de la actividad 1 conservada.
2. Abrir ConfirmacionLab2Test.java y señalar Arrange, Act y Assert. Mostrar el when que fuerza true, el estado y las dos verificaciones.
3. Mostrar el caso false: excepción, estado PENDIENTE y never con any(). Explicar por qué una reserva rechazada no debe guardarse ni notificarse.
4. Mostrar null: excepción temprana y cero interacciones, sin configurar una respuesta del Stub.
5. Mostrar el log 07_ejecucion_final.txt: 3 pruebas de ConfirmacionLab2Test, 10 previas y 13 en total, cero fallos/errores/omitidas y BUILD SUCCESS. La prueba inicial no cuenta como caso de negocio.
6. Mostrar 05_jacoco_antes.png y 06_hueco_reserva_nula.png: 1 línea y 1 rama pendientes del servicio. Relacionarlas con el rechazo de null.
7. Mostrar 10_jacoco_final.png y 11_codigo_final.png: cero líneas y ramas perdidas del servicio. Explicar que proyecto y servicio tienen porcentajes diferentes.
8. Mostrar git log --oneline --decorate: commits para escenarios, mejora de cobertura y documentación.

## Resultados correctos y respaldo

Las tres pruebas nuevas pasan; el log y el resumen JUnit vinculan los nombres con el resultado. El XML 09_junit_confirmacion.xml conserva los testcases y contadores reales del reporte Surefire, omitiendo propiedades del entorno y mensajes de consola innecesarios. No es una transcripción inventada. Un verify fallido haría fallar su testcase; un resultado verde indica que esas verificaciones se ejecutaron sin fallo, pero no demuestra integración externa.

La comparación exacta del servicio es 20/21 a 21/21 líneas y 11/12 a 12/12 ramas al añadir null. Los CSV contienen valores exactos; las capturas son auxiliares y no sustituyen ejecutar la suite.

## Reproducir

```bash
git clone https://github.com/jordyfajardo-17/UEES-Formativa1-Semana7.git
cd UEES-Formativa1-Semana7
git switch test/lab2-dobles-cobertura
mvn -version
mvn clean test
git log --oneline --decorate
```

Maven debe usar Java 21. Abrir después target/site/jacoco/index.html. El pom heredado ya vincula JaCoCo a test; no se añadió otro plugin. Las advertencias de carga dinámica de Mockito no son fallos de pruebas. El nombre interno Maven conserva semana7-proyecto-base-ae6 porque proviene del ZIP base, no porque se esté trabajando en el repositorio Ae6.

## Reflexión para presentar

En este laboratorio observé que el Stub me permite elegir la disponibilidad sin llamar a un servicio externo, mientras que los Mocks me permiten comprobar los efectos que debe producir o evitar la confirmación. La cobertura reveló que todavía no protegía el rechazo de una reserva nula; añadí una prueba que comprueba tanto la excepción como la ausencia de interacciones. El 100 % del servicio indica que ejecuté sus ramas, pero las aserciones y verificaciones son las que comprueban el comportamiento.

## Uso de IA y autorrevisión

Codex asistió en implementación, ejecución, interpretación, capturas y documentación. Los resultados provienen de ejecuciones reales. Se verificó que producción y las pruebas de actividad 1 permanecen sin cambios, target y tmp siguen ignorados, existen tres pruebas con Mockito y se usan when, thenReturn, verify, never y any. La revisión asistida no equivale a aprobación independiente del docente.
