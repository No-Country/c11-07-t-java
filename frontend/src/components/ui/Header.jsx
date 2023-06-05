
import  { useState } from "react";
import logo from "../../assets/img/logoNegro.png";
import { SideNabvar } from "./SideNabvar";
import "./header.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars, faTimes } from "@fortawesome/free-solid-svg-icons";
import { useAuthStore } from "../../hooks";

export const Header = () => {
  const [isOpen, setIsOpen] = useState(false);

  const {status} = useAuthStore();

  
  const handleToggleSideNav = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="container-header">
      <header>
      <button className="toggle-button" onClick={handleToggleSideNav}>
          <FontAwesomeIcon
            icon={isOpen ? faTimes : faBars}
            className="toggle-icon"
          />
        </button>
        <div className="container-logo">
          <img src={logo} alt="logo" className="logo-header" />
        </div>
       
      </header>
      <SideNabvar isOpen={isOpen} />
    </div>
  );
};



