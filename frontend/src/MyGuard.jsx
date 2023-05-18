import { BrowserRouter } from "react-router-dom";
import { AppRoutes } from "./AppRoutes";


export const MyGuard = () => {
  return (
    <BrowserRouter>
      <AppRoutes/>
    </BrowserRouter>
  );
};
