angular.module('app').
controller('AboutusController',['$rootScope', '$scope', '$state','AboutusService', 'TopbarService', 'pageInfoService',
    function ($rootScope, $scope, $state, AboutusService, TopbarService, pageInfoService) {
        pageInfoService.setTitle("关于我们");
        $scope.aboutus = {
            content : {},
            selected : {},
        };

        let initId = '';

        init();

        $scope.photo = '/app/assets/test-img/51-logo.png';

        function init(){
            AboutusService.getAboutUsInfo(function (data) {
                if (data.code) {
                    angular.forEach(data.result, function (item, index) {
                        if (index == 0) {
                            initId = item.id;
                        }
                        if (!$scope.aboutus.content.hasOwnProperty(item.id)) {
                            $scope.aboutus.content[item.id] = item;
                        }
                    });
                    $scope.aboutus.content[data.result.length +1] = {
                        'id' : data.result.length +1 + '',
                        'title' : '友情链接',
                        'content' : {},
                    };

                } else {
                    $scope.aboutus.content = null;
                }

                if ($scope.aboutus.content) {
                    let tmp = TopbarService.getSelectedAboutusChoice();
                    if (tmp) {
                         $scope.aboutus.selected = $scope.aboutus.content[tmp.id];
                         if ($scope.aboutus.selected.title === '友情链接') {
                             AboutusService.getLinks(function (data){
                                 if (data.code) {
                                     $scope.aboutus.links = data.result;
                                 }
                             })
                         }
                    } else {
                        $scope.aboutus.selected = $scope.aboutus.content[initId];
                    }
                } else {
                    $scope.aboutus.selected = $scope.aboutus.content[initId];
                }
                pageInfoService.setTitle($scope.aboutus.selected.title ,['关于我们']);

            })
        }

        $scope.seeDetail = function (key) {
            $scope.aboutus.selected = $scope.aboutus.content[key];
            if ($scope.aboutus.selected.title === '友情链接') {
                AboutusService.getLinks(function (data){
                    if (data.code) {
                        $scope.aboutus.links = data.result;
                    }
                })
            }
            pageInfoService.setTitle($scope.aboutus.selected.title ,['关于我们']);

        };

        $scope.imgUrl = function(url) {
            if (!url) {
                return;
            }
            return url + '!180-70' + '.jpeg'
        }
}]);