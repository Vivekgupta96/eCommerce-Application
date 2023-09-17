import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import "../comp_css/Navbar.css";

const Navbar = () => {
  const navigate = useNavigate();
  const location = useLocation();

  let userId = localStorage.getItem("userid");
  let name = localStorage.getItem("name");

  const handleLoginClick = () => {
    navigate("/login");
  };

  const handleLogoutClick = () => {
    localStorage.removeItem("userid");
    localStorage.removeItem("jwtToken");

    alert("Logout Successfully.....");
    navigate("/");
  };

  return (
    <>
      <nav className="navbar">
        <div className="logo">
          <h3
            onClick={() => {
              navigate("/");
            }}
          >
            E-Commerce
          </h3>
        </div>

        <div className="search-bar">
          <input
            type="text"
            placeholder="Search..."
            onClick={() => {
              navigate("/product");
            }}
          />
        </div>
        <div className="auth-buttons">
          <button
            className="cart-button"
            onClick={() => {
              navigate("user/cart");
            }}
          >
            Cart
          </button>
          {userId ? (
            <>
              <button className="login-button" disabled>
                {name}
              </button>
              <button className="login-button" onClick={handleLogoutClick}>
                Logout
              </button>
            </>
          ) : (
            <>
              <button className="login-button" onClick={handleLoginClick}>
                Login
              </button>
              <button
                className="login-button"
                onClick={() => {
                  navigate("/register-user");
                }}
              >
                Sign In
              </button>
            </>
          )}
        </div>
      </nav>
    </>
  );
};

export default Navbar;
