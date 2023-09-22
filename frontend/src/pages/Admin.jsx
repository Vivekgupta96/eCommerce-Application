import React, { useState } from "react";
import "../comp_css/Admin.css";
import AddProduct from "../components/AddProduct";
import AddCustomerAdmin from "../components/AdminUserDetails";
import AddOrderAdmin from "../components/AllOrderAdmin";
import AllProductAdmin from "../components/AllProductAdmin";
import { useNavigate } from "react-router-dom";

const Admin = () => {
  const [selectedComponent, setSelectedComponent] = useState(null);
  const navigate = useNavigate();

  const renderSelectedComponent = () => {
    switch (selectedComponent) {
      case "add-product":
        return <AddProduct />;
      case "all-orders":
        return <AddOrderAdmin />;
      case "add-customer":
        return <AddCustomerAdmin />;
      default:
        return <AllProductAdmin />;
    }
  };

  return (
    <>
      <div className="admin-navbar">
        <h3
          onClick={() => {
            setSelectedComponent(<AllProductAdmin />);
          }}
        >
          Admin Home
        </h3>
        <h1 style={{ textAlign: "center", color: "blue" }}>ADMIN PAGE</h1>
        <h3
          onClick={() => {
            localStorage.removeItem("adminid");
            localStorage.removeItem("jwtTocken");
            navigate("/admin-login");
          }}
        >
          Logout
        </h3>
      </div>

      <div className="admincontainer">
        <div className="productConatiner">{renderSelectedComponent()}</div>
        <div className="boxConatiner">
          <ul>
            <li
              onClick={() => {
                setSelectedComponent("add-product");
              }}
            >
              Add New Product
            </li>
            <li
              onClick={() => {
                setSelectedComponent("all-orders");
              }}
            >
              View All Orders
            </li>
            <li
              onClick={() => {
                setSelectedComponent("add-customer");
              }}
            >
              View All Customer
            </li>
          </ul>
        </div>
      </div>
    </>
  );
};
export default Admin;
