<template>

  <div class="pt-5">

    <h1 v-if="email">Hello, {{email}}</h1>
    <form @submit.prevent="login">

      <div class="form-group">
        <label for="email">Email</label>
        <input required v-model="email" type="text" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
<!--        ??-->
<!--        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
        <small id="emailHelp" class="form-text text-muted"> We'll never share your email with anyone else.</small>
      </div>


      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input v-model="password" required type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
      </div>

      <button type="submit" class="btn btn-primary mt-2">Submit</button>

    </form>

    <h1 style="color: red">{{invalidCredentials}}</h1>

  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      email: '',
      password: '',
      invalidCredentials: ''
    }
  },
  methods: {
    login() {
      this.$axios.post('/user/login', {
        email : this.email,
        password : this.password
      }).then(response => {
        localStorage.setItem('jwt', response.data.jwt);
        console.log(response.data.jwt);
        console.log('ulogovan');
        const payload = JSON.parse(atob(response.data.jwt.split('.')[1]));
        localStorage.setItem('role', payload.type);
        localStorage.setItem('name', payload.name);
      }, error => {
        console.log("Bad credentials + " + error);
        alert("Bad credentials");
        this.invalidCredentials = "Wrong email/password";

      })
    }
  }
}
</script>

<style scoped>

</style>