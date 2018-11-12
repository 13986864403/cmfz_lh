<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script>
    $(function(){
        $('#tbtreegrid').treegrid({
            url:'${pageContext.request.contextPath}/album/findAlbumByPage',//用来请求远程数据
            pagination:true,//显示分页工具栏
            pageNumber:1,//当前页
            pageSize:30,//每页显示记录数
            pageList:[2,3,5,10,15,30,500],
            striped:true,
            fitColumns:true,
            rownumbers:true,
            singleSelect:true,//只允许选择一行
            ctrlSelect:true,
            remoteSort:false,
            multiSort:true,
            fit:true,
            toolbar:"#tbarContent",
            idField:'id',
            treeField:'name',
            animate:true,
            scrollbarSize:18,//滚动条
            onlyLeafCheck:true,//是否在叶子前显示复选框
            lines:true,
            columns:[[
                {title:"编号",field:"id",width:120},
                {field:'name',title:'专辑名',width:60},
                {field:'author',title:'作者',width:80},
                {field:'broadcasting',title:'播音',width:80},
                {field:'evaluation',title:'评价',width:80},
                {field:'blues',title:'集数',width:80},
                {field:'download_path',title:'下载路径',width:120},
                {field:'size',title:'音频大小',width:80},
                {field:'duration',title:'音频时长',width:80},
            ]],
        });
    });

//下载视频
    function downloadAudio(){
        var roots = $("#tbtreegrid").treegrid("getRoots");//获取所有根节点,专辑
        var select = $("#tbtreegrid").edatagrid("getSelected");//获取选择的节点，章节
        if(select==null){
            //提示消息
            $.messager.show({title:'提示消息',msg:'请选择一张想要下载的音频'});
        }else{
            var index=$.inArray(select,roots);
            if(index==-1){//说明返回的专辑数组里面没有章节
                location.href="${pageContext.request.contextPath}/audio/download?fineName="+select.download_path+"&openStyle=attachment";
            }else{//说明选择了一个专辑，提示选择章节进行下载
                //提示消息
                $.messager.show({title:'提示消息',msg:'请选择想要下载的音频'});
            }
        }
    }




    //添加专辑
    function addAlbum(){
        $("#addAlbumDialog").dialog({
            buttons:[{
                iconCls:'icon-table_save',
                text:'保存',
                handler:function(){
                    $("#addAlbumInput").form('submit',{
                        url:"${pageContext.request.contextPath}/album/add",
                        success:function(result){
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示消息
                                $.messager.show({title:'提示消息',msg:'添加专辑成功'});
                            }else{
                                $.messager.show({title:'提示消息',msg:resultObj.message});
                            }
                            //保存成功关闭对话框
                            $("#addAlbumDialog").dialog('close');
                            console.log("shuaxin")
                            //刷新页面
                            $("#tbtreegrid").treegrid('reload');
                            console.log("hahha")
                        }
                    })
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#addAlbumDialog").dialog('close');
                }
            }]
        });
    }


    //展示专辑详情
       function aldumDetails() {
           var treegrid = $("#tbtreegrid").treegrid('getSelected');

           if(treegrid!=null){
               if(treegrid.size==null){
                   $("#detailsAlbumDialog").dialog({
                       href:'${pageContext.request.contextPath}/back/main/album/details.jsp?id='+treegrid.id+'&img='+treegrid.images,
                   });
               }else{
                   $.messager.show({title:'提示消息',msg:'请选择您想要了解的专辑'});
               }
           }else{
               $.messager.show({title:'提示消息',msg:'请选择您想要了解的专辑'});
           }
       }

    //添加章节
    function  addAudio() {
        var treegrid= $("#tbtreegrid").treegrid('getSelected');
        //判断是否选中专辑进行添加
        if(treegrid!=null){
            if(treegrid.size==null){
                 $("#addAudioDialog").dialog({
                 href:'${pageContext.request.contextPath}/back/main/album/addAudioSelect.jsp?id='+treegrid.id,
                    buttons:[{
                        iconCls:'icon-table_save',
                        text:'添加章节',
                        handler:function(){
                            $("#addAudioSelectInput").form('submit',{
                                url:"${pageContext.request.contextPath}/audio/save",
                                success:function(result){
                                    var resultObj = $.parseJSON(result);
                                    if(resultObj.success){
                                        //提示消息
                                        $.messager.show({title:'提示消息',msg:'添加章节成功'});
                                    }else{
                                        $.messager.show({title:'提示消息',msg:resultObj.message});
                                    }
                                    //保存成功关闭对话框
                                    $("#addAudioDialog").dialog('close');
                                    //刷新页面
                                    $("#tbtreegrid").treegrid('reload');
                                }
                            })
                        }
                    },{
                        iconCls:'icon-cancel',
                        text:'关闭',
                        handler:function(){
                            $("#addAudioDialog").dialog('close');
                        }
                    }]
                });
            }
        } else{
            $("#addAudioDialog").dialog({
                href:'${pageContext.request.contextPath}/back/main/album/addAudio.jsp',
                buttons:[{
                    iconCls:'icon-table_save',
                    text:'添加章节',
                    handler:function(){
                        $("#addAudioInput").form('submit',{
                            url:"${pageContext.request.contextPath}/audio/add",
                            success:function(result){
                                var resultObj = $.parseJSON(result);
                                if(resultObj.success){
                                    //提示消息
                                    $.messager.show({title:'提示消息',msg:'添加章节成功'});
                                }else{
                                    $.messager.show({title:'提示消息',msg:resultObj.message});
                                }
                                //保存成功关闭对话框
                                $("#addAudioDialog").dialog('close');
                                //刷新页面
                                $("#tbtreegrid").treegrid('reload');
                            }
                        })
                    }
                },{
                    iconCls:'icon-cancel',
                    text:'关闭',
                    handler:function(){
                        $("#addAudioDialog").dialog('close');
                    }
                }]
            });
            }
    }
</script>

<%--表格--%>
<table id="tbtreegrid"></table>
<%--工具栏--%>
<div id="tbarContent">
    <a href="javascript:;" onclick="addAlbum();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加专辑</a>
    <a href="javascript:;" onclick="addAudio();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加章节</a>
    <a href="javascript:;" onclick="aldumDetails();" class="easyui-linkbutton" data-options="iconCls:'icon-application_form',plain:true">专辑详情</a>
    <a href="javascript:;" onclick="downloadAudio();" class="easyui-linkbutton" data-options="iconCls:'icon-arrow_down',plain:true">下载音频</a>
</div>

<div id="addAlbumDialog" data-options="href:'${pageContext.request.contextPath}/back/main/album/add.jsp',iconCls:'icon-table_save',width:600,height:400,title:'添加专辑信息'"></div>
<div id="addAudioDialog" data-options="iconCls:'icon-table_save',width:600,height:400,title:'添加章节信息'"></div>
<div id="detailsAlbumDialog" data-options="iconCls:'icon-edit',width:600,height:400,title:'展示专辑详情'"></div>
