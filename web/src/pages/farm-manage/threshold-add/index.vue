<template>
  <el-form :model="form" class="w-500px">
    <el-form-item label="最低空气温度（单位：摄氏度）" :label-width="formLabelWidth">
      <el-input v-model="form.airTempMin" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最高空气温度（单位：摄氏度）" :label-width="formLabelWidth">
      <el-input v-model="form.airTempMax" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最低空气湿度：（单位：%）" :label-width="formLabelWidth">
      <el-input v-model="form.airHumMin" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最高空气湿度：（单位：%）" :label-width="formLabelWidth">
      <el-input v-model="form.airHumMax" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最低二氧化碳浓度（单位：/10^-6)" :label-width="formLabelWidth">
      <el-input v-model="form.co2Min" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最高二氧化碳浓度（单位：/10^-6)" :label-width="formLabelWidth">
      <el-input v-model="form.co2Max" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最低光照度（单位：lux)" :label-width="formLabelWidth">
      <el-input v-model="form.illuminanceMin" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最高光照度（单位：lux)" :label-width="formLabelWidth">
      <el-input v-model="form.illuminanceMax" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最低土壤温度（单位：摄氏度）" :label-width="formLabelWidth">
      <el-input v-model="form.soilTempMin" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最高土壤温度（单位：摄氏度）" :label-width="formLabelWidth">
      <el-input v-model="form.soilTempMax" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最低土壤湿度（单位：%）" :label-width="formLabelWidth">
      <el-input v-model="form.soilTempMin" autocomplete="off" />
    </el-form-item>
    <el-form-item label="最高土壤湿度（单位：%）" :label-width="formLabelWidth">
      <el-input v-model="form.soilTempMax" autocomplete="off" />
    </el-form-item>
    <el-form-item label="大棚ID" :label-width="formLabelWidth">
      <el-input v-model="form.greenhouseId" autocomplete="off" />
    </el-form-item>
     <el-form-item :label-width="formLabelWidth">
        <el-button @click="handleReset">重置</el-button>
        <el-button type="primary" @click="handleAdd">确定</el-button>
      </el-form-item>
  </el-form>
</template>
<route lang="yaml">
meta:
  layout: manageLayout
</route>
<script setup>
import {ElMessage} from 'element-plus'
import {reactive} from 'vue';
import service from '@/axios'

const formLabelWidth = '300px'
let form = reactive({
  date: '',
  greenhouseId: ''
})
function handleReset(){
  Object.keys(form).forEach(key => {
    form[key] = ''
  })
}

function handleAdd() {
  service.post('/environment/add', Object.assign(form,{
    updateTime:new Date().valueOf()
  })).then(res => {
    ElMessage({
      type: 'success',
      message: '添加成功',
    })
    fetchList()
  })
}
</script>
<style scoped lang="less">
</style>
