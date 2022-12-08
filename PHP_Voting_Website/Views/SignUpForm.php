
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="../Styles/SignUp.css">
</head>
<body id="bg" style="background-image: url('../Assets/Images/map.jpg');">
<section>
    <div class="form-container">
        <h1>Register to Vote</h1>
        <form action="SignUpAction.php" method="POST" >            
            
            <div class="control">
                <input type="text" name="idnumber" id="id" placeholder="National Id Number">
            </div>

            <div class="control">
                <input type="text" name="email" id="mail" placeholder="Email">
            </div>

            <div class="control">
                <input type="text" name="firstname" id="name" placeholder="Firstname">
            </div>

            <div class="control">
                <input type="text" name="lastname" id="name" placeholder="Lastname">
            </div>

            <div class="control">
                <input type="text" name="username" id="name" placeholder="Username">
            </div>

            <div class="control" >
                <input type="password" name="password" id="psw" placeholder="Password">
            </div>
            
            <div class="control" >
                <input type="password" name="confirm" id="psw" placeholder="Confirm Password">
            </div>

            <div class="control">
                <input type="submit" name="register" value="Register">
            </div>
        </form>
        <div class="link">
            <a href="LoginForm.php">  Already Registered? Login</a>
        </div>
    </div>
</section>
</body>
</html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../Scripts/LoginFormJSValidation.js"></script>