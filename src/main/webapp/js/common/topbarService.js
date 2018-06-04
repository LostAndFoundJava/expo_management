
angular.module('app')
    .factory('TopbarService', TopbarService);

/** @ngInject */
function TopbarService($resource) {

    var errorData = {
        code: false,
        errorCode: "出错了"
    };


    let selectedAboutusChoice = null;
    let selectedContinent = null;
    let allAboutLength = null;

    let setAllAboutLength = function (l) {
        allAboutLength = l;
    }

    let getAllAboutLength = function () {
        return allAboutLength;
    }

    let setSelectedContinent = function (conti) {
        selectedContinent = conti;
    };

    let getSelectedContinent = function () {
        return selectedContinent;
    }

    let setSelectedAboutusChoice = function(newObj) {
        selectedAboutusChoice = newObj;
    };

    let getSelectedAboutusChoice = function(){
        return selectedAboutusChoice;
    };

    let loadAboutUsMenu = function (callback) {
        let empty = [];
        let url = basePath + '/aboutUs/types';
        var rest = $resource(url, {});
        rest.get(function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    return {
        setSelectedAboutusChoice: setSelectedAboutusChoice,
        getSelectedAboutusChoice: getSelectedAboutusChoice,
        loadAboutUsMenu:loadAboutUsMenu,
        setSelectedContinent: setSelectedContinent,
        getSelectedContinent: getSelectedContinent,
        getAllAboutLength :getAllAboutLength,
        setAllAboutLength : setAllAboutLength,
    };
}

