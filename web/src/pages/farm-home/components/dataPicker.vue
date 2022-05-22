<!-- 组件：高级检索-->
<template>
    <el-button type="plain" @click="dialogFormVisible = true">高级检索</el-button>
    
    <el-dialog class="ad-search" title="高级检索" v-model="dialogFormVisible" width="450px"> <!-- -->
        <el-form :model="form">
            <el-form-item class="idSearStyle" label="溯源ID" :label-width="formLabelWidth" float="left">
                <el-input class="inpdateStyle" v-model="form.id" autocomplete="off" placeholder="请输入要查找的溯源ID"></el-input>
                <!-- <p>{{form.id}}</p> -->
            </el-form-item>
            <el-form-item class="timeSearStyle" label="时间段" :label-width="formLabelWidth"> 
                <el-date-picker
                    class="dateStyle"
                    v-model="value2"
                    type="daterange"
                    align="right"
                    unlink-panels
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    :picker-options="pickerOptions">
                </el-date-picker>
                <!-- <p>输出{{value2}}</p> -->
            </el-form-item>
        </el-form>
        <div  class="dialog-footer"><!--v-slot="footer" -->
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="success" @click="adSearch">查询</el-button>
        </div>
    </el-dialog>
    
    <!-- <div class="block">
        <span class="demonstration">默认</span>
        <el-date-picker
        v-model="value1"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期">
        </el-date-picker>
    </div> -->
    
</template>


<script>

export default {
    name:'dataPicker',
    data() {
        return {
            // gridData: [{
            // date: '2016-05-02',
            // name: '王小虎',
            // address: '上海市普陀区金沙江路 1518 弄'
            // }, {
            // date: '2016-05-04',
            // name: '王小虎',
            // address: '上海市普陀区金沙江路 1518 弄'
            // }, {
            // date: '2016-05-01',
            // name: '王小虎',
            // address: '上海市普陀区金沙江路 1518 弄'
            // }, {
            // date: '2016-05-03',
            // name: '王小虎',
            // address: '上海市普陀区金沙江路 1518 弄'
            // }],
            // dialogTableVisible: false,
            dialogFormVisible: false,
            form: {
                id: '',
                //region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            formLabelWidth: '80px',
        
            pickerOptions: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                    picker.$emit('pick', [start, end]);
                    }
                }]
            },
            //value1: '',
            value2: ''
        };
    },
    methods:{
        adSearch(){
            this.dialogFormVisible = false;
            //打开溯源查询列表
            dialogSearchResultShow.value = true
        }
    }
}
</script> 

<style>
/* .el-dialog .ad-search{
    max-width: 450px;
} */
.ad-search{
    width:250px;
    height: 350px;
    /* display: flex; */
    flex-direction: column;
    align-items: center;
    justify-content: center;
}
 /* .el-form-item{
   display: block; 块级* 
}*/
.demonstration{
    right:20px;
}
.dialog-footer{
    display:flex;
    flex-direction:row;
    justify-content: center;
}
</style>