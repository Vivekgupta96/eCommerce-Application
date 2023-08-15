import React,{useState} from "react";
import "../comp_css/Cart.css";
import { useNavigate } from "react-router-dom";

const Cart = () => {
  const navigate=useNavigate();

    const [cartItems, setCartItems] = useState([
      {
        productId: 1,
        name: "Potato",
        imageUrl:
          "https://www.jiomart.com/images/product/original/590002136/onion-5-kg-pack-product-images-o590002136-p590002136-0-202203141906.jpg?im=Resize=(420,420)",
        description: "Fresh fruits",
        price: 150,
        category: "Vegetable",
        available: true,
      },
      {
        productId: 2,
        name: "Tomato",
        imageUrl:
        "https://www.jiomart.com/images/product/original/590002136/onion-5-kg-pack-product-images-o590002136-p590002136-0-202203141906.jpg?im=Resize=(420,420)",
        description: "Fresh vegetable",
        price: 190,
        category: "Vegetable",
        available: true,
      }
      ]);
  // Calculate the total cart price
  const totalCartPrice = cartItems.reduce((total, item) => total + item.price, 0);
  const handleCheckout = () => {
    
    alert("Checkout button clicked!");
    navigate('/save-address')
  };


  return (
    <div className="cart-page">
      <h2>Your Cart</h2>
      <ul className="cart-items">
        {cartItems.map((item) => (
          <li key={item.productId} className="cart-item">
            <img src={item.imageUrl} alt={item.name} className="item-image" />
            <div className="item-details">
              <h3>{item.name}</h3>
              <p>Price: ${item.price}</p>
            </div>
          </li>
        ))}
      </ul>
      <div className="total-price">
        <strong>Total Cart Price: ${totalCartPrice}</strong>
        
      </div>
      <button className="checkout-button" onClick={handleCheckout}>
        Proceed to Checkout
      </button>
     
    </div>
  );
};

export default Cart;
