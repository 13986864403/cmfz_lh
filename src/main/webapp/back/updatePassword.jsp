<%@page pageEncoding="utf-8" isELIgnored="false" %>

<script>
    $(function(){
        $("#updatePasswordInput").form('load','${pageContext.request.contextPath}/admin/update?id=${sessionScope.admin.id}');



        $.extend($.fn.validatebox.defaults.rules,{
            confirmPwd:{
                validator:function(value,param){
                    return $(param[0]).val() == value;
                },
                message:"两次输入密码不一致!!!"
            }
        })
    })
   </script>
    <div style="text-align: center">
    <form id="updatePasswordInput" method="post" class="easyui-form">
        <input type="hidden"  name="id" value="${sessionScope.admin.username}">
        <input type="hidden"  name="id" value="${sessionScope.admin.id}">
        <div style="margin-top: 10px">
              原始密码:<input  name="oldPwd" type="password" class="easyui-validatebox" data-options="required:true"/>
        </div>
        <div style="margin-top: 10px">
            新密码：<input type="password" name="password" id="pwd" class="easyui-validatebox" data-options="required:true" >
        </div>
        <div style="margin-top: 10px">
            确认密码: <input type="password"  class="easyui-validatebox" data-options="required:true,validType:'confirmPwd[\'#pwd\']'" >
        </div>

    </form>
</div>


