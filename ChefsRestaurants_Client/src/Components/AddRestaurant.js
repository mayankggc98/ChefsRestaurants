import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Button, Card, CloseButton } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom';
import { addRestaurantURL } from '../Resources/BackendURLs'
import stateList from '../Resources/StateList'

const restaurant = {
    restaurantId : '',
    restaurantName : '',
    restaurantType : '',
    restaurantContact : '',
    addressLine1 : '',
    area : '',
    city : '',
    resState : '',
    pincode : null,
    photoUrls : []
}

function AddRestaurant() {
    let navigate = useNavigate();
    const [userId, setUserId] = useState('');
    const [addRestaurant, setAddRestaurant] = useState(restaurant);
    const [saveAddButton, setSaveAddButton] = useState(true);

    useEffect(()=>{
        if(userId===''){
            setUserId(sessionStorage.getItem("userId"));
        }
        if(addRestaurant.restaurantName!=='' && addRestaurant.restaurantType!=='' && addRestaurant.restaurantContact!=='' 
            && addRestaurant.addressLine1!=='' && addRestaurant.area!=='' && addRestaurant.city!=="" && addRestaurant.resState!==''
            && addRestaurant.pincode!=="" && addRestaurant.photoUrls!==[]){
            setSaveAddButton(true)
        }else{
            setSaveAddButton(false)
        }
    },[addRestaurant,userId])

    const saveImage = (e) => {
        e.preventDefault();

        let reader = new FileReader();
        let file = e.target.files[0];
        if(file!==undefined){
            let photos = addRestaurant.photoUrls;
            photos.push(`assets/${file.name}`);
            setAddRestaurant({...addRestaurant, photoUrls : photos});
            reader.readAsDataURL(file);
            let a = document.createElement('a');
            a.href = window.URL.createObjectURL(new Blob([file]));
            a.download = file.name;
            // a.click()
        }
        
      }

    const handleAddRestaurantDetails = ()=>{
        // let photos = addRestaurant.photoUrls.map((url)=> {return "assets/"+url});
        // setAddRestaurant({...addRestaurant, photoUrls : photos})
        // console.log(addRestaurant,photos);
        axios.post(addRestaurantURL+userId,addRestaurant)
            .then((res)=>{
                alert(res.data);
                navigate('/vendorRestaurants')
            })
            .catch((error)=>{
                console.log(error.response.data.errorMessage);
            })
    }

    const removePhoto = (url)=>{
        let photos = [];
        addRestaurant.photoUrls.forEach(e1=>{
            if(url!==e1){
                photos.push(e1)
            }
        });
        setAddRestaurant({...addRestaurant, photoUrls:photos});
    }

  return (
    <div className="d-flex justify-content-center align-self-center m-4">

        <Card style={{ width: '40rem' }}>
            <Card.Header>
                <Card.Title>Add New Restaurant</Card.Title>
            </Card.Header>
            <Card.Body>
                <form>
                    <div className="mb-1 row">
                        <div className='col-6'>
                            <label htmlFor="restaurantName" className="col-6 col-form-label">Restaurant Name</label>
                            <input type={"text"} className="col-6 mt-1" value={addRestaurant.restaurantName} required={true} id="restaurantName" name="restaurantName" onChange={(event) =>setAddRestaurant({ ...addRestaurant, restaurantName: event.target.value})} autoFocus={true}/>
                        </div>
                        <div className='col-5'>
                            <label htmlFor="restaurantContact" className="col-6 col-form-label">Contact No:</label>
                            <input type={"tel"} className="col-6 mt-1" value={addRestaurant.restaurantContact} required={true} maxLength="10" id="restaurantContact" name="restaurantContact" onChange={(event) =>setAddRestaurant({ ...addRestaurant, restaurantContact: event.target.value})}/>
                        </div>
                    </div>
                    <div className="mb-1 row">
                        <label htmlFor="restaurantType" className="col-3 col-form-label">Type</label>
                        <div className="col-6 mt-1">
                            <input type={"radio"} value='Veg' required={true} name="restaurantType" onChange={(event) =>setAddRestaurant({ ...addRestaurant, restaurantType: event.target.value})} checked={addRestaurant.restaurantType==='Veg'}/>Veg&nbsp;&nbsp;
                            <input type={"radio"} value='Nonveg' required={true} name="restaurantType" onChange={(event) =>setAddRestaurant({ ...addRestaurant, restaurantType: event.target.value})} checked={addRestaurant.restaurantType==='Nonveg'}/>Nonveg
                        </div>
                    </div>
                    <div className="mb-1 row">
                        <div className='col-6'>
                            <label htmlFor="addressLine1" className="col-6 col-form-label">Address Line1</label>
                            <input type={"text"} className="col-6 mt-1" value={addRestaurant.addressLine1} required={true} id="addressLine1" name="addressLine1" onChange={(event) =>setAddRestaurant({ ...addRestaurant, addressLine1: event.target.value})}/>
                        </div>
                        <div className='col-5'>
                            <label htmlFor="area" className="col-6 col-form-label">Area</label>
                            <input type={"text"} className="col-6 mt-1" value={addRestaurant.area} required={true} id="area" name="area" onChange={(event) =>setAddRestaurant({ ...addRestaurant, area: event.target.value})}/>
                        </div>
                    </div>
                    <div className="mb-1 row">
                        <div className='col-6'>
                            <label htmlFor="city" className="col-6 col-form-label">City</label>
                            <input type={"text"} className="col-6 mt-1" value={addRestaurant.city} required={true} id="city" name="city" onChange={(event) =>setAddRestaurant({ ...addRestaurant, city: event.target.value})}/>
                        </div>
                        <div className="col-5">
                            <label htmlFor="pincode" className="col-6 col-form-label">Pincode</label>
                            <input type={"text"} className="col-6 mt-1" maxLength='6' required={true} id="pincode" name="pincode" onChange={(event) =>setAddRestaurant({ ...addRestaurant, pincode: event.target.value})}/>
                        </div>
                    </div>
                    <div className='mb-1 row'>
                        <label htmlFor="resState" className="col-3 col-form-label">State</label>
                        <div className='col-7 mt-1'>
                            <select value={addRestaurant.resState} required={true} id="resState" name="resState" onChange={(event) =>setAddRestaurant({ ...addRestaurant, resState: event.target.value})}>
                                <option value="">Select</option>
                                {stateList.map((state)=> <option key={state} value={state}>{state}</option>)}
                            </select>
                        </div>
                    </div>
                    <div className="mb-1 row">
                        <label htmlFor="photoUrls" className="col-3 col-form-label">Photos</label>
                        <div className='col-7 mt-1'>
                            <input type={"file"}  alt='Restaurant Photos' required={true} id="photoUrls" name="photoUrls" onChange={(event) =>saveImage(event)}/>
                        </div>
                    </div>
                    <div className='mb-1 row'>
                        <ol className='col-4 offset-3'>
                            {addRestaurant.photoUrls.map((url,index)=>{
                                return (
                                    <li key={index} value={url}>{url} <span><CloseButton type='button' onClick={()=>removePhoto(url)}/></span></li>
                                )
                            })}
                        </ol>
                    </div>
                </form>
            </Card.Body>
            <Card.Footer className='row '>
                <div className='col-4'>
                    <Button variant="secondary"  onClick={()=>setAddRestaurant(restaurant)}>Clear</Button>
                </div>
                <div className='col-4 offset-4'>
                    <Button variant="primary" disabled={!saveAddButton} onClick={()=>[handleAddRestaurantDetails()]}>Add Restaurant</Button>
                </div>
            </Card.Footer>
        </Card>
    </div>
  )
}

export default AddRestaurant