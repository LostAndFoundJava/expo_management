angular.module('app').
controller('CategoryController',['$rootScope', '$scope', '$state', 'HomeService', 'ListService', '$window', function ($rootScope, $scope, $state, HomeService, ListService, $window) {
    $scope.category = {
        categoryList : {},
        selectedCategory : {},
        selectedId : 0,
        selectedCategoryHotExpos : [],
        slideInterval : 5000,
    };



    $scope.category.dateList = getDateObjList(12);


    let imgBase = 'assets/img/flags/64/';


    init();

    function init() {
        HomeService.getCategory(function (data) {
            if (data.code) {
                angular.forEach(data.result, function (cate, index) {
                    if (index == 0) {
                        $scope.category.selectedId = cate.id;
                    }
                    if (!$scope.category.categoryList.hasOwnProperty(cate.id)) {
                        $scope.category.categoryList[cate.id] = cate;
                    }
                });
                $scope.selectCategory($scope.category.selectedId);
            }
        });


    }


    //处理图片
    $scope.imgPath = function (enName) {
        if (enName == null) {
            return
        }
        let enList = enName.split(/(\s+)/);

        let tmp = [];
        angular.forEach(enList, function (ee) {
            if (ee.trim() != "") {
                tmp.push(ee)
            }
        });

        let en = tmp.join("-");

        return imgBase + en + '.png';
    };




    $scope.selectCategory = function (id) {
        $scope.category.selectedId = id;

        $scope.category.selectedCategory = $scope.category.categoryList[id];

        ListService.expoService().getHotExposByCategoryId(id, function (data) {
            if (data.code) {
                let tmpList = getHotList(data.result);
                $scope.category.selectedCategoryHotExpos = tmpList;
            }
        })

    };
    
    function getHotList(result) {
        let sort = ['carousal', 'hot', 'choice'];
        let t = [];

        angular.forEach(sort, function (x) {
            if (result.hasOwnProperty(x) && result[x].length > 0) {
                t.push.apply(t, result[x]);
            }
        });
       return t;
    }

    $scope.getListCondition = function (params) {
        let state = {};
        if (params.hasOwnProperty('category_id')) {
            state['category_id'] = params.category_id;
        }

        if (params.hasOwnProperty('country_id')) {
            state['country_id' ] = params.country_id;
        }

        if (params.hasOwnProperty('date')) {
            state['date' ] = params.date;
        }


        $state.go('list.condition.params', state, {location: 'replace'});

    };

    const mobile = {
        w : 250,
        h : 158
    };

    $scope.imgUrl = function(imgUrl){
        if (!imgUrl) {
            return null;
        }
        return imgUrl  + '!' + mobile.w + '-' + mobile.h + '.jpeg';

    };


    $scope.getDetail = function (item, isHot) {
        let url = $state.href('detail.view', {id: item.exhibition.id});
        if (parseInt(isHot)) {
            url = $state.href('hot.detail', {id: item.exhibition.id});

        }
        $window.open(url,'_blank');
    };


}])
