app.controller("baseController",function ($scope) {
    $scope.paginationConf = {
        //当前页
        currentPage: 1,
        //总记录数
        totalItems: 10,
        //每页查询的记录数
        itemsPerPage: 10,
        //分页选项，用于选择每页显示多少条记录
        perPageOptions: [10, 20, 30, 40, 50],
        //当页码变更后触发的函数
        onChange: function(){
            $scope.reloadList();//重新加载
        }
    };

//调起分页查询
    $scope.reloadList=function () {
        //$scope.findPage( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }

//选中的id列表
    $scope.selectIds=[];
    /**
     * 更新选中的id列表
     * @param $event
     * @param id
     */
    $scope.updateSelection=function ($event,id) {
        //如果已选中
        if($event.target.checked){
            $scope.selectIds.push(id);
        }else{
            //找出当前id所对应的下标
            var idx = $scope.selectIds.indexOf(id);
            //从选中的id列表中移除当前id
            //参数一：移除的位置，参数二：移除的个数
            $scope.selectIds.splice(idx,1);
        }
    }

    /**
     * 格式化输出json串
     * @param jsonStr 原来的json字符串
     * @param key 格式化输出的字段
     */
    $scope.jsonToString=function (jsonStr,key) {
        var result = "";
        var jsonObj = JSON.parse(jsonStr);
        for(var i = 0; i < jsonObj.length; i++){
            if(i > 0){
                result += ","
            }
            result += jsonObj[i][key];
        }
        return result;
    }
});