import React from "react";
import SideNav, {
  Toggle,
  Nav,
  NavItem,
  NavIcon,
  NavText,
} from "@trendmicro/react-sidenav";
import "@trendmicro/react-sidenav/dist/react-sidenav.css";
import "./SideNav.css";

export const MySideNav = () => {
  return (
    <SideNav
      onSelect={(selected) => {
        console.log(selected);
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

export default MySideNav;
