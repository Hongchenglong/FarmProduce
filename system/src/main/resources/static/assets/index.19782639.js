import{s as k}from"./index.76a7dd0f.js";import{b as u}from"./route-block.89f12ae7.js";import{h as V,r as l,o as v,v as w,w as o,a as t,d as a,e as i,E as g}from"./index.ef2c8b76.js";const x=i("\u91CD\u7F6E"),y=i("\u786E\u5B9A"),C={setup(Y){const s="100px";let e=V({date:"",greenhouseId:""});function p(){Object.keys(e).forEach(d=>{e[d]=""})}function f(){k.post("/environment/add",Object.assign(e,{updateTime:new Date().valueOf()})).then(d=>{g({type:"success",message:"\u6DFB\u52A0\u6210\u529F"}),fetchList()})}return(d,n)=>{const _=l("el-date-picker"),r=l("el-form-item"),h=l("el-input"),c=l("el-button"),b=l("el-form");return v(),w(b,{model:a(e),class:"w-400px"},{default:o(()=>[t(r,{label:"\u65E5\u671F","label-width":s},{default:o(()=>[t(_,{modelValue:a(e).date,"onUpdate:modelValue":n[0]||(n[0]=m=>a(e).date=m),type:"date","value-format":"YYYY-MM-DD",placeholder:""},null,8,["modelValue"])]),_:1}),t(r,{label:"\u5927\u68DAid","label-width":s},{default:o(()=>[t(h,{modelValue:a(e).greenhouseId,"onUpdate:modelValue":n[1]||(n[1]=m=>a(e).greenhouseId=m),autocomplete:"off"},null,8,["modelValue"])]),_:1}),t(r,{"label-width":s},{default:o(()=>[t(c,{onClick:p},{default:o(()=>[x]),_:1}),t(c,{type:"primary",onClick:f},{default:o(()=>[y]),_:1})]),_:1})]),_:1},8,["model"])}}};typeof u=="function"&&u(C);export{C as default};
