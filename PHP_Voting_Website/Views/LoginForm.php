
<!DOCTYPE html>
<html lang="en">
<head>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <link rel="stylesheet" href="../Styles/login.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <style>
        .warning{
            color: rgb(151, 12, 35);
            font-size: 10;
            font-weight:bold;
        }
        .check{
            color: black;
            font-weight:bold;
        }
        form .error {
            color: #bf1111;
        }

        ::placeholder { 
            color: rgb(151, 12, 35);
            opacity: 1; 
        }
        
    </style>
    <script>
 
    $(document).ready(function(){

    $("#name").blur(function () {
        if (!$(this).val()) {
            $(this).next().empty().removeClass('check').addClass('warning');
            $(this).css("border", "2px solid rgb(151, 12, 35)");
            $(this).attr("placeholder", "CANNOT BE EMPTY");
        }
        else {
            $(this).next().empty().removeClass('warning').addClass('check').append("✓");
        }
    });

    $("#psw").blur(function () {
	var number = /([0-9])/;
	var special_characters = /([~,!,@,#,$,%,^,&,*,-,_,+,=,?,>,<])/;
        if($(this).val().length<8 || !$(this).val().match(number) || !$(this).val().match(special_characters)){
            $(this).next('.Error').empty().removeClass('check').addClass('warning').append("Weak Password");
            $(this).css("border", "2px solid rgb(151, 12, 35)");
            $(this).attr("placeholder", "WEAK PASSWORD");
        }
    
        else{
            $(this).next().empty().removeClass('warning').addClass('check') .append("✓");
        } 
     });
});
 </script>

</head>
<body id="bg" style="background-image: url('../Assets/Images/map.jpg');">
<section>
    <div class="form-container">
        <h1>login As Voter</h1>
        <form action="LoginAction.php" action="home.php" method="POST" id="loginForm">

            <div class="control">
                <label for="name">Username</label>
                <input type="text" name="username" id="name" value="<?php if(isset($_COOKIE["username"])) { echo $_COOKIE["username"];}?>">
            </div>
            <div class="overlay"></div>
            <div class="control">
                <label for="psw">Password</label>
                <input type="password" name="password" id="psw">
            </div>
            <span><input type="checkbox" name="remember"> Remember me.</span>
            <br>
            <!--<br>
            <div  class="g-recaptcha" data-sitekey="6LeD3hEUAAAAAKne6ua3iVmspK3AdilgB6dcjST0"></div> -->
            <div class="control">
                <input type="submit" name="submit" value="Login" >
            </div>

        </form>
        <div class="link">
            <a href="SignUpForm.php">  Create Account?</a>
        </div>
    </div>
</section><div class="overlay"></div>



</body>
</html>

