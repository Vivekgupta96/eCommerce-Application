import React, { useState, useEffect } from "react";
import { Link, Navigate } from "react-router-dom";
import axios from "axios";
import "../comp_css/Product.css";
import api from '../Router/api'

const Product = () => {
  const [products, setProducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("All");
  const [selectedPrice, setSelectedPrice] = useState("All");

  let userid = localStorage.getItem("userid");

  useEffect(() => {
    api
      .get("/ecom/products/all")
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
      .post(
        `/ecom/cart/add-product?userId=${userid}&productId=${productid}`
      )
      .then((response) => {
        localStorage.setItem("cartid", response.data.cartId);

        alert("product added to Cart");
      })
      .catch((error) => {
        alert("Product Alredy In cart");
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
        (product) => product.price <= parseInt(price)
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
          <option value="Veg">Vegetable</option>
          <option value="Fruits">Fruits</option>
          <option value="Elect">Electronic</option>
        </select>

        <label>Filter by Price:</label>
        <select
          value={selectedPrice}
          onChange={(e) => handlePriceChange(e.target.value)}
        >
          <option value="All">All</option>
          <option value="lowToHigh">Low To High</option>
          <option value="hightToLow">High to Low</option>
          <option value="100">Less than ₹100</option>
          <option value="200">Less than ₹200</option>
        </select>
      </div>

      <div className="product-list">
        {filteredProducts.map((product) => (
          <div className="product-card" key={product.productId}>
            <div className="product-image1">
              <img src={product.imageUrl} alt={product.name} />
            </div>
            <div className="product-info">
              <h2>{product.name}</h2>
              <p>Category: {product.category}</p>
              <p>Description: {product.description}</p>
              <h2 className="product-price">Price: ₹ {product.price}</h2>
              <div>
                <button onClick={() => addProductToCart(product.productId)}>
                  Add to Cart
                </button>
                <button>
                  <Link to={`/product/${product.productId}`}>View</Link>
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Product;
