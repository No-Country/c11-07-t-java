import { BrowserRouter } from "react-router-dom";
import { Footer, Home } from "./components/ui";

export const MyGuard = () => {
  return (
    <BrowserRouter>
      <Home />
      <Footer />
    </BrowserRouter>
  );
};
