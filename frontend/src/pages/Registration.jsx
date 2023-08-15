import React, { useState } from "react";
import "../comp_css/Login.css";
import { useNavigate } from "react-router-dom";

const initialFormData = {
  email: "",
  password: "",
  firstName: "",
  lastName: "",
  phoneNumber: "",
};

const Registration = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState(initialFormData);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(form);
    try {
     
      navigate("/login");
    } catch (error) {
      console.error("Error registering:", error);
    }
  };

  const { email, password, firstName, lastName, phoneNumber } = form;

  return (
    <div className="login-form">
      <h2 style={{textAlign:"center"}}>Registration </h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Email:</label>
          <input
            type="text"
            name="email"
            value={email}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={password}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label>First Name:</label>
          <input
            type="text"
            name="firstName"
            value={firstName}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label>Last Name:</label>
          <input
            type="text"
            name="lastName"
            value={lastName}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label>Phone Number:</label>
          <input
            type="text"
            name="phoneNumber"
            value={phoneNumber}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <input type="submit" value="Register" />
        </div>
      </form>
    </div>
  );
};

export default Registration;
