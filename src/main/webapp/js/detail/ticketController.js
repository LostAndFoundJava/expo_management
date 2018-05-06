angular.module('app').
controller('TicketController',['$rootScope', '$scope', '$uibModal','$uibModalInstance', 'TicketService', 'DetailService', '$stateParams', '$state', 'resource',
    function ($rootScope, $scope,$uibModal, $uibModalInstance, TicketService, DetailService, $stateParams, $state, resource) {

        $scope.ticket = {
            isSubmit : false,
            isSuccess : false,
            isUpdate : false,
            updateSuccess : false,
            showName : true,
            showPhone : true,
            showCompany : false,
            showQQ : false,
            showAddress : false,
            showUpdateButton : false,
            showGetSuccess : false,
            showUpdateSuccess : false,
            showGetFailed : false,
            showSubmitButton : true,
            showTypes : true,
            title : '',
            startTime : '',
            endTime : '',
            form : {
                id : '',
                applicationType : 0,
                company : '',
                address : '',
                src : '',
                uid : '',
                qqNo : '',
                mobileNo : '',
                exhibition : '',
                clientName : ''
            },
            formStatus : {
                numberInvalid : true,
                nameInvalid : true,
                companyInvalid : true,
                msg : '',
            }
        };

        init();

        function init() {
            if ($state.is('ticket.obtain')) {
                DetailService.getExpoDetail($stateParams.id, function (data) {
                    if (data.code) {
                        $scope.ticket.title = data.result.exhibition.exhibition.title;
                        $scope.ticket.startTime = data.result.exhibition.exhibition.startTime;
                        $scope.ticket.endTime = data.result.exhibition.exhibition.endTime;
                        $scope.ticket.form.exhibition = data.result.exhibition.exhibition.id;
                    }
                })
            } else {
                $scope.ticket.title = resource.title;
                $scope.ticket.startTime = resource.startTime;
                $scope.ticket.endTime = resource.endTime;
                $scope.ticket.form.exhibition = resource.id;
                if (resource.form) {
                    $scope.ticket.form =resource.form;
                    $scope.ticket.isSuccess = true;
                    $scope.ticket.showName = false;
                    $scope.ticket.showPhone = false;
                    $scope.ticket.showUpdateButton = true;
                    $scope.ticket.showSubmitButton = false;
                    $scope.ticket.showGetSuccess = true;
                    $scope.ticket.showGetFailed = false;
                    $scope.ticket.showCompany  = true;
                    $scope.ticket.showQQ = true;
                    $scope.ticket.showAddress = true;
                    $scope.ticket.showUpdateSuccess = false;
                    $scope.ticket.showTypes = false;
                }
            }
        }


        $scope.submitTicket = function () {

            if ($scope.ticket.form.applicationType === 0) {
                if (!$scope.ticket.form.mobileNo || $scope.ticket.form.mobileNo == '' || $scope.formStatus.numberInvalid
                    || !$scope.ticket.form.name || $scope.ticket.form.name == '') {
                    $scope.ticket.showGetFailed = true;
                    $scope.ticket.formStatus.msg = '输入信息有误';
                    return;
                }
            } else {
                if (!$scope.ticket.form.mobileNo || $scope.ticket.form.mobileNo == '' || $scope.formStatus.numberInvalid
                    || !$scope.ticket.form.name || $scope.ticket.form.name == ''
                    || !$scope.ticket.form.company || $scope.ticket.form.company == '') {
                    $scope.ticket.showGetFailed = true;
                    $scope.ticket.formStatus.msg = '输入信息有误';
                    return;
                }
            }
            $scope.ticket.isSubmit = true;
            TicketService.saveTicket($scope.ticket.form, function (data) {
                if (data.code) {
                    $scope.ticket.isSuccess = true;
                    $scope.ticket.showName = false;
                    $scope.ticket.showPhone = false;
                    $scope.ticket.showUpdateButton = true;
                    $scope.ticket.showSubmitButton = false;
                    $scope.ticket.showGetSuccess = true;
                    $scope.ticket.showGetFailed = false;
                    $scope.ticket.showCompany  = true;
                    $scope.ticket.showQQ = true;
                    $scope.ticket.showAddress = true;
                    $scope.ticket.showUpdateSuccess = false;
                    $scope.ticket.showTypes = false;

                    $scope.ticket.form.id = data.result.id;
                } else {
                    $scope.ticket.isSuccess = false;
                    $scope.ticket.showName = true;
                    $scope.ticket.showPhone = true;
                    $scope.ticket.showTypes = true;
                    $scope.ticket.showUpdateButton = false;
                    $scope.ticket.showSubmitButton = true;
                    $scope.ticket.showGetSuccess = false;
                    $scope.ticket.showGetFailed = true;

                    $scope.ticket.showCompany  = false;
                    $scope.ticket.showQQ = false;
                    $scope.ticket.showAddress = false;
                    $scope.ticket.showUpdateSuccess = false;
                    if (data.msg) {
                        $scope.ticket.formStatus.msg = data.msg;
                    }
                }
            })


        };
        
        $scope.updateTicket = function () {
            $scope.ticket.isUpdate = true;

            $scope.ticket.showGetSuccess = false;

            TicketService.update($scope.ticket.form, function (data) {
                if (data.code) {
                    $scope.ticket.showUpdateButton = false;
                    $scope.ticket.showUpdateSuccess = true;
                    $scope.ticket.showCompany  = false;
                    $scope.ticket.showQQ = false;
                    $scope.ticket.showAddress = false;

                } else {
                    $scope.ticket.showUpdateSuccess = false;
                    $scope.ticket.showCompany  = true;
                    $scope.ticket.showQQ = true;
                    $scope.ticket.showAddress = true;
                }
            });

        };

        $scope.close = function () {
            $uibModalInstance.dismiss();
            init();
        };



        $scope.goPrevious = function () {
            if ($rootScope.previousState && $rootScope.previousState.name) {

                $state.go($rootScope.previousState.name, {id : $rootScope.previousStateParam});
            } else {
                $state.go('home');
            }
        };

        $scope.$watch('detail.form', function (newVal, oldVal) {
            if (newVal != oldVal) {
                $scope.detail.ticketStatus = true;
            }
        });

        $scope.checkPhoneNumber = function (number) {

            if (!number || !checkPhoneNumber(number)) {
                $scope.ticket.formStatus.numberInvalid = true;
            } else {
                $scope.ticket.formStatus.numberInvalid = false;
            }
        };

    }
])