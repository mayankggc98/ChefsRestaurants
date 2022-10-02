import React from "react";
import { useReducer } from "react";
import { BrowserRouter, Link, Routes, Route } from "react-router-dom";
import "../App.css";
import AddDish from "./AddDish";
import AddRestaurant from "./AddRestaurant";
import AdminRestaurants from "./AdminRestaurants";
import Home from "./Home";
import Login from "./Login";
import Logout from "./Logout";
import Register from "./Register";
import TrendingRestaurant from "./TrendingRestaurant";
import VenderRestaurants from "./VenderRestaurants";
import ViewAllRestaurents from "./ViewAllRestaurents";
import Restaurant from "./Restaurant";
import Wallet from "./Wallet";
import Orders from "./Orders";
import Cart from "./Cart";

export const UserNameContext = React.createContext();

const userInitial = {
  userId : sessionStorage.getItem("userId"),
  userName: sessionStorage.getItem("userName"),
  selectedRole : sessionStorage.getItem("selectedRole"),
};

const reduce = (state, action) => {
  switch (action) {
    case "Login":
      return {
        ...state,
        userId : sessionStorage.getItem("userId"),
        userName: sessionStorage.getItem("userName"),
        selectedRole : sessionStorage.getItem("selectedRole")
      };
      case "Logout":
        sessionStorage.clear();
        return {
          ...state,
          userID:"",
          userName: "",
          selectedRole : ""
      };
    default:
      return { state };
    }
};

function Nav() {
  const [userObj, dispatch] = useReducer(reduce, userInitial);
  // useEffect(()=>{
  //   console.log(userObj.selectedRole);
  // })
  return (
    <UserNameContext.Provider
      value={{ userNameState: userObj, userNameDispatch: dispatch }}
    >
      <BrowserRouter>
        <nav className="navbar navbar-light bg-dark">
          <div className="container-fluid">
            <ul className="nav justify-content-start">
              <li className="navbar-brand">
                <Link to="/" className="navbar-brand">
                  <span className="item">Chef's Restaurent</span>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/home">
                  <span className="item">Home</span>
                </Link>
              </li>
              {userObj.selectedRole==='CUSTOMER'||userObj.selectedRole===null ||userObj.selectedRole==='' ?
                <li className="nav-item">
                  <Link className="nav-link" to="/viewAllRestaurent">
                    <span className="item">View All Restaurents</span>
                  </Link>
                </li>
               :null}
              {userObj.selectedRole==='CUSTOMER'||userObj.selectedRole===null ||userObj.selectedRole==='' ?
                <li className="nav-item">
                  <Link className="nav-link" to="/trendingRestaurant">
                    <span className="item">Trending Restaurents</span>
                  </Link>
                </li>
              :null}
              {userObj.selectedRole==='VENDOR' ?
                <li className="nav-item">
                  <Link className="nav-link" to="/vendorRestaurants">
                    <span className="item">Registered Restaurents</span>
                  </Link>
                </li>
              :null}
              {userObj.selectedRole==='VENDOR' ?
                <li className="nav-item">
                  <Link className="nav-link" to="/addRestaurant">
                    <span className="item">Add Restaurents</span>
                  </Link>
                </li>
              :null}
              {userObj.selectedRole==='ADMIN' ?
                <li className="nav-item">
                  <Link className="nav-link" to="/adminRestaurants">
                    <span className="item">Restaurents</span>
                  </Link>
                </li>
              :null}
            </ul>
            <ul className="nav justify-content-end">
              {userObj.userName ? (
                <li className="nav-item">
                  <Link className="nav-link" to="/home">
                    <span className="item">Welcome {userObj.userName}!!</span>
                  </Link>
                </li>
              ) : null}
              {userObj.selectedRole==='CUSTOMER' ?
                <li className="nav-item">
                  <Link className="nav-link" to="/wallet">
                    <span className="item">Wallet</span>
                  </Link>
                </li>
              :null}
              {userObj.selectedRole==='CUSTOMER' ?
                <li className="nav-item">
                  <Link className="nav-link" to="/orders">
                    <span className="item">Orders</span>
                  </Link>
                </li>
              :null}
              {userObj.selectedRole==='CUSTOMER' ?
                <li className="nav-item">
                  <Link className="nav-link" to="/cart">
                    <span className="item">Cart</span>
                  </Link>
                </li>
              :null}
              {!userObj.userName ? (
                <li className="nav-item">
                  <Link className="nav-link" to="/login">
                    <span className="item">Login</span>
                  </Link>
                </li>
              ) : null}
              {userObj.userName ? (
                <li className="nav-item">
                  <Link className="nav-link" to="/logout">
                    <span className="item">Logout</span>
                  </Link>
                </li>
              ) : null}
            </ul>
          </div>
          {/* <Outlet/> */}
        </nav>
        <Routes>
          <Route index path="/" element={<Home />}/>
          <Route path="/home" element={<Home />}/>
          <Route path="/login" element={<Login />}/>
          <Route path="/register" element={<Register />}/>
          <Route path="/logout" element={<Logout />}></Route>
          <Route path="/viewAllRestaurent" element={<ViewAllRestaurents />}/>
          <Route path="/trendingRestaurant" element={<TrendingRestaurant />}/>
          <Route path="/vendorRestaurants" element={<VenderRestaurants />}/>
          <Route path="/addDish/:restaurantId" element={<AddDish />}/>
          <Route path="/addRestaurant" element={<AddRestaurant />}/>
          <Route path="/adminRestaurants" element={<AdminRestaurants />}/>
          <Route path="/restaurant/:restaurantName" element={<Restaurant />}/>
          <Route path="/wallet" element={<Wallet />}/>
          <Route path="/orders" element={<Orders />}/>
          <Route path="/cart" element={<Cart />}/>
        </Routes>
      </BrowserRouter>
    </UserNameContext.Provider>
  );
}

export default Nav;
