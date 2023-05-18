import { useState } from "react";
import { Logo } from "../util";
import "./loginForm.css";
import { Button } from "../util";
import { useNavigate } from 'react-router-dom';

export const LoginForm = () => {
  const [dni, setDni] = useState("");
  const [password, setPassword] = useState("");

const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(`DNI: ${dni}, Contraseña: ${password}`);
    navigate('/calendar')
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
        <form>
          <div>
            <input
              placeholder="DNI"
              type="text"
              id="dni"
              value={dni}
              onChange={(e) => setDni(e.target.value)}
            />
          </div>
          <div>
            <input
              placeholder="Contraseña"
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <p>Olvidaste tu contraseña?</p>
          <div onClick={handleSubmit}>
            <Button title={"Iniciar Sesion"} />
          </div>
        </form>
      </div>
    </div>
  );
};
