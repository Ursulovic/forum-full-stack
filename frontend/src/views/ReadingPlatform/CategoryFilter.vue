<template>
  <div>
    <h1>Search articles by category:</h1><br>
    <b-form-select v-model="selectedCategory"  :options="categories"></b-form-select>

    <b-list-group v-for="article in articles" :key="article.id">

      <b-list-group-item href="#"  class="flex-column align-items-start">

        <div class="d-flex w-100 justify-content-between">
          <h5 class="">{{article.title}}</h5>
          <small>{{new Date(article.date).toString().substr(0,24)}}</small>
        </div>

        <p class="mb-1">{{article.text.substr(0,30)}}...</p>
        <small>Author: {{article.author}}.</small>

        <router-link :to="{name: 'ReadArticle', params: {id: article.id}}">
          See more
        </router-link>


      </b-list-group-item>


    </b-list-group>




  </div>
</template>

<script>
export default {
  name: "CategoryFilter",
  data() {
    return {
      selectedCategory: '',
      categories: [],

      articles: [],
    }
  },
  created() {
    this.fetchCategories();
  },

  watch: {
    selectedCategory: function () {
      this.filter();
    }
  },

  methods: {

    filter() {
      this.$axios.get('/article/categoryFilter/'+ this.selectedCategory)
      .then(
          response => {
            this.articles = response.data;
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