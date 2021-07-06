let email = document.getElementById('email').value
let password = document.getElementById('password').value
let password_repeat = document.getElementById('password-repeat').value

function signup() {
    if(password === password_repeat) {
        $.ajax({
            url: '/signup',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                email: email,
                password: password
            }),
            success: function (data) {
                document.location.href = "/signin";
            }
        })
    }
}



