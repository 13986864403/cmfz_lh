<%@page pageEncoding="utf-8" isELIgnored="false" %>
<script >
    $(function(){
        $("#albumselect").combobox({
            editable: false,// 不能直接输入到列表框
            missingMesage:'请选择',
            multiple:false,// 设置下拉框的值可以多选
            url:'${pageContext.request.contextPath}/album/queryAll',
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
    <form enctype="multipart/form-data" id="addAudioInput"  method="post" class="easyui-form">
        <div style="margin-top: 10px">
            章节名：<input name="name" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入章节名...'"/>
        </div>

        <div style="margin-top: 10px">
            下载路径：<input name="imgPaths" class="easyui-filebox" data-options="required:true,buttonText:'请选择路径'" style="width:300px">
        </div>

        <div style="margin-top: 10px">
            专辑名：<input id="albumselect" class="easyui-combobox" name="album.id" />
        </div>



    </form>
</div>



