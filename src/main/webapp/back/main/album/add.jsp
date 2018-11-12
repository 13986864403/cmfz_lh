<%@page pageEncoding="utf-8"  %>
<div style="text-align: center">
    <form enctype="multipart/form-data" id="addAlbumInput"  method="post" class="easyui-form">
        <div style="margin-top: 10px">
            专辑名：<input name="name" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入专辑名...'"/>
        </div>

        <div style="margin-top: 10px">
            作者：<input name="author" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入作者名...'"/>
        </div>

        <div style="margin-top: 10px">
            播音：<input name="broadcasting" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入播音者...'"/>
        </div>

        <div style="margin-top: 10px">
            评价：
            <select class="easyui-combobox" name="evaluation" style="width:200px;" data-options="required:true,prompt:'请选择评价...'">
                <option value="⭐">⭐</option>
                <option value="⭐⭐">⭐⭐</option>
                <option value="⭐⭐⭐">⭐⭐⭐</option>
                <option value="⭐⭐⭐⭐">⭐⭐⭐⭐</option>
                <option value="⭐⭐⭐⭐⭐">⭐⭐⭐⭐⭐</option>
            </select>
        </div>


        <div style="margin-top: 10px">
            图片：<input name="imgPath" class="easyui-filebox" data-options="required:true,buttonText:'请选择图片'" style="width:300px">
        </div>

        <div style="margin-top: 10px">
            发布时间：<input name="release_time" type="text" class="easyui-datetimebox" data-options="required:true,prompt:'请输入上传日期...'"/>
        </div>

        <div style="margin-top: 10px">
            内容：<input name="content" type="text" class="easyui-textbox" data-options="multiline:true,required:true,prompt:'请输入内容...'"/>
        </div>

    </form>
</div>



