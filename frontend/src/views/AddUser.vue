<template>

  <form @submit.prevent="add">

    <h1> Add new user</h1><br><br><br>
    <div class="form-group">

      <label >Enter user's email:</label>
      <input v-model="email" type="email" class="form-control" id= "inputEmail" aria-describedby="emailHelp" placeholder="Enter email" required><br>
      <label>Enter user's name:</label>
      <input v-model="name" type="text" class="form-control" id="inputName" aria-describedby="nameHelp" placeholder="Enter name" required><br>
      <label>Enter user's surname:</label>
      <input v-model="surname" type="text" class="form-control" id="inputSurname" aria-describedby="nameHelp" placeholder="Enter surname" required><br>
      <b-form-select v-model="typeSelected" :options="typeOptions" required></b-form-select>
      <b-form-select v-model="statusSelected" :options="statusOptions" required></b-form-select><br>
      <label >Enter user's password:</label><br>
      <input type="password" class="form-control" id="inputPassword" aria-describedby="passwordhelp" placeholder="Eneter Password" required><br>

      <button type="submit" class="btn btn-primary mt-2">Submit</button>

    </div>

  </form>


</template>




<script>
export default {
  name: "AddUser",
  data() {
    return {
      email : '',
      name : '',
      surname : '',
      type : '',
      status: false,
      password : '',


      typeSelected : null,
      statusSelected: null,
      typeOptions : [
        {value: null, text: "Select user type"},
        {value : "ADMIN", text : "Admin"},
        {value: "CONTENT_CREATOR", text: "Content creator"}
      ],
      statusOptions: [
        {value: null, text: "Select user status"},
        {value: true, text: "Active"},
        {value: false, text: "Inactive"}

      ]
    }
  },
  methods: {
    add() {
      this.$axios.post('/users/addUser', {
        email : this.email,
        name: this.name,
        surname: this.surname,
        type: this.typeSelected,
        status: this.statusSelected,
        password: this.password
      }).then(response => {
        console.log(response.status)
      })
    }
  }
}
</script>

<style scoped>

</style>