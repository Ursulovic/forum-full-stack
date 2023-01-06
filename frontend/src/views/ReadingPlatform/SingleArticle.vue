<template>
  <div>
    <h1>{{article.title}}</h1><br>
    <h6>Date : {{new Date(article.date)}}</h6>
    <h3>Category: {{article.category}}</h3><br><br>
    <h5>Tags: {{article.tags}}</h5><br><br>
    <h4>{{article.text}}</h4><br><br>
    <h6>Visits: {{article.visits}}</h6>

  </div>
</template>

<script>
export default {
  name: "SingleArticle",
  props: [],
  data() {
    return {
      article: null,
      id: -1
    }
  },
  created() {
    this.id= this.$route.params.id;
    console.log(this.$route.params.id);
    this.getArticle();
  },
  methods: {
    getArticle() {
      this.$axios.get('/article/fetch/single/'+ this.id)
      .then(
          response => {
            this.article = response.data;
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