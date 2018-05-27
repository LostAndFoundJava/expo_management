angular.module('app').
controller('VisaDetailController',['$rootScope', '$scope', '$stateParams', '$state', 'VisaService', 'pageInfoService', function ($rootScope, $scope,$stateParams, $state, VisaService, pageInfoService) {
    $scope.visaDetail = {
        content : {}
    };
    pageInfoService.setTitle('签证详情');
    init();

    function init() {
        const visaId = $stateParams.id;
        if (!visaId) {
            $state.go('home.view')
        }

        VisaService.getVisaContent(visaId, function (data) {
            if (data.code) {
                $scope.visaDetail.content = data.result;
                pageInfoService.setTitle($scope.visaDetail.content.country.title,['签证详情']);
            }

        })


    }

    $scope.downloadFile = function (fileId) {
        var link = document.createElement('a');
        link.download = fileId;
        // link.href = basePath + "/visa/file/" + link.download;
        link.href = fileId
        link.target = '_blank';
        var clickEvent = document.createEvent("MouseEvent");
        clickEvent.initEvent("click", true, true);
        link.dispatchEvent(clickEvent);
        delete link;
    };

}])
