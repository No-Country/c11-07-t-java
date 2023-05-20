import {useDispatch} from 'react-redux';
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { Logo } from "../util";
import "./loginForm.css";
import { Button } from "../util";
import { useForm } from "../../hooks";
import { checkingAuthenticate } from '../../store/auth';

export const LoginForm = () => {

  const dispatch = useDispatch();

  const { username, password, onInputChange } = useForm({
    username: "rArenas",
    password: "1234",
  });

  const onSubmit = (e) => {
    e.preventDefault();
    console.log({ username, password });

    dispatch(checkingAuthenticate());
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
        <form onSubmit={onSubmit}>
          <div>
            <input
              placeholder="usuario"
              type="text"
              id="username"
              value={username}
              onChange={onInputChange}
              name="username"
            />
          </div>
          <div>
            <input
              placeholder="Contraseña"
              type="password"
              id="password"
              value={password}
              onChange={onInputChange}
              name="password"
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
