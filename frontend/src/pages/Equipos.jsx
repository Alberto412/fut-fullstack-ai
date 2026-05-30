import { useEffect, useState } from 'react';
import { Plus, Save, X } from 'lucide-react';
import { api } from '../api/api.js';
import EntityTable from '../components/EntityTable.jsx';

const emptyForm = {
  nombre: '',
  liga: '',
  pais: '',
  estadio: '',
  escudoUrl: '',
};

export default function Equipos() {
  const [equipos, setEquipos] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [editingId, setEditingId] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  async function loadEquipos() {
    try {
      setLoading(true);
      setEquipos(await api.getEquipos());
      setError('');
    } catch (err) {
      setError('No se pudieron cargar los equipos.');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadEquipos();
  }, []);

  function handleChange(event) {
    setForm({ ...form, [event.target.name]: event.target.value });
  }

  function startEdit(equipo) {
    setEditingId(equipo.id);
    setForm({
      nombre: equipo.nombre || '',
      liga: equipo.liga || '',
      pais: equipo.pais || '',
      estadio: equipo.estadio || '',
      escudoUrl: equipo.escudoUrl || '',
    });
  }

  function resetForm() {
    setEditingId(null);
    setForm(emptyForm);
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      if (editingId) {
        await api.updateEquipo(editingId, form);
      } else {
        await api.createEquipo(form);
      }
      resetForm();
      await loadEquipos();
    } catch (err) {
      setError('No se pudo guardar el equipo.');
    }
  }

  async function handleDelete(equipo) {
    if (!window.confirm(`¿Eliminar ${equipo.nombre}?`)) {
      return;
    }
    try {
      await api.deleteEquipo(equipo.id);
      await loadEquipos();
    } catch (err) {
      setError('No se pudo eliminar el equipo.');
    }
  }

  return (
    <section className="crud-layout">
      <div className="section-heading">
        <span className="eyebrow">CRUD</span>
        <h1>Equipos</h1>
      </div>

      <form className="form-card" onSubmit={handleSubmit}>
        <div className="form-title">
          <strong>{editingId ? 'Editar equipo' : 'Crear equipo'}</strong>
          {editingId && (
            <button className="icon-button" type="button" onClick={resetForm} title="Cancelar">
              <X size={18} aria-hidden="true" />
            </button>
          )}
        </div>
        <div className="form-grid">
          <input name="nombre" placeholder="Nombre" value={form.nombre} onChange={handleChange} required />
          <input name="liga" placeholder="Liga" value={form.liga} onChange={handleChange} required />
          <input name="pais" placeholder="País" value={form.pais} onChange={handleChange} required />
          <input name="estadio" placeholder="Estadio" value={form.estadio} onChange={handleChange} required />
          <input name="escudoUrl" placeholder="URL escudo" value={form.escudoUrl} onChange={handleChange} />
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
          rows={equipos}
          getKey={(equipo) => equipo.id}
          onEdit={startEdit}
          onDelete={handleDelete}
          columns={[
            { key: 'nombre', label: 'Nombre' },
            { key: 'liga', label: 'Liga' },
            { key: 'pais', label: 'País' },
            { key: 'estadio', label: 'Estadio' },
          ]}
        />
      )}
    </section>
  );
}
