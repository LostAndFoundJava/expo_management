angular.module('app')
    .factory('VisaService', VisaService);

/** @ngInject */
function VisaService($resource) {

    var errorData = {
        code: false,
        errorCode: "出错了"
    };


    function getCountries(params,callback) {
        let url = basePath + '/visa/countries';

        var rest = $resource(url, {continent :'@id'});
        rest.get({continent :params}, function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    function getVisaContent(params,callback) {
        let url = basePath + '/visa/countries/:countryId';

        var rest = $resource(url, {countryId :'@id'});
        rest.get({countryId :params}, function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }


    return {
        getCountries: getCountries,
        getVisaContent: getVisaContent
    };


}

