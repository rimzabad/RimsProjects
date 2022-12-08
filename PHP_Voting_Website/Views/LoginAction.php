<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link href="../Styles/loadingPagecss.css?" rel="stylesheet" /> 
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
    </head>
    <body>

        <div class="overlay">
            <div class="overlayDoor"></div>
            <div class="overlayContent">
                <div class="loader">
                    <div class="inner"></div>
                </div>

            </div>
        </div>

    </body>

</html>

<script>
    $(document).ready(function () {

        $(window).bind('load', function () {         //like document.ready() but on windows
            $('.overlay, body').addClass('loaded');  // class loaded ->remove overlay(class of loading page)
            setTimeout(function () {
                $('.overlay').css({'display': 'none'}); // add to overlay display none
            }, 6000);
        });

        // Will remove overlay after 1min for users cannnot load properly.
        setTimeout(function () {
            $('.overlay, body').addClass('loaded');  //after 1 min the 
        }, 60000);
    });



</script>


<?php
include('../Database/Connection.php');
include('userRequest.php');

if ( !isset($_POST['username'], $_POST['password']) ) {
	exit('PLEASE FILL BOTH FIELDS!');
}

$con = getConnection();
$sql = 'SELECT * FROM voters WHERE username = ?';
if ($stmt = $con->prepare($sql)) {
	$stmt->bind_param('s', $_POST['username']);
	$stmt->execute();
    $result = $stmt->get_result();
    if ($result->num_rows > 0){
        if($row = $result->fetch_assoc()){
            if ($_POST['password'] === $row["password"]) {

                session_start();
                $_SESSION['loggedin'] = new userRequest($row["id"],$row["username"],$row["password"],$row["email"],$row["status"],$row["usertype"]);
                if(!empty($_POST["remember"])) {
                    $cookie_name = "username";
                    $cookie_value = $_SESSION['loggedin']->getUsername;
                    $cookie_time = 60 * 60 * 24 * 30;
                    $cookie_time_Onset = $cookie_time + time();
                    setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/");

                    $cookie_name = "password";
                    $cookie_value = $_SESSION['loggedin']->getPass;
                    $cookie_time = 60 * 60 * 24 * 30;
                    $cookie_time_Onset = $cookie_time + time();
                    setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/");


                } else {
                    setcookie("username","");
                    setcookie("password","");
                }
            if ($_SESSION['loggedin']->getType() == "voter") {
                echo "<script type='text/javascript'>"
                . " window.location.href='home.php';
            </script>";
            } else if ($_SESSION['loggedin']->getType() == "admin") {
                echo "<script type='text/javascript'>"
                . " window.location.href='admin.php?page=homee';
            </script>";
            }
        }
    }

    } else {
        echo "<script type='text/javascript'>
        alert('Incorrect username or password!');
         window.location.replace('Login.php');
        </script>";
    }
	$stmt->close();
}
?>