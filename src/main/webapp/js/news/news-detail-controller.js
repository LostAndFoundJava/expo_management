angular.module('app').
controller('NewsDetailController',['$rootScope', '$scope', '$state', 'NewsService', '$stateParams', 'pageInfoService',
    function ($rootScope, $scope, $state, NewsService, $stateParams, pageInfoService) {
        pageInfoService.setTitle('资讯详情');
        $scope.newDetail = {
            content : {},
            topNews : [],
        }

        init();


        function init() {
            let id = $stateParams.id;

            if (!id) {
                $state.go('home.view')
            }

            NewsService.getNewsContent(id, function (data) {
                if (data.code) {
                    let tmp = data.result;
                    if (tmp.length > 0) {
                        $scope.newDetail.content = tmp[0];
                    }
                    pageInfoService.setTitle($scope.newDetail.content.title, ['资讯详情']);

                } else {
                    $state.go('news.list');
                    //TODO 加载出错

                }
            })

            getTopNewsBy();

        }

        function getTopNewsBy() {
            NewsService.getTopNews(3, function (data) {
                if (data.code) {
                    $scope.newDetail.topNews = data.result;
                }
            })
        }


        $scope.getNewsDetail = function (news) {
            $state.go('news.detail', {id: news.id});
        };



}])