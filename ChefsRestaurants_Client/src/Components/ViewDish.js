import axios from 'axios';
import React, { Fragment, useEffect, useState } from 'react'
import { Button, Col, Modal, Row } from 'react-bootstrap';
import { deleteDishURL } from '../Resources/BackendURLs';
import AddDish from './AddDish';
import ModifyDish from './ModifyDish';

const dishObj = {
    dishId : 0,
    dishType : ''
}

function ViewDish({restaurantId, viewDishes,handleClosePopup,updateDishes}) {
    const [userRole, setUserRole] = useState(""); 
    const [dishes,setDishes] = useState([])
    const [modifyDishPopup, setModifyDishPopup] = useState(false);
    const [modifyDish, setModifyDish] = useState(dishObj);
    const [addDishPopup, setAddDishPopup] = useState(false)
    // console.log(dishes)
    useEffect(()=>{
        setUserRole(sessionStorage.getItem("selectedRole"))
        setDishes(viewDishes)
    },[viewDishes])

    const handleEditDishClose = ()=>{
        setModifyDishPopup(false)
        setAddDishPopup(false)
    }
    const updateDishList = (modifiedDishes)=>{
        updateDishes(modifiedDishes)
    }
    const editDishes = () =>{
        if(modifyDishPopup){
            return(
                <ModifyDish restaurantId={restaurantId} dish={modifyDish} handleClose={handleEditDishClose} setDish={updateDishList}/>
                )
        }
    }

    const handleDeleteDish = (dishId) =>{
        axios.delete(deleteDishURL+restaurantId+"/"+dishId)
        .then((response)=>{
            updateDishes(response.data)
            setDishes(response.data);
        })
        .catch((error)=>{
            console.log(error.response.data.errorMessage);
        })
    }

    const addDish = ()=>{
        if(addDishPopup){
            return (
                <AddDish restaurantId={restaurantId} updateDishes={updateDishList} handleClose={handleEditDishClose}/>
            )
        }
    }

  return (
        <Fragment>
            <Modal show={!(modifyDishPopup || addDishPopup)} onHide={()=>handleClosePopup()} centered size='lg'>
                <Modal.Header closeButton>
                <Modal.Title>Dishes</Modal.Title>
                </Modal.Header>
                {(dishes.length>0)?
                <Modal.Body>
                    {viewDishes.map((dish)=>
                    <div key={dish.dishId}>
                        <Row>
                            <Col xs={3}>Dish Id : </Col>
                            <Col xs={3}><b>{dish.dishId}</b></Col>
                        </Row>
                        <Row>
                            <Col xs={3}>Dish Name : </Col>
                            <Col xs={3}><b>{dish.dishName}</b></Col>
                            <Col xs={3}>Dish Type : </Col>
                            <Col xs={3}><b className={dish.dishType==='Veg'?"text-success":"text-danger"}>{dish.dishType}</b></Col>
                        </Row>
                        <Row>
                            <Col xs={3}>Cuisine : </Col>
                            <Col xs={3}><b>{dish.dishCuisine}</b></Col>
                            <Col xs={3}>Rating : </Col>
                            <Col xs={3}><b>{dish.avgRating}</b></Col>
                        </Row>
                        <Row>
                            <Col xs={3}>Speciality : </Col>
                            <Col xs={3}><b>{dish.speciality}</b></Col>
                            <Col xs={3}>Price : </Col>
                            <Col xs={3}><b>{dish.price}</b></Col>
                        </Row>
                        <Row>
                            <Col xs={3}>Description : </Col>
                            <Col xs={9}><b>{dish.dishDescription}</b></Col>
                        </Row>
                        <Row>
                            <Col xs={3}>Images Url : </Col>
                            <Col xs={9}><b>{dish.imageUrl}</b></Col>
                        </Row>
                        {(userRole==="VENDOR")?
                        <Row className='mt-3'>
                            <Col>
                                <Button variant='primary' onClick={()=>[setModifyDish(dish),setModifyDishPopup(true)]}>Edit Dish</Button>
                            </Col>
                            <Col>
                                <Button variant='danger' type='button' onClick={()=>handleDeleteDish(dish.dishId)}>Delete Dish</Button>
                            </Col>
                        </Row>
                        :undefined}
                        {(userRole==="CUSTOMER" || userRole===null)?
                        <Row className='mt-3'>
                            <Col>
                                <Button variant='none' >0</Button>
                                <Button variant='primary' onClick={()=>[setModifyDish(dish),setModifyDishPopup(true)]}>Add</Button>
                                <Button variant='none' >0</Button>
                            </Col>
                        </Row>
                        :undefined}
                        <hr className="solid"></hr>
                    </div>
                    )}
                </Modal.Body>
                :<Modal.Body>
                    <h3 className='text-center text-danger'>No dish to display!!</h3>
                </Modal.Body>}
                <Modal.Footer>
                    <Row>
                        {(userRole==='VENDOR')?
                        <Col xs={8}>
                            <Button variant="warning" onClick={()=>setAddDishPopup(true)}>Add Dish</Button>
                        </Col>
                        :undefined}
                        <Col xs={4}>
                            <Button variant="secondary" onClick={()=>handleClosePopup()}>Close</Button>
                        </Col>
                    </Row>
                </Modal.Footer> 
            </Modal>
            {editDishes()}
            {addDish()}
        </Fragment>
  )
}

export default ViewDish