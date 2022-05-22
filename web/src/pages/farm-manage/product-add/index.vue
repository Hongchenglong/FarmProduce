
<template>
    <el-form :model="form" class="w-400px">
      <el-form-item label="名称" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="大棚id" :label-width="formLabelWidth">
        <el-input v-model="form.greenhouseId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="发芽时期" :label-width="formLabelWidth">
       <el-date-picker v-model="form.originalStage" type="date" value-format="x" placeholder="" />
      </el-form-item>
      <el-form-item label="生长时期" :label-width="formLabelWidth">
      <el-date-picker v-model="form.intermediateStage" type="date" value-format="x" placeholder="" />
      </el-form-item>
      <el-form-item label="结果时期" :label-width="formLabelWidth">
       <el-date-picker v-model="form.finishedStage" type="date" value-format="x" placeholder="" />
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
  name: '',
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
  service.post('/farmProduce/add', Object.assign(form,{
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
