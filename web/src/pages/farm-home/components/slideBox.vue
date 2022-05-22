<template>
    <div class="slideStyle">
        <!-- <div><h1>{{msg}}</h1></div> -->
        <!-- 左按钮 -->
        <!-- <label @click='leftBtn'></label> -->
        <el-icon class="slide-leftBtn-style" @click='leftBtn'><arrow-left class="slide-lrB" /></el-icon>
        <!-- 图片列表 -->
        <ul :style='{
                width: ul_w +"vw",
                left: ul_left +"vw"
            }'>
            <li v-for="n, in imgData" :key="n">
            <!-- 在v-for循环后加上:key=""。(n, index)(注意冒号和前面的引号之间加一个空格)。
                    否则，报错expect to have 'v-bind:key' directives.
                     Vue 2.2.0+的版本里，当在组件中使用v-for时，key是必须的 -->
                <img 
                    :src=n.url 
                    :style='{
                        width:100+"vw"
                    }'
                
                />
            </li>
        </ul>
        <!-- 右按钮 -->
        <!-- <span @click='rightBtn'></span> -->
        <el-icon class="slide-rightBtn-style" @click='rightBtn'><arrow-right class="slide-lrB" /></el-icon>
        <!-- 小按钮图标 -->
        <div 
            class='pointerBtn' 
            :style='{
                marginLeft: -pointerLeft + "px",
                }'
            v-show="showPointer"
            >
            <p 
                 v-for="(n,inx) in imgData" :key="n"
                :class='inx === p_inx ? "p_class" : ""'
                @click='pEventBtn(inx)'
                >
            </p>
        </div>
    </div>
</template>

<script>
import {reactive, ref, toRefs, watch} from 'vue' //vue中有个watch，监听

export default{
    name:'slideBox',
    props:{
        autoplay:{
            type:Number,
            default:4000
        },
        showPointer:{
            type:Boolean,
            default:true
        }
    },
    setup(props){
        // console.log(props);
        let msg = '自定义轮播图插件';

        //轮播图数据
        let imgData = [
            {
                url:'http://oss.lqjai.cn/traceap/img/222.jpg',
                txt:'第一张图'
            },{
                url:'http://oss.lqjai.cn/traceap/img/111.jpg',
                txt:'第三张图'
            },
            {
                url:'http://oss.lqjai.cn/traceap/img/444.jpg',
                txt:'第三张图'
            },
            {
                url:'http://oss.lqjai.cn/traceap/img/1.jpg',
                txt:'第一张图'
            },{
                url:'http://oss.lqjai.cn/traceap/img/4.jpg',
                txt:'第二张图'
            },// {
            //     url:'http://oss.lqjai.cn/traceap/img/6.jpg',
            //     txt:'第三张图'
            // },
            {
                url:'http://oss.lqjai.cn/traceap/img/61.jpg',
                txt:'第三张图'
            },
            {
                url:'http://oss.lqjai.cn/traceap/img/9.jpg',
                txt:'第三张图'
            }
        ];

        //轮播图索引，inx代表的是第几张图被显示//索引值
        let inx = ref(0);   //给初始值为0

        //计算小按钮图标的位置
        //小按钮的总宽度的计算.（width+margin）*图片数
        let pointerLeft = ref(0);
        pointerLeft.value = (20 * imgData.length)/2+10;
        //小圆点的样式
        let p_inx = ref(0);
        watch( inx, (n_val, old_val)=>{         //监听watch(监听对象,(新值，旧值)=>{ 进行的操作 });     监听某值发生变化后，进行传值
            p_inx.value = n_val;
        });

        //计算ul的宽度
        let ulStyle = reactive({        //reative对象已通过toRefs解构出去
            ul_w : imgData.length * 100,
            ul_left : 1 //索引值
        });

        //平滑自动播放。(在此现在阶段，保留冗余的代码段)
        // 当前项目大体完成之后，再来优化
        // 开始时就优化，很容易陷入过度设计之中
        let ulMove = setInterval(()=>{
            if(inx.value < imgData.length-1){
                ++inx.value;
            }else{
                inx.value = 0;
            }
            ulStyle.ul_left = -(inx.value *100);
        },props.autoplay);

        //整个组件的事件对象
        let methodsObj = {
            //左按钮
            leftBtn(){
                if(inx.value < imgData.length-1){
                    ++inx.value;
                }else{
                    inx.value = 0;
                }
                ulStyle.ul_left = -(inx.value *100);

                //清除定时器
                clearTimeout(ulMove);
            },
            //右按钮
            rightBtn(){
                if(inx.value > 0){
                    --inx.value;
                }else{
                    inx.value = imgData.length-1;
                }
                ulStyle.ul_left = -(inx.value * 100);
                //清除定时器
                clearTimeout(ulMove);
            },
            //小圆点的事件
            pEventBtn( n ){
                console.log(n);
                inx.value = n;
                ulStyle.ul_left = -(inx.value * 100);
            }
        }
        //圆点指示器，是否显示？
        let showPointer = ref(props.showPointer);


        return{
            msg, 
            imgData, 
            ...toRefs(ulStyle),
            ...methodsObj,
            pointerLeft,
            p_inx,
            inx,
            showPointer
        }
    }
}
</script>

<style scoped>
*{
    margin: 0;
    padding: 0;
    list-style: none; /* 取消小圆点 */
}
.slideStyle{
    width:100vw;
    height: 100vh;
    /* margin: 20px auto; */
    /* border:1px solid #999; */
    overflow: hidden;
    position: relative; /* 相对定位 */
    z-index: 1;
}
.slideStyle ul{
    display: block; /* 块容器 */
    height: 100vh;
    position: absolute;
    top: 0;
    left: 0;
    transition: left 1s;
}
.slideStyle ul li{
    float: left;
    width: 100vw;
    height: 100vh;
}
/* 层级 *//* 鼠标移上去的效果 */
/* .slideStyle label,.slideStyle span{
    width:30px;
    height: 30px;
    background:#000;
    display: block;
    position: absolute;
    top: 48vh;
    z-index: 5; 
    border-radius: 8px;
    cursor: pointer;   
}
.slideStyle label{
    left:20px;
} */
.el-icon{
    width:40px;
    height: 40px;
    /* background:#000; */
    display: block;
    position: absolute;
    top: 48vh;
    z-index: 5; /* 层级 */
    border-radius: 8px;
    cursor: pointer;   /* 鼠标移上去的效果 */
}
.slide-leftBtn-style{
    left:20px;
}
.slide-rightBtn-style{
    right:20px;
}
.slide-lrB{
    width:40px;
    height: 40px;
    color: rgb(199, 186, 186);
    /* background: rgb(165, 157, 157);
    border-radius: 5px; */
}
.slideStyle span{
    right: 20px;
}
/* 小按钮图标 */
.pointerBtn{
    width:200px;
    position:absolute;
    bottom: 20px;
    left: 50vw;
}
.pointerBtn p{
    float: left;
    width: 10px;
    height: 10px;
    margin: 0 5px 0 5px;
    background: #fff;
    border-radius: 100%;
    cursor: pointer;
    /* border:2px solid #000; */
}
.pointerBtn p.p_class{
   background: #999; 
}
.pointerBtn p:hover{
    background: #999;
}
</style>