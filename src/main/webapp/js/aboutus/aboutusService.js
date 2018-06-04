
angular.module('app')
    .factory('AboutusService', AboutusService);

/** @ngInject */
function AboutusService($resource) {


    var errorData = {
        code: false,
        errorCode: "出错了"
    };

    function getAboutUsInfo(callback) {
        let url = basePath + '/aboutUs';
        var rest = $resource(url, {});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    function getLinks(callback) {
        let url = basePath + '/link';
        var rest = $resource(url, {});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }


    return {
        getAboutUsInfo: getAboutUsInfo,
        getLinks :getLinks,
    };
}

