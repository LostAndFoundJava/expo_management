const tele = '400-6188-555';


//TODO 修改成对象
var topbarList =  [
    {
        firstMenu : { title: '首页', link: 'home.view'}
    },
    {
        firstMenu : { title: '展会全览', link :'list.condition' },
        secondMenu: [],
    },
    {
        firstMenu : { title : '展会资讯', link: 'news.list' },
    },
];

var visaMenu =  {
        firstMenu : { title: '签证', link :'visa'},
        secondMenu: [
            {title : '亚洲', pathId : '1', link :'visa'},
            {title : '欧洲', pathId : '2', link :'visa'},
            {title : '北美洲', pathId : '4', link :'visa'},
            {title : '南美洲', pathId : '5', link :'visa'},
            {title : '非洲', pathId : '3', link :'visa'},
            {title : '大洋洲', pathId : '6', link :'visa'},
        ],
    };




function getDateObjList(num) {
    var dateList = [];
    if (num <= 0) {
        return dateList;
    }
    var today = new Date();
    var mm = today.getMonth(); //January is 0!
    var yyyy = today.getFullYear();
    for (var i = 0; i < num; i++) {
        var tmp = dateObj = {
            title : '',
            param: ''
        };
        if (mm % 12 == 0) {
            mm = 1;
            yyyy = yyyy + 1;
        } else {
            mm = mm +1;
        }
        var mmStr = '' + mm;
        if (mm < 10) {
            mmStr = '0' + mmStr;
        }
        tmp.title = yyyy + '年' + mmStr + '月';
        tmp.param = yyyy + '/' + mmStr;
        dateList.push(tmp);
    }
    return dateList;
}

var alphabet = 'abcdefghijklmnopqrstuvwxyz'.toUpperCase().split('');

var basePath = "/api";


function calDate(startTime, endTime) {
    // 如果当前时间小于开始时间，难么就显示 离开始还有几天，如果大于开始，显示离结束的时间，否则显示已经结束
    let o = {
        end : false,
        start : true,
        day : 0,
    };

    let date1 = new Date();
    let date2 = new Date(startTime);
    let tmp = date2.getTime() - date1.getTime();
    if (tmp > 0) {
        //还没有开始
        o.start = false;
        o.day = getDay(tmp);

    } else {
        let date3 = new Date(endTime);
        let tmp = date3.getTime() - date1.getTime();
        if (tmp > 0) {
            o.start = true;
            o.day = getDay(tmp);
        } else {
            o.start = false;
            o.end = true;
        }
    }
    return o;

}

function getDay(tmp) {
    var timeDiff = Math.abs(tmp);
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
    return diffDays;
}


function checkPhoneNumber(number) {
    let num = '' + number;

    if (num.indexOf('-') > -1) {
        if (number.match(/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/) != null) {
            return true;
        } else {
            return false;
        }
    } else if (num.match(/^1[345678]\d{9}$/)) {
        return true;
    } else {
        return false;
    }
}


function getCookie(name) {
    var v = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return v ? v[2] : null;
}


