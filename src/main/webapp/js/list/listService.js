angular.module('app')
    .factory('ListService', ListService);

/** @ngInject */
function ListService($resource) {

    var api = {};

    let errorData = {
        code: false,
        errorCode: "出错了"
    };

    api.getAllCategory = function (callback) {
        let categoryUrl = basePath + '/categories';
        let rest = $resource(categoryUrl, {});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    };

    api.expoService = function () {
        let expoUrl = basePath + '/expos';
        let rest = $resource(expoUrl , {}, {
            query: {method:'GET', params:{page: 1}, isArray:false},
            queryByCondition:{method:'GET', params:{ page : 1}}
        });

        function getExpoes(param,callback){
            rest.query({page : param},function (data) {
                callback(data);
            }, function (error) {
                callback(errorData);
            })
        }

        function getExpoesByCondition(params, callback) {
            rest.queryByCondition(params, function (data) {
                callback(data);
            }, function (error) {
                callback(errorData);
            })
        }

        function getHotExpos(callback) {
            let expoUrl = basePath + '/expos/hot';
            let rest = $resource(expoUrl, {});
            rest.get(function (data) {
                callback(data);
            }, function (error) {
                callback(errorData);
            })
        }

        function getHotExposByCategoryId(category, callback) {
            let expoUrl = basePath + '/expos/hotExpos';
            let rest = $resource(expoUrl, {categoryId : '@category' });
            rest.get({categoryId : category }, function (data) {
                callback(data);
            }, function (error) {
                callback(errorData);
            })
        }

        function getTopExpos(num, callback) {
            let expoUrl = basePath + '/expos/clicks';
            let rest = $resource(expoUrl, {top : '@top'});
            rest.get({top : num},function (data) {
                callback(data);
            }, function (error) {
                callback(errorData);
            })
        }

        return {
            getExpoes : getExpoes,
            getExpoesByCondition : getExpoesByCondition,
            getHotExpos : getHotExpos,
            getTopExpos : getTopExpos,
            getHotExposByCategoryId : getHotExposByCategoryId,
        }
    };



    api.countryService = function () {
        let categoryUrl = basePath + '/countries';
        let rest = $resource(categoryUrl , {}, {
            query: {method:'GET', params:{}, isArray:false},
            queryByContinent:{method:'GET', params:{continent:'0'}}
        });

        function getCountries(callback) {
            rest.query(function (data) {
                callback(data);
            }, function (error) {
                callback(errorData);
            })
        }


        function getCountryByParams(params, callback) {
            rest.queryByContinent({continent: params}, function (data) {
                callback(data);
            }, function (error) {
                callback(errorData);
            })
        }

        return {
            getCountries : getCountries,
            getCountryByParams : getCountryByParams
        }
    }

    return api;
}

