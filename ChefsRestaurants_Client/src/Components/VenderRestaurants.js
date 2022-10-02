import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { vendorsRestaurantsURL } from '../Resources/BackendURLs'

function VenderRestaurants() {
    let navigate = useNavigate();
    const [userId, setUserId] = useState("");
    const [restaurants , setRestaurants] = useState([]);

    useEffect(()=>{
        setUserId(sessionStorage.getItem("userId"));
        if(userId!==""){
            axios.get(vendorsRestaurantsURL+userId)
            .then((response)=>{
                console.log(response.data)
                setRestaurants(response.data);
            })
            .catch((error)=>{
                console.log(error.response.data.errorMessage);
            })
        }
    },[userId]);

    const handleViewRestaurant = (restaurant)=>{
        navigate(`/restaurant/${restaurant.restaurantName}`,{state : {restaurant : restaurant}});
      }

    if(restaurants.length===0){
        return(
            <div className='text-center text-info mt-4'>
                <h2 className='text-danger'>You have not registered any Restaurant!!</h2>
                <a className='text-info' style={{cursor:"pointer"}} href={`/addRestaurant`} onClick={()=>console.log("clicked")}>Click here to add a restaurant</a>
            </div>
            )
    }else{
        return (
            <div className='row m-auto'>
            {restaurants.map((restaurant) => (
                <div className="col-xl-3 col-lg-4 col-md-5 mb-4 mt-4" key={restaurant.restaurantId}>
                    <div className="card" style={{ width: "20rem" }}>
                        <img
                        src={require(`../${restaurant.photoUrls[0]}`)}
                        style={{ width: "20rem", height: "10rem" }}
                        className="card-img-top"
                        alt="..."
                        />
                        <div className="card-body">
                        <div className="row">
                            <h5 className="card-title col-8">
                                {restaurant.restaurantName}
                            </h5>
                            <b className={`col-4 ${restaurant.restaurantType === "Veg"? "text-success": "text-danger"}`}>
                                {restaurant.restaurantType}
                            </b>
                        </div>
                        <p className="card-text">
                            <b>Status: - </b>
                            <span className={restaurant.approvalStatus==="Accepted"?"text-success":"text-danger"}>
                            <b>{restaurant.approvalStatus}</b>
                            </span>
                        </p>
                        <p className="card-text">
                            <b>Address : - </b>
                            {`${restaurant.addressLine1}, ${restaurant.area}, ${restaurant.city}, ${restaurant.resState}`}
                        </p>
                        <div className='row'>
                            <div className='col-8'>
                                <button type="Click" className='btn btn-primary' onClick={()=>handleViewRestaurant(restaurant)}>View Details</button>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
                ))}
            </div>
        )
    }
}

export default VenderRestaurants