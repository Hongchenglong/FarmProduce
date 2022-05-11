import{s as V}from"./index.96063585.js";import{g as w,h as C,r as p,o as y,c as E,a as e,w as i,b as B,d as t,F as I,e as b,x as $,E as c}from"./index.96eb606f.js";import{b as _}from"./route-block.89f12ae7.js";const D=b("\u7F16\u8F91"),F=b("\u5220\u9664"),N={class:"dialog-footer"},L=b("\u53D6\u6D88"),j=b("\u786E\u5B9A"),O={setup(W){const x=w([]),s=w(!1),d="100px";let a=C({id:1,airTempMax:"42.00",airTempMin:"20.00",airHumMax:"90.00",airHumMin:"20.00",co2Max:"1300.00",co2Min:"300.00",illuminanceMax:"900.00",illuminanceMin:"200.00",soilTempMax:"45.00",soilTempMin:"20.00",soilHumidMin:"20.00",soilHumidMax:"90.00",greenhouseId:1,createTime:1652152958e3,updateTime:null});function M(){V.get("/threshold/search/1/10").then(r=>{x.value=r.rows})}function h(r,l){s.value=!0,Object.keys(a).forEach(n=>{a[n]=l[n]})}function T(r,l){console.log(l),$.confirm("\u786E\u8BA4\u5220\u9664\uFF1F",{confirmButtonText:"\u5220\u9664",cancelButtonText:"\u53D6\u6D88",type:"warning"}).then(()=>{V.del("/threshold/"+l.id).then(n=>{M(),c({type:"success",message:"\u5220\u9664\u6210\u529F"})})})}function g(){V.put("/threshold/update",a).then(r=>{c({type:"success",message:"\u7F16\u8F91\u6210\u529F"}),s.value=!1,M()})}return M(),(r,l)=>{const n=p("el-table-column"),f=p("el-button"),H=p("el-table"),u=p("el-input"),m=p("el-form-item"),U=p("el-form"),v=p("el-dialog");return y(),E(I,null,[e(H,{data:x.value,stripe:"",style:{width:"100%"}},{default:i(()=>[e(n,{prop:"airTempMin",label:"\u6700\u4F4E\u7A7A\u6C14\u6E29\u5EA6\uFF08\u5355\u4F4D\uFF1A\u6444\u6C0F\u5EA6\uFF09",width:"150"}),e(n,{prop:"airTempMax",label:"\u6700\u9AD8\u7A7A\u6C14\u6E29\u5EA6\uFF08\u5355\u4F4D\uFF1A\u6444\u6C0F\u5EA6\uFF09",width:"150"}),e(n,{prop:"airHumMin",label:"\u6700\u4F4E\u7A7A\u6C14\u6E7F\u5EA6\uFF1A\uFF08\u5355\u4F4D\uFF1A%\uFF09",width:"150"}),e(n,{prop:"airHumMax",label:"\u6700\u9AD8\u7A7A\u6C14\u6E7F\u5EA6\uFF1A\uFF08\u5355\u4F4D\uFF1A%\uFF09",width:"150"}),e(n,{prop:"co2Min",label:"\u6700\u4F4E\u4E8C\u6C27\u5316\u78B3\u6D53\u5EA6\uFF08\u5355\u4F4D\uFF1A/10^-6)",width:"150"}),e(n,{prop:"co2Max",label:"\u6700\u9AD8\u4E8C\u6C27\u5316\u78B3\u6D53\u5EA6\uFF08\u5355\u4F4D\uFF1A/10^-6)",width:"150"}),e(n,{prop:"illuminanceMin",label:"\u6700\u4F4E\u5149\u7167\u5EA6\uFF08\u5355\u4F4D\uFF1Alx)",width:"150"}),e(n,{prop:"illuminanceMax",label:"\u6700\u9AD8\u5149\u7167\u5EA6\uFF08\u5355\u4F4D\uFF1Alx)",width:"150"}),e(n,{prop:"soilTempMin",label:"\u6700\u4F4E\u571F\u58E4\u6E29\u5EA6\uFF08\u5355\u4F4D\uFF1A\u6444\u6C0F\u5EA6\uFF09",width:"150"}),e(n,{prop:"soilTempMax",label:"\u6700\u9AD8\u571F\u58E4\u6E29\u5EA6\uFF08\u5355\u4F4D\uFF1A\u6444\u6C0F\u5EA6\uFF09",width:"150"}),e(n,{prop:"soilHumidMin",label:"\u6700\u4F4E\u571F\u58E4\u6E7F\u5EA6\uFF08\u5355\u4F4D\uFF1A%\uFF09",width:"150"}),e(n,{prop:"soilHumidMax",label:"\u6700\u9AD8\u571F\u58E4\u6E7F\u5EA6\uFF08\u5355\u4F4D\uFF1A%\uFF09",width:"150"}),e(n,{prop:"greenhouseId",label:"\u5927\u68DAID",width:"150"}),e(n,{label:"\u64CD\u4F5C"},{default:i(o=>[e(f,{onClick:k=>h(o.$index,o.row)},{default:i(()=>[D]),_:2},1032,["onClick"]),e(f,{type:"danger",onClick:k=>T(o.$index,o.row)},{default:i(()=>[F]),_:2},1032,["onClick"])]),_:1})]),_:1},8,["data"]),e(v,{modelValue:s.value,"onUpdate:modelValue":l[14]||(l[14]=o=>s.value=o),title:"\u73AF\u5883\u7F16\u8F91"},{footer:i(()=>[B("span",N,[e(f,{onClick:l[13]||(l[13]=o=>s.value=!1)},{default:i(()=>[L]),_:1}),e(f,{type:"primary",onClick:g},{default:i(()=>[j]),_:1})])]),default:i(()=>[e(U,{model:t(a)},{default:i(()=>[e(m,{label:"\u6700\u4F4E\u7A7A\u6C14\u6E29\u5EA6\uFF08\u5355\u4F4D\uFF1A\u6444\u6C0F\u5EA6\uFF09","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).airTempMin,"onUpdate:modelValue":l[0]||(l[0]=o=>t(a).airTempMin=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u9AD8\u7A7A\u6C14\u6E29\u5EA6\uFF08\u5355\u4F4D\uFF1A\u6444\u6C0F\u5EA6\uFF09","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).airTempMax,"onUpdate:modelValue":l[1]||(l[1]=o=>t(a).airTempMax=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u4F4E\u7A7A\u6C14\u6E7F\u5EA6\uFF1A\uFF08\u5355\u4F4D\uFF1A%\uFF09","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).airHumMin,"onUpdate:modelValue":l[2]||(l[2]=o=>t(a).airHumMin=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u9AD8\u7A7A\u6C14\u6E7F\u5EA6\uFF1A\uFF08\u5355\u4F4D\uFF1A%\uFF09","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).airHumMax,"onUpdate:modelValue":l[3]||(l[3]=o=>t(a).airHumMax=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u4F4E\u4E8C\u6C27\u5316\u78B3\u6D53\u5EA6\uFF08\u5355\u4F4D\uFF1A/10^-6)","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).co2Min,"onUpdate:modelValue":l[4]||(l[4]=o=>t(a).co2Min=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u9AD8\u4E8C\u6C27\u5316\u78B3\u6D53\u5EA6\uFF08\u5355\u4F4D\uFF1A/10^-6)","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).co2Max,"onUpdate:modelValue":l[5]||(l[5]=o=>t(a).co2Max=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u4F4E\u5149\u7167\u5EA6\uFF08\u5355\u4F4D\uFF1Alux)","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).illuminanceMin,"onUpdate:modelValue":l[6]||(l[6]=o=>t(a).illuminanceMin=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u9AD8\u5149\u7167\u5EA6\uFF08\u5355\u4F4D\uFF1Alux)","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).illuminanceMax,"onUpdate:modelValue":l[7]||(l[7]=o=>t(a).illuminanceMax=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u4F4E\u571F\u58E4\u6E29\u5EA6\uFF08\u5355\u4F4D\uFF1A\u6444\u6C0F\u5EA6\uFF09","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).soilTempMin,"onUpdate:modelValue":l[8]||(l[8]=o=>t(a).soilTempMin=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u9AD8\u571F\u58E4\u6E29\u5EA6\uFF08\u5355\u4F4D\uFF1A\u6444\u6C0F\u5EA6\uFF09","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).soilTempMax,"onUpdate:modelValue":l[9]||(l[9]=o=>t(a).soilTempMax=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u4F4E\u571F\u58E4\u6E7F\u5EA6\uFF08\u5355\u4F4D\uFF1A%\uFF09","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).soilHumidMin,"onUpdate:modelValue":l[10]||(l[10]=o=>t(a).soilHumidMin=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u6700\u9AD8\u571F\u58E4\u6E7F\u5EA6\uFF08\u5355\u4F4D\uFF1A%\uFF09","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).soilHumidMax,"onUpdate:modelValue":l[11]||(l[11]=o=>t(a).soilHumidMax=o),autocomplete:"off"},null,8,["modelValue"])]),_:1}),e(m,{label:"\u5927\u68DAID","label-width":d},{default:i(()=>[e(u,{modelValue:t(a).greenhouseId,"onUpdate:modelValue":l[12]||(l[12]=o=>t(a).greenhouseId=o),autocomplete:"off"},null,8,["modelValue"])]),_:1})]),_:1},8,["model"])]),_:1},8,["modelValue"])],64)}}};typeof _=="function"&&_(O);export{O as default};
