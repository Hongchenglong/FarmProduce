import{s as V}from"./index.96063585.js";import{b as f}from"./route-block.89f12ae7.js";import{h,r as m,o as w,v as g,w as o,a as t,d as l,e as p,E as k}from"./index.96eb606f.js";const x=p("\u91CD\u7F6E"),v=p("\u786E\u5B9A"),y={setup(C){const s="100px";let e=h({name:"",image:"",enterpriseId:""});function c(){Object.keys(e).forEach(u=>{e[u]=""})}function _(){V.post("/greenhouse/add",Object.assign(e,{updateTime:new Date().valueOf()})).then(u=>{k({type:"success",message:"\u6DFB\u52A0\u6210\u529F"}),fetchList()})}return(u,a)=>{const r=m("el-input"),d=m("el-form-item"),i=m("el-button"),b=m("el-form");return w(),g(b,{model:l(e),class:"w-400px"},{default:o(()=>[t(d,{label:"\u5927\u68DA\u540D\u79F0","label-width":s},{default:o(()=>[t(r,{modelValue:l(e).name,"onUpdate:modelValue":a[0]||(a[0]=n=>l(e).name=n),autocomplete:"off"},null,8,["modelValue"])]),_:1}),t(d,{label:"\u56FE\u50CF\u5730\u5740","label-width":s},{default:o(()=>[t(r,{modelValue:l(e).image,"onUpdate:modelValue":a[1]||(a[1]=n=>l(e).image=n),autocomplete:"off"},null,8,["modelValue"])]),_:1}),t(d,{label:"\u4F01\u4E1Aid","label-width":s},{default:o(()=>[t(r,{modelValue:l(e).enterpriseId,"onUpdate:modelValue":a[2]||(a[2]=n=>l(e).enterpriseId=n),autocomplete:"off"},null,8,["modelValue"])]),_:1}),t(d,{"label-width":s},{default:o(()=>[t(i,{onClick:c},{default:o(()=>[x]),_:1}),t(i,{type:"primary",onClick:_},{default:o(()=>[v]),_:1})]),_:1})]),_:1},8,["model"])}}};typeof f=="function"&&f(y);export{y as default};
