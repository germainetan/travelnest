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
        this.renterID = params.get('renterID');
        console.log(this.renterID); // Outputs "123"
        
        axios.get(`http://localhost:8000/renter/${this.renterID}`)
            .then(response => { 
                console.log(response.data.data)
                this.renter = response.data.data
                this.renterID = response.data.data.renterID
                this.renterName = response.data.data.fullName
                this.renterAge = response.data.data.age
                this.renterPhone = response.data.data.phone
                this.renterEmail = response.data.data.email
                this.renterRating = response.data.data.rating
                this.renterReview = response.data.data.review
                console.log(this.renterID, this.renterName, this.renterAge, this.renterPhone, 
                this.renterEmail, this.renterRating, this.renterReview)
            })
            .catch(error => { 
                console.log(error.message) 
            })
        },

    methods: {


    }

})
root.mount("#root")
