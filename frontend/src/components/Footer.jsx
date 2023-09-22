import React from "react";
import { Link } from "react-router-dom";
import "../comp_css/Footer.css";

const Footer = () => {
  return (
    <div className="footer">
      <div className="footer-grid">
        <div className="footer-section">
          <h4>POLICY INFO</h4>
          <ul>
            <li>Privacy Policy</li>
            <li>Terms of Sale</li>
            <li>Terms of Use</li>
            <li>Report Abuse & Takedown Policy</li>
            <li>FAQ</li>
          </ul>
        </div>
        <div className="footer-section">
          <h4>COMPANY</h4>
          <ul>
            <li>Impact@eCommerseAPp</li>
            <li>Careers</li>
            <li>Blog</li>
            <li>Sitemap</li>
            <li>Contact Us</li>
          </ul>
        </div>
        <div className="footer-section">
          <h4>E-Commerse</h4>
          <ul>
            <li>Product App</li>
            <li>Sell on our Website</li>
            <li>Media Enquiries</li>
          </ul>
        </div>
        <div className="footer-section">
          <h4>POPULAR LINKS</h4>
          <ul>
            <li>Top Product</li>
            <li>Groceries</li>
            <li>Vegetable</li>
            <li>Fruits</li>
          </ul>
        </div>
        <div className="footer-section">
          <h4>SUBSCRIBE</h4>
          <div className="subscribe-box">
            <input type="text" placeholder="Enter your email" />
            <button>SUBSCRIBE</button>
          </div>
          <p>
            Register now to get updates on promotions and coupons
          </p>
          <p className="admin-link" >
            <Link to="/admin-Login"  style={{color:"white"}}>Admin Access</Link>
          </p>
        </div>
      </div>
      <div className="footer-images">
        {/* <div>
          <img src={footer1} alt="Footer 1" />
        </div>
        <div>
          <img src={footer2} alt="Footer 2" />
        </div> */}
      </div>
    </div>
  );
};

export default Footer;
