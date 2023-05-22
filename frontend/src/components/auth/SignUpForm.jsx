import React, { useState } from "react";
import { SideNabvar, Header } from "../ui";
import { Button } from "../util";
import './loginForm.css'

export const SignUpForm = () => {

    const [email, setEmail] = useState('');
    const [isValid, setIsValid] = useState(false);
    
    const handleEmailChange = (event) => {
        const inputEmail = event.target.value;
        setEmail(inputEmail);
        validateEmail(inputEmail);
    };
    
    const validateEmail = (email) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        setIsValid(emailRegex.test(email));
    }
    

  return (
    <div>
      <Header />
      <SideNabvar />
      <div className="container">
        <h1>Nuevo Perfil</h1>
        <br />

        <div>
          <input
            placeholder="especialidad"
            type="text"
            id="especialidad"
            value={""}
            onChange={""}
            name="especialidad"
          />
        </div>

        <br />

        <div>
          <input
            placeholder="nombre completo"
            type="text"
            id="nombre"
            value={""}
            onChange={""}
            name="nombre"
          />
        </div>

        <br />

        <div>
          <input
            placeholder="DNI"
            type="text"
            id="dni"
            value={""}
            onChange={""}
            name="dni"
          />
        </div>

        <br />

        <div>
          <input
            placeholder="correo electrónico"
            type="email"
            id="email"
            value={email}
            onChange={handleEmailChange}
            name="email"
          />
          { isValid ? <p>el email es válido</p> : <p>el email no es válido</p> }
        </div>

        <div>
          <input
            placeholder="contraseña"
            type="text"
            id="password"
            value={""}
            onChange={""}
            name="password"
          />
        </div>

        <br />

        <div className="contenedor">
            <Button title={"Crear Perfil"}/>
        </div>

      </div>
    </div>
  );
};
