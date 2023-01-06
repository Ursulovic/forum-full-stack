<template>
  <form @submit.prevent="add">
    <h1>Add new category</h1>

    <div class="form-group">
      <label>Enter category's name: </label>
      <input v-model="name" type="text" class="form-control" id= "inputName" aria-describedby="nameHelp" placeholder="Enter name" required>
      <label>Enter category's description: </label>
      <input v-model="description" type="text" class="form-control" id= "inputDescription" aria-describedby="descriptionHelp" placeholder="Enter description" required>

      <button type="submit" class="btn btn-primary mt-2">Add</button>

    </div>



  </form>

</template>

<script>
// import CategoryPanel from "@/views/CategoryPanel";

export default {
  name: "AddCategory",
  data() {
    return {
      name: '',
      description: ''
    }
  },
  methods: {
    add() {
      this.$axios.post('/category/add', {
        name: this.name,
        description: this.description
      }).then(
          response => {
            console.log(response.status);
            console.log('uspesno dodata kategorija');
            this.$router.push({name: 'category'});
          },
          error => {
            console.log(error);
            alert("Category with given name already exists!");
          }
      )
    }
  }
}
</script>

<style scoped>

</style>