import React, { useState, useEffect } from "react";
import "../comp_css/Slider.css"; //

const Slider = ({ images, interval }) => {
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {

    const slideInterval = setInterval(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, interval);

  }, [images, interval]);
  
  return (
    <div className="slider">
      <img
        key={currentIndex}
        src={images[currentIndex]}
        alt={`Slide ${currentIndex}`}
       
      />
    </div>
  );
};

export default Slider;
