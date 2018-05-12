angular.module('app').
    controller('HomeController',['$scope', '$state', '$window', 'HomeService','$uibModal', '$location', '$anchorScroll',
    function ($scope, $state, $window, HomeService, $uibModal, $location, $anchorScroll) {
        const mobile = {
            w : 250,
            h : 158
        };

        const pc = {
            w : 630,
            h : 400
        };

        const imgSize = {
            'carousel' : {
                w:830,
                h:360
            },
            'thumbnail' : pc,

        };

        if($("body").hasClass('body-small')) {
            imgSize.thumbnail = mobile;
        }



        $scope.home = {
            category : [],
            selectedCategory : {},
            navBar : [{
                id : 'hot',
                name : '热门推荐',
            }],
            navBarStatusActive : {
                'hot' : false,
            },
            expoDayStatus : {
                loading : true,
                details : {},
                
            },
            slideInterval : 5000,

        };

        let imgBase = 'assets/img/flags/16/';
        
        init();

        $scope.calDay = function(start, end){
            return calDate(start, end);
        };
        
        
        function init() {
            HomeService.getCategory(function (data) {
                if (data.code) {
                    $scope.home.category = data.result;
                }
            });

            HomeService.getExpos(function (data) {
                if (data.code) {
                    $scope.home.list = data.result;
                    $scope.$broadcast('data-loaded', { result: data.result });
                    $scope.home.choices = sortChoicesExpos(data.result.choice)
                    
                }
            })
            
        }

        $scope.$on('data-loaded', function (events, args) {
            getExpoDayStatus(args.result)
        });
        
        function getExpoDayStatus(allExpos) {
            for (var key in allExpos) {
                angular.forEach(allExpos[key], function (expos) {
                    if (!$scope.home.expoDayStatus.details.hasOwnProperty(expos.exhibition.id)) {
                        $scope.home.expoDayStatus.details[expos.exhibition.id] = calDate(expos.exhibition.startTime, expos.exhibition.endTime);
                    }
                })
            }
            $scope.home.expoDayStatus.loading = false;
        }

        function sortChoicesExpos(items) {
            let choices = {};
            angular.forEach(items, function (item) {
                let cateId = item.exhibition.categoryId;
                if (choices.hasOwnProperty(cateId)) {
                    choices[cateId].push(item)
                } else {
                    choices[cateId] = [item];
                    $scope.home.navBar.push({
                        id : cateId,
                        name : item.categroy,
                    });
                    if (!$scope.home.navBarStatusActive.hasOwnProperty(cateId)) {
                        $scope.home.navBarStatusActive[cateId] = false;
                    }

                }
            });
            return choices;
        }


        $scope.getDetails = function (i, isHot) {
            // $state.go('detail.view', {id: i},{newtab : true});
            let url = $state.href('detail.view', {id: i});
            if (parseInt(isHot)) {
                url = $state.href('hot.detail', {id: i});

            }
            $window.open(url,'_blank');
        };

        $scope.showMenu = function (isShow, index) {
            $scope.home.showSubmenu = isShow;
            if (index != undefined) {
                $scope.home.selectedCategory  = $scope.home.category[index];
            }
        };

        //处理图片
        $scope.imgPath = function (enName) {
            if (enName == null) {
                return
            }

            let enList = enName.split(/(\s+)/);

            let tmp = [];
            angular.forEach(enList, function (ee) {
                if (ee.trim() != "") {
                    tmp.push(ee)
                }
            });

            let en = tmp.join("-");

            return imgBase + en + '.png';
        };


        $scope.getListCondition = function (params) {
            let state = {};
            if (params.hasOwnProperty('category_id')) {
                state['category_id'] = params.category_id;
            }

            if (params.hasOwnProperty('country_id')) {
                state['country_id' ] = params.country_id;
            }

            if (params.hasOwnProperty('date')) {
                state['date' ] = params.date;
            }


            $state.go('list.condition.params', state, {location: 'replace'});

        };

        $scope.dateList = getDateObjList(12);


        String.prototype.format = function () {
            var a = this;
            for (var k in arguments) {
                if (arguments[k]) {
                    a = a.replace("{" + k + "}", arguments[k]);
                }
            }
            return a;
        };

        $scope.imgUrl = function(imgUrl, isCarousel){
            if (!imgUrl) {
                return null;
            }
            if (isCarousel) {
                return imgUrl  + '!' + imgSize.carousel.w + '-' + imgSize.carousel.h + '.jpeg';
            } else {
                return imgUrl  + '!' + imgSize.thumbnail.w + '-' + imgSize.thumbnail.h + '.jpeg';
            }

        };


        $scope.getTicket = function(expo) {
            let modalInstance = $uibModal.open({
                templateUrl: 'view/common/ticket.html',
                resolve: {
                    resource: function () {
                        return {
                            id : expo.exhibition.id,
                            title : expo.exhibition.title,
                            startTime : expo.exhibition.startTime,
                            endTime : expo.exhibition.endTime,
                        };
                    }
                },
                controller: 'TicketController',
                size: 'sm',
                keyboard : false,
                backdrop : 'static'
            });
        };

        $scope.unfoldAllCategoryNav = function () {
            $('.right-nav > .category-nav').addClass('fadeOutUp');
            $scope.home.showCategoryNav = true;
        };

        $scope.foldAllCategoryNav = function () {
            $('.right-nav > .category-nav').addClass('fadeInDown');
            $scope.home.showCategoryNav = false;
        };

        $scope.scrollToItem = function (n) {
            for (var x in $scope.home.navBarStatusActive) {
                if ($scope.home.navBarStatusActive.hasOwnProperty(x)) {
                    if (angular.equals(x, n.id)) {
                        $scope.home.navBarStatusActive[x] = true;
                    } else {
                        $scope.home.navBarStatusActive[x] = false;
                    }
                }
            }
            if ($location.hash() !== n.id) {
                $location.hash(n.id);
            } else {
                $anchorScroll();
            }
        };

        $scope.$on('scrolling', function (event, param) {
            if (!$state.is('home.view')) {
                return;
            }
            if (param.mainElementId) {
                let elem = '#' + param.mainElementId;
                var elemTop = $(elem).offset().top - $(window).scrollTop();
                let offset = param.mainElementIdOffset ? param.mainElementIdOffset : 0;
                let minusOffSet = param.mainElementIdMinusOffset ? -param.mainElementIdMinusOffset : 0;
                if (elemTop <= offset && elemTop >= minusOffSet) {
                    $scope.home.navBarStatusActive[param.mainElementId] = true;
                } else {
                    $scope.home.navBarStatusActive[param.mainElementId] = false;

                }
            }

        })

}]);
