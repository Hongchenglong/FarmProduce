import{s as b}from"./index.96063585.js";import{g as w,h as $,r as i,o as D,c as F,a as e,w as o,b as I,d,F as M,e as c,x as T,E as x,i as Y}from"./index.96eb606f.js";import{b as v}from"./route-block.89f12ae7.js";const N=c("\u7F16\u8F91"),P=c("\u5220\u9664"),j={class:"dialog-footer"},L=c("\u53D6\u6D88"),A=c("\u786E\u5B9A"),O={setup(W){const h=w([]),u=w(!1),m="100px";let a=$({id:"",name:"",greenhouseId:"",originalStage:"",intermediateStage:"",finishedStage:""});function _(){b.get("/farmProduce/findAll").then(r=>{h.value=r})}function y(r,t){u.value=!0,Object.keys(a).forEach(n=>{a[n]=t[n]})}function S(r,t){console.log(t),T.confirm("\u786E\u8BA4\u5220\u9664\uFF1F",{confirmButtonText:"\u5220\u9664",cancelButtonText:"\u53D6\u6D88",type:"warning"}).then(()=>{b.del("/farmProduce/"+t.id).then(n=>{_(),x({type:"success",message:"\u5220\u9664\u6210\u529F"})})})}function k(){b.put("/farmProduce/update",a).then(r=>{x({type:"success",message:"\u7F16\u8F91\u6210\u529F"}),u.value=!1,_()})}function p(r,t,n,s){return Y(n).format("YYYY-MM-DD")}return _(),(r,t)=>{const n=i("el-table-column"),s=i("el-button"),C=i("el-table"),V=i("el-input"),f=i("el-form-item"),g=i("el-date-picker"),E=i("el-form"),B=i("el-dialog");return D(),F(M,null,[e(C,{data:h.value,stripe:"",style:{width:"100%"}},{default:o(()=>[e(n,{prop:"name",label:"\u540D\u79F0",width:"100"}),e(n,{prop:"greenhouseId",label:"\u5927\u68DAid",width:"100"}),e(n,{prop:"originalStage",label:"\u53D1\u82BD\u65F6\u671F",formatter:p,width:"180"}),e(n,{prop:"intermediateStage",label:"\u751F\u957F\u65F6\u671F",formatter:p,width:"180"}),e(n,{prop:"finishedStage",label:"\u7ED3\u679C\u65F6\u671F",formatter:p,width:"180"}),e(n,{prop:"updateTime",label:"\u66F4\u65B0\u65F6\u95F4",width:"180",formatter:p}),e(n,{label:"\u64CD\u4F5C"},{default:o(l=>[e(s,{onClick:U=>y(l.$index,l.row)},{default:o(()=>[N]),_:2},1032,["onClick"]),e(s,{type:"danger",onClick:U=>S(l.$index,l.row)},{default:o(()=>[P]),_:2},1032,["onClick"])]),_:1})]),_:1},8,["data"]),e(B,{modelValue:u.value,"onUpdate:modelValue":t[6]||(t[6]=l=>u.value=l),title:"\u519C\u4EA7\u54C1\u7F16\u8F91"},{footer:o(()=>[I("span",j,[e(s,{onClick:t[5]||(t[5]=l=>u.value=!1)},{default:o(()=>[L]),_:1}),e(s,{type:"primary",onClick:k},{default:o(()=>[A]),_:1})])]),default:o(()=>[e(E,{model:d(a)},{default:o(()=>[e(f,{label:"\u540D\u79F0","label-width":m},{default:o(()=>[e(V,{modelValue:d(a).name,"onUpdate:modelValue":t[0]||(t[0]=l=>d(a).name=l),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(f,{label:"\u5927\u68DAid","label-width":m},{default:o(()=>[e(V,{modelValue:d(a).greenhouseId,"onUpdate:modelValue":t[1]||(t[1]=l=>d(a).greenhouseId=l),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(f,{label:"\u53D1\u82BD\u65F6\u671F","label-width":m},{default:o(()=>[e(g,{modelValue:d(a).originalStage,"onUpdate:modelValue":t[2]||(t[2]=l=>d(a).originalStage=l),type:"date","value-format":"x",placeholder:""},null,8,["modelValue"])]),_:1}),e(f,{label:"\u751F\u957F\u65F6\u671F","label-width":m},{default:o(()=>[e(g,{modelValue:d(a).intermediateStage,"onUpdate:modelValue":t[3]||(t[3]=l=>d(a).intermediateStage=l),type:"date","value-format":"x",placeholder:""},null,8,["modelValue"])]),_:1}),e(f,{label:"\u7ED3\u679C\u65F6\u671F","label-width":m},{default:o(()=>[e(g,{modelValue:d(a).finishedStage,"onUpdate:modelValue":t[4]||(t[4]=l=>d(a).finishedStage=l),type:"date","value-format":"x",placeholder:""},null,8,["modelValue"])]),_:1})]),_:1},8,["model"])]),_:1},8,["modelValue"])],64)}}};typeof v=="function"&&v(O);export{O as default};
