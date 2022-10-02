import React, { useEffect } from "react";
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserNameContext } from "./Nav";

function Logout() {
  const navigate = useNavigate();
  const userNameContext = useContext(UserNameContext);

  useEffect(() => {
    userNameContext.userNameDispatch("Logout");
    setTimeout(() => {
      navigate('/home')
    }, 1000);
  },[]);

  return( 
        <div className="text-center text-danger mt-4">
          <h2>You are logged out successfully!!</h2>          
        </div>
  );
}

export default Logout;
