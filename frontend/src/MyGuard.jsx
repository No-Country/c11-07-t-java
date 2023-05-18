import { BrowserRouter } from "react-router-dom";
import { Footer, Home } from "./components/ui";
import MySideNav from "./components/ui/SideNav";

export const MyGuard = () => {
  return (
    <BrowserRouter>
      <Home />
      <Footer />
      <MySideNav />
    </BrowserRouter>
  );
};
