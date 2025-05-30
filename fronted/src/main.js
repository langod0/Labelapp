
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from "axios"
//需要挂载到Vue原型上
import scroll from 'vue-seamless-scroll'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// Vue.prototype.$echarts = echarts
const app = createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(scroll)
app.use(ElementPlus)
// axios.defaults.baseURL = "http://localhost:8001"
// axios.defaults.baseURL= 'http://127.0.0.1:7234/'
// app.config.globalProperties.$axios = axios
app.config.globalProperties.$http = axios

app.use(router);

app.mount('#app');