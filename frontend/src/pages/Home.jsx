import { useEffect, useState } from 'react';
import { ArrowRight, BadgeCheck, Shield, Shirt, Trophy } from 'lucide-react';
import { api } from '../api/api.js';
import FutCard from '../components/FutCard.jsx';
import StatCard from '../components/StatCard.jsx';

export default function Home({ onNavigate }) {
  const [data, setData] = useState({ equipos: [], jugadores: [], cartas: [], tests: null });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    async function loadDashboard() {
      try {
        setLoading(true);
        const [equipos, jugadores, cartas, tests] = await Promise.all([
          api.getEquipos(),
          api.getJugadores(),
          api.getCartas(),
          api.getTestsStatus(),
        ]);
        setData({ equipos, jugadores, cartas, tests });
        setError('');
      } catch (err) {
        setError('No se pudo cargar el dashboard. Revisa que el backend esté activo en el puerto 8090.');
      } finally {
        setLoading(false);
      }
    }

    loadDashboard();
  }, []);

  return (
    <section className="stack">
      <div className="hero-panel">
        <div>
          <span className="eyebrow">App Full Stack con IA</span>
          <h1>Gestor FUT con Spring Boot, React y MySQL</h1>
          <p>Panel operativo para gestionar equipos, jugadores, cartas FUT y el estado de pruebas del backend.</p>
        </div>
        <div className="hero-actions">
          <button type="button" onClick={() => onNavigate('jugadores')}>
            <Shirt size={18} aria-hidden="true" />
            Jugadores
          </button>
          <button type="button" className="secondary" onClick={() => onNavigate('tests')}>
            <BadgeCheck size={18} aria-hidden="true" />
            Tests
          </button>
        </div>
      </div>

      {loading && <p className="status-message">Cargando...</p>}
      {error && <p className="error-message">{error}</p>}

      {!loading && !error && (
        <>
          <div className="stats-grid">
            <StatCard label="Equipos" value={data.equipos.length} tone="gold" />
            <StatCard label="Jugadores" value={data.jugadores.length} tone="green" />
            <StatCard label="Cartas FUT" value={data.cartas.length} tone="blue" />
            <StatCard label="Tests backend" value={data.tests?.estado || 'N/D'} tone="red" />
          </div>

          <div className="quick-grid">
            <button type="button" onClick={() => onNavigate('equipos')}>
              <Shield size={22} aria-hidden="true" />
              <span>Equipos</span>
              <ArrowRight size={18} aria-hidden="true" />
            </button>
            <button type="button" onClick={() => onNavigate('jugadores')}>
              <Shirt size={22} aria-hidden="true" />
              <span>Jugadores</span>
              <ArrowRight size={18} aria-hidden="true" />
            </button>
            <button type="button" onClick={() => onNavigate('cartas')}>
              <Trophy size={22} aria-hidden="true" />
              <span>Cartas FUT</span>
              <ArrowRight size={18} aria-hidden="true" />
            </button>
          </div>

          <section className="section-block">
            <div className="section-heading">
              <span className="eyebrow">Showcase</span>
              <h2>Cartas destacadas</h2>
            </div>
            <div className="cards-grid">
              {data.cartas.slice(0, 4).map((carta) => (
                <FutCard key={carta.id} carta={carta} />
              ))}
            </div>
          </section>
        </>
      )}
    </section>
  );
}
