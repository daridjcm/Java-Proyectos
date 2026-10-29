require('dotenv').config();
const express = require('express');
const mariadb = require('mariadb');
const path = require('path');

const app = express();
app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));

const pool = mariadb.createPool({
  host: process.env.DB_HOST,
  port: Number(process.env.DB_PORT || 3306),
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,
  connectionLimit: 5
});

app.get('/api/health', async (_req, res) => {
  let conn;
  try { conn = await pool.getConnection(); await conn.query('SELECT 1'); res.json({ok:true}); }
  catch (e) { res.status(500).json({ok:false, error:'No se pudo conectar con MariaDB. Revisa el archivo .env.'}); }
  finally { if (conn) conn.release(); }
});

app.get('/api/facultades', async (_req, res) => {
  let conn;
  try {
    conn = await pool.getConnection();
    const rows = await conn.query('SELECT id, facultad FROM facultades ORDER BY id DESC');
    res.json(rows);
  } catch (e) { res.status(500).json({error:'No se pudieron consultar las facultades. Verifica que exista la tabla facultades.'}); }
  finally { if (conn) conn.release(); }
});

app.get('/api/estudiantes', async (_req, res) => {
  let conn;
  try {
    conn = await pool.getConnection();
    const rows = await conn.query(`SELECT id, cod_estudiantil, cedula_ciudadania, fecha_nacimiento,
      edad, nombre, apellido, id_facultad, rama, estado, fecha_matricula
      FROM estudiantes ORDER BY id DESC`);
    res.json(rows);
  } catch (e) { res.status(500).json({error:'No se pudieron consultar los estudiantes. Verifica que exista la tabla estudiantes.'}); }
  finally { if (conn) conn.release(); }
});

app.post('/api/facultades', async (req, res) => {
  const nombre = String(req.body.facultad || '').trim();
  if (!nombre) return res.status(400).json({error:'Escribe el nombre de la facultad.'});
  let conn;
  try {
    conn = await pool.getConnection();
    const result = await conn.query('INSERT INTO facultades (facultad) VALUES (?)', [nombre]);
    res.status(201).json({id:Number(result.insertId), facultad:nombre});
  } catch (e) { res.status(500).json({error:'No se pudo guardar la facultad. Verifica la tabla y la conexión.'}); }
  finally { if (conn) conn.release(); }
});

app.post('/api/estudiantes', async (req, res) => {
  const d = req.body;
  const required = ['cod_estudiantil','cedula_ciudadania','fecha_nacimiento','nombre','apellido','id_facultad','rama','fecha_matricula'];
  for (const key of required) if (d[key] === undefined || String(d[key]).trim() === '')
    return res.status(400).json({error:'Completa todos los campos obligatorios.'});
  const nacimiento = new Date(`${d.fecha_nacimiento}T00:00:00`);
  const hoy = new Date();
  let edad = hoy.getFullYear() - nacimiento.getFullYear();
  if (hoy.getMonth() < nacimiento.getMonth() || (hoy.getMonth() === nacimiento.getMonth() && hoy.getDate() < nacimiento.getDate())) edad--;
  if (!Number.isFinite(edad) || edad < 0 || edad > 120) return res.status(400).json({error:'La fecha de nacimiento no es válida.'});
  let conn;
  try {
    conn = await pool.getConnection();
    const result = await conn.query(`INSERT INTO estudiantes
      (cod_estudiantil, cedula_ciudadania, fecha_nacimiento, edad, nombre, apellido, id_facultad, rama, estado, fecha_matricula)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      [Number(d.cod_estudiantil), Number(d.cedula_ciudadania), d.fecha_nacimiento, edad,
       String(d.nombre).trim(), String(d.apellido).trim(), Number(d.id_facultad),
       String(d.rama).trim(), d.estado === false || d.estado === 'false' ? false : true, d.fecha_matricula]);
    res.status(201).json({id:Number(result.insertId), message:'Estudiante matriculado correctamente.'});
  } catch (e) { res.status(500).json({error:'No se pudo matricular. Verifica que el ID de facultad exista y que los datos no estén duplicados.'}); }
  finally { if (conn) conn.release(); }
});

const port = Number(process.env.PORT || 3000);
app.listen(port, () => console.log(`Universidad web disponible en http://localhost:${port}`));
