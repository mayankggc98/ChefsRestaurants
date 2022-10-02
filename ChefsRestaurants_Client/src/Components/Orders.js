import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Button, Card, Col, Modal, Row } from 'react-bootstrap';
import { getOrdersURL } from '../Resources/BackendURLs';

const orderObj = {
    orderId:0,
    orderBill:0,
    orderStatus:'',
    orderItemsList:[]
}

function Orders() {
    const[userId, setUserId] = useState(sessionStorage.getItem('userId'));
    const[orders, setOrders]=useState([]);
    const[viewOrder, setViewOrder] = useState(orderObj)
    const[showViewOrderPopup, setShowViewOrderPopup] = useState(false)
    console.log(orders);

    useEffect(()=>{
        console.log(userId)
        axios.get(getOrdersURL+userId)
        .then((res)=>{
            console.log(res.data)
            setOrders(res.data.ordersList)
        })
        .catch((error)=>{
            if(error.response!==undefined){
                console.log(error.response.data.errorMessage);
              }
        })
    },[userId])

    const handleViewOrder = (order)=>{
        setViewOrder(order);
        setShowViewOrderPopup(true)
    }


  return (
    <div  className="d-flex justify-content-center align-self-center m-4">
      <Card style={{ width: '40rem' }}>
        <Card.Header><Card.Title>Order History</Card.Title></Card.Header>
        <Card.Body>
        {orders.map((order)=>{
        return (
            <div>
                <Row>
                    <Col xs={3}>Order Id : </Col>
                    <Col xs={3}><b>{order.orderId }</b></Col>
                    <Col xs={3}>Order Status : </Col>
                    <Col xs={3}><b className={order.orderStatus==='ACTIVE'?"text-success":"text-danger"}>{order.orderStatus}</b></Col>
                </Row>
                <Row>
                    <Col xs={3}>Order Bill : </Col>
                    <Col xs={6}><b>{order.orderBill} INR</b></Col>
                </Row>
                <Row className='mt-3'>
                    <Col><Button variant='info' onClick={()=>handleViewOrder(order)}>View Order</Button></Col>
                </Row>
                <hr className="solid"></hr>
            </div>
        )})}
        </Card.Body>
      </Card>
      <Modal show={showViewOrderPopup} onHide={()=>setShowViewOrderPopup(false)} centered>
            <Modal.Header closeButton>Order Items</Modal.Header>
            <Modal.Body>
                <Row>
                    <Col xs={6}><b>Dish</b></Col>
                    <Col xs={3}><b>Price</b></Col>
                    <Col xs={3}><b>Quantity</b></Col>
                </Row>
                {viewOrder.orderItemsList.map((orderItem)=>{
                    return(
                        <Row>
                            <Col xs={6}>{orderItem.dish.dishName}</Col>
                            <Col xs={3}>{orderItem.dish.price}</Col>
                            <Col xs={3}>X {orderItem.qty}</Col>
                        </Row>
                    )
                })}
                <hr className='solid'></hr>
                <Row>
                    <Col xs={9}>Total Bill</Col>
                    <Col>{viewOrder.orderBill}</Col>
                </Row>
            </Modal.Body>
            <Modal.Footer>
                <Row>
                    <Button variant='warning'>Reorder</Button>
                </Row>
            </Modal.Footer>
      </Modal>
    </div>
  )
}

export default Orders