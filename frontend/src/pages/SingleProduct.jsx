import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../comp_css/SingleProduct.css";

const singleProduct = {
  productId: 1,
  name: "potato",
  imageUrl:
    "https://www.jiomart.com/images/product/original/590002136/onion-5-kg-pack-product-images-o590002136-p590002136-0-202203141906.jpg?im=Resize=(420,420)",
  description: "Fresh fruits",
  price: 150,
  category: "Vegetable",
  available: true,
};
const SingleProduct = () => {
  const navigate = useNavigate();
  const [product, setProduct] = useState(singleProduct);

  const [productSize, setProductSize] = useState("");
  const user = localStorage.getItem("user");
  const [cart, setCart] = useState(
    JSON.parse(localStorage.getItem(user)) || []
  );

  useEffect(() => {
    localStorage.setItem(user, JSON.stringify(cart));
  }, [cart, user]);

  const options = ["S", "M", "L", "XL", "2XL"];

  const handleAddToCart = () => {
    if (!user) {
      // Handle login error
    } else if (!productSize) {
      // Handle product size error
    } else {
      const productExists = cart.some((item) => item.id === product.id);
      if (productExists) {
        // Handle product already in cart error
      } else {
        const newCartItem = { ...product, size: productSize, Qty: 1 };
        setCart([...cart, newCartItem]);
        // Update localStorage and show success message
      }
    }
  };

  const handleBuyNow = () => {
    // Similar logic as handleAddToCart, with navigation to the cart page
  };

  return (
    <div className="single-product-container">
      <div className="product-image">
        <img src={product.imageUrl} style={{width:"200px"}} alt={product.name} />
      </div>
      <div className="product-info">
        <h2 >{product.name}</h2>
        <h3 >Category: {product.category}</h3>
        <h3 >Description: {product.description}</h3>

        <h3 className="product-price">Price: ₹ {product.price}</h3>
        <div>
        <button onClick={handleAddToCart}>
          ADD TO CART
        </button>
        <button onClick={handleBuyNow} >Buy</button>
      </div>
      </div>
      
    </div>
  );
};

export default SingleProduct;
