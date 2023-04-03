// Create a new Vue instance
const root = Vue.createApp({

    // Data Properties
    data() {
        return {

            properties: [],

            facilities: [],
            
            message: "",

            startdate: "0",

            enddate: "0",

            starttime: "09:00:00",
             
            endtime: "09:00:00",

            numofdays: 0,

            price: 0,

            country: "",

            guests: 1,

            error: ""

        }
    },
    methods:{

        getResult(){

            this.error = ""

            axios.post(`http://localhost:8000/filter_property`, {

                "country" : this.country,
                "guests" : this.guests,
                "start_datetime" : this.startdate + " " + this.starttime,
                "end_datetime" : this.enddate + " " + this.endtime

            })
            .then(response => {

                console.log(response.data.data.property_result)

                // this.properties = response.data.data.property_result

                props = response.data.data.property_result
                availprops = []

                for(prop in props)
                {
                    console.log(props[prop].propertyID)
                    availprops.push(props[prop].propertyID)
                }

                if(availprops.includes(this.properties.propertyID))
                {
                    window.location.replace(`payment.html?title=${this.properties.title}&property=${this.properties.propertyID}&price=${this.price}&startdate=${this.startdate}&enddate=${this.enddate}&numofnights=${this.numofdays}`);
                }
                else
                {
                    this.error = "Booking is unavailable at the chosen date and time. Please choose another option."
                }
        
                
            })
            .catch(error => {
                console.log(error.message)
                // this.filtererror = "No available Properties that meets your requirements"
                this.error = "Booking is unavailable at the chosen date and time. Please choose another option."
            })

        }

    },
    computed: {
        result(){

            if(this.startdate === "0" || this.enddate === "0")
            {
                return this.numofdays
            }
            else
            {
                date1 = new Date(this.startdate)
                date2 = new Date(this.enddate)

                var Difference_In_Time = date2.getTime() - date1.getTime();

                var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);

                this.numofdays = Difference_In_Days

                return this.numofdays
            }
            
        },

        total(){

            this.price = this.properties.price*this.numofdays
            return this.price

        }

    },

    created() {

        let params = (new URL(document.location)).searchParams
        let property = params.get("property")
        this.country = params.get("country")

        console.log(property)

        axios.get(`http://localhost:8000/property/${property}`)
            .then(response => { 
                console.log(response.data.data)
                this.properties = response.data.data
                facil = response.data.facilities
                facil = facil.split(", ")
                this.facilities = facil
            })
            .catch(error => { 
                console.log(error.message) 
            })
        }

})
root.mount("#root")
