import { Logo } from "../util";
import "./loginForm.css";
import { Button } from "../util";
import { useAuthStore, useForm } from "../../hooks";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import Swal from "sweetalert2";

const loginForUser = {
  loginUsername: "",
  loginPassword: "",
};

export const LoginForm = () => {
  const navigate = useNavigate();
  const register = () => {
    navigate("/register");
  };

  const { startLogin, errorMessage } = useAuthStore();

  const { loginUsername, loginPassword, onInputChange } = useForm(loginForUser);

  useEffect(() => {
    if (errorMessage != undefined) {
      Swal.fire("Error al intentar logearse", errorMessage, "error");
    }
  }, [errorMessage]);

  const onLoginSubmit = (e) => {
    e.preventDefault();
    if (loginUsername.trim() == "" || loginPassword.trim() == "") return;
    startLogin({ username: loginUsername, password: loginPassword });
  };

  return (
    <div className="contenedor">
      <div className="formulario-logo">
        <Logo />
      </div>
      <div className="welcome">
        <div className="welcome-bienvenido">
          <h1>Bienvenido</h1>
          <h3>Iniciar Sesion</h3>
        </div>
        <form onSubmit={onLoginSubmit}>
          <div>
            <input
              placeholder="usuario"
              type="text"
              onChange={onInputChange}
              name="loginUsername"
              value={loginUsername}
            />
          </div>
          <div>
            <input
              placeholder="Contraseña"
              type="password"
              onChange={onInputChange}
              name="loginPassword"
              value={loginPassword}
            />
          </div>
          <p>Olvidaste tu contraseña?</p>
          <p onClick={register}>Registrarse</p>
          <div>
            <Button title={"Iniciar Sesion"} />
          </div>
        </form>
      </div>
    </div>
  );
};
