import {createApp} from 'vue'
import 'virtual:windi.css'
import router from './route/index'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './style/reset.less'
import * as ElementPlusIconsVue from '@element-plus/icons-vue' //icon图标
import slideBox from './pages/farm-home/components/slideBox.vue'
import dataPicker from './pages/farm-home/components/dataPicker.vue'
//import slideBox1 from './pages/test/components/slideBox1.vue'


const app = createApp(App);
app.use(ElementPlus)
app.use(router)
app.mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
//组件注册
app.component('slide-box',slideBox)
app.component('ad-search',dataPicker)


// createApp(App).use(ElementPlus).use(router).component('slide-box',slideBox).component('slide-box1',slideBox1).component('slide-box2',slideBox2).mount('#app')
