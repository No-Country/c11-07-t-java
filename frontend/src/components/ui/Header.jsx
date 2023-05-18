import "./header.css";
import logo from "../../assets/img/logoNegro.png";

export const Header = () => {
  return (
    <div className="container-header">
      <header>
        <div className="container-logo">
          <img src={logo} alt="logo" className="logo-header" />
        </div>
      </header>
    </div>
  );
};
