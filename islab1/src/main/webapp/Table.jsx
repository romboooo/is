import './Table.css';


const Table= ({data}) => {
    if(!data || data.length === 0){
        return <div>err with table view</div>
    }

    const columns = Object.keys(data[0]);
    const tableTitle = "TEST"; //потом подтянуть из даты

    return (
    <div className="table-container">
        <div className="table-title">{tableTitle}</div>
        <table className="data-table">
        <thead>
            <tr>
                {columns.map((column) => (
                    <th key={column}>
                        {column}
                    </th>
                ))}
            </tr>
            </thead>

            <tbody>
                {data.map((row, index) => (
                    <tr key={index}>
                        {columns.map((column) => (
                            <td key={column}>
                                {row[column]}
                            </td>
                        ))}
                    </tr>
                ))}
            </tbody>
        </table>
    </div>
    )
}

export default Table;