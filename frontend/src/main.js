import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import vMultiselectListbox from 'vue-multiselect-listbox'
import 'vue-multiselect-listbox/dist/vue-multi-select-listbox.css';




import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
Vue.component('v-multiselect-listbox', vMultiselectListbox)


Vue.config.productionTip = false

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
