<template>
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="name" label="大棚名称" width="150" />
    <el-table-column prop="image" label="大棚图像"  width="200"  />
    <el-table-column prop="enterpriseId" label="企业id"  width="150"  />
    <el-table-column prop="updateTime" label="更新时间" :formatter="timeFormat" width="180" />
    <el-table-column prop="createTime" label="创建时间" :formatter="timeFormat" width="180" />
    <el-table-column label="操作">
      <template #default="scope">
        <el-button @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="editFormVisible" title="大棚编辑">
    <el-form :model="form">
      <el-form-item label="大棚名称" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="大棚图像" :label-width="formLabelWidth">
        <el-input v-model="form.image" autocomplete="off" />
      </el-form-item>
      <el-form-item label="企业id" :label-width="formLabelWidth">
        <el-input v-model="form.enterpriseId" autocomplete="off" />
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
  name: '',
  image: '',
  enterpriseId: '',
  createTime: '',
  updateTime: ''
})


function fetchList() {
  service.get('greenhouse/search/1/10').then(res => {
    tableData.value = res.rows
  })
}
function handleEdit(index, row) {
  editFormVisible.value = true
  Object.keys(form).forEach(key => {
    form[key] = row[key]
  })
}
function handleDelete(index, row) {
  console.log(row);
  ElMessageBox.confirm(
    '确认删除？',
    {
      confirmButtonText: '删除',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      service.del('/greenhouse/' + row.id).then(res => {
        fetchList()
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
      })

    })
}
function handleEditConfirm() {
  service.put('/greenhouse/update', form).then(res => {
    ElMessage({
      type: 'success',
      message: '编辑成功',
    })
    editFormVisible.value = false
    fetchList()
  })
}
function timeFormat(row, column, cellValue, index){
  return  dayjs(cellValue).format('YYYY-MM-DD')
}
fetchList()
</script>

<style scoped lang="less">
</style>
