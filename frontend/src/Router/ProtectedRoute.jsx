import React from "react";
import { Outlet, Navigate } from "react-router-dom";

const Privateroute = () => {
  let auth = false;
  if (localStorage.getItem("jwtToken") && localStorage.getItem("name")) {
    auth = true;
  }
  return auth ? <Outlet /> : <Navigate to="/Login" />;
};


export default Privateroute;
