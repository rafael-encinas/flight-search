import { useState } from 'react'
import './App.css'

import SearchContainer from './components/SearchContainer/SearchContainer'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
          <SearchContainer />

    </>
  )
}

export default App
