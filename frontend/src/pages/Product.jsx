import React ,{useState} from "react";
import "../comp_css/Product.css"; // Make sure to link the CSS file for styling

const all = [
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
  },
  {
    productId: 3,
    name: "Fruits",
    imageUrl:
      "https://www.jiomart.com/images/product/original/590003531/ginger-indian-200-g-product-images-o590003531-p590003531-0-202203171030.jpg?im=Resize=(420,420)",
    description: "Fresh vegetable",
    price: 210,
    category: "Fruits",
    available: true,
  },
  {
    productId: 4,
    name: "Fruits",
    imageUrl: "https://www.jiomart.com/images/product/original/590002136/onion-5-kg-pack-product-images-o590002136-p590002136-0-202203141906.jpg?im=Resize=(420,420)",
    description: "Fresh fruits",
    price: 50,
    category: "Fruits",
    available: true,
  },
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
  },
  {
    productId: 3,
    name: "Fruits",
    imageUrl:
      "https://www.jiomart.com/images/product/original/590003531/ginger-indian-200-g-product-images-o590003531-p590003531-0-202203171030.jpg?im=Resize=(420,420)",
    description: "Fresh vegetable",
    price: 210,
    category: "Fruits",
    available: true,
  },
  {
    productId: 4,
    name: "Fruits",
    imageUrl: "https://www.jiomart.com/images/product/original/590002136/onion-5-kg-pack-product-images-o590002136-p590002136-0-202203141906.jpg?im=Resize=(420,420)",
    description: "Fresh fruits",
    price: 50,
    category: "Fruits",
    available: true,
  },
];

const Product = () => {
  const [filteredProducts, setFilteredProducts] = useState(all);
  const [selectedCategory, setSelectedCategory] = useState("All");
  const [selectedPrice, setSelectedPrice] = useState("All");

  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
    filterProducts(category, selectedPrice);
  };

  const handlePriceChange = (price) => {
    setSelectedPrice(price);
    filterProducts(selectedCategory, price);
  };

  const filterProducts = (category, price) => {
    let filtered = all;

    if (category !== "All") {
      filtered = all.filter((product) => product.category === category);
    }

    if (price !== "All") {
      filtered = filtered.filter((product) => product.price <= parseInt(price));
    }

    setFilteredProducts(filtered);
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
          <option value="Vegetable">Vegetable</option>
          <option value="Fruits">Fruits</option>
          {/* Add other categories here */}
        </select>

        <label>Filter by Price:</label>
        <select
          value={selectedPrice}
          onChange={(e) => handlePriceChange(e.target.value)}
        >
          <option value="All">All</option>
          <option value="100">Less than ₹100</option>
          <option value="200">Less than ₹200</option>
          {/* Add other price ranges here */}
        </select>
      </div>

      <div className="product-list">
        {all.map((product) => (
          <div className="product-card" key={product.productId}>
            <div className="product-image1">
              <img src={product.imageUrl} alt={product.name} />
            </div>
            <div className="product-info">
              <h2>{product.name}</h2>
              <p>Category: {product.category}</p>
              <p>Description: {product.description}</p>
              <p className="product-price">Price: ₹ {product.price}</p>
              <button>Add to Cart</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Product;
