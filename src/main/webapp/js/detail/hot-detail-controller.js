angular.module('app').
controller('HotDetailController',['$rootScope', '$scope','DetailService','$stateParams', '$uibModal', '$state', '$location', '$anchorScroll', 'TicketService',
    function ($rootScope, $scope, DetailService, $stateParams, $uibModal, $state, $location, $anchorScroll, TicketService) {
        init();
        let tmpform = {
                id : '',
                applicationType : 0,
                company : '',
                src : '',
                uid : '',
                mobileNo : '',
                clientName : '',
                exhibition : '',
            };

        $scope.detail = {
            briefInfo : {},
            descInfo : {},
            form : angular.copy(tmpform),
            formStatus : {
                numberInvalid : true,
                nameInvalid : true,
                companyInvalid : true,
                msg : '',
            },
            ticketStatus : true,
            showTicketBar : true,
        };


        function init() {
            if (!$stateParams.id) {
                $state.go('home')
            }

            $scope.ticket = { id : $stateParams.id};

            DetailService.getExpoDetail($stateParams.id, function (data) {
                if (data.code) {
                    $scope.detail.content = data.result;

                    let tmpp = '';


                    if ($scope.detail.content.exhibitionDetail.briefInfo) {
                        try{
                            tmpp = JSON.parse($scope.detail.content.exhibitionDetail.briefInfo);
                        } catch (e){
                            tmpp = null;
                        }

                        $scope.detail.briefInfo= tmpp;
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

                        } catch {
                            $scope.detail.desc = [{detailTitle : '展会介绍' , detailContent : $scope.detail.content.exhibitionDetail.description}];

                        }

                        // $scope.detail.descInfo['content'] = $scope.detail.desc;

                    }

                }


            })
        }


        $scope.ticket = {
            form  : {},
        };
        $scope.getTicket = function() {
            if ($scope.detail.form.applicationType === 0) {
                if (!$scope.detail.form.mobileNo || $scope.detail.form.mobileNo == '' || $scope.detail.formStatus.numberInvalid
                    || !$scope.detail.form.clientName || $scope.detail.form.clientName == '') {
                    $scope.detail.showGetFailed = true;
                    $scope.detail.formStatus.msg = '输入信息有误';
                    $scope.detail.ticketStatus = false;
                    return;
                }
            } else {
                if (!$scope.detail.form.mobileNo || $scope.detail.form.mobileNo == '' || $scope.detail.formStatus.numberInvalid
                    || !$scope.detail.form.clientName || $scope.detail.form.clientName == ''
                    || !$scope.detail.form.company || $scope.detail.form.company == '') {
                    $scope.detail.showGetFailed = true;
                    $scope.detail.formStatus.msg = '输入信息有误';
                    $scope.detail.ticketStatus = false;

                    return;
                }
            }
            $scope.detail.form.exhibition = $scope.ticket.id;
            let form = $scope.detail.form;
            TicketService.saveTicket($scope.detail.form, function (data) {
                form.id = data.result.id;
                if (data.code) {
                    var modalInstance = $uibModal.open({
                        templateUrl: 'view/common/ticket.html',
                        resolve: {
                            resource: function () {
                                return {
                                    id : $scope.detail.content.exhibition.exhibition.id,
                                    title : $scope.detail.content.exhibition.exhibition.title,
                                    startTime : $scope.detail.content.exhibition.exhibition.startTime,
                                    endTime : $scope.detail.content.exhibition.exhibition.endTime,
                                    form : form,
                                };
                            }
                        },
                        controller: 'TicketController',
                        size: 'sm',
                        keyboard : false,
                        backdrop : 'static'
                    });
                    modalInstance.result.then(function (result) {
                        $scope.detail.form = angular.copy(tmpform)
                    }, function () {
                        $scope.detail.form = angular.copy(tmpform)
                    });
                } else {
                    $scope.detail.ticketStatus = false;
                    if (data.msg) {
                        $scope.detail.formStatus.msg = data.msg;
                    }
                }
            })

        };

        $rootScope.$on('$stateChangeSuccess', function(event, to, toParams, from, fromParams) {
            //save the previous state in a rootScope variable so that it's accessible from everywhere
            $rootScope.previousState = from;
            $rootScope.previousStateParam = fromParams;
        });

        $scope.getReservation = function (x) {
            let newHash = 'reservation';
            if ($location.hash() !== newHash) {
                $location.hash(x);
            } else {
                $anchorScroll();
            }
        };

        $scope.changeTicketType = function (appId) {
            if (!appId) {
                $scope.detail.form.company = "";
            }
        };
        
        $scope.goToTicket = function () {
            $scope.getReservation('reservation');
        };

        $scope.$on('scrolling', function (event, param) {
            if (!$state.is('hot.detail')) {
                return;
            }
            if(param.hideElementId) {
                if (isScrolledIntoView('#' + param.hideElementId)) {
                    // if (checkVisible(scope.elementId)) {
                    $scope.detail.showTicketBar = false;
                } else {
                    $scope.detail.showTicketBar = true;

                }
            }

            function isScrolledIntoView(elem)
            {
                var docViewTop = $(window).scrollTop();
                var docViewBottom = docViewTop + $(window).height();

                var elemTop = $(elem).offset().top;
                var elemBottom = elemTop + $(elem).height();

                return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));
            }

        });

        $scope.checkPhoneNumber = function (number) {

            if (!number || !checkPhoneNumber(number)) {
                $scope.detail.formStatus.numberInvalid = true;
            } else {
                $scope.detail.formStatus.numberInvalid = false;
            }
        };

        $scope.$watch('detail.form', function (newVal, oldVal) {
            if (!angular.equals(newVal, oldVal)) {
                $scope.detail.ticketStatus = true;
            }
        }, true)


        $scope.imgUrl = function(imgUrl){
            if (!imgUrl) {
                return null;
            }
            return imgUrl  + '!2590-1080' ;

        };


}])