(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-3c06"],{"+7Mz":function(e,t,a){"use strict";var r=a("iMAX");a.n(r).a},SWNC:function(e,t,a){"use strict";a.d(t,"b",function(){return o}),a.d(t,"a",function(){return i}),a.d(t,"d",function(){return n}),a.d(t,"c",function(){return s});var r=a("t3Un");function o(e){var t={id:e.id};return Object(r.a)({url:"/fenxi/devicemessage",method:"get",params:t})}function i(){return Object(r.a)({url:"/fenxi/device",method:"get"})}function n(e){return Object(r.a)({url:"/fenxi/traffic",method:"post",data:e})}function s(e){return Object(r.a)({url:"/fenxi/CountryCount",method:"post",data:e})}},Znle:function(e,t,a){"use strict";a.r(t);var r=a("6ZY3"),o=a.n(r),i=a("SWNC"),n={name:"Admin",data:function(){return{formSearch:{area:"",devId:"",createTime:""},Data:[],labelPosition:"left",AreaOptions:[],DevIdOption:[],values:"",pickerOptions:{disabledDate:function(e){return e.getTime()>Date.now()-864e5},shortcuts:[{text:"所有",onClick:function(e){var t=new Date((new Date).getTime()-864e5),a=new Date;a.setTime(a.getTime()-432e9),e.$emit("pick",[a,t])}},{text:"最近一周",onClick:function(e){var t=new Date((new Date).getTime()-864e5),a=new Date;a.setTime(a.getTime()-6048e5),e.$emit("pick",[a,t])}},{text:"最近一个月",onClick:function(e){var t=new Date((new Date).getTime()-864e5),a=new Date;a.setTime(a.getTime()-2592e6),e.$emit("pick",[a,t])}},{text:"最近三个月",onClick:function(e){var t=new Date((new Date).getTime()-864e5),a=new Date;a.setTime(a.getTime()-7776e6),e.$emit("pick",[a,t])}}]},value1:[new Date(2e3,10,10,10,10),new Date(2e3,10,11,10,10)],value2:"",GJData:[],GuoBie:["国内","国外"],GeGuo:[]}},computed:{headers:function(){return{"X-Litemall-Admin-Token":getToken()}}},created:function(){this.searchArea()},methods:{search:function(){var e=this;if(""!=this.formSearch.area)if(console.log(this.formSearch.devId),null!=this.formSearch.devId)if(""!=this.formSearch.createTime){var t=o()({},this.formSearch);console.log(t),Object(i.c)(t).then(function(t){if(t&&t.data){e.Data=[],e.GJData=[],e.GeGuo=[];var a=t.data.data;console.log(a);for(var r=0;r<a.guoBieList.length;r++)e.Data.push({value:a.guoBieList[r].num,name:a.guoBieList[r].attribution});console.log(a);for(r=0;r<a.GuojiCountList.length;r++)e.GJData.push({value:a.GuojiCountList[r].num,name:a.GuojiCountList[r].country});e.GeGuo.push({value:a.MyGuoCount.num,name:"中国"});for(r=0;r<a.GuoWaiGeGuoCount.length;r++)e.GeGuo.push({value:a.GuoWaiGeGuoCount[r].num,name:a.GuoWaiGeGuoCount[r].country});e.drawPie1(),e.drawPie2(),e.drawPie3()}}).catch(function(t){e.listLoading=!1,e.$message({message:"查询异常，请重试",type:"error"})})}else this.$message({message:"请选择时间",type:"error"});else this.$message({message:"请选择设备",type:"error"});else this.$message({message:"请选择区域",type:"error"})},drawPie1:function(){var e=new echarts.init(this.$refs.eCharts1),t={title:{text:"国内各省份比例",x:"left",color:"#565050"},grid:{bottom:"1%",containLabel:!0},legend:{top:"10%",orient:"vertical",x:"left"},color:["#37a2da","#32c5e9","#3bc26b","#87681F","#c96c4c","#fb7293","#a33ebf","#8378ea","#ea02a2","#ea7c30"],tooltip:{trigger:"item",formatter:"{a} <br/>{b} : {c} ({d}%)"},toolbox:{left:"right",top:"center",orient:"vertical",show:!0,feature:{mark:{show:!0},dataView:{show:!0,readOnly:!1},magicType:{show:!0,type:["pie","funnel"]},restore:{show:!0},saveAsImage:{show:!0}}},calculable:!0,series:[{name:"归属地业务统计表",label:{normal:{formatter:"{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ",backgroundColor:"#eee",borderColor:"#aaa",borderWidth:1,borderRadius:4,rich:{a:{color:"#454343",lineHeight:22,align:"center"},hr:{borderColor:"#aaa",width:"100%",borderWidth:.5,height:0},b:{fontSize:14,lineHeight:20},per:{color:"#ffffff",backgroundColor:"#000000",padding:[2,4],borderRadius:2}}}},type:"pie",roseType:"radius",radius:[15,95],center:["50%","50%"],data:this.Data,animationEasing:"cubicInOut",animationDuration:2e3}]};e.setOption(t)},drawPie2:function(){var e=new echarts.init(this.$refs.eCharts2),t={title:{text:"各国比例",x:"left"},legend:{top:"10%",orient:"vertical",x:"left"},color:["#37a2da","#32c5e9","#9fe6b8","#ffdb5c","#ff9f7f","#fb7293","#e7bcf3","#8378ea"],tooltip:{trigger:"item",formatter:"{a} <br/>{b} : {c} ({d}%)"},toolbox:{show:!0,left:"right",top:"center",orient:"vertical",feature:{mark:{show:!0},dataView:{show:!0,readOnly:!1},magicType:{show:!0,type:["pie","funnel"]},restore:{show:!0},saveAsImage:{show:!0}}},calculable:!0,series:[{name:"归属地业务统计表",label:{normal:{formatter:"{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ",backgroundColor:"#eee",borderColor:"#aaa",borderWidth:1,borderRadius:4,rich:{a:{color:"#454343",lineHeight:22,align:"center"},hr:{borderColor:"#aaa",width:"100%",borderWidth:.5,height:0},b:{fontSize:16,lineHeight:20},per:{color:"#ffffff",backgroundColor:"#000000",padding:[2,4],borderRadius:2}}}},type:"pie",roseType:"radius",radius:[15,95],center:["50%","50%"],data:this.GeGuo,animationEasing:"cubicInOut",animationDuration:2e3}]};e.setOption(t)},drawPie3:function(){var e=new echarts.init(this.$refs.eCharts3),t={title:{text:"国内、国外对比",x:"left"},legend:{top:"10%",orient:"vertical",x:"left",data:["国内","国外"]},color:["#FB7293","#FF9F7F"],tooltip:{trigger:"item",formatter:"{a} <br/>{b} : {c} ({d}%)"},toolbox:{left:"right",top:"center",orient:"vertical",show:!0,feature:{mark:{show:!0},dataView:{show:!0,readOnly:!1},magicType:{show:!0,type:["pie","funnel"]},restore:{show:!0},saveAsImage:{show:!0}}},calculable:!0,series:[{avoidLabelOverlap:!0,name:"归属地业务统计表",label:{normal:{formatter:"{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ",backgroundColor:"#eee",borderColor:"#aaa",borderWidth:1,borderRadius:4,rich:{a:{color:"#454343",lineHeight:22,align:"center"},hr:{borderColor:"#aaa",width:"100%",borderWidth:.5,height:0},b:{fontSize:16,lineHeight:20},per:{color:"#ffffff",backgroundColor:"#000000",padding:[2,4],borderRadius:2}}}},type:"pie",roseType:"radius",radius:[15,95],center:["50%","50%"],data:this.GJData,animationEasing:"cubicInOut",animationDuration:2e3}]};e.setOption(t)},searchArea:function(){var e=this;this.AreaOptions=[],Object(i.a)().then(function(t){if(t&&t.data){var a=t.data.data;console.log(a);for(var r=0;r<a.length;r++){var o={id:a[r].devId,value:a[r].devName};e.AreaOptions.push(o)}}}).catch(function(t){e.$message({message:"查询异常，请重试",type:"error"})})},changeArea:function(){var e=this;this.DevIdOption=[],this.formSearch.devId=null;var t=this.formSearch.area;Object(i.b)({id:t}).then(function(t){if(t&&t.data){var a=t.data.data;console.log(a);for(var r=0;r<a.length;r++){var o={devId:a[r].devId,value:a[r].value};e.DevIdOption.push(o)}}})}}},s=(a("+7Mz"),a("ToIM")),l=Object(s.a)(n,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-form",{ref:"formSearch",staticClass:"filter-item",staticStyle:{display:"flex"},attrs:{"label-position":e.labelPosition,inline:!0,model:e.formSearch}},[a("el-form-item",{attrs:{label:"区域",prop:"area"}},[a("el-select",{staticClass:"select-type1",attrs:{placeholder:"请选择区域",clearable:""},on:{change:e.changeArea},model:{value:e.formSearch.area,callback:function(t){e.$set(e.formSearch,"area",t)},expression:"formSearch.area"}},e._l(e.AreaOptions,function(e){return a("el-option",{key:e.id,attrs:{label:e.value,value:e.id}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"设备号",prop:"devId"}},[a("el-select",{staticClass:"select-type1",attrs:{placeholder:"请选择设备号",clearable:""},model:{value:e.formSearch.devId,callback:function(t){e.$set(e.formSearch,"devId",t)},expression:"formSearch.devId"}},e._l(e.DevIdOption,function(e){return a("el-option",{key:e.devId,attrs:{label:e.value,value:e.devId}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"时间",prop:"createTime"}},[a("el-date-picker",{attrs:{"picker-options":e.pickerOptions,type:"daterange","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期",align:"right","value-format":"yyyy-MM-dd"},model:{value:e.formSearch.createTime,callback:function(t){e.$set(e.formSearch,"createTime",t)},expression:"formSearch.createTime"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"",prop:""}},[a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:["GET /admin/admin/list"],expression:"['GET /admin/admin/list']"}],attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.search}},[e._v("查找\n        ")])],1)],1)],1),e._v(" "),a("el-row",[a("el-col",{attrs:{span:24}},[a("div",{ref:"eCharts1",staticClass:"context-div"})])],1),e._v(" "),a("el-row",[a("el-col",{attrs:{span:24}},[a("div",{ref:"eCharts2",staticClass:"context-div"})])],1),e._v(" "),a("el-row",[a("el-col",{attrs:{span:24}},[a("div",{ref:"eCharts3",staticClass:"context-div"})])],1)],1)},[],!1,null,null,null);t.default=l.exports},iMAX:function(e,t,a){}}]);