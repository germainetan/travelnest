
const root = Vue.createApp({

    // Data Properties
    data() {
        return {

            displaytext: "Processing your request... Please Wait...",
            displaybody: "",
            accept_params: ""
        }
    },

    created() {

        let params = (new URL(document.location)).searchParams
        let bookingid = params.get("bookingID")
        let bookingstatus = params.get("action")
        console.log(bookingid)

        if (params.has("ownerid")) {
            this.personid = params.get("ownerid")
            console.log("ownerid: ", this.personid)
            this.accept_params = `ownerid=${this.personid}`
        }
        else {
            this.personid = params.get("renterid")
            console.log("renterid: ", this.personid)
            this.accept_params = `renterid=${this.personid}`
        }



        axios.post(`http://localhost:8000/process_booking`, {

            "bookingid": bookingid,
            "booking_status": bookingstatus

            })
            .then(response => {

                this.displaytext = `Booking ${bookingstatus}!`
                axios.get(`http://localhost:8000/booking/${bookingid}`)
                .then(response => {

                    console.log(response.data.data)
                    axios.get(`http://localhost:8000/property/${response.data.data.propertyID}`)
                    .then(resp => {

                        console.log(resp.data.data)
                        
                        this.displaybody = `Booking ID: ${bookingid} <br> Booking Status: ${bookingstatus}`

                    })
                    .catch(error => {

                    })

                })
                .catch(error => {

                })

            })
            .catch(error => {
                this.displaytext = `There was a problem with your request. Please try again.`
            })
    }

})
root.mount("#root")