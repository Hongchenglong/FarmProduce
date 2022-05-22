<template>
  <div class="source-graph h-[100%]">

  </div>
</template>
<route lang="yaml">
meta:
  layout: manageLayout
</route>
<script setup>
import useGraph from '../useGraph';
import dayjs from 'dayjs'

const {chart}  = useGraph('.source-graph',{
    title: {
      text: '农场品溯源次数统计'
    },
    xAxis: {
      type: 'category',
      data: new Array(6).fill('').map((value,index) =>  dayjs().subtract(index+1,'day').format('YYYY-MM-DD')).reverse()
    },
    yAxis: {
      type: 'value',

    },
    series: [
      {
        data: [6, 5, 4,6,10],
        type: 'line'
      }
    ]
})
</script>

<style scoped lang="less">
</style>
