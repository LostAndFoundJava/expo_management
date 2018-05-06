angular.module('app')
    .factory('TicketService', TicketService);


function TicketService($resource) {


    var errorData = {
        code: false,
        errorCode: "出错了"
    };

    function saveTicket(params, callback) {
        let url = basePath + '/flowSrc/insert';

        let rest = $resource(url,{},{
            create: { method: 'POST' }});
        rest.create(params,function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    function update(params, callback) {
        let url = basePath + '/flowSrc/update';

        let rest = $resource(url,{},{
            update: { method: 'POST' }});

        rest.update(params,function (data) {
            callback(data);
        }, function (error) {
            callback(errorData);
        })
    }

    return {
        saveTicket: saveTicket,
        update :update,
    };
}

