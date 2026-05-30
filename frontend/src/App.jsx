import { useState } from 'react';
import Navbar from './components/Navbar.jsx';
import Home from './pages/Home.jsx';
import Equipos from './pages/Equipos.jsx';
import Jugadores from './pages/Jugadores.jsx';
import Cartas from './pages/Cartas.jsx';
import TestsStatus from './pages/TestsStatus.jsx';

const pages = {
  home: Home,
  equipos: Equipos,
  jugadores: Jugadores,
  cartas: Cartas,
  tests: TestsStatus,
};

export default function App() {
  const [activePage, setActivePage] = useState('home');
  const Page = pages[activePage];

  return (
    <div className="app-shell">
      <Navbar activePage={activePage} onNavigate={setActivePage} />
      <main className="page-container">
        <Page onNavigate={setActivePage} />
      </main>
    </div>
  );
}
