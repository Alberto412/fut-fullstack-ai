export default function FutCard({ carta }) {
  const jugador = carta?.jugador?.nombre || 'Sin jugador';
  const media = carta?.jugador?.media || Math.round(((carta?.ritmo || 0) + (carta?.tiro || 0) + (carta?.pase || 0)) / 3);

  return (
    <article className="fut-card">
      <div className="fut-card-header">
        <strong>{media}</strong>
        <span>{carta?.jugador?.posicion || 'FUT'}</span>
      </div>
      <div className="fut-card-player">{jugador}</div>
      <div className="fut-card-type">{carta?.tipoCarta}</div>
      <div className="fut-stats">
        <span>RIT {carta?.ritmo}</span>
        <span>TIR {carta?.tiro}</span>
        <span>PAS {carta?.pase}</span>
        <span>REG {carta?.regate}</span>
        <span>DEF {carta?.defensa}</span>
        <span>FIS {carta?.fisico}</span>
      </div>
    </article>
  );
}
