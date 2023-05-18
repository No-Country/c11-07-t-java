import React from "react";
import SideNav, {
  Toggle,
  Nav,
  NavItem,
  NavIcon,
  NavText,
} from "@trendmicro/react-sidenav";
import "@trendmicro/react-sidenav/dist/react-sidenav.css";
import "./sideNabvar.css";
import { useNavigate } from "react-router-dom";

export const SideNabvar = () => {

  const navigate = useNavigate();

  return (
    <SideNav
      onSelect={(selected) => {
        navigate(selected)
      }}
      className="sidenav"
    >
      <SideNav.Toggle />
      <SideNav.Nav defaultSelected="home" className="icons">
        <NavItem eventKey="home">
          <NavIcon>
            <i className="fa fa-fw fa-home"></i>
          </NavIcon>
          <NavText>Home</NavText>
        </NavItem>
        <NavItem eventKey="profile">
          <NavIcon>
            <i className="fa fa-fw fa-user"></i>
          </NavIcon>
          <NavText>Perfil</NavText>
        </NavItem>
        <NavItem eventKey="settings">
          <NavIcon>
            <i className="fa fa-fw fa-cog"></i>
          </NavIcon>
          <NavText>Configuración</NavText>
        </NavItem>
      </SideNav.Nav>
    </SideNav>
  );
};

