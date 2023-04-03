const check_booking_url = "http://localhost:8000/check_booking"

var app = Vue.createApp({

    data() {
      return {
        bookings: [],
        personid: "",
        bookingstatus: "pending",
        errormsg: "",
        personstatus: ""
      }
    },

    methods: {
      updateResult(selected_bookingstatus){

        this.errormsg = ""

        this.bookingstatus = selected_bookingstatus

        axios.post(check_booking_url, {

          "personID": this.personid,
          "booking_status": this.bookingstatus,
          "status": this.personstatus
  
        })
        .then(response => {
          console.log("bookings", response.data.data.check_book_results)
          this.bookings = response.data.data.check_book_results

        })
        .catch(error => {
          this.bookings = []
          this.errormsg = `There are no ${this.bookingstatus} bookings`
        })
      }
    },

    created() {
  
      let params = new URL(document.location).searchParams

      if (params.has("ownerid")) {
        this.personid = params.get("ownerid")
        console.log("ownerid: ", this.personid)
        this.personstatus = "owner"
      }
      else {
        this.personid = params.get("renterid")
        console.log("renterid: ", this.personid)
        this.personstatus = "renter"
      }

      axios.post(check_booking_url, {

        "personID": this.personid,
        "booking_status": this.bookingstatus,
        "status": this.personstatus

        })
        .then(response => {
          console.log("bookings", response.data.data.check_book_results)
          this.bookings = response.data.data.check_book_results

        })
        .catch(error => {
          this.bookings = []
          this.errormsg = `There are no ${this.bookingstatus} bookings`
        })
    }

})

app.mount('#root')