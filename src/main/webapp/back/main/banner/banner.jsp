<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script>
    $(function(){
        $("#tb").datagrid({
            url:'${pageContext.request.contextPath}/banner/findBannerByPage',//用来请求远程数据
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
            toolbar:'#tbarBanner',
            columns:[[
                {title:'cks',field:"cks",width:180,checkbox:true},
                {title:"编号",field:"id",width:120},
                {title:"图片标题",field:"title",width:150},
                {title:"图片路径",field:"imgPaths"},
                {title:"上传时间",field:"createTime",sortable:true,order:'desc',width:150},
                {title:"状态",field:"status",width:120},
                {title:"描述",field:"descs",width:150},
                {title:'操作',field:'options',width:120,
                    formatter:function(value,row,index){
                        return "<a  class='options' onclick=\"delOneBanner('"+row.id+"')\" data-options=\"iconCls:'icon-delete',plain:true\">删除</a>&nbsp;&nbsp;" +
                            "<a class='options' onclick=\"updateBanner('"+row.id+"','"+row.imgPaths+"')\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a>";
                    }},
            ]],
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=3 style="border:0"><img src="${pageContext.request.contextPath}/back/img/' + rowData.imgPaths +'" style="height:150px;,width:180px"></td>' +
                    '<td style="border:0">' +
                    '<p style="font-size:16px">title: ' + rowData.title + '</p>' +
                    '<p style="font-size:16px">createTime: ' + rowData.createTime + '</p>' +
                    '<p style="font-size:16px">imgPaths: ' + rowData.imgPaths + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },

            onLoadSuccess:function () {
                $(".options").linkbutton();
            }
        });
    });

   //删除一个用户
    function delOneBanner(id){
        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
            if(r){
                $.post("${pageContext.request.contextPath}/banner/del",{"id":id},function(result){
                    //刷新页面
                    $("#tb").datagrid('reload');
                })
                return true;
            }else{
                return false;
                //刷新页面
                $("#tb").datagrid('reload');
            }
        })
    }


    //保存用户
    function addBanner(){
        $("#addBannerDialog").dialog({
            buttons:[{
                iconCls:'icon-table_save',
                text:'保存',
                handler:function(){
                    $("#addBannerInput").form('submit',{
                        url:"${pageContext.request.contextPath}/banner/add",
                        success:function(result){
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示消息
                                $.messager.show({title:'提示消息',msg:'添加用户成功'});
                            }else{
                                $.messager.show({title:'提示消息',msg:resultObj.message});
                            }
                            //保存成功关闭对话框
                            $("#addBannerDialog").dialog('close');
                            //刷新页面
                            $("#tb").datagrid('reload');
                        }
                    })
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#addBannerDialog").dialog('close');
                }
            }]
        })
    }


    //修改用户
    function updateBanner(id,img){
        $("#updateBannerDialog").dialog({
            href:'${pageContext.request.contextPath}/back/main/banner/update.jsp?id='+id+'&img='+img,
            buttons:[{
                iconCls:'icon-edit',
                text:'修改',
                handler:function(){
                    $("#updateBannerInput").form('submit',{
                        url:'${pageContext.request.contextPath}/banner/update?imgPaths='+img,
                        success:function (result) {//注意一定是json字符串  使用需要转为js对象
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示消息
                                $.messager.show({title:'提示',msg:"用户信息修改成功!!!"});
                            }else{
                                $.messager.show({title:'提示消息',msg:resultObj.message});
                            }
                            //关闭dialog
                            $("#updateBannerDialog").dialog('close');
                            //刷新datagrid
                            $("#tb").datagrid('reload');//刷新当前datagrid
                        }
                    })
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#updateBannerDialog").dialog('close');
                }
            }]
        });
    }


    //批量删除
    function delBannerAll() {
        var rows = $("#tb").datagrid('getSelections');
        if(rows.length<=0){
            $.messager.show({title:'提示消息',msg:'请选择要删除的数据'});
        }else{
            var ids=[];
            for (var i = 0; i < rows.length ; i++) {
                ids.push(rows[i].id);
            }
            $.ajax({
                url:'${pageContext.request.contextPath}/banner/delAll',
                type:'post',
                traditional:true,
                data:{ids:ids},
                success:function(result){
                    //消息提示
                    $.messager.show({title:'提示消息',msg:'删除成功'});
                    //刷新页面‘
                    $("#tb").datagrid('reload');
                },
                error:function(){
                    //消息提示
                    $.messager.show({title:'提示消息',msg:'删除失败'});
                    //刷新datagrid
                    $("#tb").datagrid('reload');
                }
            })
        }
    }
</script>

<%--表格--%>
<table id="tb"></table>

<%--工具栏--%>
<div id="tbarBanner">
    <a href="javascript:;" onclick="addBanner();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">上传</a>
    <a href="javascript:;" onclick="delBannerAll();" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true">批量删除</a>
</div>

<div id="addBannerDialog" data-options="href:'${pageContext.request.contextPath}/back/main/banner/add.jsp',iconCls:'icon-table_save',width:600,height:400,title:'保存用户信息'"></div>

<div id="updateBannerDialog" data-options="iconCls:'icon-edit',width:600,height:400,title:'修改用户信息'"></div>




