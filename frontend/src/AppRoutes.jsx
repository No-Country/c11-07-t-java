import { Routes, Route, useNavigate } from "react-router-dom";
import { Calendario } from "./components/pages";
import { Home, Loading } from "./components/ui";
import { SelectedGuard } from "./components/pages/";
import { SignUpForm } from "./components/auth/SignUpForm";
import { useAuthStore } from "./hooks";
import { useEffect } from "react";
import { LoginForm } from "./components/auth";

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
      {status === "not-authenticated" ? (
        <>
          <Route path="/auth/*" element={<LoginPage />} />
          <Route path="/*" element={<Navigate to="/auth/login" />} />
        </>
      ) : (
        <>
          <Route path="/" element={<Home />} />
          <Route path="/calendar" element={<Calendario />} />
          <Route path="/selec" element={<SelectedGuard />} />
          <Route path="/*" element={<Home />} />
          <Route path="/register" element={<SignUpForm />} />
        </>
      )}
    </Routes>
  );
};
