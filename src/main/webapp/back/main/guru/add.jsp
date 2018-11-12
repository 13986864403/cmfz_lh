<%@page pageEncoding="utf-8" %>
<div style="text-align: center">
    <form enctype="multipart/form-data"  id="addGuruInput" action="${pageContext.request.contextPath}/guru/add" method="post" class="easyui-form">
        <div style="margin-top: 50px">
            法名：<input name="name" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入上师法名...'"/>
        </div>

        <div style="margin-top: 10px">
            上师头像：<input name="imgPath" class="easyui-filebox" data-options="required:true,buttonText:'请选择图片'" style="width:300px">
        </div>

        <div style="margin-top: 10px">
            性别：<select class="easyui-combobox" name="gender" style="width:200px;" data-options="required:true,prompt:'请选择性别...'">
            <option value="男">男</option>
            <option value="女">女</option>
        </select>

        </div>
        <div style="margin-top: 10px">
            注册时间：<input name="createTime" type="text" class="easyui-datetimebox" data-options="required:true,prompt:'请输入上传日期...'"/>
        </div>

        <div style="margin-top: 10px">
            状态：
            <select class="easyui-combobox" name="status" style="width:200px;">
                <option value="show">正常展示</option>
                <option value="freeze">其他</option>
            </select>
        </div>



    </form>
</div>



