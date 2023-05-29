import { SideNabvar, Header } from "../ui";
import { Button } from "../util";
import "./loginForm.css";
import { useAuthStore, useForm } from "../../hooks";

const registerForUser = {
  registerUsername: "",
  registerEmail: "",
  registerPassword: "",
};

export const SignUpForm = () => {
  const { startRegister, startLogin, status } = useAuthStore();

  const { registerUsername, registerEmail, registerPassword, onInputChange } =
    useForm(registerForUser);

  const onRegisterSubmit = (e) => {
    e.preventDefault();
    if (
      registerUsername.trim() == "" ||
      registerEmail.trim() == "" ||
      registerPassword.trim() == ""
    ) {
      alert("Los datos subministrados no son validos");
    }
    startRegister({
      username: registerUsername,
      email: registerEmail,
      password: registerPassword,
    });
  };

  return (
    <>
      <div>
        <Header />
        <SideNabvar />
        <div className="contenedor contenedor-register">
          <h1>Nuevo Perfil</h1>

          <form onSubmit={onRegisterSubmit}>
            <div>
              <input
                placeholder="usuario. ej: aRamirez"
                type="text"
                id="nombre"
                value={registerUsername}
                onChange={onInputChange}
                name="registerUsername"
              />
            </div>
            <div>
              <input
                placeholder="correo electrónico"
                type="email"
                id="email"
                value={registerEmail}
                onChange={onInputChange}
                name="registerEmail"
              />
            </div>

            <div>
              <input
                placeholder="contraseña"
                type="text"
                id="password"
                value={registerPassword}
                onChange={onInputChange}
                name="registerPassword"
              />
            </div>

            <div>
              <Button title={"Crear Perfil"} />
            </div>
          </form>
        </div>
      </div>
    </>
  );
};
