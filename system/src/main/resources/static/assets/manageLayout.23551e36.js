import{u as Q,r as o,o as O,c as E,a as e,w as t,b as n,d as l,l as d,m as R,e as s,f as M}from"./index.ef2c8b76.js";var k="/assets/u419.1708e9a7.jpeg",C="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAASCAYAAABrXO8xAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAD/SURBVDhPY2RAA2fOnEkAUs5AbPD//38WRkbGK0D64JcvX2Y4Ojr+ASsCArjGEydOyLCwsMwHMl0gIhjgAhBHmpiY3ABxwBqvXLnC8+PHj8tApgKIjwsAbf7w9+9fXQsLiydMIIHv37/3Aym8mkAA6GwBqKsYGIF+EgHSr0EcYgHQVlOQjRYQLvGAiYnJAqRRB8IlHgCdrM0E9DAHlE80AGrkAAcOOWBEaASGEDOUTTQAJ/7Tp09vBmomNS4/YGQrEDh16lQMMHWwQLkMnz9/XgHMUj+gXDDA6kegJn8gBUrM84HOSkfXBAJYNQITcS5QQweQOQVoSCxEFBkwMAAAAOhRrJfZILUAAAAASUVORK5CYII=",I="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAAGCAYAAAARx7TFAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABtSURBVBhXYzx79uz8////JzDgBjuY2NnZc4GMKxA+BngCxLFMOjo6X/79+xcJNO0LRBwO/gBxpImJyRsmEM/MzOwKIyMjyERkUAtUcATKRgCQ+86cOfMfiLdDhcAAbBIMQN23A4hjwQJgwMAAAEEEJDPdunpdAAAAAElFTkSuQmCC";const U={class:"farm-manage"},S=["onClick"],D=n("span",null,"\u519C\u4EA7\u54C1\u7BA1\u7406",-1),Y=s("\u519C\u4EA7\u54C1\u5217\u8868"),b=s("\u519C\u4EA7\u54C1\u65B0\u589E"),j=n("span",null,"\u519C\u4EA7\u54C1\u6EAF\u6E90",-1),G=s("\u6EAF\u6E90\u7BA1\u7406"),J=s("\u6EAF\u6E90\u65B0\u589E"),N=s("\u6EAF\u6E90\u8D8B\u52BF"),y=n("span",null,"\u73AF\u5883\u7BA1\u7406",-1),H=s("\u73AF\u5883\u5217\u8868"),X=s("\u73AF\u5883\u65B0\u589E"),L=n("span",null,"\u5927\u68DA\u7BA1\u7406",-1),V=s("\u5927\u68DA\u5217\u8868"),K=s("\u5927\u68DA\u65B0\u589E"),T={class:"header pl-30px pr-50px h-65px flex-between-center"},q=n("img",{src:k,width:"32",alt:""},null,-1),z={class:"right flex-center-center space-x-5"},F=n("img",{src:C,height:"18",width:"14",alt:""},null,-1),P=n("span",{class:"cursor-pointer"},"nickName",-1),Z=n("span",{class:"flex-center-center cursor-pointer"},[n("span",{class:"mr-2"},"\u66F4\u591A"),n("img",{src:I,class:"cursor-pointer",height:"18",width:"12",alt:"",srcset:""})],-1),W=s("\u9000\u51FA"),$={class:"content p-20px"},se={setup(ee){const c=Q().path,i=c.substring(c.lastIndexOf("/"+1),c.lastIndexOf("-"));function u(){M.push("/farm-login"),localStorage.clear()}return(te,ne)=>{const m=o("router-link"),_=o("el-icon"),a=o("el-menu-item"),A=o("el-sub-menu"),p=o("el-menu"),r=o("el-col"),f=o("el-badge"),h=o("el-dropdown-item"),g=o("el-dropdown-menu"),x=o("el-dropdown"),w=o("router-view"),v=o("el-row");return O(),E("div",U,[e(v,{class:"tac h-100vh overflow-hidden"},{default:t(()=>[e(r,{span:3},{default:t(()=>[e(m,{to:"/farm-manage",custom:""},{default:t(({navigate:B})=>[n("h5",{onClick:B,class:"h-100px leading-100px text-center text-primary"},"\u667A\u6167\u519C\u4EA7",8,S)]),_:1}),e(p,{"default-active":l(c),"default-openeds":[l(i)],class:"!border-0",router:""},{default:t(()=>[e(A,{index:"product"},{title:t(()=>[e(_,null,{default:t(()=>[e(l(d))]),_:1}),D]),default:t(()=>[e(a,{index:"/farm-manage/product-list"},{default:t(()=>[Y]),_:1}),e(a,{index:"/farm-manage/product-add"},{default:t(()=>[b]),_:1})]),_:1}),e(A,{index:"source"},{title:t(()=>[e(_,null,{default:t(()=>[e(l(R))]),_:1}),j]),default:t(()=>[e(a,{index:"/farm-manage/source-list"},{default:t(()=>[G]),_:1}),e(a,{index:"/farm-manage/source-add"},{default:t(()=>[J]),_:1}),e(a,{index:"/farm-manage/source-graph"},{default:t(()=>[N]),_:1})]),_:1}),e(A,{index:"environment"},{title:t(()=>[e(_,null,{default:t(()=>[e(l(d))]),_:1}),y]),default:t(()=>[e(a,{index:"/farm-manage/environment-list"},{default:t(()=>[H]),_:1}),e(a,{index:"/farm-manage/environment-add"},{default:t(()=>[X]),_:1})]),_:1}),e(A,{index:"greenhouse"},{title:t(()=>[e(_,null,{default:t(()=>[e(l(d))]),_:1}),L]),default:t(()=>[e(a,{index:"/farm-manage/greenhouse-list"},{default:t(()=>[V]),_:1}),e(a,{index:"/farm-manage/greenhouse-add"},{default:t(()=>[K]),_:1})]),_:1})]),_:1},8,["default-active","default-openeds"])]),_:1}),e(r,{span:21,class:""},{default:t(()=>[n("div",T,[q,n("div",z,[e(f,{"is-dot":"",class:"item cursor-pointer"},{default:t(()=>[F]),_:1}),P,e(x,null,{dropdown:t(()=>[e(g,null,{default:t(()=>[e(h,{onClick:u},{default:t(()=>[W]),_:1})]),_:1})]),default:t(()=>[Z]),_:1})])]),n("div",$,[e(w)])]),_:1})]),_:1})])}}};export{se as default};
