function signup() {
    if(document.getElementById('password').value === document.getElementById('password-repeat').value) {
        console.log(JSON.stringify({
            email: document.getElementById('email').value,
            password: document.getElementById('password').value
        }))
        alert("clicked")

        $.ajax({
            url: '/register',
            method: 'POST',
            data: JSON.stringify({
                email: document.getElementById('email').value,
                password: document.getElementById('password').value
            }),
            success: function (data) {
                console.log(data);
                document.location.href = "/signin";
            },
            error: function (data){
                document.location.href = "/signup";
                console.log(data.toString());
                alert("error" + data.toString());
            }
        })
    }
}