const BASE_URL = 'http://localhost:8081';

async function request(path, options = {}) {
  const response = await fetch(`${BASE_URL}${path}`, {
    headers: {
      'Content-Type': 'application/json',
      ...options.headers,
    },
    ...options,
  });

  if (!response.ok) {
    const text = await response.text();
    throw new Error(text || `Error HTTP ${response.status}`);
  }

  if (response.status === 204) {
    return null;
  }

  const text = await response.text();
  return text ? JSON.parse(text) : null;
}

export const api = {
  getEquipos: () => request('/api/equipos'),
  createEquipo: (data) => request('/api/equipos', { method: 'POST', body: JSON.stringify(data) }),
  updateEquipo: (id, data) => request(`/api/equipos/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
  deleteEquipo: (id) => request(`/api/equipos/${id}`, { method: 'DELETE' }),

  getJugadores: () => request('/api/jugadores'),
  createJugador: (data) => request('/api/jugadores', { method: 'POST', body: JSON.stringify(data) }),
  updateJugador: (id, data) => request(`/api/jugadores/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
  deleteJugador: (id) => request(`/api/jugadores/${id}`, { method: 'DELETE' }),

  getCartas: () => request('/api/cartas'),
  createCarta: (data) => request('/api/cartas', { method: 'POST', body: JSON.stringify(data) }),
  updateCarta: (id, data) => request(`/api/cartas/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
  deleteCarta: (id) => request(`/api/cartas/${id}`, { method: 'DELETE' }),

  getTestsStatus: () => request('/api/tests/status'),
};
