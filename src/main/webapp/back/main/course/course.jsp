<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<script>
    $(function(){
        $("#tbCourseMaster").datagrid({
            url:'${pageContext.request.contextPath}/course/findCourseByPage?mark='+"yes",//用来请求远程数据
            pagination:true,//显示分页工具栏
            pageNumber:1,//当前页
            pageSize:5,//每页显示记录数
            pageList:[2,3,5,10,15,30,500],
            striped:true,
            fitColumns:true,
            rownumbers:true,
            singleSelect:false,
            ctrlSelect:true,
            remoteSort:false,
            multiSort:true,
            fit:true,
            toolbar:'#tbarCourseMaster',
            columns:[[
                {title:'cks',field:"cks",width:180,checkbox:true},
                {title:"课程名",field:"name",width:100},
                {title:"创建时间",field:"createTime",width:150,},
                {title:'操作',field:'options',width:120,
                    formatter:function(value,row,index){
                        return "<a  class='options' onclick=\"delOneCourse('"+row.id+"')\" data-options=\"iconCls:'icon-delete',plain:true\">删除</a>&nbsp;&nbsp;"
                    }},
            ]],

             onLoadSuccess:function () {
                $(".options").linkbutton();
            }
        });
    });

    //删除
    function delOneCourse(id){
        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
            if(r){
                $.post("${pageContext.request.contextPath}/course/del",{"id":id},function(result){
                    //刷新页面
                    $("#tbCourseMaster").datagrid('reload');
                })
                return true;
            }else{
                return false;
                //刷新页面
                $("#tbCourseMaster").datagrid('reload');
            }
        })
    }


    //保存用户
    function addCourseMaster(){
        $("#addCourseMasterDialog").dialog({
            buttons:[{
                iconCls:'icon-table_save',
                text:'保存',
                handler:function(){
                    $("#addCourseMasterInput").form('submit',{
                        url:"${pageContext.request.contextPath}/course/add",
                        success:function(result){
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示消息
                                $.messager.show({title:'提示消息',msg:'添加课程成功'});
                            }else{
                                $.messager.show({title:'提示消息',msg:resultObj.message});
                            }
                            //保存成功关闭对话框
                            $("#addCourseMasterDialog").dialog('close');
                            //刷新页面
                            $("#tbCourseMaster").datagrid('reload');
                        }
                    })
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#addCourseMasterDialog").dialog('close');
                }
            }]
        })
    }




</script>

<%--表格--%>
<table id="tbCourseMaster"></table>

<%--工具栏--%>
<div id="tbarCourseMaster">
    <a href="javascript:;" onclick="addCourseMaster();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">上传文章</a>
</div>

<div id="addCourseMasterDialog" data-options="href:'${pageContext.request.contextPath}/back/main/course/addCourseMaster.jsp',iconCls:'icon-table_save',width:600,height:400,title:'添加课程'"></div>





