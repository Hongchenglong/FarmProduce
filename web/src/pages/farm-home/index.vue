<template>
  <div class="farm-home">
    <!-- 自定义全局轮播图组件 -->
    <!-- 绑定一个自定义属性:autoplay自动播放毫秒 ，showPointer小圆点的显示true与隐藏false -->
    <!-- :imgUrl="imgDate" -->
    <slide-box :autoplay="5000" :showPointer="true">
    </slide-box>

    <p class="farm-home-title">智慧农业系统</p>
    <div class="farm-home-search">
      <el-input class="farm-seatch-input" v-model="sourceSearchInput" placeholder="请输入溯源ID" />
      <el-button @click="sourceSearch">搜索</el-button>
      <!-- 高级检索 -->
      <!-- <el-button @click="advancedSearch" class="ad-search-style"><data-picker /></el-button> -->
    </div>
    <el-button class="farm-home-login" type="success" @click="farmLogin">登录</el-button>
  </div>
  <el-dialog>
    <el-table>
      <el-table-column />
      <el-table-column />
    </el-table>
  </el-dialog>

  <el-dialog v-model="dialogSearchResultShow" title="溯源详情" width="80%">
    <el-table class="sourceTabStyle" :data="dialogSearchResult">
      <el-table-column property="produceName" label="农产品名称" width="100" />
      <el-table-column property="id" label="农产品编号" width="100" />
      <el-table-column label="溯源二维码" width="100">
        <template #default="scope">
          <img width="50" height="50" :src="scope.row.qrcode" />
        </template>
      </el-table-column>
      <el-table-column property="enterpriseId" label="企业编号" width="100" />
      <el-table-column property="greenhouseId" label="大棚编号" width="100" />
      <el-table-column property="nurtureWorker" label="培育人员" width="100" />
      <el-table-column property="productWorker" label="生产人员" width="100" />
      <el-table-column property="qualityWorker" label="质检人员" width="100" />
      <el-table-column property="transportWorker" label="运输人员" width="100" />
      <el-table-column property="createTime" label="创建时间" width="200" />
      <el-table-column property="updateTime" label="更新时间" width="200" />
    </el-table>
  </el-dialog>
</template>

<script setup>
import router from '../../route/index';
import {ref} from 'vue';
import service from '@/axios';
import {ElMessage} from 'element-plus';
import dayjs from 'dayjs';
// import DataPicker from './components/dataPicker.vue';

/* // 局部注册组件  Internal server error:
import { defineComponent } from 'vue';
import sideBox from './components/sideBox.vue';
export default defineComponent({
  name:'slideBox',
  components:{
    slideBox
  },
  setup(){
    return {};
  }
}); */

const sourceSearchInput = ref('')
const dialogSearchResultShow = ref(false)
const dialogSearchResult = ref([])
// dialogSearchResult.value = [
//   {
//     date: '2022-04-25',
//     name: 'John Smith',
//     category: 'No.1518,  Jinshajiang Road, Putuo District',
//   },
//   {
//     id: "1517700905896550401", //农产品id
//     name:'橙子',
//     qrcode: "二维码",
//     enterpriseId: 1,
//     greenhouseId: 1, //大棚id
//     nurtureWorker: "王五",
//     productWorker: "张三",
//     qualityWorker: "李四",
//     transportWorker: "刘二",
//     produceId: 0,
//     updateTime: "2022-04-23 00:00:00", //更新时间
//     createTime: "2022-04-23 00:00:00", //创建时间
//   },
//    {
//     id: "1517700905896550401", //农产品id
//     name:'榴莲',
//     qrcode: "二维码",
//     enterpriseId: 1,
//     greenhouseId: 1, //大棚id
//     nurtureWorker: "王五",
//     productWorker: "张三",
//     qualityWorker: "李四",
//     transportWorker: "刘二",
//     produceId: 0,
//     updateTime: "2022-04-23 00:00:00", //更新时间
//     createTime: "2022-04-23 00:00:00", //创建时间
//   },
// ]
function sourceSearch() {
  if (!sourceSearchInput.value) {
    ElMessage({
      type: 'error',
      message: '请输入溯源ID'
    })
  } else {
    service.get('/trace/' + sourceSearchInput.value).then(res => {
      dialogSearchResult.value.push(res);   //返回对象，push进去 & vue3中 取变量值-》变量.value
      res.createTime = dayjs(res.createTime).format('YYYY-MM-DD HH:mm:ss');
      res.updateTime = dayjs(res.updateTime).format('YYYY-MM-DD HH:mm:ss')
      console.log(res.qrcode);
      //res.qrcode.innerHTML = '<img src = "'+ res.qrcode +'" />';
      //res.qrcode.innerHTML = '<a href="'+  res.qrcode + '"></a>';
      dialogSearchResultShow.value = true;
    }).catch(error => {
      ElMessage({
        type: 'error',
        message: '请输入正确的溯源ID'
      })
    })

    /* 查看哪里出问题 */
    // .catch(function (error) {
    //   if (error.response) {
    //     // 请求成功发出且服务器也响应了状态码，但状态代码超出了 2xx 的范围
    //     console.log("请求成功发出且服务器也响应了状态码，但状态代码超出了 2xx 的范围");
    //     console.log(error.response.data);
    //     console.log(error.response.status);
    //     console.log(error.response.headers);
    //   } else if (error.request) {
    //     // 请求已经成功发起，但没有收到响应
    //     // `error.request` 在浏览器中是 XMLHttpRequest 的实例，
    //     // 而在node.js中是 http.ClientRequest 的实例
    //     console.log("请求已经成功发起，但没有收到响应");
    //     console.log(error.request);
    //   } else {
    //     // 发送请求时出了点问题
    //     console.log("发送请求时出了点问题");
    //     console.log('Error', error.message);
    //   }
    //   console.log(error.config);
    // });


  }
  sourceSearchInput.value = '';
  dialogSearchResult.value = [];
}
// function advancedSearch(){
// }
function farmLogin() {
  router.push('/farm-login')
}
</script>


<style lang="less">
.farm-home {
  //background-image: url('https://www.keaidian.com/uploads/allimg/190424/24110307_6.jpg');
  //background-size: cover;
  height: 100vh; //100
  //padding-top: 200px; //200
  display: flex;
  flex-direction: column;
  align-items: center;
  box-sizing: border-box;

  .farm-home-title {
    color: rgb(255, 255, 255);
    font-size: 50px;
    text-align: center;
    padding-top: 200px;
    //top:30vh;
    z-index: 100;
    position: absolute;
  }

  .farm-home-search {
    width: 390px;
    margin-top: 40px;
    display: flex;
    justify-content: space-between;
    padding-top: 254px;
    //top:43vh;
    position: absolute;
    z-index: 100;

    .farm-seatch-input {
      width: 300px;
      margin-right: auto;
      border: 1px solid rgb(82, 217, 143);
      border-radius: 5px;
    }

    .ad-search-style {
      width: 88px;
    }
  }

  .farm-home-login {
    position: absolute;
    top: 30px;
    right: 80px;
    z-index: 100;
  }
}

.sourceTabStyle {
  text-align: center;
}
</style>
