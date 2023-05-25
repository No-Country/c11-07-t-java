import { Routes, Route } from 'react-router-dom';
import { Calendario } from "./components/pages";
import {Home} from './components/ui'
import { SelectedGuard } from './components/pages/';
import { SignUpForm } from './components/auth/SignUpForm';


export const AppRoutes = () => {



  return (
    <div>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/calendar" element={<Calendario />} />
        <Route path="/selec" element={<SelectedGuard />} />
        <Route path="/*" element={<Home />} />
        <Route path="/register" element={<SignUpForm />} />
      </Routes>
    </div>
  )
}
