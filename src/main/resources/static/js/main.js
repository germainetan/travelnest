// Create a new Vue instance
const root = Vue.createApp({

    // Data Properties
    data() {
        return {

            properties: [],

            propertiesreset: [],

            country: "",

            startdate: "",

            enddate: "",

            guests: 0,

            price: 0,

            filtererror: "",

            starttime: "09:00:00",

            endtime: "09:00:00",

            randnum: 0,

            imgsrc: ""
            
        }
    },

    created() {

        axios.get("http://localhost:8000/property")
            .then(response => { 
                console.log(response.data)
                this.properties = response.data.data
                this.propertiesreset = response.data.data // Store all values for reset during filtering
            })
            .catch(error => { 
                console.log(error.message) 
            })
        },

    methods: {
        updateData(){

            console.log(this.startdate)
            console.log(this.starttime)
            console.log(this.enddate)
            console.log(this.endtime)

            axios.post(`http://localhost:8000/filter_property`, {

                "country" : this.country,
                "guests" : this.guests,
                "price" : this.price,
                "start_datetime" : this.startdate + " " + this.starttime,
                "end_datetime" : this.enddate + " " + this.endtime

            })
            .then(response => {

                if(response.data.data.property_result !== "No available properties")
                {
                    console.log(response.data.data.property_result)
                    this.properties = response.data.data.property_result
                }
                else
                {
                    this.filtererror = "No Available Properties"
                }
                
            })
            .catch(error => {
                console.log(error.message)
                this.filtererror = "No available Properties"
            })
            
        },

        resetData(){
            this.properties = this.propertiesreset
            this.filtererror = ""
        },

        getRand(){
            this.randnum = Math.floor(Math.random() * 9) + 1

            this.imgsrc = "../images/property/P00" + this.randnum + "_1.jpg"
        }

    }

})
root.mount("#root")
