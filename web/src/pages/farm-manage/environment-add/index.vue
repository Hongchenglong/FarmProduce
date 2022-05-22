<template>
    <el-form :model="form" class="w-400px">
      <el-form-item label="日期" :label-width="formLabelWidth">
       <el-date-picker v-model="form.date" type="date" value-format="YYYY-MM-DD" placeholder="" />
      </el-form-item>
      <el-form-item label="大棚id" :label-width="formLabelWidth">
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

const formLabelWidth = '100px'
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
