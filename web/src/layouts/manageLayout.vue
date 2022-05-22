<template>
  <div class="farm-manage ">
    <el-row class="tac h-100vh overflow-hidden ">
      <el-col :span="3">
        <router-link to="/farm-manage" v-slot="{ navigate }" custom>
          <h5 @click="navigate" class=" h-100px leading-100px text-center text-primary">智慧农场</h5>
        </router-link>
        <el-menu :default-active="activeIndex" :default-openeds="[activeOpeneds]" class="!border-0" router>
          <el-sub-menu index="product">
            <template #title>
              <el-icon>
                <location />
              </el-icon>
              <span>农产品管理</span>
            </template>
            <el-menu-item index="/farm-manage/product-list">农产品列表</el-menu-item>
            <el-menu-item index="/farm-manage/product-add">农产品新增</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="source">
            <template #title>
              <el-icon>
                <icon-menu />
              </el-icon>
              <span>农产品溯源</span>
            </template>
            <el-menu-item index="/farm-manage/source-list">溯源管理</el-menu-item>
            <el-menu-item index="/farm-manage/source-add">溯源新增</el-menu-item>
            <el-menu-item index="/farm-manage/source-graph">溯源趋势</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="environment">
            <template #title>
              <el-icon>
                <Sunny />
              </el-icon>
              <span>环境管理</span>
            </template>
            <el-menu-item index="/farm-manage/environment-list">环境列表</el-menu-item>
            <el-menu-item index="/farm-manage/environment-add">环境新增</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="greenhouse">
            <template #title>
              <el-icon>
                <HomeFilled />
              </el-icon>
              <span>大棚管理</span>
            </template>
            <el-menu-item index="/farm-manage/greenhouse-list">大棚列表</el-menu-item>
            <el-menu-item index="/farm-manage/greenhouse-add">大棚新增</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="threshold">
            <template #title>
            <el-icon><Odometer /></el-icon>
              <span>阈值告警</span>
            </template>
            <el-menu-item index="/farm-manage/threshold-list">阈值列表</el-menu-item>
            <el-menu-item index="/farm-manage/threshold-add">新增阈值</el-menu-item>
            <el-menu-item index="/farm-manage/threshold-alert">阈值监控</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-col>
      <el-col :span="21" class="">
        <div class="header pl-30px pr-50px h-65px flex-between-center">
          <img src="http://oss.lqjai.cn/traceap/img/u419.jpeg" width="32" alt="">
          <div class="right flex-center-center space-x-5">
            <el-badge is-dot class="item cursor-pointer" @click="handleAlert" v-if="alertList.length">
              <img src="http://oss.lqjai.cn/traceap/img/u1074.png" height="18" width="14" alt="">
            </el-badge>
            <span class="cursor-pointer">{{ name }}</span>
            <el-dropdown>
              <span class="flex-center-center cursor-pointer">
                <span class="mr-2">更多</span>
                <img src="http://oss.lqjai.cn/traceap/img/u425.png" class="cursor-pointer" height="18" width="12" alt="" srcset="">
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="logout">退出</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
        <div class="content p-20px">
          <router-view></router-view>
        </div>
      </el-col>
    </el-row>

    <el-drawer title="告警信息" v-model="alertShow">
      <el-card class="box-card mb-20px" v-for="(item, key) in alertList">
        <el-descriptions class="margin-top" :title="'大棚' + item.greenhouseId" :column="1" :size="size" border>
          <template #extra>
            <el-button class="button" @click="handleAlertComfirm(key)">我知道了</el-button>
          </template>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                告警时间：
              </div>
            </template>
            {{ dayjs(item.createTime).format('YYYY-MM-DD HH:mm:ss') }}
          </el-descriptions-item>
          <el-descriptions-item v-if="item.airHumidity !== 0">
            <template #label>
              <div class="cell-item">
                空气湿度
              </div>
            </template>
            {{ item.airHumidity }}
          </el-descriptions-item>
          <el-descriptions-item v-if="item.airTemperature !== 0">
            <template #label>
              <div class="cell-item">
                温度
              </div>
            </template>
            {{ item.airTemperature }}
          </el-descriptions-item>
          <el-descriptions-item v-if="item.co2 !== 0">
            <template #label>
              <div class="cell-item">
                二氧化碳
              </div>
            </template>
            {{ item.co2 }}
          </el-descriptions-item>
          <el-descriptions-item v-if="item.illuminance !== 0">
            <template #label>
              <div class="cell-item">
                光照度
              </div>
            </template>
            {{ item.illuminance }}
          </el-descriptions-item>
          <el-descriptions-item v-if="item.soilHumidity !== 0">
            <template #label>
              <div class="cell-item">
                土壤湿度
              </div>
            </template>
            {{ item.soilHumidity }}
          </el-descriptions-item>
          <el-descriptions-item v-if="item.soilTemperature !== 0">
            <template #label>
              <div class="cell-item">
                土壤温度
              </div>
            </template>
            {{ item.soilTemperature }}
          </el-descriptions-item>
        </el-descriptions>
      </el-card>
      <div class="flex justify-end py-10px" v-if="alertList.length">
         <el-button type="primary" size="large" class="mr-10px" @click="handleAlertComfirmAll">
          清除全部
        </el-button>
      </div>
     
    </el-drawer>

  </div>
</template>

<script setup>
import {Location, Menu as IconMenu} from '@element-plus/icons-vue'
import {useRoute} from 'vue-router';
import router from '../route/index'
import {ref} from 'vue'
import service from '@/axios'
import dayjs from 'dayjs'

const activeIndex = useRoute().path
const activeOpeneds = activeIndex.substring(activeIndex.lastIndexOf('/' + 1), activeIndex.lastIndexOf('-'))
const name = ref(JSON.parse(localStorage.getItem('userInfo'))?.name)
const alertShow = ref(false)
function logout() {
  router.push('/farm-login')
  localStorage.clear()
}
const alertList = ref([])
service.get('/alert/search/1/10').then(res => {
  alertList.value = res.rows
})
function handleAlert() {
  alertShow.value = true

}
function handleAlertComfirm(index) {
  service.put('/alert/' + alertList.value[index].id).then(res => {
    alertList.value.splice(index,1)
  })

}
function handleAlertComfirmAll(){
   service.post('/alert/batchUpdate',alertList.value.map(item =>  item.id)).then(res => {
    alertList.value = []
    alertShow.value = false
  })
}
</script>

<style  lang="less">
.is-active {
  background-color: #1da9ff;
  color: #fff !important;
}

.el-sub-menu__title {
  background-color: #fff;
}

.el-menu-item {
  min-width: 100% !important;
}

.content {
  background: rgb(242, 247, 251);
  height: calc(100% - 65px);
}
.el-drawer__header{
  margin-bottom:0px !important; 
}
.el-card__header {
  padding: 5px 10px;
}
</style>
