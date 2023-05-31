import { Routes, Route, useNavigate } from "react-router-dom";
import { Calendario, SelectedGuard } from "./components/pages";
import {  Loading } from "./components/ui";
import { useAuthStore } from "./hooks";
import { useEffect } from "react";
import { LoginForm, SignUpForm } from "./components/auth";

export const AppRoutes = () => {
  const { status, checkAuthToken } = useAuthStore();

  const navigate = useNavigate();

  useEffect(() => {
    checkAuthToken();
  }, []);

  if (status == "checking") {
    return <Loading />;
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
        </>
      )}
    </Routes>
  );
};
