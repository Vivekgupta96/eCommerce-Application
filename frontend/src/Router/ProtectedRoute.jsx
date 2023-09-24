import React from "react";
import { Outlet, Navigate } from "react-router-dom";

const Privateroute = () => {
  let auth = false;
  if (localStorage.getItem("jwtToken") && localStorage.getItem("userid")) {
    auth = true;
  }
  return auth ? <Outlet /> : <Navigate to="/login" />;
};

const Privaterouteadmin = () => {
  let auth = false;
  if (localStorage.getItem("jwtToken") && localStorage.getItem("adminid")) {
    auth = true;
  }
  return auth ? <Outlet /> : <Navigate to="/admin-login" />;
};


export {Privateroute,Privaterouteadmin};
