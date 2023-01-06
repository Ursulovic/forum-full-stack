<template>
<div class="overflow-auto">
  <h1>Search articles</h1><br>
  <b-form-input v-model="searchValue" type="text" debounce="500" placeholder="Search Articles"></b-form-input><br>


  <b-pagination
    v-model="currentPage"
    :total-rows="totalRows"
    :per-page="10"
    aria-controls="my-table"
    align="center"

    ></b-pagination>

  <h2 v-if="emptyString">Enter search value</h2>

  <h2>{{searchResults.length}}</h2>
  <b-table striped hover id="my-table" :items="searchResults" :current-page="currentPage" :per-page="10"></b-table>



</div>
</template>

<script>
export default {
  name: "SearchArticle",
  data() {
    return {
      currentPage: 1,
      searchValue: '',
      emptyString: true,
      searchResults: [],
      totalRows: 0,
    }
  },
  watch: {
    searchValue : function(value) {
      if (value === '') {
        this.emptyString = true;
        this.searchResults = [];
        this.totalRows = this.searchResults.length;

      }
      else {
        this.emptyString = false;
        this.search(value);
        this.totalRows = this.searchResults.length;
      }
    }
  },
  methods: {
    search(value) {
      this.$axios.post('/article/search', {
        text: value
      }).then(
          response => {
            this.searchResults = response.data;
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