<template>
  <el-button @click="exportReport()">导出报表</el-button>
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
  "id": 1,
  "airTempMax": "42.00",
  "airTempMin": "20.00",
  "airHumMax": "90.00",
  "airHumMin": "20.00",
  "co2Max": "1300.00",
  "co2Min": "300.00",
  "illuminanceMax": "900.00",
  "illuminanceMin": "200.00",
  "soilTempMax": "45.00",
  "soilTempMin": "20.00",
  "soilHumidMin": "20.00",
  "soilHumidMax": "90.00",
  "greenhouseId": 1,
  "createTime": 1652152958000,
  "updateTime": null
})


function fetchList() {
  service.get('/threshold/search/1/10').then(res => {
    tableData.value = res.rows
  })
}
function handleEdit(index, row) {
  editFormVisible.value = true
  Object.keys(form).forEach(key => {
    form[key] = row[key]
  })
}
function exportReport() {
  window.location.href = "/api/alert/exportReport"
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
      service.del('/threshold/' + row.id).then(res => {
        fetchList()
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
      })

    })
}
function handleEditConfirm() {
  service.put('/threshold/update', form).then(res => {
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
