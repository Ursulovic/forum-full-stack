<template>
  <div>

    <h1>Most visited articles in last 30 days:</h1><br><br>

    <b-list-group v-for="article in articles" :key="article.id">

      <b-list-group-item href="#"  class="flex-column align-items-start">

        <div class="d-flex w-100 justify-content-between">
          <h5 class="">{{article.title}}</h5>
          <h6 class="mx-auto">Category : {{article.category}}</h6>
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
  name: "MostReaded",
  data() {
    return {
      articles: []
    }
  },
  created() {
    this.getArticles();
  },
  methods: {
    getArticles() {
      this.$axios.get('/article/fetch/mostReaded')
      .then(
          response => {
            this.articles = response.data;
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