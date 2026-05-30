import { useEffect, useState } from 'react';
import { Plus, Save, X } from 'lucide-react';
import { api } from '../api/api.js';
import EntityTable from '../components/EntityTable.jsx';
import FutCard from '../components/FutCard.jsx';

const emptyForm = {
  tipoCarta: '',
  ritmo: '',
  tiro: '',
  pase: '',
  regate: '',
  defensa: '',
  fisico: '',
  imagenUrl: '',
  jugadorId: '',
};

export default function Cartas() {
  const [cartas, setCartas] = useState([]);
  const [jugadores, setJugadores] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [editingId, setEditingId] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  async function loadData() {
    try {
      setLoading(true);
      const [cartasData, jugadoresData] = await Promise.all([api.getCartas(), api.getJugadores()]);
      setCartas(cartasData);
      setJugadores(jugadoresData);
      setError('');
    } catch (err) {
      setError('No se pudieron cargar cartas y jugadores.');
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
      tipoCarta: form.tipoCarta,
      ritmo: Number(form.ritmo),
      tiro: Number(form.tiro),
      pase: Number(form.pase),
      regate: Number(form.regate),
      defensa: Number(form.defensa),
      fisico: Number(form.fisico),
      imagenUrl: form.imagenUrl,
      jugador: form.jugadorId ? { id: Number(form.jugadorId) } : null,
    };
  }

  function startEdit(carta) {
    setEditingId(carta.id);
    setForm({
      tipoCarta: carta.tipoCarta || '',
      ritmo: carta.ritmo || '',
      tiro: carta.tiro || '',
      pase: carta.pase || '',
      regate: carta.regate || '',
      defensa: carta.defensa || '',
      fisico: carta.fisico || '',
      imagenUrl: carta.imagenUrl || '',
      jugadorId: carta.jugador?.id || '',
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
        await api.updateCarta(editingId, payload);
      } else {
        await api.createCarta(payload);
      }
      resetForm();
      await loadData();
    } catch (err) {
      setError('No se pudo guardar la carta. Revisa si el jugador ya tiene una carta FUT.');
    }
  }

  async function handleDelete(carta) {
    const nombre = carta.jugador?.nombre || carta.tipoCarta;
    if (!window.confirm(`¿Eliminar carta de ${nombre}?`)) {
      return;
    }
    try {
      await api.deleteCarta(carta.id);
      await loadData();
    } catch (err) {
      setError('No se pudo eliminar la carta.');
    }
  }

  return (
    <section className="crud-layout">
      <div className="section-heading">
        <span className="eyebrow">CRUD</span>
        <h1>Cartas FUT</h1>
      </div>

      <form className="form-card" onSubmit={handleSubmit}>
        <div className="form-title">
          <strong>{editingId ? 'Editar carta' : 'Crear carta'}</strong>
          {editingId && (
            <button className="icon-button" type="button" onClick={resetForm} title="Cancelar">
              <X size={18} aria-hidden="true" />
            </button>
          )}
        </div>
        <div className="form-grid">
          <input name="tipoCarta" placeholder="Tipo de carta" value={form.tipoCarta} onChange={handleChange} required />
          <input name="ritmo" type="number" min="1" max="99" placeholder="Ritmo" value={form.ritmo} onChange={handleChange} required />
          <input name="tiro" type="number" min="1" max="99" placeholder="Tiro" value={form.tiro} onChange={handleChange} required />
          <input name="pase" type="number" min="1" max="99" placeholder="Pase" value={form.pase} onChange={handleChange} required />
          <input name="regate" type="number" min="1" max="99" placeholder="Regate" value={form.regate} onChange={handleChange} required />
          <input name="defensa" type="number" min="1" max="99" placeholder="Defensa" value={form.defensa} onChange={handleChange} required />
          <input name="fisico" type="number" min="1" max="99" placeholder="Físico" value={form.fisico} onChange={handleChange} required />
          <input name="imagenUrl" placeholder="URL imagen" value={form.imagenUrl} onChange={handleChange} />
          <select name="jugadorId" value={form.jugadorId} onChange={handleChange}>
            <option value="">Sin jugador</option>
            {jugadores.map((jugador) => (
              <option key={jugador.id} value={jugador.id}>{jugador.nombre}</option>
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
        <>
          <div className="cards-grid compact">
            {cartas.slice(0, 4).map((carta) => (
              <FutCard key={carta.id} carta={carta} />
            ))}
          </div>
          <EntityTable
            rows={cartas}
            getKey={(carta) => carta.id}
            onEdit={startEdit}
            onDelete={handleDelete}
            columns={[
              { key: 'jugador', label: 'Jugador', render: (carta) => carta.jugador?.nombre || 'Sin jugador' },
              { key: 'tipoCarta', label: 'Tipo' },
              { key: 'ritmo', label: 'RIT' },
              { key: 'tiro', label: 'TIR' },
              { key: 'pase', label: 'PAS' },
              { key: 'regate', label: 'REG' },
              { key: 'defensa', label: 'DEF' },
              { key: 'fisico', label: 'FIS' },
            ]}
          />
        </>
      )}
    </section>
  );
}
