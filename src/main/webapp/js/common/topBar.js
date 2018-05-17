angular.module('app').
controller('TopBarController',['$rootScope', '$scope', '$state', 'TopbarService', function ($rootScope, $scope, $state, TopbarService) {
    $scope.homeList = topbarList;
    $scope.top = {
        weChat : 'weChat.html'
    };

    $scope.aboutus = {
        firstMenu : { title: '关于我们', id: 'about'},
        secondMenu : []
    };

    $scope.visa = visaMenu;


    TopbarService.loadAboutUsMenu(function (data) {
        let index = 0;
        if (data.code) {
            angular.forEach(data.result, function (item) {
                index ++ ;
                let tmp = {
                    title : item.title,
                    id : item.id,
                };

                $scope.aboutus.secondMenu.push(tmp);
            })
            $scope.aboutus.secondMenu.push({
                title : '友情链接',
                id : index + 1,
            });
            TopbarService.setAllAboutLength(index +1);
        } else {
        }
    });

    $scope.goToAboutus = function(aboutUsTopic) {
        TopbarService.setSelectedAboutusChoice(aboutUsTopic);
        if ($state.is('contact.aboutCompany')) {
            $state.reload();
        } else {
            $state.go('contact.aboutCompany');
        }
    };

    $scope.goToVisa = function (visaContinent) {
        TopbarService.setSelectedContinent(visaContinent);
        if ($state.is('visa.view')) {
            $state.reload();
        } else {
            $state.go('visa.view');
        }
    };

    $scope.reload = function (stateName) {
        $state.go(stateName, {}, {reload: true});

    };

    $scope.searchExpos = function (query) {
        if ($state.includes('list')) {
            $rootScope.$broadcast('querying', {query : query});
        } else {
            $state.go('list.condition.query', {query : query});
        }
    }


}])