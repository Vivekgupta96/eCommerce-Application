import React from "react";
import { useNavigate } from "react-router-dom";
import "../comp_css/Navbar.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCartShopping,
  faSearch,
  faUser,
} from "@fortawesome/free-solid-svg-icons";

const Navbar = () => {
  const iconstyle = {
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
  };
  const navigate = useNavigate();

  let userId = localStorage.getItem("userid");
  let name = localStorage.getItem("name");

  const handleLoginClick = () => {
    navigate("/login");
  };

  const handleLogoutClick = () => {
    localStorage.removeItem("userid");
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("cartid")
    localStorage.removeItem("name")

    alert("Logout Successfully.....");
    navigate("/");
  };

  return (
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
        <FontAwesomeIcon icon={faSearch} className="search-icon" />
      </div>

      <div className="iconbutton">
        <div
          style={iconstyle}
          onClick={() => {
            navigate("/user/cart");
          }}
          className="cart-button"
        >
          <FontAwesomeIcon icon={faCartShopping} className="cart-icon" />

          <p style={{ margin: "4px" }}>Cart</p>
        </div>
        {userId ? (
          <>
            <div
              style={iconstyle}
              className="login-button"
              onClick={() => {
                navigate("/user/order-details");
              }}
            >
              <FontAwesomeIcon icon={faUser} className="cart-icon" />
              {name}
            </div>
            <div onClick={handleLogoutClick}>Logout</div>
          </>
        ) : (
          <>
            <div
              style={iconstyle}
              className="login-button"
              onClick={handleLoginClick}
            >
              <FontAwesomeIcon icon={faUser} className="cart-icon" />
              Login
            </div>
            <div
              className="iconbutton"
              onClick={() => {
                navigate("/register-user");
              }}
            >
              SignIn
            </div>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
