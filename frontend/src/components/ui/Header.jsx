// import "./header.css";
// import logo from "../../assets/img/logoNegro.png";
// import { SideNabvar } from "./SideNabvar";
// import SideNav, {
//   Toggle,
//   // eslint-disable-next-line no-unused-vars
//   Nav,
//   NavItem,
//   NavIcon,
//   NavText,
// } from "@trendmicro/react-sidenav";


// export const Header = () => {
//   return (
//     <div className="container-header">
//       <header>
//         <div className="container-logo">
//           <img src={logo} alt="logo" className="logo-header" />
//         </div>
//         <Toggle />
//       </header>
//     </div>
//   );
// };

import  { useState } from "react";
import logo from "../../assets/img/logoNegro.png";
import { SideNabvar } from "./SideNabvar";
import "./header.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars, faTimes } from "@fortawesome/free-solid-svg-icons";

export const Header = () => {
  const [isSideNavOpen, setIsSideNavOpen] = useState(false);

  const handleToggleSideNav = () => {
    setIsSideNavOpen(!isSideNavOpen);
  };

  return (
    <div className="container-header">
      <header>
      <button className="toggle-button" onClick={handleToggleSideNav}>
          <FontAwesomeIcon
            icon={isSideNavOpen ? faTimes : faBars}
            className="toggle-icon"
          />
        </button>
        <div className="container-logo">
          <img src={logo} alt="logo" className="logo-header" />
        </div>
        
      </header>
      <SideNabvar isOpen={isSideNavOpen} />
    </div>
  );
};



