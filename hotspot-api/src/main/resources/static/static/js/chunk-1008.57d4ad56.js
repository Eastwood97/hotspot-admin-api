(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-1008"],{NHmn:function(t,e,a){"use strict";a.r(e);var i=a("t3Un");var l=a("X4fA"),n={data:function(){return{picURL:"http://192.168.244.3:9222/",multipleSelection:[],advanceSearchViewVisible:!1,list:[],total:0,listLoading:!0,listQuery:{page:1,limit:10,targetName:"",id:void 0},dataForm:{targetName:""},downloadLoading:!1}},computed:{headers:function(){return{"X-Litemall-Admin-Token":Object(l.a)()}}},created:function(){this.getList()},methods:{getList:function(){var t=this;this.listLoading=!0,function(t){return Object(i.a)({url:"/relatedNumResult",method:"get",params:t})}(this.listQuery).then(function(e){t.list=e.data.data.list,console.log(e.data.data.list),t.total=e.data.data.total,t.listLoading=!1}).catch(function(){t.list=[],t.total=0,t.listLoading=!1})},handleSizeChange:function(t){this.listQuery.limit=t,this.getList()},currentChange:function(t){this.listQuery.page=t,this.getList()},handleFilter:function(){this.listQuery.page=1,this.getList()},emptyListQuery:function(){this.listQuery={page:1,limit:10,targetName:"",id:void 0}},resetForm:function(){this.dataForm={targetName:""}},handleDownload:function(){var t=this;this.downloadLoading=!0,Promise.all([a.e("chunk-0d49"),a.e("chunk-7fdf")]).then(a.bind(null,"S/jZ")).then(function(e){e.export_json_to_excel2(["目标ID","目标名称","目标外貌","Top1","Top2","Top3","中标次数"],t.list,["id","targetName","targetFace","relatedResult","relatedResult.topTwo.imsi","relatedResult.topThree.imsi","captureNum"],"关联号码结果"),t.downloadLoading=!1})},showAdvanceSearchView:function(){this.advanceSearchViewVisible=!this.advanceSearchViewVisible,this.emptyListQuery(),this.advanceSearchViewVisible||(this.emptyListQuery(),this.getList())},cancelSearch:function(){this.advanceSearchViewVisible=!1,this.emptyListQuery(),this.beginDateScope="",this.getList()}}},r=(a("o8sJ"),a("KHd+")),o=Object(r.a)(n,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{clearable:"",placeholder:"请输入目标名称"},model:{value:t.listQuery.targetName,callback:function(e){t.$set(t.listQuery,"targetName",e)},expression:"listQuery.targetName"}}),t._v(" "),a("el-button",{staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.handleFilter}},[t._v("查找")])],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],attrs:{data:t.list,"element-loading-text":"正在查询中。。。",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"ID",prop:"id","min-width":"30px"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"目标名称",prop:"targetName","min-width":"40px"}}),t._v(" "),a("el-table-column",{attrs:{align:"center","min-width":"80px",label:"目标外貌",prop:"targetFace"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.targetFace?a("img",{attrs:{src:t.picURL+e.row.targetFace,width:"80",preview:"1","preview-text":"1"}}):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"Top1",prop:"relatedResult.topOne.imsi,relatedResult.topOne.count"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.relatedResult.topOne?a("p",[t._v(t._s(e.row.relatedResult.topOne.imsi))]):t._e(),t._v(" "),e.row.relatedResult.topOne?a("p",[t._v(t._s(e.row.relatedResult.topOne.count)+"次")]):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"Top2",prop:"relatedResult.topTwo.imsi,relatedResult.toptwo.count"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.relatedResult.topTwo?a("p",[t._v(t._s(e.row.relatedResult.topTwo.imsi))]):t._e(),t._v(" "),e.row.relatedResult.topTwo?a("p",[t._v(t._s(e.row.relatedResult.topTwo.count)+"次")]):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"Top3",prop:"relatedResult.topThree.imsi,relatedResult.topThree.count"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.relatedResult.topThree?a("p",[t._v(t._s(e.row.relatedResult.topThree.imsi))]):t._e(),t._v(" "),e.row.relatedResult.topThree?a("p",[t._v(t._s(e.row.relatedResult.topThree.count)+"次")]):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"中标次数",prop:"captureNum"}})],1),t._v(" "),a("el-pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,"page-sizes":[5,10,20,100],page:t.listQuery.page,limit:t.listQuery.limit,layout:"total, sizes, prev, pager, next, jumper"},on:{"update:page":function(e){t.$set(t.listQuery,"page",e)},"update:limit":function(e){t.$set(t.listQuery,"limit",e)},"current-change":t.currentChange,"size-change":t.handleSizeChange}})],1)},[],!1,null,null,null);e.default=o.exports},O5j0:function(t,e,a){},o8sJ:function(t,e,a){"use strict";var i=a("O5j0");a.n(i).a}}]);