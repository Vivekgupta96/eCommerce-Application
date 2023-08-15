import React from "react";
import Home from "../pages/Home";
import Product from "../pages/Product";
import Login from "../pages/LogIn";
import AdminLogin from "../pages/AdminLogIn";
import Registration from "../pages/Registration";
import SingleProduct from "../pages/SingleProduct";
import { Routes, Route } from "react-router-dom";

const AllRoutes = () => {
  return (
    <>
      <Routes>
        <Route path="/" exact element={<Home />} />
        <Route path="/product" element={<Product />} />
        <Route path="/login" element={<Login />} />
        <Route path="/admin-login" element={<AdminLogin />} />
        <Route path="/register-user" element={<Registration />} />
        <Route path="/single-product" element={<SingleProduct />} />
        <Route path="/products" element={<Product />} />
      </Routes>
    </>
  );
};
export default AllRoutes;
