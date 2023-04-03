// Create a new Vue instance
const root = Vue.createApp({

    // Data Properties
    data() {
        return {

            renter: [],

            renterID: 0,

            renterName: "",

            renterAge: 0,

            renterPhone: "",

            renterEmail: "",

            renterRating: 0,

            renterReview: ""
            
        }
    },

    created() {

        const params = new URLSearchParams(window.location.search);
        this.renterID = params.get('renterid');
        console.log(this.renterID); // Outputs "123"
        
        axios.get(`http://localhost:8000/renter/${this.renterID}`)
            .then(response => { 
                console.log(response.data.data)
                this.renter = response.data.data
                this.renterID = this.renter.renterID
                this.renterName = this.renter.fullName
                this.renterAge = this.renter.age
                this.renterPhone = this.renter.phone
                this.renterEmail = this.renter.email
                this.renterRating = this.renter.rating
                this.renterReview = `"` + this.renter.review + `"`
                console.log(this.renterID, this.renterName, this.renterAge, this.renterPhone, 
                this.renterEmail, this.renterRating, this.renterReview)
            })
            .catch(error => { 
                console.log(error.message) 
            })
        }

})
root.mount("#root")
