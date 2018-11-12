<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script>
    $(function(){
        $("#tbSecret").datagrid({
            url:'${pageContext.request.contextPath}/article/findArticleByPage?difference='+"显密法要",//用来请求远程数据
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
            toolbar:'#tbarSecret',
            columns:[[
                {title:'cks',field:"cks",width:180,checkbox:true},
                {title:"编号",field:"id",width:100},
                {title:"文章名",field:"title",width:150,},
                {title:"文章图片",field:"imgPath",width:150},
                {title:"发布日期",field:"publishDate",sortable:true,order:'desc',width:150},
                {title:"展示状态",field:"shows"},
                {title:'操作',field:'options',width:120,
                    formatter:function(value,row,index){
                        return "<a  class='options' onclick=\"delOneSecret('"+row.id+"')\" data-options=\"iconCls:'icon-delete',plain:true\">删除</a>&nbsp;&nbsp;" +
                            "<a class='options' onclick=\"updateSecret('"+row.id+"','"+row.shows+"')\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a>";
                    }},
            ]],
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=3 style="border:0"><img src="${pageContext.request.contextPath}/back/img/' + rowData.guru.headPic +'" style="height:100px;,width:150px"></td>' +
                    '<td style="border:0">' +
                    '<p style="font-size:16px;margin-right:30px;text-align:center">上师法名: ' + rowData.guru.name + '</p>' +
                    '</td>' +
                    '<td rowspan=3 style="border:0"><img src="${pageContext.request.contextPath}/back/img/' + rowData.imgPath +'" style="height:120px;,width:170px"></td>' +
                    '<td style="border:0">' +
                    '<p style="font-size:16px">title: ' + rowData.title + '</p>' +
                    '<p style="font-size:16px">imgPath: ' + rowData.imgPath + '</p>' +
                    '<p style="font-size:16px">content: ' + rowData.content + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },

            onLoadSuccess:function () {
                $(".options").linkbutton();
            }
        });
    });

    //删除一个用户
    function delOneSecret(id){
        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
            if(r){
                $.post("${pageContext.request.contextPath}/article/del",{"id":id},function(result){
                    //刷新页面
                    $("#tbSecret").datagrid('reload');
                })
                return true;
            }else{
                return false;
                //刷新页面
                $("#tbSecret").datagrid('reload');
            }
        })
    }


    //保存用户
    function addSecret(){
            $("#addSecretDialog").dialog({
                buttons:[{
                    iconCls:'icon-table_save',
                    text:'保存',
                    handler:function(){
                        $("#addGuruTeachesInput").form('submit',{
                            url:"${pageContext.request.contextPath}/article/add",
                            success:function(result){
                                var resultObj = $.parseJSON(result);
                                if(resultObj.success){
                                    //提示消息
                                    $.messager.show({title:'提示消息',msg:'添加文章成功'});
                                }else{
                                    $.messager.show({title:'提示消息',msg:resultObj.message});
                                }
                                //保存成功关闭对话框
                                $("#addSecretDialog").dialog('close');
                                //刷新页面
                                $("#tbSecret").datagrid('reload');
                            }
                        })
                    }
                },{
                    iconCls:'icon-cancel',
                    text:'关闭',
                    handler:function(){
                        $("#addSecretDialog").dialog('close');
                    }
                }]
            })
        }



    //修改文章
    function updateSecret(id,shows){
        $("#updateGuruTeachesDialog").dialog({
            href:'${pageContext.request.contextPath}/back/main/guru/updateGuruTeaches.jsp?id='+id+'&show='+shows,
            buttons:[{
                iconCls:'icon-edit',
                text:'修改',
                handler:function(){
                    $("#updateGuruTeachesInput").form('submit',{
                        url:'${pageContext.request.contextPath}/article/update',
                        success:function (result) {//注意一定是json字符串  使用需要转为js对象
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示消息
                                $.messager.show({title:'提示',msg:"文章状态修改成功!!!"});
                            }else{
                                $.messager.show({title:'提示消息',msg:resultObj.message});
                            }
                            //关闭dialog
                            $("#updateGuruTeachesDialog").dialog('close');
                            //刷新datagrid
                            $("#tbSecret").datagrid('reload');//刷新当前datagrid
                        }
                    })
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#updateGuruTeachesDialog").dialog('close');
                }
            }]
        });
    }


    //批量删除
    function delSecretAll() {
        var rows = $("#tbSecret").datagrid('getSelections');
        if(rows.length<=0){
            $.messager.show({title:'提示消息',msg:'请选择要删除的数据'});
        }else{
            var ids=[];
            for (var i = 0; i < rows.length ; i++) {
                ids.push(rows[i].id);
            }
            $.ajax({
                url:'${pageContext.request.contextPath}/article/delAll',
                type:'post',
                traditional:true,
                data:{ids:ids},
                success:function(result){
                    //消息提示
                    $.messager.show({title:'提示消息',msg:'删除成功'});
                    //刷新页面‘
                    $("#tbSecret").datagrid('reload');
                },
                error:function(){
                    //消息提示
                    $.messager.show({title:'提示消息',msg:'删除失败'});
                    //刷新datagrid
                    $("#tbSecret").datagrid('reload');
                }
            })
        }
    }
</script>

<%--表格--%>
<table id="tbSecret"></table>

<%--工具栏--%>
<div id="tbarSecret">
    <a href="javascript:;" onclick="addSecret();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加文章</a>
    <a href="javascript:;" onclick="delSecretAll();" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true">批量删除</a>
</div>

<div id="addSecretDialog" data-options="href:'${pageContext.request.contextPath}/back/main/guru/addGuruTeaches.jsp',iconCls:'icon-table_save',width:600,height:400,title:'上传文章'"></div>

<div id="updateSecretDialog" data-options="iconCls:'icon-edit',width:600,height:400,title:'修改状态信息'"></div>




