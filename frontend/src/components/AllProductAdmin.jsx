import React, { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import "../comp_css/AllProductAdmin.css";
import axios from "axios";

const AllProductAdmin = () => {
  const navigate = useNavigate();
  const { productId } = useParams();
  const [product, setProduct] = useState([]);

  const updateProduct = () => {};
  const deleteProduct = () => {};

  useEffect(() => {
    axios
    .get("http://127.0.0.1:8080/ecom/products/all")
      .then((response) => {
        setProduct(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data from the API: ", error);
      });
  }, [productId]);

  return (
    <>
      <h1 style={{ color: "green", textAlign: "left", margin: "5px" }}>
        ALL Live Product{" "}
      </h1>
      <div className="product-container">
      {product.map((product) => (
          <div className="product-card" key={product.productId}>
            <div className="product-image1">
              <img src={product.imageUrl} alt={product.name} />
            </div>
            <div className="product-info">
              <h2>{product.name}</h2>
              <p>Product ID: {product.productId}</p>
              <p>Category: {product.category}</p>
              <p>Description: {product.description.length > 30 ? product.description.substring(0, 50) + '...' : product.description}</p>

              <h2 className="product-price">Price: ₹ {product.price}</h2>
              <div>
                <button onClick={() => updateProduct(product.productId)}>
                 update
                </button>
                <button onClick={() => deleteProduct(product.productId)}>
                  delete
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default AllProductAdmin;
