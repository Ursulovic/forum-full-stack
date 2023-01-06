<template>
<h1>Add new tag</h1>
</template>

<script>
export default {
  name: "AddTag",
  data() {
    return {
      name : '',
    }
  },
  methods: {
    add() {
      this.$axios.post('/tag/add', {
        id: 0,
        tags: this.name
      }).then(
          response => {
            console.log(response.status);
          },
          error => {
            console.log(error);
          }
      )
    }
  }
}
</script>

<style scoped>

</style>