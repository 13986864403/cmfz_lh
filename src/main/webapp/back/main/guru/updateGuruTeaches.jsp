<%@page pageEncoding="utf-8" isELIgnored="false" %>


<div style="text-align: center">
    <form enctype="multipart/form-data"  id="updateGuruTeachesInput" method="post" class="easyui-form">

           <input name="id" type="hidden" value="${param.id}">
            <div style="margin-top: 10px">
                请选择状态：
                <select value="${param.shows}" class="easyui-combobox" name="shows" style="width:200px;">
                    <option value="yes">正常展示</option>
                    <option value="no">冻结</option>
                </select>
            </div>


    </form>
</div>



