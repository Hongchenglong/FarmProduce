import{s as l}from"./index.75aa442e.js";import{r as t,p as c,a as p,o as m,f as u,w as f,i as x}from"./index.5512758f.js";import{b as i}from"./route-block.89f12ae7.js";const M=x("\u5BFC\u51FA\u62A5\u8868"),d={setup(_){const a=t([]);t(!1),c({id:1,airTempMax:"42.00",airTempMin:"20.00",airHumMax:"90.00",airHumMin:"20.00",co2Max:"1300.00",co2Min:"300.00",illuminanceMax:"900.00",illuminanceMin:"200.00",soilTempMax:"45.00",soilTempMin:"20.00",soilHumidMin:"20.00",soilHumidMax:"90.00",greenhouseId:1,createTime:1652152958e3,updateTime:null});function n(){l.get("/threshold/search/1/10").then(e=>{a.value=e.rows})}function r(){window.location.href="/api/alert/exportReport"}return n(),(e,o)=>{const s=p("el-button");return m(),u(s,{onClick:o[0]||(o[0]=h=>r())},{default:f(()=>[M]),_:1})}}};typeof i=="function"&&i(d);export{d as default};
