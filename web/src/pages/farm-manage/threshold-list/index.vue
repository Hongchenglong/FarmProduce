<template>
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="airTempMin" label="最低空气温度（单位：摄氏度）" width="150" />
    <el-table-column prop="airTempMax" label="最高空气温度（单位：摄氏度）" width="150" />
    <el-table-column prop="airHumMin" label="最低空气湿度：（单位：%）"  width="150"  />
    <el-table-column prop="airHumMax" label="最高空气湿度：（单位：%）"  width="150"  />
    <el-table-column prop="co2Min" label="最低二氧化碳浓度（单位：/10^-6)"  width="150"  />
    <el-table-column prop="co2Max" label="最高二氧化碳浓度（单位：/10^-6)"  width="150"  />
    <el-table-column prop="illuminanceMin" label="最低光照度（单位：lx)"  width="150"  />
    <el-table-column prop="illuminanceMax" label="最高光照度（单位：lx)"  width="150"  />
    <el-table-column prop="soilTempMin" label="最低土壤温度（单位：摄氏度）"  width="150"  />
    <el-table-column prop="soilTempMax" label="最高土壤温度（单位：摄氏度）"  width="150"  />
    <el-table-column prop="soilHumidMin" label="最低土壤湿度（单位：%）"  width="150"  />
    <el-table-column prop="soilHumidMax" label="最高土壤湿度（单位：%）"  width="150"  />
    <el-table-column prop="greenhouseId" label="大棚ID"  width="150"  />
    <!-- <el-table-column prop="updateTime" label="更新时间" :formatter="timeFormat" width="180" />
    <el-table-column prop="createTime" label="创建时间" :formatter="timeFormat" width="180" /> -->
    <el-table-column label="操作">
      <template #default="scope">
        <el-button @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="editFormVisible" title="环境编辑">
    <el-form :model="form">
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
        <el-input v-model="form.soilHumidMin" autocomplete="off" />
      </el-form-item>
      <el-form-item label="最高土壤湿度（单位：%）" :label-width="formLabelWidth">
        <el-input v-model="form.soilHumidMax" autocomplete="off" />
      </el-form-item>
      <el-form-item label="大棚ID" :label-width="formLabelWidth">
        <el-input v-model="form.greenhouseId" autocomplete="off" />
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
