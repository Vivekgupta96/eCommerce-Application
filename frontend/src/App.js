import "./App.css";
import AllRoutes from "./Router/AllRoutes";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import { useLocation } from "react-router-dom";

function App() {
  const location = useLocation();
  const isRestrictedPath = [
    "/login",
    "/register-user",
    "/admin-login",
    "/admin/admin",
    "*"
  ].includes(location.pathname);

  return (
    <div >
      {!isRestrictedPath && <Navbar />}
      <div style={{minHeight:"90vh"}}>
      <AllRoutes />
      </div>
      {!isRestrictedPath && <Footer />}
    </div>
  );
}
export default App;

/* function App() {
  const location = useLocation();
  return (
    <div>
      {location.pathname == "/login" ||
      location.pathname == "/register-user" ||
      location.pathname == "/admin-login" ? (
        ""
      ) : (
        <Navbar />
      )}

      <AllRoutes />
      {location.pathname == "/login" ||
      location.pathname == "/register-user" ||
      location.pathname == "/admin-login" ? (
        ""
      ) : (
        <Footer />
      )}
    </div>
  );
}
*/
