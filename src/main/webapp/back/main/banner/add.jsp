<%@page pageEncoding="utf-8" %>
<div style="text-align: center">
    <form enctype="multipart/form-data"  id="addBannerInput" action="${pageContext.request.contextPath}/banner/add" method="post" class="easyui-form">
        <div style="margin-top: 50px">
            图片标题：<input name="title" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入图片标题...'"/>
        </div>

        <div style="margin-top: 10px">
            图片路径：<input name="imgPath" class="easyui-filebox" data-options="required:true,buttonText:'请选择图片'" style="width:300px">
        </div>

        <div style="margin-top: 10px">
            上传时间：<input name="createTime" type="text" class="easyui-datetimebox" data-options="required:true,prompt:'请输入上传日期...'"/>
        </div>
        <div style="margin-top: 10px">
            状态：
            <select class="easyui-combobox" name="status" style="width:200px;">
                <option value="show">正常展示</option>
                <option value="freeze">其他</option>
            </select>

        </div>

        <div style="margin-top: 10px">
            描述：<input name="descs" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入描述...'"/>

        </div>

    </form>


</div>



