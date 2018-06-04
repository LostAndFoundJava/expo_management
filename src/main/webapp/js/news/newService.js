
angular.module('app')
    .factory('NewsService', NewsService);

/** @ngInject */
function NewsService($resource) {


    var errorData = {
        code: false,
        errorCode: "出错了"
    };

    function getAllNews(param, callback) {
        let url = basePath + '/news';
        var rest = $resource(url, {page : '@page'});
        rest.get({page : param}, function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    function getNewsCategory(callback) {
        let url = basePath + '/news/categories';
        var rest = $resource(url, {});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    function getNewsByNewsCategory(category, page, callback) {
        let url = basePath + '/news/sortedNews';
        var rest = $resource(url, {'newsCategory' : category, 'page': page});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    function getNewsContent(param, callback) {
        let url = basePath + '/news/:id';
        var rest = $resource(url, {'id' : param});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    function getTopNews(topNum, callback) {
        let url = basePath + '/news/clicks';
        var rest = $resource(url, {'top' : topNum});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    return {
        getAllNews: getAllNews,
        getNewsCategory : getNewsCategory,
        getNewsByNewsCategory: getNewsByNewsCategory,
        getNewsContent: getNewsContent,
        getTopNews: getTopNews,
    };
}

