import React, { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import "../comp_css/AllProductAdmin.css";
import axios from "axios";
import api from "../Router/api";
import UpdateProductForm from "./UpdateProductForm";

const AllProductAdmin = () => {
  const { productId } = useParams();
  const [products, setProducts] = useState([]);
  const [deleted, setDeleted] = useState(false);
  const [showUpdateModal, setShowUpdateModal] = useState(false); 
  const [selectedProduct, setSelectedProduct] = useState(null);

  const updateProduct = (productIdToUpdate) => {
    const productToUpdate = products.find(
      (product) => product.productId === productIdToUpdate
    );
    setSelectedProduct(productToUpdate);
    setShowUpdateModal(true);
  };

  const closeUpdateModal = () => {//sending function as a props to the updateproduct form
    setSelectedProduct(null);
    setShowUpdateModal(false);
  };
  
  const handleUpdate = (updatedProduct) => {//sending function as a props to the updateproduct form
    api
      .put(`/ecom/products/update/${updatedProduct.productId}`, updatedProduct)
      .then((response) => {
        console.log(response.data);
        console.log("working.....");
        closeUpdateModal();
      })
      .catch((error) => {
        console.log(error.response.data.message);
      });
  };

  const deleteProduct = (productIdToDelete) => {
    api
      .delete(`/ecom/products/${productIdToDelete}`)
      .then((response) => {
        alert(response.data);

        const updatedProducts = products.filter(
          (product) => product.productId !== productIdToDelete
        );
        setProducts(updatedProducts);
        setDeleted(true);
      })
      .catch((error) => {
        console.error("Error deleting product: ", error);
        alert(error.response.data.message);
      });
  };

  useEffect(() => {
    axios
      .get("http://127.0.0.1:8080/ecom/products/all?sort=desc")
      .then((response) => {
        const sortedProducts = response.data.sort(
          (a, b) => b.productId - a.productId
        );
        setProducts(sortedProducts);

        setDeleted(false);
      })
      .catch((error) => {
        console.error("Error fetching data from the API: ", error);
      });
  }, [productId, deleted]);

  return (
    <>
      <h1 style={{ color: "green", textAlign: "center", margin: "5px" }}>
        ALL Live Products{" "}
      </h1>

      {showUpdateModal && (
        <div className="update-modal">
          <UpdateProductForm product={selectedProduct} onUpdate={handleUpdate}onClose={closeUpdateModal}
          />
        </div>
      )}


      <div className="product-container1">
        {products.map((product) => (
          <div className="product-card1" key={product.productId}>
            <div className="product-image11">
              <img src={product.imageUrl} alt={product.name} />
            </div>
            <div className="product-info1">
              <h2>{product.name}</h2>
              <p>Product ID: {product.productId}</p>
              <p>Category: {product.category}</p>
              <p>
                Description:{" "}
                {product.description.length > 30
                  ? product.description.substring(0, 50) + "..."
                  : product.description}
              </p>

              <h2 className="product-price1">Price: ₹ {product.price}</h2>
              <div className="button-container1">
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
