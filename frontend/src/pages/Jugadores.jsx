import { useEffect, useState } from 'react';
import { Plus, Save, X } from 'lucide-react';
import { api } from '../api/api.js';
import EntityTable from '../components/EntityTable.jsx';

const emptyForm = {
  nombre: '',
  posicion: '',
  edad: '',
  nacionalidad: '',
  media: '',
  imagenUrl: '',
  equipoId: '',
};

export default function Jugadores() {
  const [jugadores, setJugadores] = useState([]);
  const [equipos, setEquipos] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [editingId, setEditingId] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  async function loadData() {
    try {
      setLoading(true);
      const [jugadoresData, equiposData] = await Promise.all([api.getJugadores(), api.getEquipos()]);
      setJugadores(jugadoresData);
      setEquipos(equiposData);
      setError('');
    } catch (err) {
      setError('No se pudieron cargar jugadores y equipos.');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadData();
  }, []);

  function handleChange(event) {
    setForm({ ...form, [event.target.name]: event.target.value });
  }

  function buildPayload() {
    return {
      nombre: form.nombre,
      posicion: form.posicion,
      edad: Number(form.edad),
      nacionalidad: form.nacionalidad,
      media: Number(form.media),
      imagenUrl: form.imagenUrl,
      equipo: form.equipoId ? { id: Number(form.equipoId) } : null,
    };
  }

  function startEdit(jugador) {
    setEditingId(jugador.id);
    setForm({
      nombre: jugador.nombre || '',
      posicion: jugador.posicion || '',
      edad: jugador.edad || '',
      nacionalidad: jugador.nacionalidad || '',
      media: jugador.media || '',
      imagenUrl: jugador.imagenUrl || '',
      equipoId: jugador.equipo?.id || '',
    });
  }

  function resetForm() {
    setEditingId(null);
    setForm(emptyForm);
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      const payload = buildPayload();
      if (editingId) {
        await api.updateJugador(editingId, payload);
      } else {
        await api.createJugador(payload);
      }
      resetForm();
      await loadData();
    } catch (err) {
      setError('No se pudo guardar el jugador.');
    }
  }

  async function handleDelete(jugador) {
    if (!window.confirm(`¿Eliminar ${jugador.nombre}?`)) {
      return;
    }
    try {
      await api.deleteJugador(jugador.id);
      await loadData();
    } catch (err) {
      setError('No se pudo eliminar el jugador.');
    }
  }

  return (
    <section className="crud-layout">
      <div className="section-heading">
        <span className="eyebrow">CRUD</span>
        <h1>Jugadores</h1>
      </div>

      <form className="form-card" onSubmit={handleSubmit}>
        <div className="form-title">
          <strong>{editingId ? 'Editar jugador' : 'Crear jugador'}</strong>
          {editingId && (
            <button className="icon-button" type="button" onClick={resetForm} title="Cancelar">
              <X size={18} aria-hidden="true" />
            </button>
          )}
        </div>
        <div className="form-grid">
          <input name="nombre" placeholder="Nombre" value={form.nombre} onChange={handleChange} required />
          <input name="posicion" placeholder="Posición" value={form.posicion} onChange={handleChange} required />
          <input name="edad" type="number" min="15" placeholder="Edad" value={form.edad} onChange={handleChange} required />
          <input name="nacionalidad" placeholder="Nacionalidad" value={form.nacionalidad} onChange={handleChange} required />
          <input name="media" type="number" min="1" max="99" placeholder="Media" value={form.media} onChange={handleChange} required />
          <input name="imagenUrl" placeholder="URL imagen" value={form.imagenUrl} onChange={handleChange} />
          <select name="equipoId" value={form.equipoId} onChange={handleChange}>
            <option value="">Sin equipo</option>
            {equipos.map((equipo) => (
              <option key={equipo.id} value={equipo.id}>{equipo.nombre}</option>
            ))}
          </select>
        </div>
        <button type="submit">
          {editingId ? <Save size={18} aria-hidden="true" /> : <Plus size={18} aria-hidden="true" />}
          {editingId ? 'Guardar' : 'Crear'}
        </button>
      </form>

      {loading && <p className="status-message">Cargando...</p>}
      {error && <p className="error-message">{error}</p>}
      {!loading && (
        <EntityTable
          rows={jugadores}
          getKey={(jugador) => jugador.id}
          onEdit={startEdit}
          onDelete={handleDelete}
          columns={[
            { key: 'nombre', label: 'Nombre' },
            { key: 'posicion', label: 'Posición' },
            { key: 'edad', label: 'Edad' },
            { key: 'nacionalidad', label: 'Nacionalidad' },
            { key: 'media', label: 'Media' },
            { key: 'equipo', label: 'Equipo', render: (jugador) => jugador.equipo?.nombre || 'Sin equipo' },
          ]}
        />
      )}
    </section>
  );
}
