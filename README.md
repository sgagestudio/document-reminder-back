# Document Reminder – Estado del backend y plan para cubrir los requisitos

Este repositorio contiene un esqueleto de API en Spring Boot para Document Reminder. A continuación encontrarás un resumen del estado actual del código, los huecos respecto a los requisitos funcionales descritos y una guía paso a paso de lo que todavía falta implementar.

## 1. Estado actual del código
- **Aplicación base:** `DocumentReminderApplication` arranca Spring Boot 3.5 y habilita `@EnableScheduling`, pero no existe ningún *scheduled job* que consuma esa anotación todavía. 【F:src/main/java/com/sgagestudio/demo/document_reminder/DocumentReminderApplication.java†L4-L16】
- **Modelo de datos parcial:** existen entidades para organizaciones, usuarios, clientes, plantillas, tokens y solicitudes de datos, aunque varias carecen de relaciones entre sí, validaciones o getters/setters completos (por ejemplo `TokenEntity`). 【F:src/main/java/com/sgagestudio/demo/document_reminder/data/entity/OrganizationEntity.java†L1-L39】【F:src/main/java/com/sgagestudio/demo/document_reminder/data/entity/UserEntity.java†L1-L98】【F:src/main/java/com/sgagestudio/demo/document_reminder/data/entity/ClientEntity.java†L1-L67】【F:src/main/java/com/sgagestudio/demo/document_reminder/data/entity/TemplateEntity.java†L1-L55】【F:src/main/java/com/sgagestudio/demo/document_reminder/data/entity/DataRequestEntity.java†L1-L63】【F:src/main/java/com/sgagestudio/demo/document_reminder/data/entity/TokenEntity.java†L1-L24】
- **Controladores CRUD incompletos:** hay controladores para usuarios, clientes y plantillas, pero los `GET` usan `@RequestBody` y sólo exponen operaciones básicas. No existe controlador para organizaciones, *data requests* o envío de correos. 【F:src/main/java/com/sgagestudio/demo/document_reminder/controller/UserController.java†L1-L39】【F:src/main/java/com/sgagestudio/demo/document_reminder/controller/ClientController.java】【F:src/main/java/com/sgagestudio/demo/document_reminder/controller/TemplateController.java†L1-L41】
- **Servicios y repositorios:** los servicios (`UserServiceImpl`, `ClientServiceImpl`, `TemplateServiceImpl`) delegan en repositorios JPA, pero las consultas personalizadas contienen errores de sintaxis/campos inexistentes (p. ej. `ClientRepository.findAllByOrganizationName` referencia `organizationName`, columna que no existe en `ClientEntity`, y `TemplateRepository` tiene un `subject.` en la sentencia JPQL). 【F:src/main/java/com/sgagestudio/demo/document_reminder/service/UserServiceImpl.java†L1-L40】【F:src/main/java/com/sgagestudio/demo/document_reminder/service/ClientServiceImpl.java†L1-L41】【F:src/main/java/com/sgagestudio/demo/document_reminder/service/TemplateServiceImpl.java†L1-L34】【F:src/main/java/com/sgagestudio/demo/document_reminder/repository/UserRepository.java†L1-L15】【F:src/main/java/com/sgagestudio/demo/document_reminder/repository/ClientRepository.java†L1-L33】【F:src/main/java/com/sgagestudio/demo/document_reminder/repository/TemplateRepository.java†L1-L20】
- **Autenticación y seguridad:** el `AuthenticationController` y `AuthenticationServiceImpl` están comentados; no hay configuración de filtros, `SecurityFilterChain`, ni endpoints de registro/login operativos. El `pom.xml` mezcla Spring Boot 3.5 con `spring-boot-starter-security` 2.7.5, lo que impediría compilar. 【F:src/main/java/com/sgagestudio/demo/document_reminder/controller/AuthenticationController.java†L1-L33】【F:src/main/java/com/sgagestudio/demo/document_reminder/service/AuthenticationServiceImpl.java†L1-L68】【F:pom.xml†L4-L76】
- **Infraestructura de correo:** existe `GmailSenderService`, pero envía siempre desde `sgagestudio@gmail.com`, sin respetar remitentes por organización ni plantillas asociadas a clientes. Tampoco hay integración con `TemplateEntity` o `DataRequestEntity`. 【F:src/main/java/com/sgagestudio/demo/document_reminder/service/GmailSenderService.java†L1-L46】
- **Configuración:** `application.yml` apunta a una base local y mezcla valores reales con variables de entorno; además `starttls.enable` aparece mal escrito (sin salto de línea). 【F:src/main/resources/application.yml†L1-L19】

## 2. Brecha vs. requisitos del producto
1. **Multi-empresa completa**
   - Falta un flujo para crear organizaciones junto con el primer usuario administrador y para asociar remitentes SMTP/dominios personalizados. Actualmente sólo se guarda `organizationId` en los usuarios/clientes, pero no hay endpoints ni lógica para crearlas o validarlas. 【F:src/main/java/com/sgagestudio/demo/document_reminder/data/entity/OrganizationEntity.java†L7-L36】【F:src/main/java/com/sgagestudio/demo/document_reminder/data/entity/UserEntity.java†L17-L70】
   - No se aplican restricciones por organización en los repositorios/servicios; cualquier usuario podría consultar otras organizaciones si conociera el nombre.

