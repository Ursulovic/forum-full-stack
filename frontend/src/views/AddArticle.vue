<template>

  <form @submit.prevent="add()">
    <h1>Add new article</h1>
    <div class="form-group">

      <label>Enter article's title:</label>
      <input v-model="title" type="text" class="form-control" id="inputTitle" aria-describedby="titleHelp" placeholder="Enter title" required><br>
      <label>Article's category:</label><br>
      <b-form-select v-model="category" :options="categories" required></b-form-select><br><br>
      <label>Enter article's text:</label>
      <input v-model="text" type="text" class="form-control" id="inputText" aria-describedby="textHelp" placeholder="Enter text" required><br>
      <label>Article's tags:</label><br><br>
      <v-multiselect-listbox :options="tags" v-model="chosenTags" aria-required="Required"></v-multiselect-listbox><br>

      <b-form-input v-model="newTag" type="text" aria-describedby="New tag" placeholder="Enter name ofa new tag"></b-form-input>
      <b-button size="sm" @click="addTag()" variant="primary">Add new tag</b-button><br><br>


      <b-button type="submit" variant="primary">Submit</b-button>

      <h2>{{this.chosenTags.toString().toUpperCase()}}</h2>


    </div>

  </form>

</template>

<script>
export default {
  name: "AddArticle",
  data() {
    return {

      title: '',
      category: '',
      text : '',
      chosenTags: [],
      formatedTags: '',
      author: '',
      newTag: '',

      categories: {},
      tags: []
    }
  },
  created() {
    this.fetchCategories();
    this.fetchTags();
    this.author = localStorage.getItem('name');
  },

  watch: {
    chosenTags : function () {
      console.log(this.chosenTags);
    }
  },
  methods: {

    addTag() {

      if (this.newTag === '')
        alert("Tag field cannot be empty");
      else {
        this.$axios.post('/tag/add', {
          id: 0,
          name: this.name
        }).then(
            response => {
              console.log('Dodat tag');
              console.log(response.status);
              this.fetchTags();
            },
            error => {
              console.log(error);
            }
        )
      }
    },

    add() {
      this.$axios.post('/article/add', {
        id: 0,
        category: this.category,
        title: this.title,
        text: this.text,
        date : Date.now(),
        visits : 0,
        author: this.author,
        name: this.chosenTags.toString().toUpperCase()
      }).then(
          response => {
            console.log(response);
          },
          error => {
            console.log(error);
          }
      )
    },

    fetchTags() {
      this.$axios.get('/tag/fetchTags')
      .then(
          response => {
            this.tags = response.data;
            console.log(response.data)
          },
          error => {
            console.log(error);
          }
      )
    },

    fetchCategories() {
      this.$axios.get('/category/fetchNames')
      .then(
          response => {
            this.categories = response.data;
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