
<template>
    <el-form :model="form" class="w-400px">
     <el-form-item label="名称" :label-width="formLabelWidth">
        <el-input v-model="form.produceName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="大棚id" :label-width="formLabelWidth">
        <el-input v-model="form.greenhouseId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="培育工人" :label-width="formLabelWidth">
        <el-input v-model="form.nurtureWorker" autocomplete="off" />
      </el-form-item>
      <el-form-item label="生产工人" :label-width="formLabelWidth">
        <el-input v-model="form.productWorker" autocomplete="off" />
      </el-form-item>
      <el-form-item label="运输工人" :label-width="formLabelWidth">
        <el-input v-model="form.qualityWorker" autocomplete="off" />
      </el-form-item>
      <el-form-item label="质检工人" :label-width="formLabelWidth">
        <el-input v-model="form.transportWorker" autocomplete="off" />
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

const formLabelWidth = '100px'
let form = reactive({
  id:'',
  produceName: '',
  greenhouseId: '',
  originalStage: '',
  intermediateStage: '',
  finishedStage: ''
})
function handleReset(){
  Object.keys(form).forEach(key => {
    form[key] = ''
  })
}

function handleAdd() {
  service.post('/trace/add', Object.assign(form,{
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
