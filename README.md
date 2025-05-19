# Solución

## Prueba Técnica Inditex Core Platform

## Descripción del Proyecto

Este proyecto implementa un servicio REST para consultar precios de productos en función de una marca, un producto y una fecha específica. Utiliza una arquitectura hexagonal basada en los principios de **Domain-Driven Design (DDD)** para garantizar un diseño modular, escalable y fácil de mantener.

---

## Arquitectura del Proyecto

### Arquitectura Hexagonal

La arquitectura hexagonal, también conocida como **Ports and Adapters**, organiza el sistema en capas independientes que interactúan a través de interfaces. Esto permite desacoplar la lógica de negocio de las tecnologías externas.

#### Componentes Principales:
1. **Capa de Dominio**:
   - Contiene las reglas de negocio y las entidades principales.
   - Es independiente de cualquier tecnología externa.
   - Ejemplo: `Price`, `PriceParam`, `FinalPrice`.

2. **Capa de Aplicación**:
   - Implementa los casos de uso del sistema.
   - Orquesta las interacciones entre el dominio y las capas externas.
   - Ejemplo: `PriceUseCase`.

3. **Capa de Infraestructura**:
   - Contiene los adaptadores para interactuar con tecnologías externas como bases de datos, APIs, etc.
   - Ejemplo: `PriceService`, `PriceDataRepository`.

#### Diagrama de Arquitectura
```plaintext
+-------------------+       +-------------------+
|   Infraestructura |       |   Infraestructura |
| (Adaptadores Sec.)|       | (Adaptadores Pri.)|
+-------------------+       +-------------------+
         ^                           |
         |                           v
+-------------------------------------------+
|               Capa de Aplicación           |
|  (Casos de Uso, Servicios de Aplicación)   |
+-------------------------------------------+
         ^
         |
+-------------------+
|   Capa de Dominio |
| (Entidades, VO,   |
|  Repositorios)    |
+-------------------+
```

---

### Principios de Domain-Driven Design (DDD)

El diseño del sistema sigue los principios de DDD para garantizar que el código refleje fielmente el dominio del negocio.

1. **Entidades**:
    - Representan objetos con identidad única.
    - Ejemplo: `Price`.

2. **Value Objects**:
    - Representan conceptos del dominio sin identidad.
    - Ejemplo: `PriceParam`.

3. **Repositorios**:
    - Interfaces para acceder a los agregados.
    - Ejemplo: `PriceRepository`.

4. **Casos de Uso**:
    - Encapsulan la lógica de aplicación.
    - Ejemplo: `PriceUseCase`.

5. **Mapeadores**:
    - Transforman datos entre las capas.
    - Ejemplo: `PriceMapper`.

---

## Guía de Ejecución

### Prerrequisitos
- **Java 17** o superior.
- **Gradle** instalado.
- **Docker** (opcional, si se usa una base de datos en contenedor).

### Pasos para Ejecutar el Proyecto
1. Clona el repositorio:
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   cd [NOMBRE_DEL_PROYECTO]
   ```
2. Compila el proyecto:
   ```bash
   ./gradlew build
   ```
3. Ejecuta la aplicación:
   ```bash
   ./gradlew bootRun
   ```

### Ejecución de Tests Automatizados
Para ejecutar los tests, usa el siguiente comando:
```bash
./gradlew test
```

### Acceso a la Documentación Swagger
1. Inicia la aplicación.
2. Accede a la documentación Swagger en:
   ```
   http://localhost:8080/webjars/swagger-ui/index.html
   ```
 [![Swagger](images/swagger.png)](http://localhost:8080/webjars/swagger-ui/index.html)
---

## Cobertura de Código

### Generar y Visualizar el Reporte de Cobertura
1. Genera el reporte de cobertura con JaCoCo:
   ```bash
   ./gradlew jacocoTestReport
   ```
2. Abre el archivo `build/reports/jacoco/test/html/index.html` en tu navegador.

[![JaCoCo](images/jacocoReport.png)]()
---

## Caso de Uso Principal

### Descripción del Flujo
El caso de uso principal permite consultar el precio más relevante para un producto y marca en una fecha específica.

#### Flujo desde el Endpoint hasta el Repositorio:
1. **Controlador (`PriceController`)**:
    - Recibe la solicitud HTTP.
    - Valida los parámetros de entrada.
    - Convierte los datos de entrada en un modelo de dominio (`PriceParam`).
    - Llama al caso de uso correspondiente.

2. **Caso de Uso (`PriceUseCase`)**:
    - Orquesta la lógica de negocio.
    - Llama al repositorio para obtener los precios relevantes.
    - Aplica la lógica de prioridad para seleccionar el precio más relevante.
    - Devuelve el resultado como un modelo de dominio (`FinalPrice`).

3. **Repositorio (`PriceService`)**:
    - Implementa la lógica de acceso a datos.
    - Consulta la base de datos utilizando `PriceDataRepository`.
    - Convierte los datos de la base en modelos de dominio (`Price`).

4. **Base de Datos**:
    - Almacena los precios en la tabla `PRICES`.

#### Diagrama de Secuencia
```plaintext
Usuario -> Controlador -> Caso de Uso -> Repositorio -> Base de Datos
```

---

## Diagramas

### Diagrama de Arquitectura
```plaintext
+-------------------+       +-------------------+
|   Infraestructura |       |   Infraestructura |
| (Adaptadores Sec.)|       | (Adaptadores Pri.)|
+-------------------+       +-------------------+
         ^                           |
         |                           v
+-------------------------------------------+
|               Capa de Aplicación           |
|  (Casos de Uso, Servicios de Aplicación)   |
+-------------------------------------------+
         ^
         |
+-------------------+
|   Capa de Dominio |
| (Entidades, VO,   |
|  Repositorios)    |
+-------------------+
```

### Diagrama de Flujo del Caso de Uso
```plaintext
+-------------------+
|   PriceController |
+-------------------+
          |
          v
+-------------------+
|    PriceUseCase   |
+-------------------+
          |
          v
+-------------------+
|    PriceService   |
+-------------------+
          |
          v
+-------------------+
|    Base de Datos  |
+-------------------+
```

---

## Estructura del Proyecto

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── com.bcnc.domain/       # Capa de Dominio
│   │   ├── com.bcnc.application/  # Capa de Aplicación
│   │   ├── com.bcnc.infrastructure/ # Capa de Infraestructura
│   └── resources/
│       ├── application.yml           # Configuración
├── test/                             # Tests unitarios y de integración
build.gradle                          # Configuración de Gradle
```

---
