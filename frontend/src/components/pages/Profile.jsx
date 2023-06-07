
import "./pages.css";
import "../auth/auth.css";
import { Header, SideNabvar } from "../ui";
import { useAuthStore, useForm } from "../../hooks";
import { Button } from "../util";

const initialProfile = {
  updateName: "",
  updateLastName: "",
  updateProfesional: "",
};

export const Profile = () => {
  
  const { user, startUpdateUser } = useAuthStore();
  

  const { updateName, updateLastName, updateProfesional, onInputChange } =
    useForm(initialProfile);

  const onUpdateSubmit = (e) => {
  e.preventDefault();
  if (
    updateName.trim() == "" ||
    updateLastName.trim() == "" ||
    updateProfesional.trim() == ""
  ) {
    alert("No puede dejar campos vacios");
  }
  startUpdateUser({
    id: user.token,
    name: updateName,
    lastName: updateLastName,
    profesion: updateProfesional,
  });
};

  return (
    <>
      <Header />
      <SideNabvar />
      <div className="profile">
        <h3>Puede Actualizar su perfil si lo desea</h3>
        <div className="contenedor contenedor-register">
          <form onSubmit={onUpdateSubmit}>
            <h3>Usuario: {user.username}</h3>
            <div>
              <input
                placeholder="Nombre"
                type="text"
                id="nombre"
                value={updateName}
                onChange={onInputChange}
                name="updateName"
              />
            </div>
            <div>
              <input
                placeholder="Apellido"
                type="text"
                id="apellido"
                value={updateLastName}
                onChange={onInputChange}
                name="updateLastName"
              />
            </div>
            <div>
              <input
                placeholder="profesion"
                type="text"
                id="profesion"
                value={updateProfesional}
                onChange={onInputChange}
                name="updateProfesional"
              />
            </div>
            <div>
              <Button title={"Actualizar Perfil"} />
            </div>
          </form>
        </div>
      </div>
    </>
  );
};
