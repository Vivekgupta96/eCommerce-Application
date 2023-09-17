import React from "react";
import Home from "../pages/Home";
import Product from "../pages/Product";
import Profile from "../components/Profile";
import OrderDetails from "../pages/OrderDetails";
import Payment from "../pages/Payment";
import PaymentForm from "../pages/PaymentForm";
import Login from "../pages/LogIn";
import AdminLogin from "../pages/AdminLogIn";
import Registration from "../pages/Registration";
import SingleProduct from "../pages/SingleProduct";
import Cart from "../pages/Cart";
import ShippingDetails from "../pages/ShippingDetails";
import { Routes, Route } from "react-router-dom";
import ProtectedRoute from "../Router/ProtectedRoute";

const AllRoutes = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/product" element={<Product />} />
        <Route path="/login" element={<Login />} />

        <Route path="/user" element={<ProtectedRoute />}>
          <Route path="cart" element={<Cart />} />
          <Route path="save-address" element={<ShippingDetails />} />
          <Route path="order-details" element={<OrderDetails />} />
          <Route path="payment-success" element={<Payment />} />
          <Route path="make-payment" element={<PaymentForm />} />
          <Route path="profile" element={<Profile />} />
        </Route>

        <Route path="/admin-login" element={<AdminLogin />} />
        <Route path="/register-user" element={<Registration />} />
        <Route path="/product/:productId" element={<SingleProduct />} />
        <Route path="/products" element={<Product />} />
      </Routes>
    </>
  );
};
export default AllRoutes;
