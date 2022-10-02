import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { adminGetRestaurantURL } from '../Resources/BackendURLs';

function AdminRestaurants() {
    let navigate = useNavigate();
    const [restaurants , setRestaurants] = useState([]);

    useEffect(() => {
        axios.get(adminGetRestaurantURL)
          .then((res) => {
            setRestaurants(res.data);
          })
          .catch((error) => {
            console.error(error);
          });
      }, []);

      const handleViewRestaurant = (restaurant)=>{
        navigate(`/restaurant/${restaurant.restaurantName}`,{state : {restaurant : restaurant}});
      }

  return (
    <div className="row">
      {restaurants.map((restaurant) => (
        <div className="col-3 mb-4 mt-4 p-auto" key={restaurant.restaurantId}>
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
                <b className={`col-4 ${restaurant.restaurantType === "Veg"  ? "text-success"  : "text-danger"}`}>
                  {restaurant.restaurantType}
                </b>
              </div>
              <p className="card-text">
                <b>Status: - </b><span className={restaurant.approvalStatus==="Accepted"?"text-success":"text-danger"}><b>{restaurant.approvalStatus}</b></span>
              </p>
              <p className="card-text">
                <b>Address : - </b>
                {`${restaurant.addressLine1}, ${restaurant.area}, ${restaurant.city}, ${restaurant.resState}`}
              </p>
            </div>
            <div className='card-footer'>
                <button className='btn btn-primary' onClick={()=>handleViewRestaurant(restaurant)}>View Details</button>
            </div>
          </div>
        </div>
      ))}
    </div>
  )
}

export default AdminRestaurants