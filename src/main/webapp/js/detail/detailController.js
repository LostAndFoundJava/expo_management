angular.module('app').
    controller('DetailController',['$rootScope', '$scope', 'DetailService', '$state', '$stateParams', '$sce', '$uibModal',
    function ($rootScope, $scope, DetailService, $state, $stateParams, $sce, $uibModal) {

    $('#mobile-nav-bar').attr('display', 'none');
    init();
    $scope.detail = {
        slideInterval : 5000,
    }

    function init() {
        if (!$stateParams.id) {
            $state.go('home.view')
        }

        $scope.ticket = { id : $stateParams.id};

        DetailService.getExpoDetail($stateParams.id, function (data) {
            if (data.code) {
                $scope.detail.content = data.result;

                $scope.detail.expoStatus = calDate($scope.detail.content.exhibition.exhibition.startTime, $scope.detail.content.exhibition.exhibition.endTime);

                if ($scope.detail.content.exhibitionDetail.briefInfo) {
                    try{
                        $scope.detail.brief = JSON.parse($scope.detail.content.exhibitionDetail.briefInfo);
                    } catch (e){
                        $scope.detail.brief = null;
                    }
                }

                if ($scope.detail.content.exhibitionDetail.description) {
                    $scope.detail.desc = {};
                    try{
                        $scope.detail.desc = JSON.parse($scope.detail.content.exhibitionDetail.description);
                        // for (var o in tmp) {
                        //     if (tmp.hasOwnProperty(o)) {
                        //         $scope.detail.desc[o] = $sce.trustAsHtml(tmp.o);
                        //     }
                        // }

                    } catch (e){
                        $scope.detail.desc = [{detailTitle : '展会介绍' , detailContent : $scope.detail.content.exhibitionDetail.description}];


                    }

                }

            }


        })
    }



        $scope.getTicket = function() {
            var modalInstance = $uibModal.open({
                templateUrl: 'view/common/ticket.html',
                resolve: {
                    resource: function () {
                        return {
                            id : $scope.detail.content.exhibition.exhibition.id,
                            title : $scope.detail.content.exhibition.exhibition.title,
                            startTime : $scope.detail.content.exhibition.exhibition.startTime,
                            endTime : $scope.detail.content.exhibition.exhibition.endTime,
                        };
                    }
                },
                controller: 'TicketController',
                size: 'sm',
                keyboard : false,
                backdrop : 'static'
            });
        }

        const imgSize = {
            'carousel' : {
                w:1260,
                h:800,
            },

        };

        $scope.imgUrl = function(imgUrl){
            if (!imgUrl) {
                return null;
            }
            if ($('body').hasClass('body-small')) {
                return imgUrl + '!630-400' + '.jpeg'
            } else {
                return imgUrl  + '!' + imgSize.carousel.w + '-' + imgSize.carousel.h + '.jpeg';
            }
        };

        $rootScope.$on('$stateChangeSuccess', function(event, to, toParams, from, fromParams) {
            //save the previous state in a rootScope variable so that it's accessible from everywhere
            $rootScope.previousState = from;
            $rootScope.previousStateParam = fromParams;
        });

        $scope.goToTicket = function () {
            $state.go('ticket.obtain',{id: $scope.ticket.id});
        }

}])