2. **Gestión de usuarios y roles**
   - No hay tablas ni servicios que asignen permisos diferenciados entre `Admin` y `Worker`. `UserRole` existe pero nunca se usa en filtros o anotaciones. 【F:src/main/java/com/sgagestudio/demo/document_reminder/data/entity/UserEntity.java†L33-L70】
   - No existe un endpoint para invitar o activar usuarios, ni validación de dominios.

3. **Gestión completa de clientes**
   - Los DTOs permiten listar/crear clientes, pero faltan filtros, estados personalizados y campos como `status`, `tags` o `customFields` mencionados en la descripción.
   - Las consultas actuales se basan en `organizationName`, campo inexistente en la entidad, por lo que aún no se puede persistir/consultar datos reales. 【F:src/main/java/com/sgagestudio/demo/document_reminder/repository/ClientRepository.java†L17-L27】

4. **Plantillas personalizadas vs. por defecto**
   - No hay lógica para distinguir plantillas globales (sin `userId`) de plantillas personalizadas (con `userId`), ni endpoints para gestionar las plantillas por defecto.
   - Tampoco existe relación entre plantillas y clientes/data requests, lo cual impide montar campañas o recordatorios.

5. **Data Requests y automatizaciones**
   - Aunque `DataRequestEntity` está definido, no existe repositorio, servicio ni controlador que permita crear las tareas recurrentes, adjuntar clientes/plantillas o programar envíos.
   - No hay *scheduler* que consuma esas definiciones para disparar correos según la temporalidad (diaria, semanal, etc.).

6. **Motor de notificaciones**
   - `GmailSenderService` no respeta remitentes dinámicos, ni soporta adjuntos, ni plantillas HTML almacenadas en base de datos.
   - No existe integración con colas o trabajos asincrónicos que permitan reintentos y trazabilidad de envíos.

7. **Seguridad y autenticación JWT**
   - Falta el `SecurityFilterChain`, la capa de persistencia de tokens (los campos existen pero no se usan) y la lógica de registro/login/refresco.
   - No se implementó aún un *middleware* multitenant que limite el acceso de cada token a su organización.

8. **Observabilidad y pruebas**
   - No hay tests unitarios/integración, métricas ni manejo de excepciones centralizado.

## 3. Pasos recomendados para completar el proyecto
1. **Corregir configuración y dependencias básicas**
   - Alinear todas las dependencias con Spring Boot 3.5 (especialmente `spring-boot-starter-security`).
   - Añadir perfiles (`application-dev.yml`, `application-prod.yml`) y mover credenciales sensibles a variables de entorno.

2. **Diseñar la capa de seguridad multi-tenant**
   - Implementar endpoints `/auth/register` y `/auth/login` que creen la organización cuando no exista y asignen `UserRole.ADMIN` al creador.
   - Configurar `SecurityFilterChain` + JWT filters y asegurar que cada consulta se filtra por `organizationId` del usuario autenticado.

3. **Completar la gestión de organizaciones/usuarios**
   - Crear endpoints CRUD para organizaciones (nombre, dominio, remitente SMTP, preferencias).
   - Añadir servicios para invitar `Worker`s, cambiar roles, desactivar usuarios y auditar accesos.

4. **Expandir la gestión de clientes**
   - Corregir el esquema (añadir `status`, `customFields`, `tags`, `updatedAt`) y los repositorios.
   - Implementar filtros avanzados (por estado, etiquetas, fecha) y asignación de clientes a `Worker`s específicos.

5. **Implementar plantillas y campañas**
   - Distinguir plantillas por defecto vs. personalizadas (`userId = null`) y exponer endpoints para listarlas/aplicarlas.
   - Permitir contenido dinámico (variables de cliente) y almacenamiento de idioma/segmento.

6. **Construir Data Requests y automatizaciones**
   - Crear repositorio/servicio/controlador para `DataRequestEntity` enlazando cliente + plantilla + temporización.
   - Implementar un *scheduler* que, en función de la temporalidad, genere eventos y envíe correos mediante colas o `@Scheduled` jobs resilientes.

7. **Motor de correo multi-empresa**
   - Configurar remitentes SMTP por organización (subdominio `organization@sgagestudio.com` o dominio propio autenticado) y permitir adjuntar archivos.
   - Integrar `GmailSenderService` (o un servicio neutral) con `TemplateEntity` y `DataRequestEntity` para componer el correo antes de enviar.

8. **Monitoreo, auditoría y pruebas**
   - Añadir logs estructurados, métricas (Micrometer) y almacenamiento del historial de envíos/errores.
   - Crear una suite de pruebas unitarias y de integración para controladores/servicios, además de pruebas de seguridad.

9. **Documentación y DX**
   - Documentar los endpoints (OpenAPI/Swagger), añadir scripts de `docker-compose` para Postgres/Brevo y definir un *onboarding* claro para nuevos desarrolladores.

Con estos pasos, el backend alcanzará la funcionalidad descrita: organizaciones aisladas, usuarios con roles, clientes administrados, plantillas y recordatorios automatizados con correos personalizados y seguros.
