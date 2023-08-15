import React from "react";
import { useNavigate, useLocation } from "react-router-dom";
import "../comp_css/Navbar.css";

const Navbar = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const handleLoginClick = () => {
    navigate("/login");
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
            E-commerce-App
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
          <button className="cart-button" onClick={() => {
              navigate("/cart");
            }}>
            Cart
          </button>
          <button className="login-button" onClick={handleLoginClick}>
            Log In
          </button>
          <button
            className="signup-button"
            onClick={() => {
              navigate("/register-user");
            }}
          >
            Sign Up
          </button>
        </div>
      </nav>
    </>
  );
};

export default Navbar;
