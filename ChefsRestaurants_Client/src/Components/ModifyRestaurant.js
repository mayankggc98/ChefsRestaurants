import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import { activateRestaurantURL, deactivateRestaurantURL, modifyRestaurantsURL } from '../Resources/BackendURLs';
import StateList from '../Resources/StateList';


function ModifyRestaurant({restaurant,handleClose,setRestaurantDetails}) {
    const [userId, setUserId] = useState("");
    const [modifyRestaurant, setModifyRestaurant] = useState(restaurant);
    const [saveModificatonButton, setSaveModificatonButton] = useState(true);
    useEffect(()=>{
        setUserId(sessionStorage.getItem("userId"))
    },[])

    const handleModifyRestaurantDetails = ()=>{
        axios.put(modifyRestaurantsURL+userId,modifyRestaurant)
            .then((res)=>{
                setRestaurantDetails(res.data);
                handleClose();
            })
            .catch((error)=>{
                console.log(error.response.data.errorMessage);
            })
    }

    const handleDeactivateRestaurant = ()=>{
        let confirm = window.confirm('Are you sure you wand to deactivate this restaurant?')
        if(confirm){
            axios.put(deactivateRestaurantURL+userId+"/"+modifyRestaurant.restaurantId)
            .then((response)=>{
                alert(`You have successfully deactivated restaurant ${modifyRestaurant.restaurantName}`);
                setRestaurantDetails(response.data);
                handleClose();
            })
            .catch((error)=>{
                console.log(error.response.data.errorMessage);
            })
        }
    }
    const handleActivateRestaurant = ()=>{
        let confirm = window.confirm('Are you sure you wand to Activate this restaurant?')
        if(confirm){
            axios.put(activateRestaurantURL+userId+"/"+modifyRestaurant.restaurantId)
            .then((response)=>{
                alert(`You request to activate restaurant ${modifyRestaurant.restaurantName} has been registered!!`);
                setRestaurantDetails(response.data);
                handleClose()
            })
            .catch((error)=>{
                console.log(error.response.data.errorMessage);
            })
        }
    }

    const validateModification = (event) => {
        if(event.target.value===''){
            console.log(event.target.value)
            setSaveModificatonButton(false);
        }else{
            setSaveModificatonButton(true);
        }
        
        if(event.target.name==='restaurantContact'){
            if(event.target.value.length<10){
                setSaveModificatonButton(false);
            }else{
                setSaveModificatonButton(true);

            }
        }
    }



  return (
            <Modal show={true} onHide={()=>handleClose()} size='lg' centered>
                <Modal.Header closeButton>
                    <Modal.Title>Modify Restaurant Details</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form>
                        <div className="mb-1 row">
                            <div className='col-5'>
                                <label htmlFor="restaurantName" className="col-6 col-form-label">Restaurant Name</label>
                                <input type={"text"} className="col-6 mt-1" value={modifyRestaurant.restaurantName} required={true} id="restaurantName" name="restaurantName" onChange={(event) =>[validateModification(event),setModifyRestaurant({ ...modifyRestaurant, restaurantName: event.target.value})]} autoFocus={true}/>
                            </div>
                            <div className='col-5'>
                                <label htmlFor="restaurantContact" className="col-6 col-form-label">Contact No:</label>
                                <input type={"tel"} className="col-6 mt-1" value={modifyRestaurant.restaurantContact} required={true} maxLength="10" id="restaurantContact" name="restaurantContact" onChange={(event) =>[validateModification(event),setModifyRestaurant({ ...modifyRestaurant, restaurantContact: event.target.value})]}/>
                            </div>
                        </div>
                        <div className="mb-1 row">
                            <label htmlFor="restaurantType" className="col-3 col-form-label">Type</label>
                            <div className="col-6 mt-1">
                                <input type={"radio"} value='Veg' required={true} name="restaurantType" onChange={(event) =>[validateModification(event),setModifyRestaurant({ ...modifyRestaurant, restaurantType: event.target.value})]} checked={modifyRestaurant.restaurantType==='Veg'}/>Veg&nbsp;&nbsp;
                                <input type={"radio"} value='Nonveg' required={true} name="restaurantType" onChange={(event) =>[validateModification(event),setModifyRestaurant({ ...modifyRestaurant, restaurantType: event.target.value})]} checked={modifyRestaurant.restaurantType==='Nonveg'}/>Nonveg
                            </div>
                        </div>
                        <div className="mb-1 row">
                            <div className='col-5'>
                                <label htmlFor="addressLine1" className="col-6 col-form-label">Address Line1</label>
                                <input type={"text"} className="col-6 mt-1" value={modifyRestaurant.addressLine1} required={true} id="addressLine1" name="addressLine1" onChange={(event) =>[validateModification(event),setModifyRestaurant({ ...modifyRestaurant, addressLine1: event.target.value})]}/>
                            </div>
                            <div className='col-5'>
                                <label htmlFor="area" className="col-6 col-form-label">Area</label>
                                <input type={"text"} className="col-6 mt-1" value={modifyRestaurant.area} required={true} id="area" name="area" onChange={(event) =>[validateModification(event),setModifyRestaurant({ ...modifyRestaurant, area: event.target.value})]}/>
                            </div>
                        </div>
                        <div className="mb-1 row">
                            <div className='col-5'>
                                <label htmlFor="city" className="col-6 col-form-label">City</label>
                                <input type={"text"} className="col-6 mt-1" value={modifyRestaurant.city} required={true} id="city" name="city" onChange={(event) =>[validateModification(event),setModifyRestaurant({ ...modifyRestaurant, city: event.target.value})]}/>
                            </div>
                            <div className='col-5'>
                            <label htmlFor="pincode" className="col-6 col-form-label">Pincode</label>
                                <input type={"text"} className="col-6 mt-1" value={modifyRestaurant.pincode} required={true} id="pincode" name="pincode" onChange={(event) =>[validateModification(event),setModifyRestaurant({ ...modifyRestaurant, pincode: event.target.value})]}/>
                            </div>
                        </div>
                        <div className="mb-1 row">
                            <label htmlFor="resState" className="col-3 col-form-label">State</label>
                            <div className='col-5'>
                                <select value={modifyRestaurant.resState} required={true} id="resState" name="resState" onChange={(event) =>[validateModification(event),setModifyRestaurant({ ...modifyRestaurant, resState: event.target.value})]}>
                                    <option value="">Select</option>
                                    {StateList.map((state)=> <option key={state} value={state}>{state}</option>)}
                                </select>
                            </div>
                        </div>
                    </form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={()=>handleClose()}>Close</Button>
                    <Button variant="primary" disabled={!saveModificatonButton} onClick={()=>handleModifyRestaurantDetails()}>Save Changes</Button>
                    {modifyRestaurant.approvalStatus!=="Deactivated"?
                        <Button variant="danger" onClick={()=>handleDeactivateRestaurant()}>Deactivate Restaurant</Button>
                        :<Button variant="success" onClick={()=>handleActivateRestaurant()}>Activate Restaurant</Button>
                    }
                </Modal.Footer>
            </Modal>
  )
}

export default ModifyRestaurant