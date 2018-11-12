<%@page pageEncoding="utf-8" isELIgnored="false" %>
<script>
    $(function(){
        $("#updateBannerInput").form('load','${pageContext.request.contextPath}/banner/findOne?id=${param.id}');
    })
</script>

<div style="text-align: center">
    <form id="updateBannerInput" enctype="multipart/form-data" method="post" class="easyui-form">
        <input type="hidden" name="id" value="${param.id}">
        <div style="margin-top: 50px">
        图片标题：<input name="title" type="text"  class="easyui-textbox" data-options="prompt:'请输入图片标题...'"/>
         </div>

        <div style="margin-top: 10px">
            图片路径：<input name="img" value="${param.img}"   type="text" class="easyui-filebox" data-options="buttonText:'请选择文件'" style="width:300px">
        </div>

        <div style="margin-top: 10px">
            状态：<input name="status" type="text" class="easyui-textbox" data-options="prompt:'请输入状态...'"/>
        </div>

        <div style="margin-top: 10px">
            描述：<input name="descs" type="text" class="easyui-textbox" data-options="prompt:'请输入图片描述...'"/>
        </div>

        <div style="margin-top: 10px">
            上传日期：<input name="createTime" type="text" class="easyui-datetimebox" data-options="required:true,prompt:'请输入上传日期...'"/>
        </div>

    </form>
</div>


