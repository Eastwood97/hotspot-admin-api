(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-474a"],{"9x9g":function(t,e,a){"use strict";var i=a("bS7j");a.n(i).a},CzTM:function(t,e,a){"use strict";a.r(e);var i=a("FyfS"),n=a.n(i),o=a("P2sY"),r=a.n(o),l=a("t3Un");var s=a("rs3x"),d=a("X4fA"),u={name:"Admin",components:{Pagination:a("Mz3J").a},data:function(){return{uploadPath:s.c,list:null,total:0,roleOptions:[{value:"0",label:"超级管理员"},{value:"1",label:"普通管理员"}],listLoading:!0,listQuery:{page:1,limit:20,username:void 0,sort:"add_time",order:"desc"},dataForm:{id:void 0,username:void 0,password:void 0,avatar:void 0,roleIds:[]},dialogFormVisible:!1,dialogFormVisibles:!1,dialogStatus:"",textMap:{update:"编辑",create:"创建"},rules:{username:[{required:!0,message:"管理员名称不能为空",trigger:"blur"}],password:[{required:!0,message:"密码不能为空",trigger:"blur"}]},downloadLoading:!1,listadmins:["超级管理员","普通管理员","普通管理员"]}},computed:{headers:function(){return{"X-Litemall-Admin-Token":Object(d.a)()}}},created:function(){this.getList()},methods:{formatRole:function(t){return this.listadmins[t]},getList:function(){var t=this;this.listLoading=!0,function(t){return Object(l.a)({url:"/admin/list",method:"get",params:t})}(this.listQuery).then(function(e){0==e.data.data.type?(t.$notify.error({title:"失败",message:"您没有权限访问!"}),t.listLoading=!1,t.$router.push({path:"/"})):(t.list=e.data.data.list,t.total=e.data.data.total),t.listLoading=!1}).catch(function(){t.list=[],t.total=0,t.listLoading=!1})},handleFilter:function(){this.listQuery.page=1,this.getList()},resetForm:function(){this.dataForm={id:void 0,username:void 0,password:void 0,avatar:void 0,roleIds:[]}},uploadAvatar:function(t){this.dataForm.avatar=t.data.url},handleCreate:function(){var t=this;this.resetForm(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.dataForm.clearValidate()})},createData:function(){var t=this;this.$refs.dataForm.validate(function(e){e&&function(t){return Object(l.a)({url:"/admin/create",method:"post",data:t})}(t.dataForm).then(function(e){t.list.unshift(e.data.data),t.dialogFormVisible=!1,t.$notify.success({title:"成功",message:"添加管理员成功"})}).catch(function(e){t.$notify.error({title:"失败",message:e.data.errmsg})})})},handleUpdate:function(t){var e=this;this.dataForm=r()({},t);var a=this.formatRole(this.dataForm.roleIds);this.dataForm.roleIds=[],this.dataForm.roleIds.push(a),this.dialogStatus="update",this.dialogFormVisibles=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},updateData:function(){var t=this;this.$refs.dataForm.validate(function(e){e&&function(t){return Object(l.a)({url:"/admin/update",method:"post",data:t})}(t.dataForm).then(function(){var e=!0,a=!1,i=void 0;try{for(var o,r=n()(t.list);!(e=(o=r.next()).done);e=!0){var l=o.value;if(l.id===t.dataForm.id){var s=t.list.indexOf(l);t.list.splice(s,1,t.dataForm);break}}}catch(t){a=!0,i=t}finally{try{!e&&r.return&&r.return()}finally{if(a)throw i}}t.dialogFormVisibles=!1,t.$notify.success({title:"成功",message:"更新管理员成功"})}).catch(function(e){t.$notify.error({title:"失败",message:e.data.errmsg})})})},handleDelete:function(t){var e=this;(function(t){return Object(l.a)({url:"/admin/delete",method:"post",data:t})})(t).then(function(a){e.$notify.success({title:"成功",message:"删除管理员成功"});var i=e.list.indexOf(t);e.list.splice(i,1)}).catch(function(t){e.$notify.error({title:"失败",message:t.data.errmsg})})},handleDownload:function(){var t=this;this.downloadLoading=!0,Promise.all([a.e("chunk-0d49"),a.e("chunk-7fdf")]).then(a.bind(null,"S/jZ")).then(function(e){e.export_json_to_excel2(["管理员ID","管理员名称","管理员头像"],t.list,["id","username","avatar"],"管理员信息"),t.downloadLoading=!1})}}},c=(a("9x9g"),a("KHd+")),m=Object(c.a)(u,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{clearable:"",placeholder:"请输入管理员名称"},model:{value:t.listQuery.username,callback:function(e){t.$set(t.listQuery,"username",e)},expression:"listQuery.username"}}),t._v(" "),a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:["GET /admin/admin/list"],expression:"['GET /admin/admin/list']"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.handleFilter}},[t._v("查找\n    ")]),t._v(" "),a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:["POST /admin/admin/create"],expression:"['POST /admin/admin/create']"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-edit"},on:{click:t.handleCreate}},[t._v("添加\n    ")]),t._v(" "),a("el-button",{staticClass:"filter-item",attrs:{loading:t.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:t.handleDownload}},[t._v("导出\n    ")])],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],attrs:{data:t.list,"element-loading-text":"正在查询中。。。",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"管理员ID",prop:"id",sortable:""}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"管理员名称",prop:"username"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"管理员角色",prop:"roleIds"},scopedSlots:t._u([{key:"default",fn:function(e){return t._l(e.row.roleIds,function(e){return a("el-tag",{key:e,staticStyle:{"margin-right":"20px"},attrs:{type:"primary"}},[t._v(t._s(t.formatRole(e))+"\n        ")])})}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作","class-name":"small-padding fixed-width"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:["POST /admin/admin/update"],expression:"['POST /admin/admin/update']"}],attrs:{type:"primary",size:"mini"},on:{click:function(a){t.handleUpdate(e.row)}}},[t._v("编辑\n        ")]),t._v(" "),a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:["POST /admin/admin/delete"],expression:"['POST /admin/admin/delete']"}],attrs:{type:"danger",size:"mini"},on:{click:function(a){t.handleDelete(e.row)}}},[t._v("删除\n        ")])]}}])})],1),t._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.page,limit:t.listQuery.limit},on:{"update:page":function(e){t.$set(t.listQuery,"page",e)},"update:limit":function(e){t.$set(t.listQuery,"limit",e)},pagination:t.getList}}),t._v(" "),a("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{rules:t.rules,model:t.dataForm,"status-icon":"","label-position":"left","label-width":"100px"}},[a("el-form-item",{attrs:{label:"管理员名称",prop:"username"}},[a("el-input",{model:{value:t.dataForm.username,callback:function(e){t.$set(t.dataForm,"username",e)},expression:"dataForm.username"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"管理员密码",prop:"password"}},[a("el-input",{attrs:{type:"password","auto-complete":"off"},model:{value:t.dataForm.password,callback:function(e){t.$set(t.dataForm,"password",e)},expression:"dataForm.password"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"管理员角色",prop:"roleIds"}},[a("el-select",{attrs:{multiple:"",placeholder:"请选择"},model:{value:t.dataForm.roleIds,callback:function(e){t.$set(t.dataForm,"roleIds",e)},expression:"dataForm.roleIds"}},t._l(t.roleOptions,function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})}))],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取消")]),t._v(" "),"create"==t.dialogStatus?a("el-button",{attrs:{type:"primary"},on:{click:t.createData}},[t._v("确定")]):a("el-button",{attrs:{type:"primary"},on:{click:t.updateData}},[t._v("确定")])],1)],1),t._v(" "),a("el-dialog",{attrs:{visible:t.dialogFormVisibles,title:"编辑"},on:{"update:visible":function(e){t.dialogFormVisibles=e}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{rules:t.rules,model:t.dataForm,"status-icon":"","label-position":"left","label-width":"100px"}},[a("el-form-item",{attrs:{label:"管理员名称",prop:"username"}},[a("el-input",{model:{value:t.dataForm.username,callback:function(e){t.$set(t.dataForm,"username",e)},expression:"dataForm.username"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"管理员角色",prop:"roleIds"}},[a("el-select",{attrs:{multiple:"",placeholder:"请选择"},model:{value:t.dataForm.roleIds,callback:function(e){t.$set(t.dataForm,"roleIds",e)},expression:"dataForm.roleIds"}},t._l(t.roleOptions,function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})}))],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisibles=!1}}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.updateData}},[t._v("确定")])],1)],1)],1)},[],!1,null,null,null);e.default=m.exports},LROu:function(t,e,a){"use strict";var i=a("Qvsb");a.n(i).a},Mz3J:function(t,e,a){"use strict";Math.easeInOutQuad=function(t,e,a,i){return(t/=i/2)<1?a/2*t*t+e:-a/2*(--t*(t-2)-1)+e};var i=window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)};function n(t,e,a){var n=document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop,o=t-n,r=0;e=void 0===e?500:e;!function t(){r+=20,function(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}(Math.easeInOutQuad(r,n,o,e)),r<e?i(t):a&&"function"==typeof a&&a()}()}var o={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&n(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&n(0,800)}}},r=(a("LROu"),a("KHd+")),l=Object(r.a)(o,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[a("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},[],!1,null,"2fc659d3",null);e.a=l.exports},Qvsb:function(t,e,a){},bS7j:function(t,e,a){},rs3x:function(t,e,a){"use strict";a.d(e,"a",function(){return n}),a.d(e,"b",function(){return o}),a.d(e,"c",function(){return r});var i=a("t3Un");function n(t){return Object(i.a)({url:"/storage/create",method:"post",data:t})}function o(t){return Object(i.a)({url:"/storage/delete",method:"delete",data:t})}var r="http://localhost:8084/admin/storage/create"}}]);