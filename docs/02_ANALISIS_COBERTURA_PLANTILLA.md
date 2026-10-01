# Análisis JaCoCo del laboratorio 2

## Evolución medida

| Etapa | Pruebas | Líneas de ReservaService | Ramas | Instrucciones |
|---|---:|---:|---:|---:|
| Actividad 1 conservada | 10 | 13/21 = 61,90 % | 8/12 = 66,67 % | 46/74 = 62,16 % |
| Disponible y no disponible | 12 | 20/21 = 95,24 % | 11/12 = 91,67 % | 69/74 = 93,24 % |
| Reserva nula añadida | 13 | 21/21 = 100 % | 12/12 = 100 % | 74/74 = 100 % |

Las tres etapas terminaron con cero fallos, errores u omitidas. Los CSV incluyen contadores exactos y las capturas muestran el HTML real de JaCoCo. El HTML trunca algunos porcentajes: por ejemplo muestra 91 % para 11/12 ramas; la tabla calcula 91,67 % sin cambiar los contadores.

## Método con menor cobertura y mejora

En la línea base, confirmar no estaba ejecutado (0/8 líneas, 0/4 ramas). Tras las dos primeras pruebas, confirmar tenía 7/8 líneas y 3/4 ramas: la condición reserva == null era parcialmente cubierta y su excepción no se ejecutaba. La primera columna Cov. de confirmar mostraba 82 % de instrucciones y la segunda 75 % de ramas. No eran porcentajes de líneas.

Se añadió reservaNulaNoConsultaDependencias a partir de este hueco: exige IllegalArgumentException, el mensaje Reserva obligatoria y cero consultas/escrituras/notificaciones. También usa verifyNoInteractions para rechazar cualquier interacción, incluso con argumentos null. Así se alcanzaron 8/8 líneas y 4/4 ramas en confirmar; los cuatro métodos de ReservaService quedaron completos.

## Comportamientos todavía sin proteger

Reserva conserva 9/14 líneas (64,29 %) y 3/6 ramas (50 %). Faltan identificadores nulos/vacíos/en blanco, tipo nulo, getId, getTipo y cancelar. El proyecto completo registra 34/39 líneas (87,18 %) y 15/18 ramas (83,33 %), aunque el servicio alcanza 100 %. No se añaden pruebas de dominio solo para inflar el indicador.

Tampoco se comprueban integración real, disponibilidad remota, recuperación cuando guardar o notificar fallan, transacciones ni concurrencia. Si guardar falla, el objeto ya está CONFIRMADA en memoria; la política de recuperación debe definirse antes de exigirla con pruebas. Los importes no especifican NaN, infinito o redondeo.

## Código cubierto pero mal probado

La prueba inicial assertTrue(true) no valida una regla. Asimismo, ejecutar confirmar sin comparar el estado ni verificar los colaboradores podría aumentar cobertura sin detectar la ausencia de guardar o notificar. Nuestras pruebas exigen estado, misma instancia y una sola llamada en el éxito; ausencia de efectos en los rechazos. Una implementación con descuento incorrecto puede recorrer las mismas ramas: son los valores esperados 85 y 90 los que la rechazan.

## Cómo leer el HTML

Abrir target/site/jacoco/index.html, entrar en edu.uees.testing.service y ReservaService. Missed Instructions y su Cov. miden instrucciones. Missed Branches y su Cov. miden caminos de decisión. Missed junto a Lines indica líneas no ejecutadas y Lines el total; líneas cubiertas = total menos perdidas. En el fuente: rojo significa instrucciones no ejecutadas, amarillo cobertura parcial y verde ejecutadas; los diamantes marcan decisiones. Una línea verde no asegura todos los resultados de negocio.

Evidencias y guía: docs/lab2/01_GUIA_EVIDENCIAS.md. El reporte se regenera con mvn clean test usando Java 21. target no se versiona.
