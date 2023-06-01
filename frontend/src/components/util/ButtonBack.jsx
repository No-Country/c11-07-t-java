import { useAuthStore } from "../../hooks/useAuthStore";
import "./util.css";

export const ButtonBack = () => {
  const { startLogout } = useAuthStore();

  return (
    <button className="logout-button" onClick={startLogout()}>
      salir
    </button>
  );
};
