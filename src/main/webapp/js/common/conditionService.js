
angular.module('app')
    .factory('ConditionService', ConditionService);

/** @ngInject */
function ConditionService() {

    let tmpObj = {
        id : '',
        title : '',
    };

    let selectCondition = {};

    let getSelectCondition = function () {
        return selectCondition;
    };

    let setSelectCondition = function (params) {
        selectCondition = {};
        for (let key in params) {
            let t = angular.copy(tmpObj);
            t.id = params[key].id;
            t.title = params[key].title;
            selectCondition.key = t;
        }
    };

    return {
        getSelectCondition : getSelectCondition,
        setSelectCondition : setSelectCondition,
    };
}

