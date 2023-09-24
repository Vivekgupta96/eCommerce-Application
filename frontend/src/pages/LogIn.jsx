import React, { useState, useEffect } from "react";
import "../comp_css/Login.css";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";
import loginbg from "../picture/loginbg1.webp";

const bg = {
  backgroundImage: `url(${loginbg})`,
  backgroundSize: "cover",
  backgroundRepeat: "no-repeat",
  backgroundPosition: "center center",
  border: "1px solid grey",
  height: "100vh",
};

const formData = {
  username: "",
  password: "",
};

const Login = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState(formData);
  
  useEffect(() => {
    document.title = 'Ecommerse | LogIn';
    return () => { 
      document.title = 'Ecommerse App';
    };
  }, []); 


  const setHandlerChange = (e) => {
    const val = e.target.value;
    setForm({ ...form, [e.target.name]: val });
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    try {
      const authHeader = `Basic ${btoa(`${form.username}:${form.password}`)}`;
      const response = await axios.get("http://localhost:8080/ecom/signIn", {
        headers: {
          Authorization: authHeader,
        },
      });
      //console.log(response.data);
      if (response.headers.authorization != undefined) {
        localStorage.setItem("jwtToken", response.headers.authorization);
        localStorage.setItem("name", response.data.firstNAme || "LogIn");
        localStorage.setItem("userid", response.data.id);
        alert("Login successfully");
        navigate("/");
      } else {
        alert("Invalid Credential");
        console.error("JWT retrieval failed");
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        alert("Invalid credentials. Please try again.");
      } else {
        alert("Error during login. Please try again later.");
        console.error("Error during login:", error);
      }
    }
  };

  const { username, password } = form;

  return (
    <>
    <div style={bg}>
      <h2 style={{ textAlign: "center", color: "White", margin: "20px" }}>
       WELCOME TO USER LOGIN PAGE
      </h2>
      <div className="loginConatiner" >
        <div className="login-form">
          <h2 style={{ textAlign: "center" }}>LogIn </h2>
          <form onSubmit={submitHandler}>
            <div className="form-group">
              <label htmlFor="username">Username:</label>
              <input
                id="username"
                type="text"
                name="username"
                value={username}
                onChange={setHandlerChange}
              />
            </div>
            <br />
            <div className="form-group">
              <label>Password:</label>
              <input
                type="password"
                name="password"
                value={password}
                onChange={setHandlerChange}
              />
            </div>
            <div className="form-group">
              <input type="submit" value="Login" />
              <p>
                Don't have an account?{" "}
                <Link to="/register-user">Register here</Link>
              </p>
            </div>
          </form>
        </div>
      </div>
      </div>
    </>
  );
};

export default Login;
