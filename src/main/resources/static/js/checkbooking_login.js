
const root = Vue.createApp({

    // Data Properties
    data() {
      return {

          personid: 1,
          password: "password",
          person_status: ""


      }
    },

    created() {
        let params = new URL(document.location).searchParams

        this.person_status = params.get("person_status")
             
    }

})
root.mount("#root")