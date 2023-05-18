import { Routes, Route } from 'react-router-dom';
import { Calendario } from "./components/pages";
import {Home} from './components/ui'


export const AppRoutes = () => {



  return (
    <div>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/calendar" element={<Calendario />} />
        <Route path="/*" element={<Home />} />
      </Routes>
    </div>
  )
}
