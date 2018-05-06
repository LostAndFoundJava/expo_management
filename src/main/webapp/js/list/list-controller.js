angular.module('app').
controller('ListController',['$rootScope', '$scope', '$state', 'ListService', '$window', '$stateParams', '$uibModal',
    function ($rootScope, $scope, $state, ListService, $window, $stateParams, $uibModal) {
    $scope.indexBar = [{title : '全部', params : 'all'}];

    $scope.mobile = {
        selectedCondition : 'category'
    };

    let indexObj = {
        title : '',
        params : '',
    };

    let expoTransObj = {
        country : undefined,
        categories : undefined,
        date : undefined,
        page : 1
    };

    for (let i = 0; i < alphabet.length; i++) {
        let tmp = angular.copy(indexObj);
        tmp.title = alphabet[i];
        tmp.params = alphabet[i];
        $scope.indexBar.push(tmp);
    }

    let pageInfo = {
        last : false,
        pageNum : 1,
        pageSize : 0,
        totalNum : 0,
    };

    $scope.list = {pageInfo : pageInfo};
    $scope.list.condition = {};
    $scope.list.dateCollapse = true;
    $scope.list.countryCollapse = true;

        $scope.getTops = function () {
            ListService.expoService().getTopExpos(3, function (data) {
                if (data.code) {
                    $scope.list.topExpos = data.result;
                } else {
                    $scope.list.topExpos = [];
                }
            })

        };

    init();

    function init() {
        //加载日期
        $scope.list.dateList = getDateObjList(36);

        //加载国家
        if ($state.is('list.condition.params')) {
            loadConditionList();
            return;
        }

        ListService.countryService().getCountries(function (data) {
            if (data.code) {
                $scope.list.country = data.result;
                $scope.list.countryBackup = angular.copy(data.result);
                const resultList = {};
                data.result.forEach(item => {
                    const pinyin = item.pinyin;
                    if (pinyin) {
                        let letter = item.pinyin[0].toUpperCase();
                        if (!letter.match(/[A-Z]/)) {
                            letter = '*';
                        }

                        if (resultList.hasOwnProperty(letter)) {
                            resultList[letter].push(item);
                        } else {
                            resultList[letter]= [item];
                        }
                    }
                });
                $scope.list.countryObj = resultList;
            }
        });

        //加载行业
        ListService.getAllCategory(function (data) {
            if (data.code) {
                $scope.list.category = data.result;
            }
        });



        if ($state.is('list.condition.hot')) {
            if ($stateParams.hot) {
                ListService.expoService().getHotExpos(function (data) {
                    if (data.code) {
                        $scope.list.expoes = data.result.content;
                        let tmp = data.result;
                        delete tmp.content;
                        $scope.list.pageInfo = tmp;
                    }
                });
            }
        } else {
            ListService.expoService().getExpoes(1,function (data) {
                if (data.code) {
                    $scope.list.expoes = data.result.content;
                    let tmp = data.result;
                    delete tmp.content;
                    $scope.list.pageInfo = tmp;
                }
            });
        }

        $scope.getTops();
    }

    function loadConditionList(){
        ListService.countryService().getCountries(function (data) {
            if (data.code) {
                $scope.list.country = data.result;
                $scope.list.countryBackup = angular.copy(data.result);

                $scope.list.dateList = getDateObjList(36);
                //加载行业
                ListService.getAllCategory(function (data) {
                    if (data.code) {
                        $scope.list.category = data.result;
                        getConditionFromUrl($scope.list.category, $scope.list.country, $scope.list.dateList);

                        let pagePojo = getSearchCondition($scope.list.condition.country_id, $scope.list.condition.date,
                            $scope.list.condition.category_id, 1);
                        ListService.expoService().getExpoesByCondition(pagePojo, function (data) {
                            if (data.code) {
                                $scope.list.expoes = data.result.content;
                                let tmp = data.result;
                                delete tmp.content;
                                $scope.list.pageInfo = tmp;
                            }
                        })
                    }
                });

                const resultList = {};
                data.result.forEach(item => {
                    const pinyin = item.pinyin;
                    if (pinyin) {
                        let letter = item.pinyin[0].toUpperCase();
                        if (!letter.match(/[A-Z]/)) {
                            letter = '*';
                        }

                        if (resultList.hasOwnProperty(letter)) {
                            resultList[letter].push(item);
                        } else {
                            resultList[letter]= [item];
                        }
                    }
                });
                $scope.list.countryObj = resultList;
            } else {
                $state.go('list.condition');
            }
        });
    }

    function getConditionFromUrl(categoryList,countries,dates) {
        $scope.list.condition = {};
        let country = $stateParams.country_id;
        let category = $stateParams.category_id;
        let date = $stateParams.date;
        if (country) {
            for (let i= 0; i < countries.length; i ++) {
                if (angular.equals(country, countries[i].id + "")) {
                    $scope.list.condition.country_id = {
                        id : countries[i].id,
                        title : countries[i].title,
                    };
                    break;
                }
            }
        }

        if (category) {
            for (let i= 0; i < categoryList.length; i ++) {
                if (angular.equals(category, categoryList[i].id)) {
                    $scope.list.condition.category_id = {
                        id : categoryList[i].id,
                        title : categoryList[i].title,
                    };
                    break;
                }
            }
        }

        if (date) {
            for (let i= 0; i < dates.length; i ++) {
                if (angular.equals(date, dates[i].param)) {
                    $scope.list.condition.date = {
                        id : dates[i].param,
                        title : dates[i].title,
                    };
                    break;
                }
            }
        }
    }



    $scope.inputQuery = function () {
        $('#input-hide').addClass('hid');
        $scope.mobile.isCancel = true;
        $scope.mobile.isReady = false;
    };

    $scope.mobile = {
        query : '',
        isCancel : false,
        isReady : false,
        filterPanel : false,
    };

    $scope.$watch('mobile.query', function (newVal,oldVal) {
        if (newVal) {
            $('#input-remove').removeClass('hid');
            $scope.mobile.isCancel = false;
            $scope.mobile.isReady = true;
        }
    });

    $scope.removeQuery = function () {
        $scope.mobile.query = '';
        $('#input-remove').addClass('hid');
        $scope.mobile.isCancel = true;
        $scope.mobile.isReady = false;
    };

    $scope.cancel = function () {
        $scope.mobile.isCancel = false;
        $scope.mobile.isReady = false;
    };

    $scope.showFilterPanel = function () {
        if (!$scope.mobile.filterPanel) {
            $scope.mobile.filterPanel = true;
            $('#filter-panel').removeClass('hid');
        } else {
            $scope.mobile.filterPanel = false;
            $('#filter-panel').addClass('hid');

        }
    };

    $scope.setQueryParam = function (letter) {
        $scope.list.country = $scope.list.countryObj[letter];
        $scope.countryQuery = "";
    };

    $scope.addCondition = function(obj) {
        if (!obj) {
            return;
        }
        let objKeys = Object.keys(obj);
        let objKey = objKeys[0];
        $scope.list.condition[objKey] = obj[objKey];

        let pagePojo = getSearchCondition($scope.list.condition.country_id, $scope.list.condition.date,
            $scope.list.condition.category_id, 1);
        ListService.expoService().getExpoesByCondition(pagePojo, function (data) {
            if (data.code) {
                $scope.list.expoes = data.result.content;
                let tmp = data.result;
                delete tmp.content;
                $scope.list.pageInfo = tmp;
            }
        })

    };

    function getSearchCondition(country, date, category, num) {
        let t = angular.copy(expoTransObj);
        if (country) {
            t.country = country.id;
        }

        if (date) {
            t.date = date.id;
        }

        if (category) {
            t.categories = category.id;
        }

        if (num) {
            t.page = num;
        } else {
            t.page = 1;
        }

        return t;
    }


    $scope.removeCondition = function () {
        $scope.list.condition = {};
        ListService.expoService().getExpoes(1,function (data) {
            if (data.code) {
                $scope.list.expoes = data.result.content;
                let tmp = data.result;
                delete tmp.content;
                $scope.list.pageInfo = tmp;
            }
        });

    };

    $scope.removeOneCondition = function (key) {
        if ($scope.list.condition.hasOwnProperty(key)) {
            delete $scope.list.condition[key];
        }
        if ($scope.list.condition) {
            let pagePojo = getSearchCondition($scope.list.condition.country_id, $scope.list.condition.date,
                $scope.list.condition.category_id, 1);
            ListService.expoService().getExpoesByCondition(pagePojo, function (data) {
                if (data.code) {
                    $scope.list.expoes = data.result.content;
                    let tmp = data.result;
                    delete tmp.content;
                    $scope.list.pageInfo = tmp;
                }
            })
        } else {
            $scope.removeCondition();
        }
    };

    $scope.getDetail = function (item, isHot) {
        let url = $state.href('detail.view', {id: item.exhibition.id});
        if (parseInt(isHot)) {
            url = $state.href('hot.detail', {id: item.exhibition.id});

        }
        $window.open(url,'_blank');
    };

    $scope.loadMore = function () {
        $scope.list.loadMoreLoading = true;
        if (!$scope.list.condition || !angular.equals({}, $scope.list.condition)) {
            let pagePojo = getSearchCondition($scope.list.condition.country_id, $scope.list.condition.date,
                $scope.list.condition.category_id, $scope.list.pageInfo.pageNum + 1);
            ListService.expoService().getExpoesByCondition(pagePojo, function (data) {
                $scope.list.loadMoreLoading = false;
                if (data.code) {

                    $scope.list.expoes.push.apply($scope.list.expoes,data.result.content);
                    let tmp = data.result;
                    delete tmp.content;
                    $scope.list.pageInfo = tmp;
                }
            })

        } else {
            ListService.expoService().getExpoes($scope.list.pageInfo.pageNum + 1,function (data) {
                $scope.list.loadMoreLoading = false;
                if (data.code) {
                    $scope.list.expoes.push.apply($scope.list.expoes,data.result.content);
                    let tmp = data.result;
                    delete tmp.content;
                    $scope.list.pageInfo = tmp;
                }
            });
        }
    };

    $scope.switchCategory = function (id) {
        $scope.mobile.selectedCondition = id;
    };

    $scope.changeDateCollapse = function () {
        $scope.list.dateCollapse = !$scope.list.dateCollapse;
    };

    $scope.changeCountryCollapse = function () {
        $scope.list.countryCollapse = !$scope.list.countryCollapse;
    };

    $scope.inputClick = function () {
        if (!$scope.countryQuery) {
            $scope.list.country = $scope.list.countryBackup;
        }
    };

    $scope.inputQuery = function() {
        if (!$scope.countryQuery) {
            $scope.list.country = $scope.list.countryBackup;
            return;
        }
        const ans= [];
        angular.forEach($scope.list.countryBackup, function (country) {
            if (country.title.indexOf($scope.countryQuery) > -1) {
                ans.push(country);
            } else if (country.pinyin.indexOf($scope.countryQuery) > -1) {
                ans.push(country);
            }
        });
        $scope.list.country = ans;
    };

    $scope.getTicket = function(expo) {
        let modalInstance = $uibModal.open({
            templateUrl: 'view/common/ticket.html',
            resolve: {
                resource: function () {
                    return {
                        id : expo.exhibition.id,
                        title : expo.exhibition.title,
                        startTime : expo.exhibition.startTime,
                        endTime : expo.exhibition.endTime,
                    };
                }
            },
            controller: 'TicketController',
            size: 'sm',
            keyboard : false,
            backdrop : 'static'
        });
    };



        $scope.imgUrl = function(url) {
            if (!url) {
                return;
            }
            return url + '!357-201'
        }


}]);
