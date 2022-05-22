import axios from "axios";
import router from '../route/index'
import {ElMessage} from 'element-plus'

const instance = axios.create({
  baseURL: '/api',
  timeout: 30000,
  
});
const noToeknList = ['trace']
// 添加响应拦截器
instance.interceptors.request.use(config => {
  if(!noToeknList.some(item => config.url.includes(item)) )
  config.headers = {
    Authorization:'Bearer ' + localStorage.getItem('token')
  }
  return config
})
instance.interceptors.response.use(function (response) {
  if(response.status === 200 && response.data.flag ){
    return response.data.data
  }
  if(response.data.code === 2022520){
    ElMessage.error('登录失效，请重新登录')
    setTimeout(() => {
    router.replace('/farm-login')
    }, 2000);
  }
  return Promise.reject(response);
}, function (error) {
  console.log(error);
  if(error.response.status === 401){
    ElMessage.error('登录失效，请重新登录')
    setTimeout(() => {
    router.replace('/farm-login')
    }, 2000);
  }
  return Promise.reject(error);
});
function get(url,params ={}){
   return instance.get(url,{
     params
   })
}
function del(url,params = {}){
  return instance.delete(url,{
    data:params
  })
}
function put(url,params = {}){
  return instance.put(url,params)
}
function post(url,params = {}){
  return instance.post(url,params)
}
export default  {
  get,
  del,
  put,
  post
}