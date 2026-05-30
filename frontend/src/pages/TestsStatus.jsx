import { useEffect, useState } from 'react';
import { BadgeCheck, CircleAlert } from 'lucide-react';
import { api } from '../api/api.js';
import StatCard from '../components/StatCard.jsx';

export default function TestsStatus() {
  const [status, setStatus] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    async function loadStatus() {
      try {
        setLoading(true);
        setStatus(await api.getTestsStatus());
        setError('');
      } catch (err) {
        setError('No se pudo cargar el estado de tests.');
      } finally {
        setLoading(false);
      }
    }

    loadStatus();
  }, []);

  return (
    <section className="stack">
      <div className="section-heading">
        <span className="eyebrow">Backend</span>
        <h1>Estado de tests</h1>
      </div>

      {loading && <p className="status-message">Cargando...</p>}
      {error && <p className="error-message">{error}</p>}

      {!loading && !error && status && (
        <>
          <div className={`test-banner ${status.estado === 'OK' ? 'ok' : 'fail'}`}>
            {status.estado === 'OK' ? <BadgeCheck size={28} aria-hidden="true" /> : <CircleAlert size={28} aria-hidden="true" />}
            <div>
              <strong>{status.estado}</strong>
              <span>{status.mensaje}</span>
            </div>
          </div>

          <div className="stats-grid">
            <StatCard label="Total" value={status.totalTests} />
            <StatCard label="Correctos" value={status.testsCorrectos} tone="green" />
            <StatCard label="Fallidos" value={status.testsFallidos} tone="red" />
          </div>

          <div className="detail-list">
            {status.detalle.map((testName) => (
              <span key={testName}>{testName}</span>
            ))}
          </div>
        </>
      )}
    </section>
  );
}
