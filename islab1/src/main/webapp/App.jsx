import './App.css'
import Table from "./Table.jsx";

const App = () => {
    const testData = [
        {id:1, name: "test", age: "25"},
        {id:2, name: "test2", age: "45"},
    ]

    const data = get();

    return (
        <>
        <Table data={data}/>
        </>
    )
}

export default App
