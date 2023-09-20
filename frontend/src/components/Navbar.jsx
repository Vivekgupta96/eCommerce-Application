import React from "react";
import { useNavigate } from "react-router-dom";
import "../comp_css/Navbar.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartPlus, faSearch } from "@fortawesome/free-solid-svg-icons";

const Navbar = () => {
  const navigate = useNavigate();

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
          onClick={() => {
            navigate("/user/cart");
          }}
          className="cart-button"
        >
          <FontAwesomeIcon icon={faCartPlus} className="cart-icon" />
        </div>
        {userId ? (
          <>
            <h3 className="login-button" onClick={()=>{
              navigate("/user/order-details");
            }}>
              {name}
            </h3>
            <h3 className="login-button" onClick={handleLogoutClick}>
              Logout
            </h3>
          </>
        ) : (
          <>
            <h3 className="login-button" onClick={handleLoginClick}>
              Login
            </h3>
            <h3
              className="login-button"
              onClick={() => {
                navigate("/register-user");
              }}
            >
              SignIn
            </h3>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
