
angular.module('app')
    .factory('DetailService', DetailService);

/** @ngInject */
function DetailService($resource) {
    let url = basePath + '/expos/detail/:id';
    var rest = $resource(url, {id :'@id'});

    var errorData = {
        code: false,
        errorCode: "出错了"
    };

    function getExpoDetail(params, callback) {
        rest.get({id : params},function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }
    return {
        getExpoDetail: getExpoDetail,
    };
}

