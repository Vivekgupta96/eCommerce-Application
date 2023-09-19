import React, { useState } from "react";
import "../comp_css/Admin.css";
import AddProduct from "../components/AddProduct";
import AddCustomerAdmin from "../components/AllCustomerAdmin";
import AddOrderAdmin from "../components/AllOrderAdmin";
import AllProductAdmin from "../components/AllProductAdmin";

const Admin = () => {
  const [selectedComponent, setSelectedComponent] = useState(null);

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
      <h1 style={{ textAlign: "center", color: "blue", margin: "20px" }}>
        ADMIN PAGE
      </h1>
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