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
        const [equiposResult, jugadoresResult, cartasResult, testsResult] = await Promise.allSettled([
          api.getEquipos(),
          api.getJugadores(),
          api.getCartas(),
          api.getTestsStatus(),
        ]);

        const equipos = equiposResult.status === 'fulfilled' && Array.isArray(equiposResult.value)
          ? equiposResult.value
          : [];
        const jugadores = jugadoresResult.status === 'fulfilled' && Array.isArray(jugadoresResult.value)
          ? jugadoresResult.value
          : [];
        const cartas = cartasResult.status === 'fulfilled' && Array.isArray(cartasResult.value)
          ? cartasResult.value
          : [];
        const tests = testsResult.status === 'fulfilled' ? testsResult.value : null;

        if (equiposResult.status === 'rejected') console.error('Error cargando equipos:', equiposResult.reason);
        if (jugadoresResult.status === 'rejected') console.error('Error cargando jugadores:', jugadoresResult.reason);
        if (cartasResult.status === 'rejected') console.error('Error cargando cartas:', cartasResult.reason);
        if (testsResult.status === 'rejected') console.error('Error cargando estado de tests:', testsResult.reason);

        setData({ equipos, jugadores, cartas, tests });

        const allFailed = [equiposResult, jugadoresResult, cartasResult, testsResult]
          .every((result) => result.status === 'rejected');
        setError(allFailed ? 'No se pudo cargar el dashboard. Revisa la consola del navegador o el backend.' : '');
      } catch (err) {
        console.error('Error inesperado al cargar dashboard:', err);
        setError('No se pudo cargar el dashboard. Revisa la consola del navegador o el backend.');
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
