import React, { useState } from "react";
import "../comp_css/Navbar.css";
import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate("/login");
  };

  return (
    <>
      <nav className="navbar">
        <div className="logo">
          <h3>E-commerse-App</h3>
        </div>

        <div className="search-bar">
          <input type="text" placeholder="Search..." />
        </div>
        <div className="auth-buttons">
          <button className="login-button" onClick={handleLoginClick}>
            Log In
          </button>
          <button className="signup-button">Sign Up</button>
        </div>
      </nav>
    </>
  );
};

export default Navbar;
