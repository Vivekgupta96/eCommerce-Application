import "./App.css";
import AllRoutes from "./Router/AllRoutes";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import { useLocation } from "react-router-dom";

function App() {
  const location = useLocation();
  return (
    <div>
      {location.pathname == "/login" ||
      location.pathname == "/register-user" ? (
        ""
      ) : (
        <Navbar />
      )}

      <AllRoutes />
      {location.pathname == "/login" ||
      location.pathname == "/register-user" ? (
        ""
      ) : (
        <Footer />
      )}
    </div>
  );
}

export default App;
