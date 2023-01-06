<template>
  <div class="overflow-auto">
    <h1>Articles</h1><br><br>

    <b-button size="sm" @click="$router.push('/article/add')" variant="primary">Add new article</b-button><br><br>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalArticles"
      :per-page="10"
      @change="handlePageChange"
      align="center"
      ></b-pagination>
<!--    @change="handlePageChange"-->


    <b-table striped hover :items="currentArticles" :current-page="0" per-page="10" :fields="fields">

      <template #cell(Title)="row">
        <b-form-input v-if="selectedRow[row.item.id]" type="text" v-model="newTitle" v-bind:placeholder="row.item.title"></b-form-input>
        <span v-else>{{row.item.title}}</span>
      </template>

      <template #cell(Category)="row">
        <b-form-select v-if="selectedRow[row.item.id]" v-model="newCategory" :options="allCategories" required></b-form-select>
<!--        <b-form-input v-if="selectedRow[row.item.id]" type="text" v-model="newCategory" v-bind:placeholder="row.item.category"></b-form-input>-->
        <span v-else>{{row.item.category}} </span>
      </template>

      <template #cell(Text)="row">
        <b-form-input required v-if="selectedRow[row.item.id]" type="text" v-model="newText" v-bind:placeholder="row.item.text"></b-form-input>
        <span v-else>{{row.item.text}} </span>
      </template>

      <template #cell(Author)="row">
        <b-form-input  required v-if="selectedRow[row.item.id]" type="text" v-model="newAuthor" v-bind:placeholder="row.item.author"></b-form-input>
        <span v-else>{{row.item.author}} </span>
      </template>

      <template #cell(Visits)="row">
        <b-form-input required v-if="selectedRow[row.item.id]" type="text" v-model="newVisits" v-bind:placeholder="row.item.visits.toString()"></b-form-input>
        <span v-else> {{row.item.visits}}</span>
      </template>

      <template #cell(Actions)="row">

        <b-button size="sm" @click="deleteArticle(row.item.id)" class="btn btn-danger">Delete</b-button>
        <b-button size="sm" @click="edit(row)" class="btn-warning">
          <span v-if="selectedRow[row.item.id]">Done</span>
          <span v-else>Edit</span>
        </b-button>
        <router-link :to="{name: 'ReadArticle', params: {id: row.item.id}}">
          See more
        </router-link>


      </template>



    </b-table>



  </div>

</template>

<script>
export default {
  name: "ArticlePanel",
  data() {
    return {
      fields : ['Title','Category', 'Text',  'Author','Visits', 'Actions'],

      totalArticles: 0,
      currentPage: 1,
      currentArticles : [],

      newText: '',
      newTitle: '',
      newCategory: '',
      newAuthor: '',
      newVisits: 0,

      selectedRow: {},

      searchKey: '',

      searchResults: false,

      allCategories: [],



    }
  },
  created() {
    this.fetchTotalCategories();
    this.loadPage(1);
    this.getCategoryNames();
    },

  watch: {
    searchKey: function (key) {
      if (key === '') {
        this.loadPage(this.currentPage);
        console.log("vraceno u norm")
        this.searchResults = false;
      }
      else {
        this.searchResults = true;
        this.$axios.post('/article/search', {
          text: key
        }).then(
            response => {
              this.currentArticles = response.data;
            },
            error => {
              console.log(error);
            }
        )
      }
    }

  },
  methods: {

    edit(row) {

      console.log(this.selectedRow[row.item.id]);
      this.selectedRow = {
        [row.item.id]: !this.selectedRow[row.item.id]
      }

      console.log(this.selectedRow[row.item.id]);

      if(!this.selectedRow[row.item.id] && !this.newTitle == '' && !this.newCategory == '' && !this.newAuthor == '', !this.newVisits == '') {
        if (this.newTitle !== row.item.title || this.newCategory !== row.item.description || this.newAuthor !== row.item.author || this.newVisits !== row.item.visits) {
          this.editArticle(row);
          this.newCategory = '';
          this.newTitle = '';
          this.newText = '';
          this.newVisits = 0;
          this.newAuthor = '';
        }
      }

    },


    editArticle(row) {
      this.$axios.post('/article/edit', {
        id: row.item.id,
        category: this.newCategory,
        title: this.newTitle,
        text: this.newText,
        visits: this.newVisits,
        author: this.newAuthor
      }).then(
          response => {
            console.log(response.status);

          },
          error => {console.log(error);}
      )
    },

    deleteArticle(id) {
      this.$axios.delete('/article/delete/' + id)
      .then(response => {
        console.log(response.status);
        this.loadPage(this.currentPage);
      })
    },

    getCategoryNames() {
      this.$axios.get('/category/fetchNames')
      .then(
          response => {
            console.log(response.status);
            this.allCategories = response.data;
          },
          error => {
            console.log(error);
          }
      )
    },

    fetchTotalCategories() {
      this.$axios.get('/article/total')
      .then(
          response => {
            console.log(response);
            this.totalArticles = response.data;
          },
          error => {
            console.log(error);
          }
      )
    },

    loadPage(value) {
      this.$axios.get('/article/fetch/' + value)
      .then(
          response => {
            console.log(response.status);
            this.currentArticles = response.data;
          },
          error => {
            console.log(error.status);
          }
      )
    },

    handlePageChange(value) {
      if (this.searchResults === false) {
        this.currentPage = value;
        this.loadPage(value);
      }
    }



  }
}
</script>

<style scoped>

</style>