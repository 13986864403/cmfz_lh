<%@page pageEncoding="utf-8" isELIgnored="false" %>
<script>
        $(function(){
            $("#addAudioSelectInput").form('load','${pageContext.request.contextPath}/album/findOne?id=${param.id}');
        })
</script>
<div style="text-align: center">
    <form enctype="multipart/form-data" id="addAudioSelectInput"  method="post" class="easyui-form">

        <input type="hidden" name="album.id" value="${param.id}"/>
        <div style="margin-top: 10px">
            章节名：<input name="names" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入章节名...'"/>
        </div>

        <div style="margin-top: 10px">
            下载路径：<input name="imgPath" class="easyui-filebox" data-options="required:true,buttonText:'请选择路径'" style="width:300px">
        </div>

    </form>
</div>


