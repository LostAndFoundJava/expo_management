angular.module('app').
controller('VisaDetailController',['$rootScope', '$scope', '$stateParams', '$state', 'VisaService', function ($rootScope, $scope,$stateParams, $state, VisaService) {
    $scope.visaDetail = {
        content : {}
    };

    init();

    function init() {
        const visaId = $stateParams.id;
        if (!visaId) {
            $state.go('home.view')
        }

        VisaService.getVisaContent(visaId, function (data) {
            if (data.code) {
                $scope.visaDetail.content = data.result;
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
