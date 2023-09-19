
import React, { useState } from 'react';
import axios from 'axios'; 
import '../comp_css/AddProduct.css'; 


function AddProduct() {
  const [product, setProduct] = useState({
    name: '',
    imageUrl: '',
    description: '',
    price: 0,
    category: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send a POST request to your server with the product data
      const response = await axios.post('/api/addProduct', product);

      // Handle the response as needed
      console.log('Product added successfully:', response.data);

      // Optionally, you can clear the form after successful submission
      setProduct({
        name: '',
        imageUrl: '',
        description: '',
        price: 0,
        category: '',
      });
    } catch (error) {
      // Handle any errors that occur during the POST request
      console.error('Error adding product:', error);
    }
  };

  return (
    <div className="add-product-container">
      <h2>Add Product</h2>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label htmlFor="name">Product Name:</label>
          <input
            type="text"
            id="name"
            name="name"
            value={product.name}
            onChange={handleChange}
            placeholder="Product Name"
          />
        </div>
        <div className="input-group">
          <label htmlFor="imageUrl">Image URL:</label>
          <input
            type="text"
            id="imageUrl"
            name="imageUrl"
            value={product.imageUrl}
            onChange={handleChange}
            placeholder="Image URL"
          />
        </div>
        <div className="input-group">
          <label htmlFor="description">Description:</label>
          <input
            type="text"
            id="description"
            name="description"
            value={product.description}
            onChange={handleChange}
            placeholder="Description"
          />
        </div>
        <div className="input-group">
          <label htmlFor="price">Price:</label>
          <input
            type="number"
            id="price"
            name="price"
            value={product.price}
            onChange={handleChange}
            placeholder="Price"
          />
        </div>
        <div className="input-group">
          <label htmlFor="category">Category:</label>
          <input
            type="text"
            id="category"
            name="category"
            value={product.category}
            onChange={handleChange}
            placeholder="Category"
          />
        </div>
        {/* Add more input fields for other product details */}
        <button type="submit">Add Product</button>
      </form>
    </div>
  );
}

export default AddProduct;
