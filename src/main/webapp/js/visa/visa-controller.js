angular.module('app').
controller('VisaController',['$rootScope', '$scope', '$anchorScroll', '$location', 'TopbarService', 'VisaService', '$state',
    function ($rootScope, $scope, $anchorScroll, $location, TopbarService, VisaService, $state) {
        $scope.visa = {
            list : visaMenu.secondMenu,
            selectedMenu : {},
            seletedContinentCountries : []
        };


        var indexObj = {
            title : '',
            params : '',
        };
        $scope.indexBar = [];
        for (var i = 0; i < alphabet.length; i++) {
            var tmp = angular.copy(indexObj);
            tmp.title = alphabet[i];
            tmp.params = alphabet[i];
            $scope.indexBar.push(tmp);
        }


        init();
        function init() {
            let selected = TopbarService.getSelectedContinent();

            if (selected) {
                $scope.visa.selectedMenu =  selected;
            } else {
                $scope.visa.selectedMenu =  $scope.visa.list[0];
            }

            getCountries($scope.visa.selectedMenu)

        }

        function getCountries(menu) {
            VisaService.getCountries(menu.pathId, function (data) {
                let countries = [];
                if (data.code) {
                    countries = data.result;
                } else {
                    countries = country;
                }
                const resultList = [];
                countries.forEach(item => {
                    const pinyin = item.pinyin;
                    if (pinyin) {
                        let letter = item.pinyin[0].toUpperCase();
                        if (!letter.match(/[A-Z]/)) {
                            letter = '*';
                        }
                        const obj = resultList.find(obj => obj.letter === letter);
                        if (obj) {
                            obj.list.push(item);
                        } else {
                            resultList.push({
                                letter,
                                list: [item]
                            });
                        }
                    }
                });

                $scope.visa.seletedContinentCountries = resultList;
            })
        }


        $scope.chooseContinent = function (continent) {
            $scope.visa.selectedMenu = continent;
            getCountries($scope.visa.selectedMenu)
        };

        $scope.getVisaContent = function (country) {
            $state.go('visa.details', {id : country.id});

        }



        $scope.gotoAnchor = function (x) {
            var newHash = 'anchor' + x;
            //用来控制 滚动到离顶端距离多少的位置
            $anchorScroll.yOffset = 50;
            if ($location.hash() !== newHash) {
                // set the $location.hash to `newHash` and
                // $anchorScroll will automatically scroll to it
                $location.hash('anchor' + x);
            } else {
                // call $anchorScroll() explicitly,
                // since $location.hash hasn't changed
                $anchorScroll();
            }

        }

        $scope.$on('scrolling', function (event, param) {
            if (!$state.is('visa.view')) {
                return;
            }
            if (param.currentPageOffset) {
                if (param.currentPageOffset > 208) {
                    var myEl = angular.element( document.querySelector( '#indexBar' ) );
                    var parentEl = angular.element( document.querySelector( '#index-bar-parent' ) );
                    var parentWidth = parentEl.width();
                    myEl.addClass('index-fix');
                    // myEl.css('width', parentWidth);
                } else {
                    var myEl = angular.element( document.querySelector( '#indexBar' ) );
                    myEl.removeAttr('width');
                    if (myEl.hasClass('index-fix')) {
                        myEl.removeClass('index-fix');
                    }
                }
            }

        })

        // $scope.$watch('boolChangeClass', function (newValue, oldValue) {
        //     if (newValue) {
        //         var myEl = angular.element( document.querySelector( '#indexBar' ) );
        //         var parentEl = angular.element( document.querySelector( '#index-bar-parent' ) );
        //         var parentWidth = parentEl.width();
        //         console.log(parentWidth);
        //         myEl.addClass('index-fix');
        //         myEl.css('width', parentWidth);
        //     } else {
        //         var myEl = angular.element( document.querySelector( '#indexBar' ) );
        //         myEl.removeAttr('width');
        //         if (myEl.hasClass('index-fix')) {
        //             myEl.removeClass('index-fix');
        //         }
        //     }
        // },true)


}])