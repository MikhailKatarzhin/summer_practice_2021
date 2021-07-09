window.onload = function updateCountRP(){
    $.ajax({
        url: '/menu/countRP',
        method: 'GET',
        contentType: "application/json",
        success: function (data) {
            switch (data.key){
                case "200":{
                    document.getElementById("countRP").innerHTML = data.status;
                    break;
                }
                default:{
                    document.location.href = "/signin";
                }
            }
        },
        error: function (){
            document.location.href = "/signin";
        }
    }, 600);
}