<?php 
include('../Database/Connection.php');
include('userRequest.php');
session_start();

if (!isset($_SESSION['loggedin'])) {

    header('location : LoginForm.php');
}
$username = $_SESSION['loggedin']->getUsername();
?>
<head>
<link rel="stylesheet" href="../Styles/adminStyle.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<nav>
<ul style="color:white;">
            <li><span>Voting System</span></li>

            <li><i class="fas fa-users" style="padding-bottom: 12px; "></i><a href="#">Home</a></li>
            <li><i class="fas fa-briefcase" style="padding-bottom: 12px; "></i><a href="About.php">About</a></li>
            <li><i class="fas  fa-users" style="padding-bottom: 12px; "></i><a href="candidates.php">Nomination</a></li>
            <li><i class="fa  fa-envelope" style="padding-bottom: 12px; "></i><a href="ContactForm.php"> Contact</a></li>
            <li><i class="fa  fa-power-off" style="padding-bottom: 12px; "></i><a href="logout.php"> Logout from <?php echo $username?></a></li>
        </ul>

    </nav> 