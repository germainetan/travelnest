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

            guests: 1,

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

            this.filtererror = ""

            console.log(this.startdate)
            console.log(this.starttime)
            console.log(this.enddate)
            console.log(this.endtime)

            axios.post(`http://localhost:8000/filter_property`, {

                "country" : this.country,
                "guests" : this.guests,
                "start_datetime" : this.startdate + " " + this.starttime,
                "end_datetime" : this.enddate + " " + this.endtime

            })
            .then(response => {

                console.log(response.data.data.property_result)
                this.properties = response.data.data.property_result
                
            })
            .catch(error => {
                console.log(error.message)
                this.filtererror = "No available Properties that meets your requirements"
            })
            
        },

        resetData(){
            this.properties = this.propertiesreset
            this.filtererror = ""
            this.country = ""
            this.startdate = "dd/mm/yyyy"
            this.enddate = "dd/mm/yyyy"
            this.starttime = "09:00:00"
            this.endtime = "09:00:00"
            this.guests = 0
        },

        getRand(){
            this.randnum = Math.floor(Math.random() * 9) + 1

            this.imgsrc = "images/property/P00" + this.randnum + "_1.jpg"
        }

    }

})
root.mount("#root")
