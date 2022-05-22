<template>
  <div class="farm-screen ">
    <div class="text-20px leading-30px mb-10px">你好！{{name}}</div>
    <div style="height: calc(100% - 40px);">
      <el-row>
        <el-col :span="12">
          <div class="online-monitoring">
            <img object="contain" style="width: 100%;height: 300px;"
              :src="mockData[checkHouse].imgUrl" alt="" srcset="">
            <el-radio-group v-model="checkHouse" size="large">
              <el-radio-button v-for="(item,key) in mockData" :label="key" >{{'大棚'+(key+1)}}</el-radio-button>
            </el-radio-group>
          </div>
        </el-col>
        <el-col :span="12" class="!pl-20px">
          <p class="text-center text-[20px] p-t-30px">关键数据指标</p>
          <div class="wrapper flex-center-center px-50px flex-wrap">
            <div class="item w-[50%] flex-center-center mb-30px"
              v-for="item in mockData[checkHouse].liveData">
              <div class="">
                <span class="text-[14px] p">{{ item.name }}</span>
                <p style="color:#FFCC00;" class="mt-10px ">{{ item.value }}<span class="text-[#212121] ml-10px">{{
                    item.unit
                }}</span></p>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <div class="source-temperature h-[100%]">

          </div>
        </el-col>
        <el-col :span="12">
          <div class="source-humidity h-[100%]"></div>
        </el-col>
      </el-row>
    </div>

  </div>
</template>
<route lang="yaml">
meta:
  layout: manageLayout
</route>
<script setup>
import {reactive, ref, watch} from 'vue'
import useGraph from './useGraph';
import service from '@/axios'
import dayjs from 'dayjs';

const historyDate = new Array(8).fill('').map((value, index) => dayjs().subtract(index + 1, 'day').format('YYYY-MM-DD')).reverse()
const name = ref(JSON.parse(localStorage.getItem('userInfo'))?.name)
const checkHouse = ref(0)
const enumHouse = {
  '大棚1': 0,
  '大棚2': 1,
  '大棚3': 2,
}
let mockData = reactive([{
  liveData:[],
   history: {
      temperature: {
        title: {
          text: '历史温度'
        },
        xAxis: {
          type: 'category',
          data: historyDate
        },
        yAxis: {
          type: 'value',

        },
        series: [
          {
            data: [],
            type: 'line'
          }
        ]
      },
      humidity: {
        title: {
          text: '历史湿度'
        },
        xAxis: {
          type: 'category',
          data: historyDate
        },
        yAxis: {
          type: 'value',

        },
        series: [
          {
            data: [],
            type: 'line'
          }
        ]
      }
    },
    imgUrl:''
}])
service.get('/environment/all/7').then(res => {
  mockData.shift()
  mockData.push(...res.map(item => ({
    liveData: [{
      name: '湿度',
      value: '26',
      key: 'airHumidity',
      unit: '%'
    }, {
      name: '温度',
      value: '22',
      unit: '℃',
      key: 'airTemperature',
    }, {
      name: '室外湿度',
      value: '24',
      unit: '℃'
    },
    {
      name: '土壤温度',
      value: '21',
      unit: '℃',
      kye: 'soilTemperature'
    },
    {
      name: '光照度',
      value: '560',
      unit: 'lux',
      key: 'illuminance'
    },
    {
      name: '土壤水份',
      value: '30',
      unit: '%',
      key: 'soilHumidity'
    }].map(liveiItem => {
      liveiItem.value = item[liveiItem.key] || liveiItem.value
      return liveiItem
    }),
    history: {
      temperature: {
        title: {
          text: '历史温度'
        },
        xAxis: {
          type: 'category',
          data: historyDate
        },
        yAxis: {
          type: 'value',

        },
        series: [
          {
            data: item.history.airTemperature,
            type: 'line'
          }
        ]
      },
      humidity: {
        title: {
          text: '历史湿度'
        },
        xAxis: {
          type: 'category',
          data: historyDate
        },
        yAxis: {
          type: 'value',

        },
        series: [
          {
            data: item.history.airHumidity,
            type: 'line'
          }
        ]
      }
    },
    imgUrl:item.greenhouse.image
  })))
  
})

const { chart: chartTemperature } = useGraph('.source-temperature', mockData[checkHouse.value].history.temperature)
const { chart: chartHumidity } = useGraph('.source-humidity', mockData[checkHouse.value].history.humidity)


watch([checkHouse,mockData], (val, prevVal) => {
  chartTemperature.value.setOption(mockData[checkHouse.value].history.temperature)
  chartHumidity.value.setOption(mockData[checkHouse.value].history.humidity)

})
</script>

<style scoped lang="less">
.farm-screen {
  height: 100%;

  .el-row {
    height: 50%;

    .el-col {
      padding: 10px 0;
    }
  }

  .online-monitoring {
    position: relative;

    .el-radio-group {
      position: absolute;
      top: 0;
      right: 0;
    }
  }

}
</style>
