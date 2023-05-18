
import React from 'react'
import ReactDOM from 'react-dom/client'
import { Provider } from 'react-redux'
import {store} from './store'
import "./index.css";
import { MyGuard } from "./MyGuard";
import { BrowserRouter } from "react-router-dom";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <MyGuard />
      </BrowserRouter>
    </Provider>
  </React.StrictMode>
);
