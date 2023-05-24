import {useDispatch} from 'react-redux';
import { Logo } from "../util";
import "./loginForm.css";
import { Button } from "../util";
import { useAuthStore, useForm } from "../../hooks";



const loginForUser = {
    loginUsername : "",
    loginPassword: "",
}



export const LoginForm = () => {

const { startLogin } = useAuthStore();
 
const {loginUsername, loginPassword, onInputChange} = useForm(loginForUser);

  const onLoginSubmit = (e) => {
    e.preventDefault();
    startLogin({username: loginUsername, password: loginPassword})
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
          <div>
            <Button title={"Iniciar Sesion"} />
          </div>
        </form>
      </div>
    </div>
  );
};
