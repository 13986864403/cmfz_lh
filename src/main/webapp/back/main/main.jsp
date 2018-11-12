<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/themes/IconExtension.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.etree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>


    <script type="text/javascript">
    $(function(){
        //强制登陆
        var username="${sessionScope.admin.username}";
          if(username==""){
             location.href="${pageContext.request.contextPath}/back/login.jsp";
            }
        $.post("${pageContext.request.contextPath}/menu/findAll",function(result){
            //遍历一级菜单
            $.each(result,function(index,menu){
                //遍历二级菜单
                content="<div style='text-align: center'>"
                $.each(menu.menus,function(idx,child){
                    content+= "<a onclick=\"addTabs('"+child.title+"','"+child.logo+"','"+child.href+"')\" style='width:90%;margin:10px 0px;border:1px solid #95B8E7 ;' class='easyui-linkbutton' data-options=\"plain:true,iconCls:'"+child.logo+"'\">"+child.title+"</a><br>"
                });
                content+="</div>"
                $("#menu").accordion('add',{
                    title:menu.title,
                    content:content,
                    logo:menu.logo,
                });
            });
        });
    });

    //单击菜单追加选项卡
    function addTabs(title,logo,href){
        //不能让用户一直添加选项卡，首先判断选项卡是否存在
        if(!$("#tabs").tabs('exists',title)){
            $("#tabs").tabs('add',{
                title:title,
                iconCls:logo,
                closable:true,
                href:"${pageContext.request.contextPath}/"+href,
            })
        }else{
            //如果存在这个选项卡，则选中
            $("#tabs").tabs('select',title);
        }
    }

    //修改管理员密码
    function updateAdminPassword(id){
        $("#updateAdminDialog").dialog({
            href:'${pageContext.request.contextPath}/back/updatePassword.jsp',
            buttons:[{
                iconCls:'icon-save',
                text:'修改',
                handler:function(){
                    $("#updatePasswordInput").form('submit',{
                        url:'${pageContext.request.contextPath}/admin/update',
                        success:function (result) {//注意一定是json字符串  使用需要转为js对象
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示消息
                                $.messager.show({title:'提示',msg:resultObj.message,timeout:3000});
                                //延迟跳转页面
                                window.setTimeout(function(){
                                    //修改成功之后跳转到登录页面重新登陆
                                    location.href='${pageContext.request.contextPath}/admin/loginOut';
                                },3000)
                            }else{
                                $.messager.show({title:'提示消息',msg:resultObj.message})
                            }
                        }
                    })
                }
            },{
                iconCls:'icon-cancel',
                text:'关闭',
                handler:function(){
                    $("#updateAdminDialog").dialog('close');
                }
            }]
        });
    }




    //安全退出
    function adminLoginOut() {
        $.messager.confirm('确认','您真的要离开吗？',function(r){
            if(r){
                location.href="${pageContext.request.contextPath}/admin/loginOut";
                return true;
            }else{
                return false;
            }
        })
    }

</script>
</head>
<body class="easyui-layout" id="body">
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.admin.username} &nbsp;<a href="#" onclick="updateAdminPassword(${sessionScope.admin.id})" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="adminLoginOut()" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>


    <!--菜手风琴单，左侧位置-->
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="menu" class="easyui-accordion" data-options="fit:true">

		</div>  
    </div>


    <div data-options="region:'center'">
    	<div id="tabs" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>



		</div>  
    </div>

<%--修改管理员密码--%>
    <div id="updateAdminDialog" data-options="iconCls:'icon-edit',width:600,height:400,title:'修改密码'"></div>
</body> 
</html>