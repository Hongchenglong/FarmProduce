<template>
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="produceName" label="名称" width="100" />
    <el-table-column prop="greenhouseId" label="大棚id" width="100" />
    <el-table-column prop="nurtureWorker" label="培育工人" width="100" />
    <el-table-column prop="productWorker" label="生产工人" width="100" />
    <el-table-column prop="qualityWorker" label="质检工人" width="100" />
    <el-table-column prop="transportWorker" label="运输工人" width="130" />
    <el-table-column prop="createTime" label="创建时间" width="180" :formatter="timeFormat" />
    <el-table-column label="溯源二维码" width="100">
      <template #default="scope">
        <img width="50" height="50" :src="scope.row.qrcode" />
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="editFormVisible" title="农产品编辑">
    <el-form :model="form">
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
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click="handleEditConfirm">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<route lang="yaml">
meta:
  layout: manageLayout
</route>
<script setup>
import {ElMessage, ElMessageBox} from 'element-plus'
import {reactive, ref} from 'vue';
import service from '@/axios'
import dayjs from 'dayjs';

const tableData = ref([])
const editFormVisible = ref(false)
const formLabelWidth = '100px'
let form = reactive({
  id: '',
  produceName: '',
  greenhouseId: '',
  nurtureWorker: '',
  productWorker: '',
  qualityWorker: '',
  transportWorker: ''
})


function fetchList() {
  service.get('/trace/findAll').then(res => {
    tableData.value = res
  })
}
function handleEdit(index, row) {
  editFormVisible.value = true
  Object.keys(form).forEach(key => {
    form[key] = row[key]
  })
}
function handleDelete(index, row) {
  ElMessageBox.confirm(
    '确认删除？',
    {
      confirmButtonText: '删除',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      service.del('/trace/' + row.id).then(res => {
        fetchList()
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
      })

    })
}
function handleEditConfirm() {
  service.put('/trace/update', form).then(res => {
    ElMessage({
      type: 'success',
      message: '编辑成功',
    })
    editFormVisible.value = false
    fetchList()
  })
}
function timeFormat(row, column, cellValue, index) {
  return dayjs(cellValue).format('YYYY-MM-DD')
}
fetchList()
</script>

<style scoped lang="less">
</style>
