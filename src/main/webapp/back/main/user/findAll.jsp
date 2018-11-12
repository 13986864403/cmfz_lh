<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<script>


    $(function(){
        $("#tbUser").datagrid({
            url:'${pageContext.request.contextPath}/user/findUserByPage',//用来请求远程数据
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
            toolbar:'#tbarUser',
            columns:[[
                {title:'cks',field:"cks",width:180,checkbox:true},
                {title:"法名",field:"Dharma_name",width:100},
                {title:"用户名",field:"username",width:150,},
                {title:"密码",field:"password",width:150,},
                {title:"性别",field:"gender",width:150,},
                {title:"家庭住址",field:"home",width:150,},
                {title:"状态",field:"status",width:150,},
            ]],
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=3 style="border:0"><img src="${pageContext.request.contextPath}/back/img/' + rowData.headPic +'" style="height:150px;,width:180px"></td>' +
                    '<td style="border:0">' +
                    '<p style="font-size:16px">title: ' + rowData.name + '</p>' +
                    '<p style="font-size:16px">sign: ' + rowData.sign + '</p>' +
                    '<p style="font-size:16px">register_time: ' + rowData.register_time + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
        });
    });
    function coldUser(){
        var select=$("#tbUser").datagrid("getSelections");
        if(select==null){

        }

    }


</script>

<%--表格--%>
<table id="tbUser"></table>

<%--工具栏--%>
<div id="tbarUser">
    <a href="javascript:;" onclick="coldUser();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">冻结用户</a>
</div>








