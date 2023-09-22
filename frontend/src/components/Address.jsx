import React, { useState } from "react";
import api from "../Router/api";

const Address = ({ id, onClose }) => {

  const userid= localStorage.getItem("userid");
  const addressobj = {
    flatNo: "",
    city: "",
    state: "",
    zipCode: "",
    street: "",
  };
  const [address, setAddress] = useState(addressobj);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setAddress((prevAddress) => ({
      ...prevAddress,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(address);
    api
      .post(`/ecom/customer-addresses/${userid}`, address)
      .then((response) => {
        alert(response.data.messege);
      })
      .catch((error) => {
        alert(error.response.data.message);
        console.error("Error fetching data from the API: ", error);
      });
  };

  return (
    <>
      <div className="modal-backdrop">
        <div className="update-product-form" style={{ width: "400px" }}>
          <span className="close-button" onClick={onClose}>
            &times;
          </span>
          <h2 style={{ textAlign: "center" }}>Required Address</h2>
          <form onSubmit={handleSubmit}>
            <div>
              <label htmlFor="flatNo">Flat No:</label>
              <input
                type="text"
                name="flatNo"
                value={address.flatNo}
                onChange={handleChange}
              />
            </div>
            <div>
              <label htmlFor="street">Street:</label>
              <input
                type="text"
                name="street"
                value={address.street}
                onChange={handleChange}
              />
            </div>
            <div>
              <label htmlFor="city">City:</label>
              <input
                type="text"
                name="city"
                value={address.city}
                onChange={handleChange}
              />
            </div>
            <div>
              <label htmlFor="zipCode">Zip Code:</label>
              <input
                type="text"
                name="zipCode"
                value={address.zipCode}
                onChange={handleChange}
              />
            </div>
            <div>
              <label htmlFor="state">State:</label>
              <input
                type="text"
                name="state"
                value={address.state}
                onChange={handleChange}
              />
            </div>

            <button type="submit">Save</button>
          </form>
        </div>
      </div>
    </>
  );
};

export default Address;
