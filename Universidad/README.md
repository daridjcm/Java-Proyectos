# Universidad — interfaz web + API MariaDB

Este proyecto agrega una interfaz web al proyecto Java del ZIP original. Permite registrar estudiantes y facultades y consultar las tablas con actualización automática cada 3 segundos.

## Requisitos
- Node.js 18 o superior
- MariaDB/MySQL ejecutándose
- Base de datos configurada y accesible

## Instalación
1. En MariaDB, ejecuta `schema.sql` si todavía no existen las tablas. **Si ya tienes tablas con datos, primero revisa el esquema y haz una copia de seguridad; no recrees tablas existentes a ciegas.**
2. Copia `.env.example` como `.env` y completa los datos de conexión.
3. En una terminal, dentro de esta carpeta, ejecuta:
   ```bash
   npm install
   npm start
   ```
4. Abre `http://localhost:3000`.

## Configuración
`DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER` y `DB_PASSWORD` deben coincidir con tu servidor. El `database.properties` del ZIP original tiene los valores vacíos, así que no se puede detectar automáticamente el servidor ni sus credenciales.

## Sobre la actualización en tiempo real
La página consulta la API cada 3 segundos. Los registros añadidos mediante el formulario aparecen después de guardarse. Los cambios realizados por otros clientes también aparecerán en la siguiente consulta (no es un flujo push de WebSockets).

## Nota de esquema
El API usa `facultades(id, facultad)` y `estudiantes(id, cod_estudiantil, cedula_ciudadania, fecha_nacimiento, edad, nombre, apellido, id_facultad, rama, estado, fecha_matricula)`, inferido de las sentencias SQL del código Java incluido. Si tu esquema real tiene nombres o tipos distintos, ajusta `server.js` antes de usarlo.
