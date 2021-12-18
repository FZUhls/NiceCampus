let warnText = "<div class=\"alert alert-warning alert-dismissible\"style=\"position: absolute; left: 50%; top: 30%; text-align: center\" role=\"alert\">Warning!</div>";
let errorText = "<div class=\"alert alert-danger alert-dismissible\"style=\"position: absolute; left: 50%; top: 30%; text-align: center\" role=\"alert\">Error!</div>";
let infoText = "<div class=\"alert alert-info alert-dismissible\"style=\"position: absolute; left: 50%; top: 30%; text-align: center\" role=\"alert\">Info!</div>";
const toastr = {
    info:function (str){
        var dom = $(infoText);
        dom.text(str);
        $('body').append(dom);
        setTimeout(function () {
            dom.remove();
        },3000);
    },
    warn:function (str){
        var dom = $(warnText);
        dom.text(str);
        $('body').append(dom);
        setTimeout(function () {
            dom.remove();
        },3000);
    },
    error:function (str){
        var dom = $(errorText);
        dom.text(str);
        $('body').append(dom);
        setTimeout(function () {
            dom.remove();
        },3000);
    }
}