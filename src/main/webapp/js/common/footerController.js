angular.module('app').
controller('FooterController',['$rootScope', '$scope', '$state', '$resource', 'TopbarService',
    function ($rootScope, $scope, $state, $resource, TopbarService) {
        $scope.footer = {
            links : []
        };
        let url = basePath + '/link';
        var rest = $resource(url, {});
        rest.get(function (data) {
            if (data.code) {
                $scope.footer.links = data.result;
            }
        }, function (error) {
        });
        
        $scope.goLinks = function () {
            TopbarService.setSelectedAboutusChoice({
                id : TopbarService.getAllAboutLength(),
                title : '友情链接'

            });
            if ($state.is('contact.aboutCompany')) {
                $state.reload();
            } else {
                $state.go('contact.aboutCompany');
            }
        };

        $scope.imgUrl = function(url) {
            if (!url) {
                return;
            }
            return url + '!180-70' + '.jpeg'
        }

    }
]);
