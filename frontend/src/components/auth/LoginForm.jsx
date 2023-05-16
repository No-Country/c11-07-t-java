import { useState } from "react";
import { Logo } from "../util";
import "./loginForm.css";
import { Button } from "../util";

export const LoginForm = () => {
  const [dni, setDni] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(`DNI: ${dni}, Contraseña: ${password}`);
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
        <form onSubmit={handleSubmit}>
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
          <div>
            <Button title={"Iniciar Sesion"} />
          </div>
        </form>
      </div>
    </div>
  );
};
