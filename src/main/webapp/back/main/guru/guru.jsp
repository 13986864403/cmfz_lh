<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script>
    $(function(){
        $("#tbGuru").datagrid({
            url:'${pageContext.request.contextPath}/guru/findGuruByPage',//用来请求远程数据
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
            toolbar:'#tbarGuru',
            columns:[[
                {title:'cks',field:"cks",width:180,checkbox:true},
                {title:"编号",field:"id",width:120},
                {title:"法名",field:"name",width:150},
                {title:"上师头像",field:"headPic"},
                {title:"性别",field:"gender",sortable:true,order:'desc',width:150},
                {title:"注册时间",field:"createTime",width:120},
                {title:"状态",field:"status",width:150,},
                {title:'操作',field:'options',width:120,
                    formatter:function(value,row,index){
                        return "<a  class='options' onclick=\"delOneGuru('"+row.id+"')\" data-options=\"iconCls:'icon-delete',plain:true\">删除</a>&nbsp;&nbsp;" +
                            "<a class='options' onclick=\"updateGuru('"+row.id+"','"+row.headPic+"')\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a>";
                    }},
            ]],
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=3 style="border:0"><img src="${pageContext.request.contextPath}/back/img/' + rowData.headPic +'" style="height:150px;,width:180px"></td>' +
                    '<td style="border:0">' +
                    '<p style="font-size:16px">title: ' + rowData.name + '</p>' +
                    '<p style="font-size:16px">createTime: ' + rowData.createTime + '</p>' +
                    '<p style="font-size:16px">headPic: ' + rowData.headPic + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },

            onLoadSuccess:function () {
                $(".options").linkbutton();
            }
        });
    });

   //删除一个用户
    function delOneGuru(id){
        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
            if(r){
                $.post("${pageContext.request.contextPath}/guru/del",{"id":id},function(result){
                    //刷新页面
                    $("#tbGuru").datagrid('reload');
                })
                return true;
            }else{
                return false;
                //刷新页面
                $("#tbGuru").datagrid('reload');
            }
        })
    }


    //保存用户
    function addGuru(){
        $("#addGuruDialog").dialog({
            buttons:[{
                iconCls:'icon-table_save',
                text:'保存',
                handler:function(){
                    $("#addGuruInput").form('submit',{
                        url:"${pageContext.request.contextPath}/guru/add",
                        success:function(result){
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示消息
                                $.messager.show({title:'提示消息',msg:'添加用户成功'});
                            }else{
                                $.messager.show({title:'提示消息',msg:resultObj.message});
                            }
                            //保存成功关闭对话框
                            $("#addGuruDialog").dialog('close');
                            //刷新页面
                            $("#tbGuru").datagrid('reload');
                        }
                    })
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#addGuruDialog").dialog('close');
                }
            }]
        })
    }


    //修改用户
    function updateGuru(id,img){
        $("#updateGuruDialog").dialog({
            href:'${pageContext.request.contextPath}/back/main/guru/update.jsp?id='+id+'&img='+img,
            buttons:[{
                iconCls:'icon-edit',
                text:'修改',
                handler:function(){
                    $("#updateGuruInput").form('submit',{
                        url:'${pageContext.request.contextPath}/guru/update?imgPaths='+img,
                        success:function (result) {//注意一定是json字符串  使用需要转为js对象
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示消息
                                $.messager.show({title:'提示',msg:"用户信息修改成功!!!"});
                            }else{
                                $.messager.show({title:'提示消息',msg:resultObj.message});
                            }
                            //关闭dialog
                            $("#updateGuruDialog").dialog('close');
                            //刷新datagrid
                            $("#tbGuru").datagrid('reload');//刷新当前datagrid
                        }
                    })
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#updateGuruDialog").dialog('close');
                }
            }]
        });
    }


    //批量删除
    function delGuruAll() {
        var rows = $("#tbGuru").datagrid('getSelections');
        if(rows.length<=0){
            $.messager.show({title:'提示消息',msg:'请选择要删除的数据'});
        }else{
            var ids=[];
            for (var i = 0; i < rows.length ; i++) {
                ids.push(rows[i].id);
            }
            $.ajax({
                url:'${pageContext.request.contextPath}/guru/delAll',
                type:'post',
                traditional:true,
                data:{ids:ids},
                success:function(result){
                    //消息提示
                    $.messager.show({title:'提示消息',msg:'删除成功'});
                    //刷新页面‘
                    $("#tbGuru").datagrid('reload');
                },
                error:function(){
                    //消息提示
                    $.messager.show({title:'提示消息',msg:'删除失败'});
                    //刷新datagrid
                    $("#tbGuru").datagrid('reload');
                }
            })
        }
    }
</script>

<%--表格--%>
<table id="tbGuru"></table>

<%--工具栏--%>
<div id="tbarGuru">
    <a href="javascript:;" onclick="addGuru();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加上师</a>
    <a href="javascript:;" onclick="delGuruAll();" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true">批量删除</a>
</div>

<div id="addGuruDialog" data-options="href:'${pageContext.request.contextPath}/back/main/guru/add.jsp',iconCls:'icon-table_save',width:600,height:400,title:'保存上师信息'"></div>

<div id="updateGuruDialog" data-options="iconCls:'icon-edit',width:600,height:400,title:'修改上师信息'"></div>




