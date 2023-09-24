import React, { useState } from "react";
import "../comp_css/updateform.css";
import api from "../Router/api";
import { useNavigate } from "react-router-dom";

const UpdateProductForm = ({ address, onclose }) => {
  const [updatedAddress, setUpdatedAddress] = useState({ ...address });
  const navigate = useNavigate();
  const userid = localStorage.getItem("userid");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUpdatedAddress({ ...updatedAddress, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(updatedAddress);
    api

      .put(
        `/ecom/customer-addresses/update/${address.addressID}`,
        updatedAddress
      )
      .then((response) => {
        alert("Address Updated Succesfully..");
        navigate(`/user/profile/${userid}`);
        onclose();
        window.location.reload();
      })
      .catch((error) => {
        alert(error.response.data.message);
        console.error("Error fetching data from the API: ", error);
      });
    // make api call to update address
  };

  return (
    <>
      <div className="modal-backdrop">
        <div className="update-product-form" style={{ width: "400px" }}>
          <span className="close-button" onClick={onclose}>
            &times;
          </span>
          <h2 style={{ textAlign: "center" }}>Update Address</h2>
          <form onSubmit={handleSubmit}>
            <div>
              <label htmlFor="flatNo">Flat No:</label>
              <input
                type="text"
                name="flatNo"
                value={updatedAddress.flatNo}
                onChange={handleChange}
              />
            </div>
            <div>
              <label htmlFor="street">Street:</label>
              <input
                type="text"
                name="street"
                value={updatedAddress.street}
                onChange={handleChange}
              />
            </div>
            <div>
              <label htmlFor="city">City:</label>
              <input
                type="text"
                name="city"
                value={updatedAddress.city}
                onChange={handleChange}
              />
            </div>
            <div>
              <label htmlFor="zipCode">Zip Code:</label>
              <input
                type="text"
                name="zipCode"
                value={updatedAddress.zipCode}
                onChange={handleChange}
              />
            </div>
            <div>
              <label htmlFor="state">State:</label>
              <input
                type="text"
                name="state"
                value={updatedAddress.state}
                onChange={handleChange}
              />
            </div>
            <button type="submit">Update Address</button>
          </form>
        </div>
      </div>
    </>
  );
};

export default UpdateProductForm;
