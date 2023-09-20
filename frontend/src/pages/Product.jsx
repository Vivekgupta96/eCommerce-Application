import React, { useState, useEffect } from "react";
import { Link, Navigate } from "react-router-dom";
import axios from "axios";
import "../comp_css/Product.css";
import api from "../Router/api";

const Product = () => {
  const [products, setProducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("All");
  const [selectedPrice, setSelectedPrice] = useState("All");

  let userid = localStorage.getItem("userid");

  useEffect(() => {
    axios
      .get("http://127.0.0.1:8080/ecom/products/all")
      .then((response) => {
        setProducts(response.data);
        filterProducts(selectedCategory, selectedPrice, response.data);
      })
      .catch((error) => {
        console.error("Error fetching data from the API: ", error);
      });
  }, [selectedCategory, selectedPrice]);

  const addProductToCart = (productid) => {
    api
      .post(`/ecom/cart/add-product?userId=${userid}&productId=${productid}`)
      .then((response) => {
        localStorage.setItem("cartid", response.data.cartId);

        alert("product added to Cart");
      })
      .catch((error) => {
        if (error.response && error.response.data) {
          alert(error.response.data.message);
        } else {
          alert("Error To adding Product . Please try again later.");
          console.error("Error registering:", error);
        }
      });
  };

  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
  };

  const handlePriceChange = (price) => {
    setSelectedPrice(price);
  };

  const filterProducts = (category, price, data) => {
    let filteredProducts = data;

    if (category !== "All") {
      filteredProducts = filteredProducts.filter(
        (product) => product.category === category
      );
    }

    if (price !== "All") {
      filteredProducts = filteredProducts.filter(
        (product) => product.price > product.price
      );
    }
    setFilteredProducts(filteredProducts);
  };

  return (
    <div className="product-page">
      <div className="filter-section">
        <label>Filter by Category:</label>
        <select
          value={selectedCategory}
          onChange={(e) => handleCategoryChange(e.target.value)}
        >
          <option value="All">All</option>
          <option value="vegetables">Vegetable</option>
          <option value="fruits">Fruits</option>
          <option value="electronics">Electronic</option>
          <option value="gadgets">Gaggets</option>
        </select>

        <label>Filter by Price:</label>
        <select
          value={selectedPrice}
          onChange={(e) => handlePriceChange(e.target.value)}
        >
          <option value="All">All</option>
          <option value="lowToHigh">Low To High</option>
          <option value="hightToLow">High to Low</option>
        </select>
      </div>

      <div className="product-list">
        {filteredProducts.length == 0 ? (
          <h1
            style={{
              textAlign: "center",
              margin: "50px",
              color: "green",
              width: "800px",
            }}
          >
            Product Not found With Selected{" "}
          </h1>
        ) : (
          filteredProducts.map((product) => (
            <div className="product-card" key={product.productId}>
              <div className="product-image1">
                <img src={product.imageUrl} alt={product.name} />
              </div>
              <div className="product-info">
                <h2>{product.name}</h2>
                <p>
                  <strong>Category :</strong> {product.category}
                </p>
                <p>
                  <strong>Description: </strong>
                  {product.description.substring(0, 25)}
                </p>
                <h2 className="product-price">Price: ₹ {product.price}</h2>
                <p>
                  {" "}
                  <strong>Rating :</strong>
                  {product.reviews.length == 0
                    ? "Not Available"
                    : product.reviews[0].rating}
                </p>

                <div>
                  <button onClick={() => addProductToCart(product.productId)}>
                    Add to Cart
                  </button>
                  <button>
                    <Link
                      to={`/product/${product.productId}`}
                      style={{ textDecoration: "none", color: "white" }}
                    >
                      View
                    </Link>
                  </button>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Product;
