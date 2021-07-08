function signup() {
    let password = document.getElementById('password').value
    let password_repeat = document.getElementById('password-repeat').value
    let email = document.getElementById('email').value

    if( password === password_repeat) {
        $.ajax({
            url: '/register',
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                "email": email,
                "password": password
            }),
            success: function (data) {
                checkSuccess(data);
            },
            error: function (data){
                alert(data.status);
            }
        })
    }
}

function checkSuccess(data){
    switch (data.key){
        case "200":{
            document.location.href = "/signin";
            break;
        }
        default:{
            alert("Error" + data.key + " " + data.status);
        }
    }
}