angular.module('app').
controller('NewsController',['$rootScope', '$scope', '$state', 'NewsService', 'pageInfoService', function ($rootScope, $scope, $state, NewsService, pageInfoService) {
    pageInfoService.setTitle('资讯列表');
    $scope.news = {
        categories : [],
        mobileCategories : [],
        pageInfo : {},
        selectedCategory : {
            'name' :'最新资讯',
            'id' : 0,
        },
        topNews :[],
    };

    const pageInfo = {
        last : false,
        pageNum : 1,
        pageSize : 0,
        totalNum : 0,
    };

    let InMobile = false;

    $scope.news.categories.push({
        'name' :'最新资讯',
        'id' : 0,
    });

    init();

    function getTopNewsBy() {
        NewsService.getTopNews(3, function (data) {
            if (data.code) {
                $scope.news.topNews = data.result;
            }
        })
    };

    function init(){
        NewsService.getNewsCategory(function (data) {
            let tmp = '';
            if (data.code) {
                tmp = data.result;
            } else {
                tmp = [];
            }
            angular.forEach(tmp, function (category,index) {
                if (InMobile && index > 2) {
                    $scope.news.mobileCategories.push({
                        'name' :category,
                        'id' : index + 1,
                    })
                } else {
                    $scope.news.categories.push({
                        'name' :category,
                        'id' : index + 1,
                    });
                }

            })
        });

        NewsService.getAllNews(1, function (data) {
            $scope.news.pageInfo = angular.copy(pageInfo);
            if (data.code) {
                $scope.news.allNews = data.result.content;
                $scope.news.pageInfo.pageNum = data.result.pageNum;
                $scope.news.pageInfo.pageSize = data.result.pageSize;
                $scope.news.pageInfo.totalNum = data.result.totalNum;
                $scope.news.pageInfo.last = data.result.last;
            } else {
                $scope.news.allNews = [];
            }
        });

        getTopNewsBy();
    }


    $scope.switchCategory = function (category) {
        $scope.news.selectedCategory = category;
        if(category.id == 0) {
            NewsService.getAllNews(1, function (data) {
                if (data.code) {
                    $scope.news.allNews = data.result.content;
                    $scope.news.pageInfo.pageNum = data.result.pageNum;
                    $scope.news.pageInfo.pageSize = data.result.pageSize;
                    $scope.news.pageInfo.totalNum = data.result.totalNum;
                    $scope.news.pageInfo.last = data.result.last;
                } else {
                    $scope.news.allNews = [];
                    $scope.news.allNews = newsList;
                }
            })
        } else {
            let cate = category.name;
            NewsService.getNewsByNewsCategory(cate, 1, function (data) {
                if (data.code) {
                    $scope.news.allNews = data.result.content;
                    $scope.news.pageInfo.pageNum = data.result.pageNum;
                    $scope.news.pageInfo.pageSize = data.result.pageSize;
                    $scope.news.pageInfo.totalNum = data.result.totalNum;
                    $scope.news.pageInfo.last = data.result.last;
                } else {
                    $scope.news.allNews = [];
                    $scope.news.allNews = newsList;
                }
            })
        }
    };

    $scope.getNewsDetail = function (news) {
        $state.go('news.detail', {id: news.id});
    };

    $scope.loadMore = function() {
        $scope.news.loadMoreLoading = true;
        if ($scope.news.selectedCategory.id == 0) {
            NewsService.getAllNews($scope.news.pageInfo.pageNum + 1, function (data) {
                $scope.news.loadMoreLoading = false;
                if (data.code) {
                    $scope.news.allNews.push.apply($scope.news.allNews, data.result.content);
                    $scope.news.pageInfo.pageNum = data.result.pageNum;
                    $scope.news.pageInfo.pageSize = data.result.pageSize;
                    $scope.news.pageInfo.totalNum = data.result.totalNum;
                    $scope.news.pageInfo.last = data.result.last;
                } else {
                    $scope.news.allNews = [];
                    $scope.news.allNews = newsList;
                }
            })
        } else {
            let cate = $scope.news.selectedCategory.name;
            NewsService.getNewsByNewsCategory($scope.news.pageInfo.totalNum + 1, 1, function (data) {
                $scope.news.loadMoreLoading = false;
                if (data.code) {
                    $scope.news.allNews.push.apply($scope.news.allNews, data.result.content);
                    $scope.news.pageInfo.pageNum = data.result.pageNum;
                    $scope.news.pageInfo.pageSize = data.result.pageSize;
                    $scope.news.pageInfo.totalNum = data.result.totalNum;
                    $scope.news.pageInfo.last = data.result.last;
                } else {
                    $scope.news.allNews = [];
                    $scope.news.allNews = newsList;
                }
            })
        }

    }

}])
