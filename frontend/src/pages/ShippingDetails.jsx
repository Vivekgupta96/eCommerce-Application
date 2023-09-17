import React, { useState } from "react";
import '../comp_css/ShippingDetails.css'

const ShippingDetails = () => {
  const initialShippingDetails = {
    address: "",
    city: "",
    state: "",
    country: "",
    postalCode: ""
  };

  const [shippingDetails, setShippingDetails] = useState(initialShippingDetails);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setShippingDetails((prevDetails) => ({
      ...prevDetails,
      [name]: value
    }));
  };

  const handleAddShippingDetails = () => {
    // Implement your logic to add the shipping details
    console.log("Adding shipping details:", shippingDetails);
    // You can reset the form after adding details
    setShippingDetails(initialShippingDetails);
  };

  return (
    <div className="shipping-details">
      <h2>Shipping Details</h2>
      <form>
        <label>
          Address:
          <input
            type="text"
            name="address"
            value={shippingDetails.address}
            onChange={handleInputChange}
          />
        </label>
        <label>
          City:
          <input
            type="text"
            name="city"
            value={shippingDetails.city}
            onChange={handleInputChange}
          />
        </label>
        <label>
          State:
          <input
            type="text"
            name="state"
            value={shippingDetails.state}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Country:
          <input
            type="text"
            name="country"
            value={shippingDetails.country}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Postal Code:
          <input
            type="text"
            name="postalCode"
            value={shippingDetails.postalCode}
            onChange={handleInputChange}
          />
        </label>
        <button type="button" onClick={handleAddShippingDetails}>
          Add Shipping Details
        </button>
      </form>
    </div>
  );
};

export default ShippingDetails;
