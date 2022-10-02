import axios from 'axios';
import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import { modifyDishURL } from '../Resources/BackendURLs';

function ModifyDish({restaurantId,dish,handleClose,setDish}) {
    const [modifyDish, setModifyDish] = useState(dish);
    const [saveModificatonButton, setSaveModificatonButton] = useState(true);

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

    const handleModifyDishDetails = () =>{
        axios.put(modifyDishURL+restaurantId,modifyDish)
        .then((response)=>{
            setDish(response.data);
            handleClose();
        })
        .catch((error)=>{
            console.log(error.response.data.errorMessage);
        })
    }

  return (
        <Modal show={true} onHide={()=>handleClose()} size='lg' centered>
            <Modal.Header closeButton>
                <Modal.Title>Modify Dish Details</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <form>
                    <div className="mb-1 row">
                        <div className='col-5'>
                            <label htmlFor="dishName" className="col-6 col-form-label">Dish Name</label>
                            <input type={"text"} className="col-6 mt-1" value={modifyDish.dishName} required={true} id="dishName" name="dishName" onChange={(event) =>[validateModification(event),setModifyDish({ ...modifyDish, dishName: event.target.value})]} autoFocus={true}/>
                        </div>
                        <div className='col-5'>
                            <label htmlFor="dishCuisine" className="col-6 col-form-label">Dish Couisine</label>
                            <input type={"tel"} className="col-6 mt-1" value={modifyDish.dishCuisine} required={true} maxLength="10" id="dishCuisine" name="dishCuisine" onChange={(event) =>[validateModification(event),setModifyDish({ ...modifyDish, dishCuisine: event.target.value})]}/>
                        </div>
                    </div>
                    <div className="mb-1 row">
                        <label htmlFor="dishType" className="col-3 col-form-label">Type</label>
                        <div className="col-6 mt-1">
                            <input type={"radio"} value='Veg' required={true} name="dishType" onChange={(event) =>[validateModification(event),setModifyDish({ ...modifyDish, dishType: event.target.value})]} checked={modifyDish.dishType==='Veg'}/>Veg&nbsp;&nbsp;
                            <input type={"radio"} value='Nonveg' required={true} name="dishType" onChange={(event) =>[validateModification(event),setModifyDish({ ...modifyDish, dishType: event.target.value})]} checked={modifyDish.dishType==='Nonveg'}/>Nonveg
                        </div>
                    </div>
                    <div className="mb-1 row">
                        <div className='col-5'>
                            <label htmlFor="dishDescription" className="col-6 col-form-label">Description</label>
                            <input type={"text"} className="col-6 mt-1" value={modifyDish.dishDescription} required={true} id="dishDescription" name="dishDescription" onChange={(event) =>[validateModification(event),setModifyDish({ ...modifyDish, dishDescription: event.target.value})]}/>
                        </div>
                        <div className='col-5'>
                            <label htmlFor="price" className="col-6 col-form-label">Price</label>
                            <input type={"number"} className="col-6 mt-1" value={modifyDish.price} required={true} id="price" name="price" onChange={(event) =>[validateModification(event),setModifyDish({ ...modifyDish, price: event.target.value})]}/>
                        </div>
                    </div>
                    <div className="mb-1 row">
                        <div className='col-5'>
                            <label htmlFor="speciality" className="col-6 col-form-label">Speciality</label>
                            <input type={"text"} className="col-6 mt-1" value={modifyDish.speciality} required={true} id="speciality" name="speciality" onChange={(event) =>[validateModification(event),setModifyDish({ ...modifyDish, speciality: event.target.value})]}/>
                        </div>
                        <div className='col-5'>
                            <label htmlFor="imageUrl" className="col-6 col-form-label">Image</label>
                            <input type={"text"} className="col-6 mt-1" value={modifyDish.imageUrl} required={true} id="imageUrl" name="imageUrl" onChange={(event) =>[validateModification(event),setModifyDish({ ...modifyDish, imageUrl: event.target.value})]}/>
                        </div>
                    </div>
                </form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={()=>handleClose()}>Close</Button>
                <Button variant="primary" disabled={!saveModificatonButton} onClick={()=>[handleModifyDishDetails()]}>Save Changes</Button>
            </Modal.Footer>
        </Modal>
  )
}

export default ModifyDish