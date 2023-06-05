import { Logo } from "../util";
import { Header } from "../ui"
import { useState } from "react";
import "./olvidoPass.css"

export const OlvidoPass = () => {
    function Formulario() {
        const {email, setEmail} = useState();
    }
  return (
    <>
    <Header/>
    <form>
          <div>
            <label>Recuperar Contraseña</label>
            <input
              placeholder="Ingrese Su Email"
              type="email"
              onChange={(e) => setEmail(e.target.value)}
              name="email"
              value={email}
            />
          </div>
          <div>
            <Button title={"Enviar"} />
          </div>
        </form>
    </>
  )
}
