import SideNav, {  NavItem, NavIcon, NavText } from "@trendmicro/react-sidenav";
import "@trendmicro/react-sidenav/dist/react-sidenav.css";
import "./sideNabvar.css";
import { Link } from "react-router-dom";
import { useAuthStore } from "../../hooks/useAuthStore";
import Swal from 'sweetalert2'


// eslint-disable-next-line react/prop-types
export const SideNabvar = ({ isOpen }) => {

  const {startLogout} = useAuthStore();

  const showAlert = () => {
    Swal.fire({
        title: '¿Quieres cerrar sesión?',
        showDenyButton: true,
        denyButtonColor: 'var(--verdeLimon)',
        showCancelButton: false,
        confirmButtonText: 'Si',
        confirmButtonColor: '#d33',
        denyButtonText: `No`,
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            startLogout();
            Swal.fire({
              icon: 'success',
              title: 'Sesión cerrada',
              showConfirmButton: false,
              timer: 1500
            })
            //Swal.fire('Sesión cerrada', '', 'success')
        }
    })
  }

  return (
    <SideNav expanded={isOpen} className="sidenav">
      <SideNav.Nav defaultSelected="home" className="icons">
        <NavItem eventKey="home">
          <NavIcon>
          </NavIcon>
          <NavText>
            <Link to="/home" className="texto-negro">
            <i className="fa-solid fa-home iconColor"></i>
                 Home
            </Link>
          </NavText>
        </NavItem>
        <NavItem eventKey="profile">
          <NavIcon>
          </NavIcon>
          <NavText>
            <Link to="/profile" className="texto-negro">
            <i className="fa-solid fa-user iconColor"></i>
                  Perfil
            </Link>
          </NavText>
        </NavItem>
        
        <NavItem eventKey="close" className="align-bottom">
          <NavIcon>
          </NavIcon>
          <NavText>
            <Link className="texto-logout" onClick={()=> showAlert()}>
            <i className="fa-solid fa-right-from-bracket iconColor"></i>
                Cerrar Sesión
            </Link>
          </NavText>
        </NavItem>
      </SideNav.Nav>
    </SideNav>
  );
};
