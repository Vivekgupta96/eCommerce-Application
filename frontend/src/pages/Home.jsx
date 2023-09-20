import React,{useEffect} from "react";
import Slider from "../components/Slider";

import "../comp_css/Slider.css"
import Atta from "../picture/Atta_and_dals.avif";
import Beauty from "../picture/Beauty_and_personal_care.avif";
import Cleaning from "../picture/Cleaning_essentials.avif";
import Home_essentials from "../picture/Home_essentials.avif";
import kids_fashion from "../picture/kids_fashion.avif";
import Kitchen_must_haves from "../picture/Kitchen_must_haves.avif";
import Laptops_and_Tablets from "../picture/Laptops_and_Tablets.avif";
import men_fashion from "../picture/men_fashion.avif";
import Oil_and_ghee from "../picture/Oil_and_ghee.avif";
import Smart_Televisions from "../picture/Smart_Televisions.avif";


const Home = () => {
  const veritycard = [
    Atta,
    Beauty,
    Cleaning,
    Home_essentials,
    kids_fashion,
    Kitchen_must_haves,
    Laptops_and_Tablets,
    men_fashion,
    Oil_and_ghee,
    Smart_Televisions,
  ];
  const slideImages = [
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691935239_Freedom_Finds.jpg?im=Resize=(1680,320)",
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691950461_Handloom_Sarees_in_Colors_of_India.jpg?im=Resize=(1680,320)",
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691612739_Aaj_Ki_Deals_Desktop.jpg?im=Resize=(1680,320)",
  ];
  const slideImages2 = [
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691773740_Paytm_Wallet_1240x209.jpg?im=Resize=(1241,195)",
    "https://www.jiomart.com/images/cms/aw_rbslider/slides/1691435554_MobiKwik_1240x209.jpg?im=Resize=(1241,195)",
  ];
  const styleFixedImg = {
    width: "100%",
    height: "25vh",
    marginTop: "10px",
    marginBottom: "10px",
  };
  useEffect(() => {
    document.title = 'Ecommerse | Home Page';
    return () => { 
      document.title = 'Ecommerse App';
    };
  }, []); 

  return (
    <>
      <div>
        <Slider images={slideImages} interval={4000} />
      </div>
      <div className="ImageFixed">
        <img
          style={styleFixedImg}
          src="https://www.jiomart.com/images/cms/aw_rbslider/slides/1691749079_Aaj_Ki_Deals.jpg?im=Resize=(1240,150)"
          alt="Image"
        />
      </div>
      <div>
        <Slider images={slideImages2} interval={5000} />
      </div>
      <div className="cardbox">
        {veritycard.map((el, index) => (
          <img src={el} alt={`image${index}`} />
        ))}
      </div>
    </>
  );
};

export default Home;
