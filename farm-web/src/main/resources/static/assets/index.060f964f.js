import{s as V}from"./index.912389eb.js";import{b as p}from"./route-block.b5bad31b.js";import{p as h,a as m,o as w,f as x,w as d,b as l,e as t,i as c,E as S}from"./index.9339062c.js";const k=c("\u91CD\u7F6E"),y=c("\u786E\u5B9A"),v={setup(U){const n="100px";let e=h({id:"",name:"",greenhouseId:"",originalStage:"",intermediateStage:"",finishedStage:""});function _(){Object.keys(e).forEach(u=>{e[u]=""})}function b(){V.post("/farmProduce/add",Object.assign(e,{updateTime:new Date().valueOf()})).then(u=>{S({type:"success",message:"\u6DFB\u52A0\u6210\u529F"}),fetchList()})}return(u,a)=>{const r=m("el-input"),i=m("el-form-item"),s=m("el-date-picker"),f=m("el-button"),g=m("el-form");return w(),x(g,{model:t(e),class:"w-400px"},{default:d(()=>[l(i,{label:"\u540D\u79F0","label-width":n},{default:d(()=>[l(r,{modelValue:t(e).name,"onUpdate:modelValue":a[0]||(a[0]=o=>t(e).name=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),l(i,{label:"\u5927\u68DAid","label-width":n},{default:d(()=>[l(r,{modelValue:t(e).greenhouseId,"onUpdate:modelValue":a[1]||(a[1]=o=>t(e).greenhouseId=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),l(i,{label:"\u53D1\u82BD\u65F6\u671F","label-width":n},{default:d(()=>[l(s,{modelValue:t(e).originalStage,"onUpdate:modelValue":a[2]||(a[2]=o=>t(e).originalStage=o),type:"date","value-format":"x",placeholder:""},null,8,["modelValue"])]),_:1}),l(i,{label:"\u751F\u957F\u65F6\u671F","label-width":n},{default:d(()=>[l(s,{modelValue:t(e).intermediateStage,"onUpdate:modelValue":a[3]||(a[3]=o=>t(e).intermediateStage=o),type:"date","value-format":"x",placeholder:""},null,8,["modelValue"])]),_:1}),l(i,{label:"\u7ED3\u679C\u65F6\u671F","label-width":n},{default:d(()=>[l(s,{modelValue:t(e).finishedStage,"onUpdate:modelValue":a[4]||(a[4]=o=>t(e).finishedStage=o),type:"date","value-format":"x",placeholder:""},null,8,["modelValue"])]),_:1}),l(i,{"label-width":n},{default:d(()=>[l(f,{onClick:_},{default:d(()=>[k]),_:1}),l(f,{type:"primary",onClick:b},{default:d(()=>[y]),_:1})]),_:1})]),_:1},8,["model"])}}};typeof p=="function"&&p(v);export{v as default};