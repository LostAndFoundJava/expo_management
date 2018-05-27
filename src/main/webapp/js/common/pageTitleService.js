angular.module('app').factory('pageInfoService', function ($document) {

    // Public API
    return {
        // Set page <title> tag. Both parameters are optional.
        setTitle: function (title, firstMenus, hideTextLogo) {
            var defaultTitle = baseTitle;
            var newTitle = defaultTitle;
            if (title) {
                let tmp = '';
                if (firstMenus && firstMenus.length > 0) {
                    angular.forEach(firstMenus, function (menu) {
                        tmp += '_' + menu;
                    });
                }
                newTitle = title + tmp + '_' + defaultTitle;
            }
            $document[0].title = newTitle;
        }
    };

});