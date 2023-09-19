import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useNavigation } from "react-router-dom";
import UpdatePassword from "../components/UpdatePassword";
import Profile from "../components/Profile";
import UpdateAddress from "../components/UpdateAddress";
import api from '../Router/api'

import "../comp_css/order.css";

const OrderDetails = () => {
  const navigate = useNavigate();
  const userId = localStorage.getItem("userid");
  const [msg, setMsg] = useState("");
  const [allOrder, setAllOrder] = useState([]);
  const [selectedComponent, setSelectedComponent] = useState(null);

  const handeldemakePayment = (orderid) => {
    localStorage.setItem("orderid", orderid);
    navigate("/user/make-payment");
  };

  const handeldeleteOrder = (orderId) => {
    axios
      .delete(
        `http://127.0.0.1:8080/ecom/orders/users/${userId}/${orderId}`
      )
      .then((response) => {
        setMsg(response.data);
        alert(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data from the API: ", error);
      });
  };

  useEffect(() => {
    api
      .get(`/ecom/orders/orders/${userId}`)
      .then((response) => {
        const sortedOrders = response.data.sort((a, b) => {
          return new Date(b.orderDate) - new Date(a.orderDate);
        });

        setAllOrder(sortedOrders);
      })
      .catch((error) => {
        console.error("Error fetching data from the API: ", error);
      });
  }, [msg, userId,selectedComponent]);

  const renderSelectedComponent = () => {
    switch (selectedComponent) {
      case "profile":
        return <Profile />;
      case "updatePassword":
        return <UpdatePassword />;
      case "updateAddress":
        return <UpdateAddress />;
      default:
        return null;
    }
  };
  return (
    <>
      <div className="container">
        <div className="orderContainer">
          {selectedComponent === null ? (
            allOrder.length > 0 ? (
              allOrder.map((order, index) => (
                <div key={index} className="order">
                  <div className="odr1">
                    <h3>Order Number : {index + 1}</h3>
                    <p>Order ID: {order.orderId}</p>
                    <p>Status: {order.status}</p>
                    <p>
                      Order Date: {new Date(order.orderDate).toLocaleString()}
                    </p>
                    <h3 style={{ color: "green" }}>
                      Total Amount: ${order.totalAmount}
                    </h3>

                    {order.status === "SHIPPED" ? (
                      <button style={{ backgroundColor: "green" }} disabled>
                        View
                      </button>
                    ) : (
                      <button
                        style={{ backgroundColor: "red" }}
                        onClick={() => {
                          handeldeleteOrder(order.orderId);
                        }}
                      >
                        Cancel Order
                      </button>
                    )}

                    {order.status === "SHIPPED" ? (
                      <button style={{ backgroundColor: "green" }} disabled>
                        SHIPPED
                      </button>
                    ) : (
                      <button
                        onClick={() => {
                          handeldemakePayment(order.orderId);
                        }}
                      >
                        Make Payment
                      </button>
                    )}
                  </div>

                  <div className="odr2">
                    <h3>Order Items</h3>
                    {order.orderItem.map((item) => (
                      <li key={item.orderItemId}>
                        {item.product.name} - Quantity: {item.quantity}
                      </li>
                    ))}
                  </div>
                </div>
              ))
            ) : (
              <div
                style={{
                  color: "green",
                  fontSize: "20px",
                  border: "2px solid grey",
                  height: "50vh",
                  textAlign: "center",
                }}
              >
                <h1 style={{ marginTop: "50px" }}>No items present</h1>
              </div>
            )
          ) : (
            <div className="selected-component-container">
              {renderSelectedComponent()}
              <button onClick={() => setSelectedComponent(null)}>
                Back to Orders
              </button>
            </div>
          )}
        </div>
        <div className="box">
          <h1>Profile</h1>
          <button onClick={() => setSelectedComponent("profile")}>
            View Profile
          </button>
          <button onClick={() => setSelectedComponent("updatePassword")}>
            Update Password
          </button>
          <button onClick={() => setSelectedComponent("updateAddress")}>
            Update Address
          </button>
        </div>
      </div>
    </>
  );
};

export default OrderDetails;