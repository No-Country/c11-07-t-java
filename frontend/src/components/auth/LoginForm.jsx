import { Logo } from "../util";
import "./auth.css";
import { Button } from "../util";
import { useAuthStore, useForm } from "../../hooks";
import { useNavigate } from "react-router-dom";

const loginForUser = {
  loginUsername: "",
  loginPassword: "",
};

export const LoginForm = () => {
  const navigate = useNavigate();
  const register = () => {
    navigate("/register");
  };

  const { startLogin } = useAuthStore();

  const { loginUsername, loginPassword, onInputChange } = useForm(loginForUser);

  const onLoginSubmit = (e) => {
    e.preventDefault();
    if (loginUsername.trim() == "" || loginPassword.trim() == "") {
      alert("Credenciales incorrectas");
    }
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
        
          <a href="#" className="link">Olvidaste tu contraseña?</a>
          <p className="link" onClick={register}>Registrarse</p>
          <div>
            <Button title={"Iniciar Sesion"} />
          </div>
        </form>
      </div>
    </div>
  );
};
