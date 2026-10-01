# Reflexión de la actividad formativa 1

## Qué prueba detectó mejor el error de frontera y por qué

La prueba dosHorasEsElLimitePermitido detectó el error porque evalúa exactamente la entrada 2, donde cambia el resultado entre `>= 2` y `> 2`. La regla permite cancelar con dos horas, así que el esperado es true. Al introducir temporalmente `> 2`, el resultado fue false y la aserción falló. La entrada habitual 5 sigue siendo válida en ambas implementaciones y no detecta ese defecto; tampoco lo detectan 1 ni 0 porque ambas implementaciones las rechazan.

## Evidencia y restauración

La línea base pasó una prueba. La suite ampliada pasó diez pruebas. El microexperimento ejecutó diez pruebas con un fallo, cero errores y cero omitidas, identificado como dosHorasEsElLimitePermitido. Se restauró el archivo productivo original y la ejecución final desde cero volvió a pasar diez pruebas. Los resultados esperados no se cambiaron para obtener verde.

## Estructura AAA y límites

Cada prueba de negocio separa preparación, ejecución y aserción con comentarios Arrange, Act y Assert. Los descuentos usan tolerancia 0.001 para double; la entrada negativa comprueba IllegalArgumentException y su mensaje. La prueba inicial se conserva, pero no se considera evidencia de comportamiento de negocio.

No se cubren confirmar, integraciones ni políticas de redondeo, NaN o infinito. Los colaboradores null son válidos para estos dos métodos puros porque no se utilizan. La actividad 2 deberá controlar las dependencias con Stub y Mock.

## Autorrevisión

- [x] Nueve casos diseñados antes de implementar JUnit, con riesgo y esperado.
- [x] Nueve pruebas de negocio más la inicial, con nombres expresivos.
- [x] Casos normales, alternativos, límites e inválido presentes.
- [x] Estructura AAA y aserciones coherentes con la regla.
- [x] Experimento real con fallo esperado y restauración de producción.
- [x] Ejecución final limpia y logs incluidos.
- [x] Repositorio separado; target y tmp no se versionan.
- [x] Uso de IA declarado; no se cambiaron esperados para hacer pasar pruebas.
