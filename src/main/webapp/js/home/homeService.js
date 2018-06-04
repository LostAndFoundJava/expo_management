
angular.module('app')
    .factory('HomeService', HomeService);

/** @ngInject */
function HomeService($resource) {


    var errorData = {
        code: false,
        errorCode: "出错了"
    };

    function getCategory(callback) {
        let url = basePath + '/home/categories';
        var rest = $resource(url, {});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    function getExpos(callback) {
        let url = basePath + '/home/exhibition';
        var rest = $resource(url, {});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    return {
        getCategory: getCategory,
        getExpos : getExpos
    };
}

