import{h as _,r as a,o as i,c as f,a as o,w as l,d as t,b as h,e as g,f as w,E as V}from"./index.3d885891.js";import{s as b}from"./index.a574311f.js";const x={class:"farm-login-tab"},v=h("p",{class:"farm-home-title"},"\u667A\u6167\u519C\u4E1A\u7CFB\u7EDF",-1),y=g("\u767B\u5F55"),E={setup(k){const e=_({account:"",password:""});function p(){b.post("/user/login",e).then(n=>{localStorage.setItem("token",n),w.push("/farm-manage")}).catch(n=>{e.account="",e.password="",V({type:"error",message:"\u8D26\u53F7\u6216\u5BC6\u7801\u9519\u8BEF"})})}return(n,s)=>{const c=a("el-input"),m=a("el-form-item"),d=a("el-button"),u=a("el-form");return i(),f("div",x,[v,o(u,{class:"farm-login-form","label-position":"top","label-width":"450px",model:t(e)},{default:l(()=>[o(m,{label:"\u7528\u6237\u540D"},{default:l(()=>[o(c,{class:"einput",modelValue:t(e).account,"onUpdate:modelValue":s[0]||(s[0]=r=>t(e).account=r),placeholder:"\u8BF7\u8F93\u5165\u7528\u6237\u540D"},null,8,["modelValue"])]),_:1}),o(m,{label:"\u5BC6\u7801"},{default:l(()=>[o(c,{class:"einput",modelValue:t(e).password,"onUpdate:modelValue":s[1]||(s[1]=r=>t(e).password=r),"show-password":"",placeholder:"\u8BF7\u8F93\u5165\u5BC6\u7801"},null,8,["modelValue"])]),_:1}),o(d,{class:"",type:"success",style:{width:"450px"},onClick:p},{default:l(()=>[y]),_:1})]),_:1},8,["model"])])}}};export{E as default};