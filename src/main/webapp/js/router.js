
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider, $provide, $locationProvider) {
    IdleProvider.idle(5); // in seconds
    IdleProvider.timeout(120); // in seconds

    $urlRouterProvider.otherwise("/home");


    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });

    $stateProvider
        .state('home', {
            abstract: true,
            url: "/home",
            templateUrl: "view/common/content.html",
        })
        .state('home.view', {
            url: "",
            title : '首页_鸿尔展览',
            templateUrl: "view/home.html",
            controller: "HomeController",
            resolve: {
                loadPlugin: ['$ocLazyLoad', '$injector',
                    function ($ocLazyLoad, $injector) {
                        return $ocLazyLoad.load({
                            files: [
                                'css/ticket-style.css',
                                '../bower_components/icheck/skins/square/green.css',
                                '../bower_components/icheck/icheck.js',
                                'js/home/homeService.js',
                                'css/home-style.css',
                                'js/home/homeController.js',
                                'js/detail/ticketController.js',
                                'js/detail/ticketService.js',
                                'js/detail/detailService.js',
                            ]
                        })

                    }]
            }
        })
        .state('category', {
            abstract: true,
            url: "/category",
            templateUrl: "view/common/content.html",
        })
        .state('category.view', {
            url: "",
            templateUrl: "view/category.html",
            controller: "CategoryController",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: [
                                'css/category-style.css',
                                'js/home/homeService.js',
                                'js/list/listService.js',
                                'js/category/category-controller.js',
                            ]
                        }
                    ]);

                }
            }
        })
        .state('detail',{
            abstract: true,
            url: "/detail",
            templateUrl: "view/common/content.html",
        })
        .state('detail.view', {
            url: "/{id}",
            templateUrl: "view/detail/detail.html",
            controller: "DetailController",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: [
                                '../bower_components/icheck/skins/square/green.css',
                                '../bower_components/icheck/icheck.js',
                                'css/detail-style.css',
                                'css/ticket-style.css',
                                'assets/css/quill/quill.snow.css',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'js/detail/ticketService.js',
                                'js/detail/detailController.js',
                                'js/detail/ticketController.js',
                                'js/detail/detailService.js'
                            ]
                        },
                    ]);

                }
            }
        })
        .state('list',{
            abstract: true,
            url: "/list",
            templateUrl: "view/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: [
                                '../bower_components/icheck/skins/square/green.css',
                                '../bower_components/icheck/icheck.js',
                                'css/list-style.css',
                                'css/list-mobile-filter.css',
                                'css/ticket-style.css',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'js/detail/detailService.js',
                                'js/detail/ticketController.js',
                                'js/detail/ticketService.js',
                                'js/list/listService.js',
                                'js/list/list-controller.js',
                            ]
                        },
                    ]);

                }
            }
        })
        .state('list.condition', {
            url: "",
            templateUrl: "view/list/list.html",
            controller: "ListController",

        })
        .state('list.condition.params', {
            url: "?category_id&country_id&date",
            templateUrl: "view/list/list.html",
            controller: "ListController",

        })
        .state('list.condition.hot', {
            url: "?hot",
            templateUrl: "view/list/list.html",
            controller: "ListController",

        })
        .state('list.condition.query', {
            url: "?query",
            templateUrl: "view/list/list.html",
            controller: "ListController",

        })
        .state('contact',{
            abstract: true,
            url: "",
            templateUrl: "view/common/content.html",
        })
        .state('contact.aboutCompany', {
            url: "/about",
            templateUrl: "view/aboutus.html",
            controller: "AboutusController",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: [
                                'css/about-us-style.css',
                                'assets/css/quill/quill.snow.css',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'js/detail/ticketService.js',
                                'js/aboutus/aboutusService.js',
                                'js/aboutus/aboutus-controller.js'
                            ]
                        },
                    ]);

                }
            }
        })

        .state('visa', {
            abstract: true,
            url: "/visa",
            templateUrl: "view/common/content.html",
        })

        .state('visa.view', {
            url: "",
            templateUrl: "view/visa.html",
            controller: "VisaController",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: [
                                'css/visa-style.css',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'js/visa/visaService.js',
                                'js/visa/visa-controller.js',
                                'js/visa/directive.js'

                            ]
                        },
                    ]);

                }
            }
        })

        .state('visa.details', {
            url: "/{id}",
            templateUrl: "view/visa-detail.html",
            controller: "VisaDetailController",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: [
                                'css/visa-detail-style.css',
                                'assets/css/quill/quill.snow.css',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'js/visa/visaService.js',
                                'js/visa/visa-detail-controller.js',

                            ]
                        },
                    ]);

                }
            }
        })

        .state('news', {
            abstract: true,
            url: "/news",
            templateUrl: "view/common/content.html",
        })

        .state('news.list', {
            url: "",
            templateUrl: "view/news.html",
            controller: "NewsController",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: [
                                'css/news-style.css',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'js/news/newService.js',
                                'js/news/news-controller.js'
                            ]
                        }
                    ]);

                }
            }
        })
        .state('news.detail', {
            url: "/{id}",
            templateUrl: "view/news-detail.html",
            controller: "NewsDetailController",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: [
                                'css/news-detail.css',
                                'assets/css/quill/quill.snow.css',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'js/news/newService.js',
                                'js/news/news-detail-controller.js'
                            ]
                        },
                    ]);

                }
            }
        })
        .state('hot', {
            abstract: true,
            url: "/hot",
            templateUrl: "view/common/content.html",
        })
        .state('hot.detail', {
            url: "/{id}",
            templateUrl: " view/detail/hot-detail.html",
            controller: "HotDetailController",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: [
                                '../bower_components/icheck/skins/square/green.css',
                                '../bower_components/icheck/icheck.js',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'css/hot-detail-style.css',
                                'css/ticket-style.css',
                                'assets/css/quill/quill.snow.css',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'js/detail/ticketService.js',
                                'js/detail/ticketController.js',
                                'js/detail/detailService.js',
                                'js/detail/hot-detail-controller.js'
                            ]
                        },
                    ]);

                }
            }
        })
        .state('ticket', {
            abstract: true,
            url: "/ticket",
            templateUrl: "view/common/content.html",
        })
        .state('ticket.obtain', {
            url: "/{id}",
            templateUrl: "view/detail/mobile-ticket.html",
            controller: "TicketController",
            resolve: {
                resource : function() { return null },
                $uibModalInstance: function () { return null },
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: [
                                '../bower_components/icheck/skins/square/green.css',
                                '../bower_components/icheck/icheck.js',
                                'css/ticket-style.css',
                            ]
                        },
                        {
                            serie: true,
                            files: [
                                'js/detail/ticketService.js',
                                'js/detail/ticketController.js',
                                'js/detail/detailService.js',
                            ]
                        },
                    ]);

                }
            }

        });
    $locationProvider.html5Mode(true);
}

