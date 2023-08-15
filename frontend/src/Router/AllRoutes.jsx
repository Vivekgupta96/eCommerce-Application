import React from "react";
import Home from "../pages/Home";
import Product from "../pages/Product";
import Login from "../pages/LogIn";
import AdminLogin from "../pages/AdminLogIn";


import { Routes, Route } from "react-router-dom";

const AllRoutes = () => {
  return (
    <>
      <Routes>
        <Route path="/" exact element={<Home />} />
        <Route path="/product" element={<Product />} />
        <Route path="/login" element={<Login />} />
        <Route path="/admin-login" element={<AdminLogin />} />
      </Routes>
    </>
  );
};
export default AllRoutes;
