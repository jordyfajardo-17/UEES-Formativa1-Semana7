# Actividad formativa 1 Diseño de casos y JUnit 5

**Estudiante:** Jordy Alessandro Fajardo Figueroa
**Carrera:** Ingeniería en Ciencias de la Computación
**Materia:** Diseño de Software UCOM0310
**Fecha:** 30 de septiembre de 2026

Repositorio independiente de Ae6: https://github.com/jordyfajardo-17/UEES-Formativa1-Semana7

## Producto entregado

- [Matriz de nueve casos y trazabilidad](docs/01_MATRIZ_CASOS_PLANTILLA.md)
- [Suite JUnit con AAA](src/test/java/edu/uees/testing/service/ReservaServiceTest.java)
- [Reflexión y experimento de frontera](docs/02_REFLEXION_FORMATIVA1.md)
- [Reporte de entrega](docs/03_REPORTE_FORMATIVA1.pdf)
- [Logs de ejecución](docs/evidencias)

## Reproducción

Requisitos: JDK 21, Maven 3.9+ y Git. Comprueba `mvn -version` para confirmar que Maven usa Java 21.

```bash
mvn clean test
```

Resultado final: 10 pruebas, cero fallos, errores u omitidas. Nueve prueban reglas de negocio y una conserva el arranque de JUnit.

Las dependencias del servicio se construyen con null únicamente para puedeCancelar y calcularTotal, que no las consultan. confirmar no se prueba en este laboratorio; corresponde al laboratorio 2. No se añadieron mocks.

## Experimento controlado

Se sustituyó temporalmente `horasAnticipacion >= 2` por `horasAnticipacion > 2`. `mvn test` produjo exactamente un fallo: dosHorasEsElLimitePermitido. Después se restauró el archivo original y se ejecutó `mvn clean test` con éxito. El defecto temporal no forma parte del código publicado.

Los logs 01 a 04 muestran la línea base, la suite antes del experimento, el fallo intencional y la ejecución final. El pom y los archivos productivos conservan el proyecto base. JaCoCo se genera por la configuración heredada, pero su análisis no es requisito de la actividad 1.

## Uso de IA

OpenAI Codex asistió en lectura de requisitos, diseño de matriz, implementación JUnit, ejecución del experimento, documentación y publicación. Los resultados se respaldan con logs reales. El estudiante debe revisar y comprender cada caso antes de entregar.

## Actividad formativa 2

Continúa este repositorio en la rama `test/lab2-dobles-cobertura`. Conserva las diez pruebas de la actividad 1 y añade tres de confirmación con Mockito. [Guía de evidencias](docs/lab2/01_GUIA_EVIDENCIAS.md), [análisis JaCoCo](docs/02_ANALISIS_COBERTURA_PLANTILLA.md) y [reporte](docs/lab2/02_REPORTE_FORMATIVA2.pdf). La rama se entrega sin fusionar: `main` conserva la actividad 1.
