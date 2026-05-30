import { Activity, BadgeCheck, House, Shield, Shirt, Trophy } from 'lucide-react';

const items = [
  { key: 'home', label: 'Dashboard', icon: House },
  { key: 'equipos', label: 'Equipos', icon: Shield },
  { key: 'jugadores', label: 'Jugadores', icon: Shirt },
  { key: 'cartas', label: 'Cartas FUT', icon: Trophy },
  { key: 'tests', label: 'Tests', icon: BadgeCheck },
];

export default function Navbar({ activePage, onNavigate }) {
  return (
    <header className="topbar">
      <div className="brand">
        <Activity size={26} aria-hidden="true" />
        <div>
          <strong>FUT Fullstack AI</strong>
          <span>Spring Boot + React + MySQL</span>
        </div>
      </div>

      <nav className="nav-tabs" aria-label="Navegación principal">
        {items.map((item) => {
          const Icon = item.icon;
          return (
            <button
              key={item.key}
              className={activePage === item.key ? 'active' : ''}
              onClick={() => onNavigate(item.key)}
              type="button"
              title={item.label}
            >
              <Icon size={18} aria-hidden="true" />
              <span>{item.label}</span>
            </button>
          );
        })}
      </nav>
    </header>
  );
}
