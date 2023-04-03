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

            numofdays: 0,

            price: 0

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
        },

    methods: {

        say_hello() {
            console.log("say_hello()")

            return "Hello World!!! from say_hello()"
        }

    }

})
root.mount("#root")
