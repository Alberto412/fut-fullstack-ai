import { Pencil, Trash2 } from 'lucide-react';

export default function EntityTable({ columns, rows, getKey, onEdit, onDelete }) {
  return (
    <div className="table-wrap">
      <table>
        <thead>
          <tr>
            {columns.map((column) => (
              <th key={column.key}>{column.label}</th>
            ))}
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {rows.length === 0 ? (
            <tr>
              <td colSpan={columns.length + 1} className="empty-cell">Sin registros</td>
            </tr>
          ) : (
            rows.map((row) => (
              <tr key={getKey(row)}>
                {columns.map((column) => (
                  <td key={column.key}>{column.render ? column.render(row) : row[column.key]}</td>
                ))}
                <td className="actions">
                  <button className="icon-button" type="button" onClick={() => onEdit(row)} title="Editar">
                    <Pencil size={17} aria-hidden="true" />
                  </button>
                  <button className="icon-button danger" type="button" onClick={() => onDelete(row)} title="Eliminar">
                    <Trash2 size={17} aria-hidden="true" />
                  </button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}
