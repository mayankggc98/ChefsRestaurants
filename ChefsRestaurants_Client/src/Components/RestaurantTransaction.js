import React from 'react'
import { Button, Col, Modal, Row } from 'react-bootstrap';

function RestaurantTransaction({transaction, handleClosePopup}) {
    console.log(transaction);
  return (
            <Modal show={true} onHide={()=>handleClosePopup()} centered size='lg'>
                <Modal.Header closeButton>
                <Modal.Title>Restaurant Transactions</Modal.Title>
                </Modal.Header>
                {/* {(transaction.transactionId!==undefined)? */}
                <Modal.Body>
                    <Row>
                        <Col xs={3}>Transaction Id : </Col>
                        <Col xs={3}><b>{transaction.restaurantTransactionId}</b></Col>
                        <Col xs={3}>Restaurant Status : </Col>
                        <Col xs={3}><b className={transaction.restaurantStatus?"text-success":"text-danger"}>{transaction.restaurantStatus?"Completed":"Not Completed"}</b></Col>
                    </Row>
                    <Row>
                        <Col xs={3}>Order Count : </Col>
                        <Col xs={3}><b>{transaction.restaurantOrderCounter}</b></Col>
                        <Col xs={3}>Approx Cost : </Col>
                        <Col xs={3}><b>{transaction.restaurantApproxCost}</b></Col>
                    </Row>
                </Modal.Body>
                {/* :<Modal.Body>
                    <h3 className='text-center text-danger'>No Transaction Available!!</h3>
                </Modal.Body>} */}
                <Modal.Footer>
                <Button variant="secondary" onClick={()=>handleClosePopup()}>
                    Close
                </Button>
                </Modal.Footer> 
            </Modal>
  )
}

export default RestaurantTransaction