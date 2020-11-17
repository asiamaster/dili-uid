<script>
    /**
     *
     * @Date 2020-2-25
     * @author asiamaster
     *
     ***/

    /*********************变量定义区 begin*************/
    //行索引计数器
    //如 let itemIndex = 0;
    let _grid = $('#grid');
    let _form = $('#_form');
    let _modal = $('#_modal');
    let _executeModal = $('#_executeModal');

    /*********************变量定义区 end***************/


    /******************************驱动执行区 begin***************************/
    $(function () {
        $(window).resize(function () {
            _grid.bootstrapTable('resetView')
        });
        queryDataHandler();
        document.addEventListener("keyup",getKey,false);
    });
    /******************************驱动执行区 end****************************/

    /*****************************************函数区 begin************************************/

    //全局按键事件
    function getKey(e){
        e = e || window.event;
        var keycode = e.which ? e.which : e.keyCode;
        //如果按下ctrl+alt+r，弹出快捷执行窗口
        if(e.ctrlKey && e.altKey && keycode == 82){
            openExecuteHandler();
        }
    }

    /**
     * 打开新增窗口
     */
    function openInsertHandler() {
        _form[0].reset();
        $("#_id").val("");
        $('#_type').attr('disabled', false);
        $('#_dateFormat').attr('disabled', false);
        $('#_length').attr('disabled', false);
        _modal.modal('show');
        _modal.find('.modal-title').text('编号规则新增');
    }

    /**
     * 打开修改窗口
     */
    function openUpdateHandler(row) {
        if(!row){
            //获取选中行的数据
            let rows = _grid.bootstrapTable('getSelections');
            if (null == rows || rows.length == 0) {
                bs4pop.alert('请选中一条数据');
                return;
            }
            row = rows[0];
        }
        $('#_type').attr('readonly', true);
        $('#_dateFormat').attr('readonly', true);
        // $('#_length').attr('readonly', true);
        _modal.modal('show');
        _modal.find('.modal-title').text('编号规则修改');
        let formData = $.extend({}, row);
        formData = bui.util.addKeyStartWith(bui.util.getOriginalData(formData), "_");
        bui.util.loadFormData(formData);
    }

    /**
     * 打开编号工具对话框
     */
    function openExecuteHandler() {
        //获取选中行的数据
        let rows = _grid.bootstrapTable('getSelections');
        if (null == rows || rows.length == 0) {
            bs4pop.alert('请选中一条数据');
            return;
        }
        $("#_executeType").val(rows[0].type);
        let _url = "${contextPath}/bizNumber/getValue.action";
        $.ajax({
            type: "POST",
            url: _url,
            data: {"type":rows[0].type},
            processData: true,
            dataType: "text",
            async: true,
            success: function (data) {
                $("#_currentValue").val(data);
            },
            error: function (a, b, c) {
                bs4pop.alert('远程访问失败', {type: 'error'});
            }
        });
        _executeModal.modal('show');
        _executeModal.find('.modal-title').text('编号工具');
    }

    /**
     * 获取下一编号获取
     */
    function getNextValueHandler() {
        let _url = "${contextPath}/api/bizNumber";
        $.ajax({
            type: "POST",
            url: _url,
            data: {"type":$("#_executeType").val()},
            processData: true,
            dataType: "json",
            async: true,
            success: function (output) {
                if (output.success) {
                    $("#_nextValue").val(output.data);
                } else {
                    bs4pop.alert(output.message, {type: 'error'});
                }
            },
            error: function (a, b, c) {
                bui.loading.hide();
                bs4pop.alert('远程访问失败', {type: 'error'});
            }
        });
    }

    /**
     *  保存及更新表单数据
     */
    function saveOrUpdateHandler() {
        if (_form.validate().form() != true) {
            return;
        }
        bui.loading.show('努力提交中，请稍候。。。');
        let _formData = bui.util.removeKeyStartWith(_form.serializeObject(true), "_");
        let _url = null;
        //没有id就新增
        if (_formData.id == null || _formData.id == "") {
            _url = "${contextPath}/bizNumberRule/insert.action";
        } else {//有id就修改
            _url = "${contextPath}/bizNumberRule/update.action";
        }
        $.ajax({
            type: "POST",
            url: _url,
            data: _formData,
            processData: true,
            dataType: "json",
            async: true,
            success: function (data) {
                bui.loading.hide();
                if (data.success) {
                    _grid.bootstrapTable('refresh');
                    _modal.modal('hide');
                } else {
                    bs4pop.alert(data.result, {type: 'error'});
                }
            },
            error: function (a, b, c) {
                bui.loading.hide();
                bs4pop.alert('远程访问失败', {type: 'error'});
            }
        });
    }

    /**
     * 禁启用操作
     * @param enable 是否启用:true-启用
     */
    function doEnableHandler(enable) {
        //获取选中行的数据
        let rows = _grid.bootstrapTable('getSelections');
        if (null == rows || rows.length == 0) {
            bs4pop.alert('请选中一条数据');
            return;
        }

        //table选择模式是单选时可用
        let selectedRow = rows[0];
        let msg = (enable || 'true' == enable) ? '确定要启用吗？' : '确定要禁用吗？';
        bs4pop.confirm(msg, undefined, function (sure) {
            if(sure){
                bui.loading.show('努力提交中，请稍候。。。');
                $.ajax({
                    type: "POST",
                    url: "${contextPath}/bizNumberRule/doEnable.action",
                    data: {id: selectedRow.id, enable: enable},
                    processData:true,
                    dataType: "json",
                    async : true,
                    success : function(data) {
                        bui.loading.hide();
                        if(data.success){
                            _grid.bootstrapTable('refresh');
                            _modal.modal('hide');
                        }else{
                            bs4pop.alert(data.result, {type: 'error'});
                        }
                    },
                    error : function() {
                        bui.loading.hide();
                        bs4pop.alert('远程访问失败', {type: 'error'});
                    }
                });
            }
        })
    }

    /**
     * 删除操作
     */
    function doDeleteHandler() {
        //获取选中行的数据
        let rows = _grid.bootstrapTable('getSelections');
        if (null == rows || rows.length == 0) {
            bs4pop.alert('请选中一条数据');
            return;
        }

        //table选择模式是单选时可用
        let selectedRow = rows[0];
        let msg = '将同步删除biz_number中对应的数据，确定要删除吗？';

        bs4pop.confirm(msg, undefined, function (sure) {
            if(sure){
                bui.loading.show('努力提交中，请稍候。。。');
                $.ajax({
                    type: "POST",
                    url: "${contextPath}/bizNumberRule/delete.action",
                    data: {id: selectedRow.id},
                    processData:true,
                    dataType: "json",
                    async : true,
                    success : function(data) {
                        bui.loading.hide();
                        if(data.success){
                            _grid.bootstrapTable('refresh');
                            _modal.modal('hide');
                        }else{
                            bs4pop.alert(data.result, {type: 'error'});
                        }
                    },
                    error : function() {
                        bui.loading.hide();
                        bs4pop.alert('远程访问失败', {type: 'error'});
                    }
                });
            }
        })
    }

    /**
     * 查询处理
     */
    function queryDataHandler() {
        _grid.bootstrapTable('refreshOptions', {url: '/bizNumberRule/listPage.action'});
    }

    /**
     * table参数组装
     * 可修改queryParams向服务器发送其余的参数
     * @param params
     */
    function queryParams(params) {
        let temp = {
            rows: params.limit,   //页面大小
            page: ((params.offset / params.limit) + 1) || 1, //页码
            sort: params.sort,
            order: params.order
        }
        return $.extend(temp, bui.util.bindGridMeta2Form('grid', 'queryForm'));
    }

    /*****************************************函数区 end**************************************/

    /*****************************************自定义事件区 begin************************************/
    //表单弹框关闭事件
    _modal.on('hidden.bs.modal', function () {
        _form[0].reset();
        //重置表单验证到初始状态
        $(this).find('input,select,textarea').removeClass('is-invalid is-valid');
        $(this).find('input,select,textarea').removeAttr('disabled readonly');
        $(this).find('.invalid-feedback').css('display','none');
    });

    _grid.on('dbl-click-cell.bs.table', function ($element, field, value, row) {
        openUpdateHandler(row);
    });

    // _grid.on('expand-row.bs.table', function (e,index, row, $detail){
    //     var cur_table = $detail.html(template('subTable',{})).find('table');
    //     $(cur_table).bootstrapTable();
    //     $(cur_table).bootstrapTable('refreshOptions', {url: '/bizNumberRule/listPage.action'});
    // });

    /*****************************************自定义事件区 end**************************************/
</script>