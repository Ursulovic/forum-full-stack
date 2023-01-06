<template>
  <div>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">

        <a class="navbar-brand" href="#">Raf-MSC</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">

            <li class="nav-item">
              <router-link to="/category" tag="a" class="nav-link">Category panel</router-link>
            </li>

            <li class="nav-item">
              <router-link to="/article" tag="a" class="nav-link">Article panel</router-link>
            </li>

            <li class="nav-item">
              <router-link to="/article/search" tag="a" class="nav-link">Search Articles</router-link>
            </li>

            <li class="nav-item">
              <router-link v-if="role === 'ADMIN' " to="/user" tag="a" class="nav-link">User panel</router-link>
            </li>
          </ul>

          <ul class="navbar-nav re-auto mb2 mb-lg-0">

            <li class="nav-item">
              <button v-if="loggedIn" class="btn btn-outline-secondary" @click="logout()">Logout</button>
            </li>

          </ul>


        </div>
      </div>
    </nav>


  </div>
</template>

<script>
export default {
  name: "NavBar",
  data() {
    return {
      role: '',
      loggedIn: false,
    }
  },
  created() {
    this.role = localStorage.getItem('role');
    this.isLoggedIn();
  },
  methods: {
    logout() {
      localStorage.removeItem('jtw');
      localStorage.removeItem('type');
      localStorage.removeItem('role');
      this.loggedIn = false;
      this.$router.push('/about');
    },
    isLoggedIn() {
      this.loggedIn = localStorage.getItem('jwt');
    }
  },
}
</script>

<style scoped>

</style>