paypal
    .Buttons({

        style: {

        shape: 'rect',
        color: 'gold',
        layout: 'horizontal',
        label: 'pay',
        
        },

        // Sets up the transaction when a PayPal payment button is clicked
        createOrder: (data, actions) => {
        return fetch("http://localhost:8000/payment/api/orders", {
            
            method: "post",
            body: JSON.stringify({
            intent: "AUTHORIZE",
            purchase_units: [
                {
                    amount: {
                        currency_code: "SGD",
                        value: price
                    }
                }
            ]
            })
        })
            .then(response => response.json())
            .then(order => order.id);
        },
        onApprove: function(data) {
            // Authorize the transaction
            return fetch(`http://localhost:8000/place_booking`, {
                method: "post",
                body: JSON.stringify({
                    orderid: data.orderID,
                    renterid: 9,
                    propertyid: property,
                    start_datetime: startdate.concat(" ", "12:00:00"),
                    end_datetime: enddate.concat(" ", "12:00:00")
                }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            })
                .then(response => response.json())
            .then(
                function(json){
                    data = json.data
                    console.log(data)
                    console.log(data.bookingID)
                    // Redirect to afterpayment page with bookingid query
                    window.location.replace(`after_payment.html?bookingid=${data.bookingID}&propertytitle=${propertytitle}&numofnights=${numofnights}&price=${price}`)
                }
            )

        }
    })
    .render("#paypal-button-container");