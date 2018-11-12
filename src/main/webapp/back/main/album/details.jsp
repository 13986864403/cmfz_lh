<%@page pageEncoding="utf-8" isELIgnored="false" %>
<script>
        $(function(){
            $("#detailseGuruInput").form('load','${pageContext.request.contextPath}/album/findOne?id=${param.id}');
        })
</script>
<div style="text-align: center">
    <form id="detailseGuruInput" enctype="multipart/form-data" method="post" class="easyui-form">
        <img style="width:100px;height:60px" src="${pageContext.request.contextPath}/back/img/${param.img}">
        <div style="margin-top: 10px">
            专辑名：<input name="name" type="text" class="easyui-textbox" data-options="readonly:'true'"/>
        </div>

        <div style="margin-top: 10px">
            作者：<input name="author" type="text" class="easyui-textbox" data-options="readonly:'true'"/>
        </div>

        <div style="margin-top: 10px">
            播音：<input name="broadcasting" type="text" class="easyui-textbox" data-options="readonly:'true'"/>
        </div>

        <div style="margin-top: 10px">
            评价：
            <select class="easyui-combobox" name="evaluation" style="width:200px;" data-options="readonly:'true'">
                <option value="⭐">⭐</option>
                <option value="⭐⭐">⭐⭐</option>
                <option value="⭐⭐⭐">⭐⭐⭐</option>
                <option value="⭐⭐⭐⭐">⭐⭐⭐⭐</option>
                <option value="⭐⭐⭐⭐⭐">⭐⭐⭐⭐⭐</option>
            </select>
        </div>

        <div style="margin-top: 10px">
            集数：<input name="blues" type="text" class="easyui-numberbox" value="100" data-options="readonly:'true'"/>
        </div>

        <div style="margin-top: 10px">
            发布时间：<input name="release_time" type="text" class="easyui-datetimebox" data-options="readonly:'true'"/>
        </div>

        <div style="margin-top: 10px">
            内容：<input  name="content" type="text" class="easyui-textbox" data-options="multiline:true,readonly:'true'"/>
        </div>
    </form>
</div>



