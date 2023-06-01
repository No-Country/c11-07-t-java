import { Routes, Route } from "react-router-dom";
import { Calendario, SelectedGuard, Profile } from "./components/pages";
import {  Loading } from "./components/ui";
import { useAuthStore } from "./hooks";
import { useEffect } from "react";
import { LoginForm, SignUpForm } from "./components/auth";

export const AppRoutes = () => {
  const { status, checkAuthToken } = useAuthStore();


  useEffect(() => {
    checkAuthToken();
  }, []);

const estado = 'authenticated'; //ESTA VARIABLE HAY QUE BORRARLA CUANDO SE TERMINA DE USAR Y REEMPLAZAR TODO POR STATUS

  if (estado == "checking") {
    return <Loading />;
  }
  return (
    <Routes>
      {estado === "not--authenticated" ? (
        <>
          <Route path="/auth/*" element={<LoginForm />} />
          <Route path="/register" element={<SignUpForm />} />
          <Route path="/*" element={<LoginForm/>} />
        </>
      ) : (
        <>
          <Route path="/calendar" element={<Calendario />} />
          <Route path="/selec" element={<SelectedGuard />} />
          <Route path="/*" element={<Calendario />} />
          <Route path="/profile" element={<Profile />} />
        </>
      )}
    </Routes>
  );
};
