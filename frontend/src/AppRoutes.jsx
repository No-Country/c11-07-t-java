import { Routes, Route } from "react-router-dom";
import { Calendario, SelectedGuard, Profile } from "./components/pages";
import {  Loading } from "./components/ui";
import { useAuthStore } from "./hooks";
import { useEffect } from "react";
import { LoginForm, SignUpForm } from "./components/auth";
import { Settings } from "./components/pages/Settings";

export const AppRoutes = () => {
  const { status, checkAuthToken } = useAuthStore();


  useEffect(() => {
    checkAuthToken();
  }, []);

//const estado = 'not--authenticated'; //ESTA VARIABLE HAY QUE BORRARLA CUANDO SE TERMINA DE USAR Y REEMPLAZAR TODO POR STATUS

  if (status == "checking") {
    setTimeout(() => {
       return <Loading />;
    }, 6000);
   
  }
  return (
    <Routes>
      {status === "not--authenticated" ? (
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
          <Route path="/settings" element={<Settings/>} />
        </>
      )}
    </Routes>
  );
};
