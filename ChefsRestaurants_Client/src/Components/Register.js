import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import StateList from '../Resources/StateList';
import {registerUserURL} from '../Resources/BackendURLs';

const userData = {
    userName : '',
    contactNumber: '',
    emailId : '',
    password:'',
    roles : [],
    addressList : []
}
const addressData = {
    userAddressName : '',
    addressLine1:'',
    addressLine2:'',
    area:'',
    city:'',
    userState:'',
    pincode:''
}

const roleData = {
    roleType : ''
}
function Register() {
    const [user , setUser] = useState(userData);
    const [address , setAddress] = useState(addressData);
    const [role , setRole] = useState(roleData);
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const [userAddress, setUserAddress] = useState(false);
    const [addressForm, setAddressForm] = useState(false);
    const [enableRegister,setEnableRegister] = useState(true);

    const handleRegister = (event) => {
        event.preventDefault();
        user.addressList = [];
        user.addressList.push(address);
        user.roles = [];
        user.roles.push(role);
        console.log(user)
        axios.post(registerUserURL,user)
            .then((res)=>{
                console.log(res.data);
                setMessage(res.data);
                setError("");
            }).catch((err)=>{
                setMessage("");
                setError(err.response.data.errorMessage);
                console.log(err)
            })
    }
    
    useEffect(()=>{
        if(role.roleType==='CUSTOMER'){
            setUserAddress(true);
        }else{
            setUserAddress(false);
            setAddressForm(false);
            setAddress(addressData);
        }
        if(user.userName!=='' && user.contactNumber!=='' && user.emailId!=='' && role!=='' && user.password!==''){
            if(userAddress || addressForm){
                if(address.userAddressName!=='' && address.area!=='' && address.city!=='' && address.userState!=='' && address.pincode!==''){
                    setEnableRegister(false);
                }else{
                    setEnableRegister(true)
                }
            }else{
                setEnableRegister(false)
            }
        }else{
            setEnableRegister(true)
        }
    },[user,role,address,userAddress,addressForm])

  return (
    <div className="card" style={{width:"35vw",top:"10vh", left:"35vw"}}>
      <div className="card-header bg-info">
        <b>Register</b>
      </div>
      <div className="card-body">
        <form  onSubmit={(event)=>handleRegister(event)}>
            <div id='mainForm' hidden={addressForm}>
                <div className="mb-3 row">
                    <label htmlFor="name" className="col-4 col-form-label">Name: </label>
                    <div className="col-6 mt-1">
                        <input type={'text'} value={user.userName} id="name" placeholder='Username' onChange={event=>setUser({...user, userName : event.target.value})} autoFocus={addressForm}/>
                    </div>
                </div>
                <div className="mb-3 row">
                    <label htmlFor="email" className="col-4 col-form-label">Email Id: </label>
                    <div className="col-6 mt-1">
                        <input type={'text'} value={user.emailId} id="email" placeholder='Email Id' onChange={event=>setUser({...user, emailId : event.target.value})}/>
                    </div>
                </div>
                <div className="mb-3 row">
                    <label htmlFor="contactNo" className="col-4 col-form-label">Contact No: </label>
                    <div className="col-6 mt-1">
                        <input type={'tel'} value={user.contactNumber} maxLength='10' id="contactNo" placeholder='Contact Number' onChange={event=>setUser({...user, contactNumber : event.target.value})}/>
                    </div>
                </div>
                <div className="mb-3 row">
                    <label htmlFor="role" className="col-4 col-form-label">Select Role: </label>
                    <div className="col-6 mt-1">
                        <select value={role.roleType} id="role" onChange={event=>setRole({...role,roleType : event.target.value})}>
                            <option value=''>Select</option>
                            <option value='CUSTOMER'>CUSTOMER</option>
                            <option value='ADMIN'>ADMIN</option>
                            <option value='VENDOR'>VENDOR</option>
                        </select>
                    </div>
                </div>
                <div className="mb-3 row">
                    <label htmlFor="password" className="col-4 col-form-label">Password: </label>
                    <div className="col-6 mt-1">
                        <input type={'password'} value={user.password} id="password" placeholder='Password' onChange={event=>setUser({...user, password : event.target.value})}/>
                    </div>
                </div>
            </div>
            <div id='addressForm' hidden={!addressForm}>
                <div className="mb-3 row">
                    <label htmlFor="addressName" className="col-4 col-form-label">Address Name<span className='text-danger'>*</span>: </label>
                    <div className="col-7 mt-1"  id="addressName">
                        <input type={'radio'} name='addressName' onChange={()=>setAddress({...address, userAddressName:'Home'})}/>&nbsp;Home&nbsp;&nbsp;&nbsp;
                        <input type={'radio'} name='addressName' onChange={()=>setAddress({...address, userAddressName:'Work'})}/>&nbsp;Work&nbsp;&nbsp;&nbsp;
                        <input type={'radio'} name='addressName' onChange={()=>setAddress({...address, userAddressName:'Other'})}/>&nbsp;Other
                    </div>
                </div>
                <div className="mb-3 row">
                    <label htmlFor="addressLine1" className="col-4 col-form-label">Address line1: </label>
                    <div className="col-7 mt-1">
                        <input type={'text'} style={{width:"20vw"}} value={address.addressLine1} id="addressLine1" placeholder='Plot No./Street Name/Building Name' onChange={event=>setAddress({...address, addressLine1:event.target.value})}/>
                    </div>
                </div>
                <div className="mb-3 row">
                    <label htmlFor="addressLine2" className="col-4 col-form-label">Address line2: </label>
                    <div className="col-7 mt-1">
                        <input type={'text'} style={{width:"20vw"}} value={address.addressLine2} id="addressLine2" placeholder='Location/Landmark' onChange={event=>setAddress({...address, addressLine2:event.target.value})}/>
                    </div>
                </div>
                <div className="mb-3 row">
                    <div className='col-5 row'>
                        <label htmlFor="addressArea" className="col-5 col-form-label">Area<span className='text-danger'>*</span>: </label>
                        <div className="col-4 mt-1">
                            <input type={'text'}  style={{width:"10vw"}} value={address.area} id="addressArea" placeholder='Area' onChange={event=>setAddress({...address, area:event.target.value})}/>
                        </div>
                    </div>
                    <div className="col-5 offset-1 row">
                        <label htmlFor="addressCity" className="col-6 col-form-label">City<span className='text-danger'>*</span>: </label>
                        <div className="col-4 mt-1">
                            <input type={'text'}  style={{width:"10vw"}} value={address.city} id="addressCity" placeholder='City' onChange={event=>setAddress({...address, city:event.target.value})}/>
                        </div>
                    </div>
                </div>
                <div className="mb-3 row">
                    <div className="col-5 row">
                        <label htmlFor="addressState" className="col-5 col-form-label">State<span className='text-danger'>*</span>: </label>
                        <div className="col-3 mt-1">
                            <select style={{width:"10vw"}} value={address.userState} id="addressState" onChange={event=>setAddress({...address, userState:event.target.value})}>
                                <option value={''}>Select</option>
                                {StateList.map((state)=><option key={state} value={state}>{state}</option>)}
                            </select>
                        </div>
                    </div>
                    <div className="col-5 offset-1 row">
                        <label htmlFor="addressPin" className="col-6 col-form-label">Pin Code<span className='text-danger'>*</span>: </label>
                        <div className="col-4 mt-1">
                            <input type={'text'} maxLength='6' style={{width:"10vw"}} value={address.pincode} id="addressPin" placeholder='Pincode' onChange={event=>setAddress({...address, pincode:event.target.value})}/>
                        </div>
                    </div>
                </div>
            </div>
            <div className="mb-3 mt-4 row">
                <div className="col-5">
                    <button type={"submit"}  disabled={enableRegister} className='btn btn-primary'>Register</button>
                </div>
                <div className="col-5" hidden={!userAddress}>
                    <button type={'button'} hidden={addressForm} onClick={()=>setAddressForm(true)} className='btn btn-primary'>Next</button>
                    <button type={'button'} hidden={!addressForm} onClick={()=>setAddressForm(false)} className='btn btn-primary'>Back</button>
                </div>
            
            </div>
        </form>
      </div>
        <div className="card-footer">
            {error?<h6 className="text-danger">{error}</h6>:null}
            {message?<h6 className="text-success">{message}</h6>:null}
            {message?<span className="mb-3"><Link to='/login'>Click here to Login</Link></span>:null}
        </div>
    </div>
  )
}

export default Register