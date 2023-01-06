<template>
<div class="overflow-auto">
  <h1>Users</h1><br><br>

  <b-button size="sm" @click="$router.push('/user/add')" variant="primary">Add new user</b-button><br><br>

  <b-pagination
    v-model="currentPage"
    :total-rows="totalUsers"
    :per-page="10"
    @change="handlePageChange"
    align="center"
    ></b-pagination>


  <b-table striped hover :items="currentUsers" :fields="fields">

    <template #cell(Name)="row">
      <b-form-input required v-if="selectedRow[row.item.email]" type="text" v-model="newName" v-bind:placeholder="row.item.name"></b-form-input>
      <span v-else>{{row.item.name}}</span>
    </template>

    <template #cell(Surname)="row">
      <b-form-input  required v-if="selectedRow[row.item.email]" type="text" v-model="newSurname" v-bind:placeholder="row.item.surname"></b-form-input>
      <span v-else>{{row.item.surname}}</span>
    </template>

    <template #cell(Email)="row">
      <b-form-input v-if="selectedRow[row.item.email]" type="text" v-model="newEmail" v-bind:placeholder="row.item.email" required></b-form-input>
      <span v-else>{{row.item.email}}</span>
    </template>

    <template #cell(Type)="row">
      <b-form-select v-if="selectedRow[row.item.email]" v-model="newType" :options="types" required></b-form-select>
      <span v-else>{{row.item.type}}</span>
    </template>

    <template #cell(Actions)="row">
      <b-button @click="edit(row)"  size="sm" class="btn-warning">
        <span v-if="!selectedRow[row.item.email]">Edit</span>
        <span v-else>Done</span>
      </b-button>
      <b-button disabled v-if="row.item.type === 'ADMIN'">Activated</b-button>
      <b-button v-else-if="row.item.status === true" class="btn-danger">Deactivate</b-button>
      <b-button v-else class="btn-primary">Activate</b-button>
    </template>

  </b-table>

</div>
</template>

<script>
export default {
  name: "AdminPanel",
  data() {
    return {
      fields: ['Name', 'Surname', 'Email', 'Type', 'Actions'],

      totalUsers: 0,
      currentPage: 1,
      currentUsers:[],

      newEmail: '',
      newName: '',
      newSurname: '',
      newType: '',
      newStatus: false,

      selectedRow: {},

      types: ['ADMIN', 'CONTENT_CREATOR'],

    }
  },
  created() {
    this.getNumber();
    this.loadPage(1);
  },
  methods: {

    changeStatus(email, newStatus) {
      this.$axios.post('/user/changeStatus', {
        email: email,
        status: newStatus
      }).then(
           response => {
             console.log(response.status);
          },
          error => {
             console.log(error.status);
             alert("Admin's status is unchangeable")
          }
      )
    } ,


    loadPage(page) {
      this.$axios.get('/user/fetch/'+ page)
      .then(
          response => {
            console.log(response.status);
            this.currentUsers = response.data;
          },
          error => {console.log(error);}
      )
    },

    getNumber() {
      this.$axios.get('/user/getNumber')
      .then(
          response => {
            this.totalUsers = response.data;
          },
          error => {
            console.log(error);
          }
      )
    },

    editUser(row) {
      this.$axios.post('/user/editUser',{
        oldEmail: row.item.email,
        newEmail: this.newEmail,
        name: this.newName,
        surname: this.newSurname,
        userType : this.newType
      }).then(
          response => {
            console.log(response);
            location.go();
          },
          error => {
            console.log(error);
          }
      )
    },

    handlePageChange(page) {
      console.log(page);
      this.currentPage = page;
      this.loadPage(page);
    },

    edit(row) {
      this.selectedRow = {
        [row.item.email]: !this.selectedRow[row.item.email]
      }

      if (!this.selectedRow[row.item.email] && !this.newName == '' && !this.newSurname == '') {
        console.log("Zatvoreno");
        if (this.newName !== row.item.name || this.newDescription !== row.item.description) {
          this.editUser(row);
        }
      }

      this.newName = '';
      this.newDescription = '';
    },


  }

}
</script>

<style scoped>

</style>