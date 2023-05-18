// // eslint-disable-next-line no-unused-vars
// import React from "react";
// import SideNav, {
//   Toggle,
//   // eslint-disable-next-line no-unused-vars
//   Nav,
//   NavItem,
//   NavIcon,
//   NavText,
// } from "@trendmicro/react-sidenav";
// import "@trendmicro/react-sidenav/dist/react-sidenav.css";
// import "./sideNabvar.css";
// import { Link } from "react-router-dom";
// import toggle from "./Header"

// export const SideNabvar = () => {



//   return (
//     <SideNav
//       onSelect={(selected) => {
//         console.log(selected);
//       }}
//       className="sidenav"
//     >
//     {<Toggle /> }
    
//       <SideNav.Nav defaultSelected="home" className="icons">
//         <NavItem eventKey="home">
//           <NavIcon>
//             <i className="fa fa-fw fa-home"></i>
//           </NavIcon>
//           <NavText><Link to="/home" className="texto-negro">Home</Link></NavText>
//         </NavItem>
//         <NavItem eventKey="profile">
//           <NavIcon>
//             <i className="fa fa-fw fa-user"></i>
//           </NavIcon>
//           <NavText><Link to="/home" className="texto-negro">Perfil</Link></NavText>
//         </NavItem>
//         <NavItem eventKey="settings">
//           <NavIcon>
//             <i className="fa fa-fw fa-cog"></i>
//           </NavIcon>
//           <NavText><Link to="/home" className="texto-negro">Configuracion</Link></NavText>
//         </NavItem>
//       </SideNav.Nav>
//     </SideNav>
//   );
// };
// export default Toggle;

import SideNav, {  NavItem, NavIcon, NavText } from "@trendmicro/react-sidenav";
import "@trendmicro/react-sidenav/dist/react-sidenav.css";
import "./sideNabvar.css";
import { Link } from "react-router-dom";

// eslint-disable-next-line react/prop-types
export const SideNabvar = ({ isOpen }) => {
  return (
    <SideNav expanded={isOpen} className="sidenav">
      <SideNav.Nav defaultSelected="home" className="icons">
        <NavItem eventKey="home">
          <NavIcon>
            <i className="fa fa-fw fa-home"></i>
          </NavIcon>
          <NavText>
            <Link to="/home" className="texto-negro">
              Home
            </Link>
          </NavText>
        </NavItem>
        <NavItem eventKey="profile">
          <NavIcon>
            <i className="fa fa-fw fa-user"></i>
          </NavIcon>
          <NavText>
            <Link to="/home" className="texto-negro">
              Perfil
            </Link>
          </NavText>
        </NavItem>
        <NavItem eventKey="settings">
          <NavIcon>
            <i className="fa fa-fw fa-cog"></i>
          </NavIcon>
          <NavText>
            <Link to="/home" className="texto-negro">
              Configuracion
            </Link>
          </NavText>
        </NavItem>
      </SideNav.Nav>
    </SideNav>
  );
};
