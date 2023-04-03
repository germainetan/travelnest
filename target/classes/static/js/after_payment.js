// Create a new Vue instance
const root = Vue.createApp({

    // Data Properties
    data() {
        return {

            booking: [],

            bookingstatus: "",

            bookingid: 0,
            
            propertyid: 0,

            ownerid: 0,

            renterid: 0,

            owneremail: "",

            ownerfullname: "",

            ownerPhone: "",

            startdate: "",

            enddate: "",

            propertytitle: "",

            numofnights: 0,

            price: 0
            
        }
    },

    created() {

        let params = (new URL(document.location)).searchParams
        this.bookingid = params.get("bookingid")
        this.propertytitle = params.get("propertytitle")
        this.numofnights = params.get("numofnights")
        this.price = params.get("price")

        axios.get(`http://localhost:8000/booking/${this.bookingid}`)
            .then(response => { 
                console.log("HERE:", response.data.data)
                this.booking = response.data.data
                console.log(this.booking)
                this.startdate = "Start Date: " + this.booking.start_datetime
                this.enddate = "End Date: " + this.booking.end_datetime
                this.bookingstatus = this.booking.booking_status
                this.renterid = this.booking.renterID

                axios.get(`http://localhost:8000/owner/${this.booking.ownerID}`)
                    .then(response => { 
                        console.log(response.data.data)
                        this.owneremail = `Email: ${response.data.data.email}`
                        this.ownerfullname = `Name: ${response.data.data.fullName}`
                        this.ownerPhone = `Phone: ${response.data.data.phone}`
                    })
                    .catch(error => { 
                        console.log(error.message) 
                    })

            })
            .catch(error => { 
                console.log(error.message) 
            })

    }

})
root.mount("#root")
