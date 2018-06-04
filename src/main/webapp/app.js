'use strict';

// Declare app level module which depends on views, and components
var webapp = angular.module('app', [
    'ui.router',                    // Routing
    'oc.lazyLoad',                  // ocLazyLoad
    'ui.bootstrap',
    'ngIdle',
    'ngTouch',
    'ngResource',
    'angular.filter',
]);

// Minimalize menu when screen is less than 768px
$(window).bind("load resize", function () {
    if ($(document).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }
});

angular.module('app').filter("trust", ['$sce', function($sce) {
    return function(htmlCode){
        if (!htmlCode || angular.equals(htmlCode, {})) {
            return "";
        }
        return $sce.trustAsHtml(htmlCode);
    }
}]).directive("scroll", function ($window) {
    return {
        scope : {
            hideElementId : '@',
            showStatus : '=',
            mainElementId : '@',
            mainElementIdOffset: '@',
            mainElementIdMinusOffset : '@',
            mainElementStatus: '=',

        },
        restrict: 'EA',
        link: function (scope, element, attrs) {
            angular.element($window).bind("scroll", function () {
                scope.$emit('scrolling', {
                    hideElementId : scope.hideElementId,
                    showStatus : scope.showStatus,
                    mainElementId : scope.mainElementId,
                    mainElementIdOffset: scope.mainElementIdOffset,
                    mainElementIdMinusOffset : scope.mainElementIdMinusOffset,
                    mainElementStatus: scope.mainElementStatus,
                    currentPageOffset : this.pageYOffset,
                });




                scope.$apply();
            });


        }
    }
});
