import React, { useState, useEffect } from "react";
import Slider from "../components/Slider";

const Home = () => {
  const slideImages = [
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691935239_Freedom_Finds.jpg?im=Resize=(1680,320)",
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691950461_Handloom_Sarees_in_Colors_of_India.jpg?im=Resize=(1680,320)",
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691773740_Paytm_Wallet_1240x209.jpg?im=Resize=(1241,195)",
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691435554_MobiKwik_1240x209.jpg?im=Resize=(1241,195)"

    
  ];
  const slideImages2 = [
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691773740_Paytm_Wallet_1240x209.jpg?im=Resize=(1241,195)",
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691435554_MobiKwik_1240x209.jpg?im=Resize=(1241,195)"

    
  ];

  return (
    <>
      <div>
        <Slider images={slideImages} interval={3000} />
      </div>
      <div className="ImageFixed">
        <img
          style={{ width: "100%", height: "25vh", marginTop: "30px" }}
          src="https://www.jiomart.com/images/cms/aw_rbslider/slides/1691749079_Aaj_Ki_Deals.jpg?im=Resize=(1240,150)"
          alt="Image"
        />
      </div>
      <div>
        <Slider images={slideImages2} interval={3000} />
      </div>
    </>
  );
};

export default Home;
