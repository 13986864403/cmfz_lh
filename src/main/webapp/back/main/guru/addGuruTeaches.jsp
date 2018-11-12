<%@page pageEncoding="utf-8" isELIgnored="false" %>

<script >
$(function(){
    $("#select").combobox({
     editable: false,// 不能直接输入到列表框
     missingMesage:'请选择',
     multiple:false,// 设置下拉框的值可以多选
    url:'${pageContext.request.contextPath}/guru/queryAll',
     valueField:'id',
     textField:'name',
     type:"post",
     onShowPanel: function () {
         var v = $(this).combobox('panel')[0].childElementCount;
         // 判断下拉框高度（如果下拉框的数值小于10个，那么下拉框高度自动显示，如果大于10个，下拉框高度最高200）
         if (v <= 10) {
             $(this).combobox('panel')
                 .height("auto");
         } else {
             $(this).combobox('panel')
                 .height(200);
         }
     },
     onLoadSuccess: function (res) {
         // 下拉框默认选择第一项
         if (res) {
             var val = $(this).combobox('getData');
             $(this).combobox('select',val[0]['code_text']); //这个数据根据自己情况来设定
         }
     }
 });
})

</script>

<div style="text-align: center">
    <form enctype="multipart/form-data"  id="addGuruTeachesInput" method="post" class="easyui-form">
        <div style="margin-top: 50px">
            文章名：<input name="title" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入文章名...'"/>
        </div>

        <div style="margin-top: 10px">
            发布时间：<input name="publishDate" type="text" class="easyui-datetimebox" data-options="required:true,prompt:'请选择发布日期...'"/>
        </div>

        <div style="margin-top: 50px">
            文章内容：<input name="content" type="text" class="easyui-textbox" data-options="multiline:true,required:true,prompt:'请输入文章内容...'"/>
        </div>

        <div style="margin-top: 10px">
            文章插图：<input name="img" class="easyui-filebox" data-options="required:true,buttonText:'请选择图片'" style="width:300px">
        </div>

        <div style="margin-top: 10px">
            请选择上师：
            <input id="select" class="easyui-combobox" name="guru.id" />
        </div>

            <div style="margin-top: 10px">
            请选择分类：
            <select class="easyui-combobox" name="difference" style="width:200px;" data-options="required:true,prompt:'请选择显示分类...'">
                <option value="上师言教">上师言教</option>
                <option value="显密法要">显密法要</option>
            </select>

         <div style="margin-top: 10px">
            请选择显示状态：
             <select class="easyui-combobox" name="shows" style="width:200px;" data-options="required:true,prompt:'请选择是否显示...'">
                <option value="yes">正常显示</option>
                <option value="no">冻结状态</option>
            </select>
        </div>


        <div style="margin-top: 10px">
            状态：
            <select class="easyui-combobox" name="status" style="width:200px;">
                <option value="show">正常展示</option>
                <option value="freeze">其他</option>
            </select>
        </div>

            </div>
    </form>
</div>



