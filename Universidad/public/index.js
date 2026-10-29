let activeTab = "estudiantes", students = [], faculties = [];

const $ = (id) => document.getElementById(id);

function esc(v) {
  return String(v ?? "").replace(
    /[&<>"']/g,
    (c) =>
      ({
        "&": "&amp;",
        "<": "&lt;",
        ">": "&gt;",
        '"': "&quot;",
        "'": "&#39;",
      })[c],
  );
}

async function api(url, options = {}) {
  const r = await fetch(url, {
    headers: { "Content-Type": "application/json" },
    ...options,
  });
  let data = {};
  try {
    data = await r.json();
  } catch {}
  if (!r.ok) throw new Error(data.error || "Ocurrió un error en la solicitud.");
  return data;
}

function msg(text, type = "success") {
  const el = $("message");
  el.textContent = text;
  el.className = "message show " + type;
  setTimeout(() => (el.className = "message"), 5000);
}

async function loadAll() {
  try {
    const [s, f] = await Promise.all([
      api("/api/estudiantes"),
      api("/api/facultades"),
    ]);
    students = s;
    faculties = f;
    $("studentCount").textContent = s.length;
    $("facultyCount").textContent = f.length;
    $("connection").textContent = "● Base de datos conectada";
    $("connection").style.color = "#d8ffe9";
    $("liveState").textContent = "En vivo";
    renderTable();
    renderFacultyOptions();
    $("lastUpdate").textContent =
      "Última consulta: " + new Date().toLocaleTimeString("es-CO");
  } catch (e) {
    $("connection").textContent = "● Sin conexión";
    $("liveState").textContent = "Desconectado";
    $("tbody").innerHTML =
      `<tr><td class="empty">${esc(e.message)}<br>Inicia el servidor y revisa el archivo .env.</td></tr>`;
  }
}

function renderFacultyOptions() {
  const select = $("facultad"),
    previous = select.value;
  select.innerHTML =
    '<option value="">Selecciona una facultad</option>' +
    faculties
      .map((f) => `<option value="${esc(f.id)}">${esc(f.facultad)}</option>`)
      .join("");
  if (faculties.some((f) => String(f.id) === previous)) select.value = previous;
}

function renderTable() {
  const q = $("search").value.toLocaleLowerCase("es");
  let cols, rows;
  if (activeTab === "estudiantes") {
    cols = [
      ["id", "ID"],
      ["cod_estudiantil", "Código"],
      ["cedula_ciudadania", "Cédula"],
      ["nombre", "Nombre"],
      ["apellido", "Apellido"],
      ["fecha_nacimiento", "Nacimiento"],
      ["edad", "Edad"],
      ["id_facultad", "ID facultad"],
      ["rama", "Programa"],
      ["estado", "Estado"],
      ["fecha_matricula", "Matrícula"],
    ];
    rows = students;
  } else {
    cols = [
      ["id", "ID"],
      ["facultad", "Facultad"],
    ];
    rows = faculties;
  }
  rows = rows.filter((r) =>
    cols.some((c) =>
      String(r[c[0]] ?? "")
        .toLocaleLowerCase("es")
        .includes(q),
    ),
  );
  $("thead").innerHTML =
    "<tr>" + cols.map((c) => `<th>${c[1]}</th>`).join("") + "</tr>";
  $("tbody").innerHTML = rows.length
    ? rows
        .map(
          (r) =>
            "<tr>" +
            cols
              .map((c) => {
                let v = r[c[0]];
                if (c[0] === "estado")
                  return `<td><span class="badge ${v ? "" : "off"}">${v ? "Activo" : "Inactivo"}</span></td>`;
                return `<td>${esc(v)}</td>`;
              })
              .join("") +
            "</tr>",
        )
        .join("")
    : `<tr><td class="empty" colspan="${cols.length}">${q ? "No hay coincidencias." : "Todavía no hay registros en esta tabla."}</td></tr>`;
}

document.querySelectorAll(".tab").forEach((b) =>
  b.addEventListener("click", () => {
    document
      .querySelectorAll(".tab")
      .forEach((x) => x.classList.toggle("active", x === b));
    activeTab = b.dataset.tab;
    renderTable();
  }),
);
$("search").addEventListener("input", renderTable);


$("studentForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const f = new FormData(e.currentTarget);
  const data = Object.fromEntries(f.entries());
  data.estado = data.estado === "true";
  try {
    await api("/api/estudiantes", {
      method: "POST",
      body: JSON.stringify(data),
    });
    e.currentTarget.reset();
    msg("Estudiante guardado correctamente.");
    await loadAll();
  } catch (err) {
    msg(err.message, "error");
  }
});

$("facultyForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const data = Object.fromEntries(new FormData(e.currentTarget).entries());
  try {
    await api("/api/facultades", {
      method: "POST",
      body: JSON.stringify(data),
    });
    e.currentTarget.reset();
    msg("Facultad agregada correctamente.");
    await loadAll();
  } catch (err) {
    msg(err.message, "error");
  }
});
loadAll();
setInterval(loadAll, 3000);
