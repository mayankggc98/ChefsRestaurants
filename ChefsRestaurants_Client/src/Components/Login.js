import axios from "axios";
import React, { useEffect, useState } from "react";
import { useContext } from "react";
import { Button, Modal } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { loginUserURL } from "../Resources/BackendURLs";
import { UserNameContext } from "./Nav";

const userData = {
  contactNumber: "",
  password: "",
};

const userObj = {
  userId : 0,
  userName : '',
  contactNumber:'',
  emailId:'',
  password:'',
  wallet : {},
  addressList :[],
  userLikesList:[],
  ordersList:[]
}

let roles = [];

function Login() {
  const userNameContext = useContext(UserNameContext);
  const [user, setUser] = useState(userData);
  const [loggedUser, setLoggedUser] = useState(userObj);
  const [enableLogin, setEnableLogin] = useState(true);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const [selectRolePopover, setSelectRolePopover] = useState(false);
  const [role, setRole] = useState("");
  
  let navigate = useNavigate();
  
  
  const handleLogin = (event) => {
    event.preventDefault();
    if (user.contactNumber === "" || user.password === "") {
      setError("Please Enter contact number and password!!");
      setMessage("");
    } else {
      axios
      .post(loginUserURL, user)
      .then((res) => {
          console.log(res.data);
          setLoggedUser(res.data);
          roles = [];
          res.data.roles.forEach((role) => roles.push(role.roleType));
          sessionStorage.setItem("userId",res.data.userId)
          sessionStorage.setItem("userName", res.data.userName);
          sessionStorage.setItem("roles", roles);
          
          setMessage(`Welcome ${res.data.userName}!! You have been logged in successfully.`);
          setError("");
          setSelectRolePopover(true);            
        })
        .catch((error) => {
          setMessage("");
          if(error.response!==undefined){
            setError(error.response.data.errorMessage);
          }
        });
      }
  };
  function onSuccess(position) {
    const {
        latitude,
        longitude
    } = position.coords;
    console.log(`Your location: (${latitude},${longitude})`);
}

function onError() {
}
  const handleRoleSelect = () => {
    let location = window.navigator.geolocation.getCurrentPosition(onSuccess,onError);
    console.log(location);
    if(role==="CUSTOMER"){
      sessionStorage.setItem("wallet",loggedUser.wallet.availableAmount)
    }
    if(role!==""){
      setSelectRolePopover(false)
      sessionStorage.setItem('selectedRole',role)
      userNameContext.userNameDispatch("Login");
      navigate("/home");
    }else{
      alert("Please Select a valid role to login.")
    }
  };

  const roleSelectPopover = () => {
    sessionStorage.clear()
    setSelectRolePopover(false);
    setMessage("");
    setError("");
    navigate('/login')
  }
  
  useEffect(() => {
    if (user.contactNumber !== "" && user.password !== "") {
      setEnableLogin(false);
    } else {
      setEnableLogin(true);
    }
  }, [user]);
  
  return (
    <div>
      <div
        className="card"
        style={{ width: "25vw", top: "10vh", left: "35vw" }}
        hidden={selectRolePopover}
      >
        <div className="card-header bg-warning">
          <b>Login</b>
        </div>
        <div className="card-body">
          <form onSubmit={(event) => handleLogin(event)}>
            <div className="mb-3 row">
              <label htmlFor="contactNo" className="col-sm-5 col-form-label">
                Enter Contact No:{" "}
              </label>
              <div className="col-sm-6 mt-1">
                <input type={"tel"} value={user.contactNumber} required={true} maxLength="10" id="contactNo" onChange={(event) =>setUser({ ...user, contactNumber: event.target.value})} autoFocus={true}/>
              </div>
            </div>
            <div className="mb-3 row">
              <label htmlFor="password" className="col-sm-5 col-form-label">
                Enter Password:{" "}
              </label>
              <div className="col-sm-6 mt-1">
                <input
                  type={"password"}
                  value={user.password}
                  required={true}
                  id="password"
                  onChange={(event) =>
                    setUser({ ...user, password: event.target.value })
                  }
                />
              </div>
            </div>
            <div className="mb-3 mt-4 row">
              <div className="col-sm-5">
                <button
                  type={"submit"}
                  disabled={enableLogin}
                  className="btn btn-primary"
                >
                  Login
                </button>
              </div>
            </div>
          </form>
        </div>
        <div className="card-footer">
          {message ? <h6 className="text-success">{message}</h6> : null}
          {error ? <h6 className="text-danger">{error}</h6> : null}
          {message ? null : (
            <span className="mb-3">
              <Link to="/register">New User? Click here to register!!</Link>
            </span>
          )}
        </div>
      </div>
      {/* <div hidden={!selectRolePopover}> */}
      <Modal show={selectRolePopover} onHide={roleSelectPopover} centered>
        <Modal.Header closeButton>
          <Modal.Title>Select Role</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <h6>
            Following roles are registered with your contact. Please select a role to login.
          </h6>
          <div className="row">
            <label htmlFor="selectRole" className="col-3">Role : </label>
            <select id="selectRole" className="col-4" onChange={(event)=>setRole(event.target.value)}>
                <option value=''>Select</option>
                {roles.map(role=><option key={role} value={role}>{role}</option>)}
            </select>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={roleSelectPopover}>
            Close
          </Button>
          <Button variant="primary" onClick={handleRoleSelect}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
    // </div>
  );
}

export default Login;
