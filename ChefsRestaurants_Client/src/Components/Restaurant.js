import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Button, Card, Col, Row } from 'react-bootstrap';
import { useLocation, useNavigate } from 'react-router-dom';
import { updateRestaurantApprovalStatusURL } from '../Resources/BackendURLs';
import ViewDish from './ViewDish';
import ModifyRestaurant from './ModifyRestaurant';
import RestaurantTransaction from './RestaurantTransaction';

const restaurantObj = {
  restaurantId : '',
  restaurantName : '',
  restaurantType : '',
  restaurantContact : '',
  approvalStatus :'',
  addressLine1 : '',
  avgRating : 0,
  area : '',
  city : '',
  resState : '',
  dishes : [],
  transaction : {
    restaurantTransactionId : '',
    restaurantStatus : false,
    restaurantOrderCounter : 0,
    restaurantApproxCost : 0
  },
  pincode : null
}

function Restaurant() {
  let location = useLocation();
  let navigate = useNavigate();
  const [userRole, setUserRole] = useState("");
  const [restaurant, setRestaurant] = useState(restaurantObj);
  const [viewDishes, setViewDishes] = useState([]);
  const [viewTransaction, setViewTransaction] = useState({});
  const [viewDishPopup, setViewDishPopup] = useState(false);
  const [viewTransactionPopup, setViewTransactionPopup] = useState(false);
  const [modifyRestaurantPopup, setModifyRestaurantPopup] = useState(false);

  useEffect(()=>{
    setUserRole(sessionStorage.getItem("selectedRole"))
    setRestaurant(location.state.restaurant)
    if(location.state.restaurant.dishes!==null){
      setViewDishes(location.state.restaurant.dishes)
    }
    if(location.state.restaurant.dishes!==null){
      setViewTransaction(location.state.restaurant.transaction)
    }
  },[location])

    const handleClose = ()=>{
        setViewDishPopup(false);
        setViewTransactionPopup(false)
        setModifyRestaurantPopup(false)
    }

    const updateDishes = (updatedDishes)=>{
      setRestaurant({...restaurant, dishes:updatedDishes});
      setViewDishes(updatedDishes)
    }
    
    const showDishes = ()=>{
      if(viewDishPopup){
        console.log(restaurant);
        return (<ViewDish restaurantId={restaurant.restaurantId} viewDishes={viewDishes} handleClosePopup={handleClose} updateDishes={updateDishes}/>)
      }
    }

    const showTransactions = ()=>{
      if(viewTransactionPopup){
        return (<RestaurantTransaction transaction={viewTransaction} handleClosePopup={handleClose} />)
      }
    }

    const modifyRestaurantModal = ()=>{
      if(modifyRestaurantPopup){
          return(
              <ModifyRestaurant restaurant={restaurant} handleClose={handleClose} setRestaurantDetails={updateRestaurantDetails}/>
          )
      }
    }

    const updateRestaurantDetails = (updatedDetails)=>{
      console.log(updatedDetails);
        setRestaurant(updatedDetails);
    }

    const handleChangeApprovalStatus = (event)=>{
      let status = event.target.value;
      let confirm = window.confirm(`Are you sure you want to ${event.target.name} restaurant ${restaurant.restaurantName}?`)
      if(confirm){
        axios.put(updateRestaurantApprovalStatusURL+restaurant.restaurantId,{...restaurant,approvalStatus:status})
        .then((res)=>{
          setRestaurant(res.data)
          navigate('/adminRestaurants')
        })
        .catch((error)=>{
          console.log(error.response.data.errorMessage)
        })
      }
    }

  return (
    <div  className="d-flex justify-content-center align-self-center m-4">
      <Card style={{ width: '40rem' }}>
        <Card.Header><Card.Title>{restaurant.restaurantName}</Card.Title></Card.Header>
        <Card.Body>
          {/* <Card.Text> */}
            <Row>
              <Col xs={3}>Restaurant Id : </Col>
              <Col xs={3}><b>{restaurant.restaurantId}</b></Col>
              <Col xs={3}>Restaurant Type : </Col>
              <Col xs={3}><b className={restaurant.restaurantType==='Veg'?"text-success":"text-danger"}>{restaurant.restaurantType}</b></Col>
            </Row>
            <Row>
              <Col xs={3}>Contact Number : </Col>
              <Col xs={3}><b>{restaurant.restaurantContact}</b></Col>
              <Col xs={3}>Rating : </Col>
              <Col xs={3}><b>{restaurant.avgRating}</b></Col>
            </Row>
            <Row>
              <Col xs={3}>Approval Status : </Col>
              <Col xs={6}><b className={restaurant.approvalStatus==='Accepted'?"text-success":"text-danger"}>{restaurant.approvalStatus}</b></Col>
            </Row>
            <Row>
              <Col xs={3}>Address Line : </Col>
              <Col xs={3}><b>{restaurant.addressLine1}</b></Col>
              <Col xs={3}>Area : </Col>
              <Col xs={3}><b>{restaurant.area}</b></Col>
            </Row>
            <Row>
              <Col xs={3}>City : </Col>
              <Col xs={3}><b>{restaurant.city}</b></Col>
              <Col xs={3}>State : </Col>
              <Col xs={3}><b>{restaurant.resState}</b></Col>
            </Row>
            <Row>
              <Col xs={3}>Pincode : </Col>
              <Col xs={3}><b>{restaurant.pincode}</b></Col>
            </Row>
        </Card.Body>
        <Card.Footer>
            <Row className='mb-2'>
              <Col xs={5}>
                  <Button variant="primary" type='button' onClick={()=>setViewDishPopup(true)}>View Dishes</Button>
              </Col>
              <Col xs={5}>
                  <Button variant="primary" type='button' onClick={()=>setViewTransactionPopup(true)}>View Transaction</Button>
              </Col>
            </Row>    
          {(userRole==="ADMIN")?
            <Row>
              <Col xs={5}>
                {(restaurant.approvalStatus==='Deactivated')?
                  <Button variant="success" type='button' value='Accepted' name='Activate' onClick={(event)=>handleChangeApprovalStatus(event)}>Activate</Button>
                :  <Button variant="danger" type='button' value='Deactivated' name='Deactivate' onClick={(event)=>handleChangeApprovalStatus(event)}>Deactivate</Button>
                }
              </Col>
              <Col xs={5}>
                {(restaurant.approvalStatus!=='Accepted' && restaurant.approvalStatus!=='Deactivated')?
                  <Button variant="success" type='button' value='Accepted' name='Approved' onClick={(event)=>handleChangeApprovalStatus(event)}>Approved</Button>
                  :  undefined
                }
              </Col>
            </Row>
            :<Row>
                <Col xs={5}>
                  <Button variant='secondary' type='button' onClick={()=>setModifyRestaurantPopup(true)}>Modify Details</Button>
                </Col>
            </Row>
          }
        </Card.Footer>
      </Card>
      {showDishes()}
      {showTransactions()}
      {modifyRestaurantModal()}
    </div>
  )
}

export default Restaurant