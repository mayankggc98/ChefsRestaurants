import React, { useEffect, useState } from 'react'
import { Button, Card } from 'react-bootstrap'

function Wallet({wallet}) {
    const [amount,setAmount] = useState(0)

    useEffect(()=>{
        console.log(sessionStorage.getItem('orders'))
        setAmount(sessionStorage.getItem('wallet'))
    },[amount])
  return (
    <div  className="d-flex justify-content-center m-4">
        <Card style={{ width: '18rem'}}>
            <Card.Header>Wallet</Card.Header>
            <Card.Body>
                <Card.Text>Amount Available : {amount}</Card.Text>
            </Card.Body>
            <Card.Footer>
                <Button variant='primary'>Add Money</Button>
            </Card.Footer>
        </Card>
    </div>
  )
}

export default Wallet