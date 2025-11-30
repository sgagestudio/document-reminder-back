-- Organizaciones de ejemplo
INSERT INTO organizations (id, name, email, domain, from_display_name, timezone) VALUES
 ('11111111-1111-1111-1111-111111111111', 'Acme Analytics', 'ops@acme.test', 'acme.test', 'Acme Ops', 'America/Bogota'),
 ('22222222-2222-2222-2222-222222222222', 'Globex Logistics', 'noreply@globex.test', 'globex.test', 'Globex Logistics', 'America/Mexico_City');

-- Usuarios sin autenticación (contraseñas en texto claro solo para pruebas locales)
INSERT INTO users (id, name, email, password, organization_id, business_area, role) VALUES
 ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Laura Admin', 'laura@acme.test', 'changeme', '11111111-1111-1111-1111-111111111111', 'Operaciones', 'UserAdmin'),
 ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Pedro Worker', 'pedro@acme.test', 'changeme', '11111111-1111-1111-1111-111111111111', 'Cobranza', 'UserWorker'),
 ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Marta Admin', 'marta@globex.test', 'changeme', '22222222-2222-2222-2222-222222222222', 'Finanzas', 'UserAdmin');

-- Clientes de cada organización
INSERT INTO clients (id, name, email, organization_id, phone, created_at, address, notes, status, assigned_user_id, custom_fields) VALUES
 ('d1111111-2222-3333-4444-555555555555', 'Constructora Andes', 'contacto@andes.test', '11111111-1111-1111-1111-111111111111', '+57 3200000001', '2024-01-15T10:00:00Z', 'Calle 123 #45-67, Bogotá', 'Cliente prioritario para recordatorios mensuales.', 'ACTIVE', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '{"rut":"12345678-9"}'),
 ('d2222222-3333-4444-5555-666666666666', 'Finanzas Delta', 'soporte@delta.test', '11111111-1111-1111-1111-111111111111', '+57 3200000002', '2024-02-10T15:30:00Z', 'Av. Siempre Viva 742, Medellín', 'Prefiere recibir comunicaciones los lunes.', 'ACTIVE', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '{"nit":"987654321"}'),
 ('e1111111-aaaa-bbbb-cccc-dddddddddddd', 'Transportes Sur', 'contacto@sur.test', '22222222-2222-2222-2222-222222222222', '+52 5500000001', '2024-03-05T09:00:00Z', 'CDMX, colonia Centro', 'Segmento de logística, enviar resúmenes semanales.', 'ACTIVE', 'cccccccc-cccc-cccc-cccc-cccccccccccc', '{"rfc":"TST1234567"}');

-- Etiquetas para los clientes
INSERT INTO client_tags (client_id, tag) VALUES
 ('d1111111-2222-3333-4444-555555555555', 'prioritario'),
 ('d1111111-2222-3333-4444-555555555555', 'mensual'),
 ('d2222222-3333-4444-5555-666666666666', 'lunes'),
 ('e1111111-aaaa-bbbb-cccc-dddddddddddd', 'logistica');

-- Plantillas de correo
INSERT INTO templates (id, organization_id, type, name, subject, body, language, user_id, is_default) VALUES
 (1, '11111111-1111-1111-1111-111111111111', 'REMINDER', 'Recordatorio mensual', 'Documentos pendientes de {{client}}', 'Hola {{client}}, recuerda subir tus documentos pendientes esta semana.', 'es', NULL, true),
 (2, '11111111-1111-1111-1111-111111111111', 'FOLLOW_UP', 'Seguimiento semanal', 'Seguimiento con {{client}}', 'Equipo de cobranza: revisar avances con {{client}} y actualizar notas.', 'es', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', false),
 (3, '22222222-2222-2222-2222-222222222222', 'REMINDER', 'Resumen semanal', 'Resumen de entregas', 'Hola {{client}}, adjuntamos tu resumen semanal de entregas.', 'es', NULL, true);

-- Solicitudes de datos programadas
INSERT INTO data_requests (id, organization_id, client_id, template_id, title, description, temporality, status, next_execution, last_execution, active) VALUES
 ('f1111111-aaaa-bbbb-cccc-eeeeeeeeeeee', '11111111-1111-1111-1111-111111111111', 'd1111111-2222-3333-4444-555555555555', 1, 'Recordatorio mensual Andes', 'Recolectar estados financieros y documentos tributarios.', 'MONTHLY', 'PENDING', '2024-12-01T13:00:00Z', NULL, true),
 ('f2222222-aaaa-bbbb-cccc-eeeeeeeeeeee', '11111111-1111-1111-1111-111111111111', 'd2222222-3333-4444-5555-666666666666', 2, 'Seguimiento semanal Delta', 'Validar avances de cobranza y notas internas.', 'WEEKLY', 'PENDING', '2024-11-25T14:00:00Z', NULL, true),
 ('f3333333-aaaa-bbbb-cccc-eeeeeeeeeeee', '22222222-2222-2222-2222-222222222222', 'e1111111-aaaa-bbbb-cccc-dddddddddddd', 3, 'Resumen semanal Transportes Sur', 'Enviar recordatorio de entregas a cliente logístico.', 'WEEKLY', 'RUNNING', '2024-11-26T16:00:00Z', '2024-11-19T16:00:00Z', true);
