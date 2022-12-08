


<html xmlns=“http://www.w3.org/1999/xhtml”>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="  ">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>Vote For Lebanon</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/bfd69857a9.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>


    <style>
        .bg {
            background-color: rgb(238, 238, 238);
            background-image: url("../Assets/Images/leb.mp4");
            min-height: 100%; 
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
            #myVideo {
            position: absolute;
            right: 0;
            bottom: 0;
            min-width: 100%;
            min-height: 100%;
        }

        #nav-placeholder{
            position: relative;
        }
    </style>
</head>

<body class="bg">
<video autoplay muted loop id="myVideo" background-position: center center;>
  <source src="../Assets/Images/leb.webm" type="video/webm">
</video>
<header>

<div id="nav-placeholder">
</div>

<script>
$(function(){
  $("#nav-placeholder").load("navbar.php");
});
</script>
<!--end of Navigation bar-->
</body>
</html>