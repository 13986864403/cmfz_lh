<%@page pageEncoding="utf-8" %>
<div style="text-align: center">
    <form enctype="multipart/form-data"  id="addCourseMasterInput" action="${pageContext.request.contextPath}/course/add" method="post" class="easyui-form">
        <div style="margin-top: 50px">
            课程名：<input name="name" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入课程名...'"/>
        </div>


        <div style="margin-top: 10px">
            请选择状态：<select class="easyui-combobox" name="mark" style="width:200px;" data-options="required:true,prompt:'请选择状态...'">
            <option value="yes">显示</option>
            <option value="no">不显示</option>
        </select>

        </div>
        <div style="margin-top: 10px">
            注册时间：<input name="createTime" type="text" class="easyui-datetimebox" data-options="required:true,prompt:'请输入添加日期...'"/>
        </div>


    </form>
</div>




