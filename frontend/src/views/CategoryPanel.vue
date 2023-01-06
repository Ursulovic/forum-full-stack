<template>

  <div class="overflow-auto">
    <h1>Categories</h1><br>
    <b-button size="sm" @click="$router.push('/category/add')" variant="primary">Add new category</b-button><br><br>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalCategories"
      :per-page="10"
      @change="handlePageChange"
      align="center"
      ></b-pagination>




    <b-table striped hover :items = "currentCategories" :fields="fields">

      <template #cell(Name)="row">
        <b-form-input v-if="selectedRow[row.item.name]" type="text" v-model="newName"  v-bind:placeholder="row.item.name"></b-form-input>
        <span v-else>{{row.item.name}} </span>
      </template>


      <template #cell(Description)="row">

        <b-form-input required v-if="selectedRow[row.item.name]" type="text"  v-model="newDescription" v-bind:placeholder="row.item.description"></b-form-input>
        <span v-else>{{row.item.description}}</span>

<!--        <b-button v-if="selectedRow[row.item.name]" size="sm" @click="test(row)">Cancel</b-button>-->

      </template>

      <template #cell(Actions)="row">
        <b-button size="sm" @click="deleteCategory(row.item.name)" class="btn btn-danger">Delete</b-button>
        <b-button size="sm" @click="edit(row)"  class="btn-warning">

          <span v-if="!selectedRow[row.item.name]">Edit</span>
          <span v-else>Done</span>

        </b-button>
      </template>




    </b-table>



  </div>

</template>

<script>





export default {

  name: "CategoryPanel",

  data() {
    return {

      fields : ['Name', 'Description', 'Actions'],

      totalCategories: 0,
      currentPage: 1,
      currentCategories : [],

      selectedRow: {},

      newName: '',
      newDescription: '',



    }
  },
  created() {
    this.fetchTotalCategories();
    this.loadPage(1);
    },
  methods: {

    edit(row) {




      console.log(this.selectedRow[row.item.name]);
      this.selectedRow = {
        [row.item.name]: !this.selectedRow[row.item.name]
      }

      console.log(this.selectedRow[row.item.name]);

      if (!this.selectedRow[row.item.name] && !this.newName == '' && !this.newDescription == '') {
        console.log("Zatvoreno");
        if (this.newName !== row.item.name || this.newDescription !== row.item.description) {
          this.editCategory(row.item.name, this.newName, this.newDescription);
        }
      }

      this.newName = '';
      this.newDescription = '';

    },

    editCategory(oldName, newName, newDescription) {
      this.$axios.post('/category/edit', {
        oldName : oldName,
        newName : newName,
        newDescription : newDescription
      }).then(
          response => {
            console.log(response);

            },
          error => {
            alert("Name with that name already exists!");
            console.log(error);
          }
      )
    },

    fetchTotalCategories() {
      this.$axios.get('/category/getPage')
      .then(
          response => {
            this.totalCategories = response.data;
          },
          error => {
            console.log(error);
          })
    },

    loadPage(value) {
      this.$axios.get('/category/fetch/' + value)
      .then(
          response => {
            this.currentCategories = response.data
          },
          error => {
            alert("Not authorized");
            console.log(error)
          })
    },



    deleteCategory(name) {
      this.$axios.delete('/category/delete/' + name)
      .then(
          response => {
            console.log(response.status);
            console.log(name + ' uspesno obrisan');
            this.loadPage(this.currentPage);
          },
          error => {
            console.log(error);
            alert("This category is related to article");
          })
    },



    handlePageChange(value) {
      this.currentPage = value;
      this.loadPage(value);
    }



  }

}
</script>

<style scoped>

</style>