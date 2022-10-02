import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Card, Col, Row } from "react-bootstrap";
import { viewAllRestaurentURL } from "../Resources/BackendURLs";
import ViewDish from "./ViewDish";

function TrendingRestaurant() {
  const [restaurents, setRestaurent] = useState([]);
  const [viewRestaurant, setViewRestaurant] = useState({});
  const [viewDishPopup, setViewDishPopup] = useState(false)

  useEffect(() => {
    axios
      .get(viewAllRestaurentURL)
      .then((res) => {
        setRestaurent(res.data.filter(restaurant => restaurant.avgRating>=4.5));
      })
      .catch((error) => {
        console.error(error);
      });

    return
  }, []);

  const viewDish = ()=>{
    if(viewDishPopup){
      return (
        <ViewDish restaurantId={viewRestaurant.restaurantId} viewDishes={viewRestaurant.dishes} handleClosePopup={handleClose} />
      )
    }
  }
  const handleClose = ()=>{
    setViewDishPopup(false);
  }

  return (
    <div className="row">
      {restaurents.map((restaurent) => (
        <div className="col-3 mb-4 mt-4 p-auto" key={restaurent.restaurantId}>
          <Card className="card" style={{ width: "20rem" }}>
            <Card.Img
              src={require(`../${restaurent.photoUrls[0]}`)}
              style={{ width: "20rem", height: "10rem" }}
              className="card-img-top"
              alt="..."
            />
            <Card.Body className="card-body">
              <Row>
                <Col>
                    <Card.Title>{restaurent.restaurantName}</Card.Title>
                </Col>
                <Col>
                    <b className={`col-4 ${restaurent.restaurantType === "Veg"  ? "text-success"  : "text-danger"}`}>
                        {restaurent.restaurantType}
                    </b>
                </Col>
              </Row>
              <Row>
                <Col>
                  <Card.Text>   
                      <b>Status: - </b><span className="text-success"><b>{restaurent.approvalStatus}</b></span>
                  </Card.Text>
                </Col>
              </Row>
               <Row>
                  <Card.Text>
                    <b>Address : - </b>
                    {`${restaurent.addressLine1}, ${restaurent.area}, ${restaurent.city}, ${restaurent.resState}`}
                  </Card.Text>
              </Row>
            </Card.Body>
            <Card.Footer>
              <Button variant="primary" onClick={()=>[setViewRestaurant(restaurent),setViewDishPopup(true)]}>View Dishes</Button>
            </Card.Footer>
          </Card>
        </div>
      ))}
      {viewDish()}
    </div>
  );
}

export default TrendingRestaurant;
