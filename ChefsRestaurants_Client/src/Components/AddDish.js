import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Button, Modal } from 'react-bootstrap';
import { addDishURL } from '../Resources/BackendURLs'

const dish = {
    dishName : '',
    dishCuisine : '',
    dishDescription : '',
    dishType : '',
    imageUrl : '',
    price : '',
    speciality : '',
    avgRating : 0
}

function AddDish({restaurantId,updateDishes,handleClose}) {
    const [addDish, setAddDish] = useState(dish);
    const [saveModificatonButton, setSaveModificatonButton] = useState(false);

    useEffect(() => {
        if(addDish.dishName!=='' && addDish.dishCuisine!=='' && addDish.dishDescription!=='' 
            && addDish.dishType!=='' && addDish.imageUrl!=='' && addDish.price!=="0" && addDish.price!==''){
            setSaveModificatonButton(true)
        }else{
            setSaveModificatonButton(false)
        }
    },[addDish])

    const handleAddDish = () => {
        axios.post(addDishURL+restaurantId,addDish)
            .then((response)=>{
                updateDishes(response.data);
                handleClose();
            })
            .catch((error)=>{
                console.log(error.response.data.errorMessage);
            })
    }

  return (
    <Modal show={true} onHide={()=>handleClose()} size='lg' centered>
        <Modal.Header closeButton>
            <Modal.Title>Add Dish</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <form>
                <div className="mb-1 row">
                    <div className='col-5'>
                        <label htmlFor="dishName" className="col-6 col-form-label">Dish Name</label>
                        <input type={"text"} className="col-6 mt-1" value={addDish.dishName} required={true} id="dishName" name="dishName" onChange={(event) =>[setAddDish({ ...addDish, dishName: event.target.value})]} autoFocus={true}/>
                    </div>
                    <div className='col-5'>
                        <label htmlFor="dishCuisine" className="col-6 col-form-label">Dish Cuisine</label>
                        <input type={"text"} className="col-6 mt-1" value={addDish.dishCuisine} required={true} maxLength="10" id="dishCuisine" name="dishCuisine" onChange={(event) =>[setAddDish({ ...addDish, dishCuisine: event.target.value})]}/>
                    </div>
                </div>
                <div className="mb-1 row">
                    <label htmlFor="dishType" className="col-3 col-form-label">Type</label>
                    <div className="col-6 mt-1">
                        <input type={"radio"} value='Veg' required={true} name="dishType" onChange={(event) =>[setAddDish({ ...addDish, dishType: 'Veg'})]}/>Veg&nbsp;&nbsp;
                        <input type={"radio"} value='Nonveg' required={true} name="dishType" onChange={(event) =>[setAddDish({ ...addDish, dishType: 'Nonveg'})]}/>Nonveg
                    </div>
                </div>
                <div className="mb-1 row">
                    <div className='col-5'>
                        <label htmlFor="dishDescription" className="col-6 col-form-label">Description</label>
                        <input type={"text"} className="col-6 mt-1" value={addDish.dishDescription} required={true} id="dishDescription" name="dishDescription" onChange={(event) =>[setAddDish({ ...addDish, dishDescription: event.target.value})]}/>
                    </div>
                    <div className='col-5'>
                        <label htmlFor="price" className="col-6 col-form-label">Price</label>
                        <input type={"number"} className="col-6 mt-1" value={addDish.price} required={true} id="price" name="price" onChange={(event) =>[setAddDish({ ...addDish, price: event.target.value})]}/>
                    </div>
                </div>
                <div className="mb-1 row">
                    <div className='col-5'>
                        <label htmlFor="speciality" className="col-6 col-form-label">Speciality</label>
                        <input type={"text"} className="col-6 mt-1" value={addDish.speciality} required={true} id="speciality" name="speciality" onChange={(event) =>[setAddDish({ ...addDish, speciality: event.target.value})]}/>
                    </div>
                    <div className='col-5'>
                        <label htmlFor="imageUrl" className="col-6 col-form-label">Image</label>
                        <input type={"text"} className="col-6 mt-1" value={addDish.imageUrl} required={true} id="imageUrl" name="imageUrl" onChange={(event) =>[setAddDish({ ...addDish, imageUrl: event.target.value})]}/>
                    </div>
                </div>
            </form>
        </Modal.Body>
        <Modal.Footer>
            <Button variant="secondary" onClick={()=>handleClose()}>Close</Button>
            <Button variant="primary" disabled={!saveModificatonButton} onClick={()=>handleAddDish()}>Save Dish</Button>
        </Modal.Footer>
    </Modal>
  )
}

export default AddDish;