### http://oss.lqjai.cn/traceap/img前端项目

### 启动  
1. 拉取代码 
2. 切到farm-smart目录
3. 执行 npm i （需要先安装git）
4. npm run dev

### file-based 路由 和 layout布局
1. 页面路由表自动生成，src/pages文件夹对应的路由项，文件夹中的index.vue 为路由加载页面组建
2. src/layouts 文件夹里面文件自动索引为路由页面的布局组件，基础用法可以参考 /pages/farm-manage/index.vue 的 route标签