webapp.config(config)
    .run(function ($rootScope, $state, $location, $anchorScroll, $http, $interval) {
        $rootScope.$state = $state;
        $rootScope.telephone = tele;
        $rootScope.inhome = false;
        $rootScope.hideFileAdvice = true;
        $rootScope.qqUrl = qqUrl;

        var form = {
            company : '',
            clientName : '',
            mobileNo : '',
            material : '',
        }

        $rootScope.advice = {
            form : angular.copy(form),
            status : {
                numberInvalid : false,
                isWrong: false,
                msg : '',
                timer : '',
                success : false,
            }
        };

        function startClock(time) {
            var seconds = time;
            $rootScope.advice.status.timer = "(" + seconds + ") ";
            var interval = $interval(function(){
                seconds--;
                $rootScope.advice.status.timer = "(" + seconds + ") ";
                if (seconds === 0) {
                    $rootScope.advice.status.timer = "";
                    $rootScope.advice.status.numberInvalid = false;
                    $rootScope.advice.status.isWrong = false;
                    $interval.cancel(interval);  // clear interval
                }
            },1000);
        }




        $rootScope.jumpToHead = function(x) {
            let newHash = 'top-nav';
            if ($location.hash() !== newHash) {
                $location.hash(x);
            } else {
                $anchorScroll();
            }

        };

        $rootScope.checkAdviceNumber = function (number) {
            if (!number || !checkPhoneNumber(number)) {
                $rootScope.advice.status.numberInvalid = true;
                $rootScope.advice.status.msg = '号码有误';
                startClock(3);

            } else {
                $rootScope.advice.status.numberInvalid = false;
            }
        }

        $rootScope.openFileAdvice = function () {
            $rootScope.hideFileAdvice = false
        };

        $rootScope.closeFileAdvice = function () {
            $rootScope.hideFileAdvice = true;
        };

        $rootScope.saveAdvice = function () {
            if (!$rootScope.advice.form.material || $rootScope.advice.form.material == '' ) {
                $rootScope.advice.status.isWrong = true;
                $rootScope.advice.status.msg = '请填写所需要的资料';
                startClock(3);
                return;
            }

            if ($rootScope.advice.status.numberInvalid) {
                $rootScope.advice.status.isWrong = true;
                $rootScope.advice.status.msg = '号码填写有误';
                startClock(3);
                return;
            }

            $http({
                method: 'POST',
                url: basePath + "/advice/insert",
                data: $rootScope.advice.form
            }).then(function (response ) {
                if (response.data.code) {
                    $rootScope.advice.status.success= true;
                    var seconds = 8;
                    $rootScope.advice.status.timer = "(" + seconds + ") ";
                    var interval = $interval(function(){
                        seconds--;
                        $rootScope.advice.status.timer = "(" + seconds + ") ";
                        if (seconds === 0) {
                            $rootScope.advice.status.timer = "";
                            $rootScope.advice.status.success = false;
                            $interval.cancel(interval);  // clear interval
                        }
                    },1000);
                } else {
                    $rootScope.advice.status.isWrong = true;
                    $rootScope.advice.status.msg = '获取失败了';
                    startClock(3);
                }
            })
        };

        $rootScope.cancelConnect = function () {
            $rootScope.advice.status.success = false;
            $rootScope.advice.form = angular.copy(form);

        };



        $rootScope.$on('scrolling', function (event, param) {


            if(param.hideElementId && param.hideElementId === 'top-nav') {
                if (isScrolledIntoView('#' + param.hideElementId)) {
                    // if (checkVisible(scope.elementId)) {
                    $rootScope.showJumpNav = false;
                } else {
                    $rootScope.showJumpNav = true;

                }
            }

            function isScrolledIntoView(elem) {
                if ($(elem).offset()) {
                    var docViewTop = $(window).scrollTop();
                    var docViewBottom = docViewTop + $(window).height();

                    var elemTop = $(elem).offset().top;
                    var elemBottom = elemTop + $(elem).height();

                    return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));
                }
            }

        })
    });
