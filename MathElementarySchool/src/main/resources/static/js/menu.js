window.onload = function updateCountRP(){
    $.ajax({
        url: '/menu/countRP',
        method: 'GET',
        contentType: "application/json",
        success: function (data) {
            document.getElementById("countRP").innerHTML = data;
        },
        error: function (data){
            document.location.href = "/signin";
        }
    }, 60);
}