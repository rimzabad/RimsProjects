<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link href="../Styles/loadingPagecss.css?" rel="stylesheet" /> 
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
    </head>
    <body src="../Assets/Images/map.jpg">

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
session_start();
if (!isset($_SESSION['loggedin'])) {

    header('location : LoginForm.php');
}
$voted = "VOTED";
$username = $_SESSION['loggedin']->getUsername();
$conn = getConnection();
if(isset($_POST['vote'])){
    if (!empty($_POST['candidate'])){
        $cand = $_POST["candidate"];
        $conn = getConnection();
        $sql = "select * from voters where username=? AND status=?";
        if ($stmt = $conn->prepare($sql)) {
            $stmt->bind_param("ss", $username, $voted);
            $stmt->execute();
            $result = $stmt->get_result();
            if ($result->num_rows > 0){
                echo "<script type='text/javascript'>
                alert('You have already voted! Cannot Vote again');
                window.location.replace('candidates.php');
                </script>";
            }
            else{
                $sql1 = mysqli_query($conn, 'UPDATE candidate SET voteCount= voteCount + 1 where FirstName= "'.$_POST['candidate'].'"');
                $sql2 = mysqli_query($conn, 'UPDATE voters SET status = "VOTED" WHERE username= "'.$_SESSION['loggedin']->getUsername().'"');
                $sql3 = mysqli_query($conn, 'UPDATE voters SET voted = "'.$_POST['candidate'].'" WHERE username= "'.$_SESSION['loggedin']->getUsername().'"');
                
                if(!$sql1 && !$sql2){
                    echo "<script type='text/javascript'>
                    alert('ERRRORRRR!');
                    </script>";
                }
                else{
                    echo "<script type='text/javascript'>
                    alert('Congratulation! Your vote is submitted successfully');
                    window.location.replace('candidates.php');
                    </script>";
                }

            }
        }}}
else if(isset($_POST['result'])){?>
   <body src = "../Assets/Images/map.jpg">
<form method="POST" >
<h1>Voting results</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
            <th class="text-center">First Name</th>
            <th class="text-center">Last Name</th>
            <th class="text-center">Gender</th>
            <th class="text-center" >Votes</th>
            </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table id="tableee" cellpadding="0" cellspacing="0" border="0">
      <tbody>
      <?php

                    $sql = "select FirstName, LastName, Gender, voteCount from candidate order by voteCount desc;";
                    $result = mysqli_query($conn,"select SUM(voteCount) as val_sum from candidate;");
                    $row = mysqli_fetch_assoc($result); 
                    $total = $row['val_sum'];
                    if ($stmt = $conn->prepare($sql)) {
                        $stmt->execute();
                        $result = $stmt->get_result();
                        $stmt->close();
                        disconnect($conn);
                    }
 					while($row = $result->fetch_assoc()) {
				 ?>
				 <tr>

				 	<td>
				 		<?php echo $row['FirstName'] ?>
				 	</td>
				 	<td>
				 		<?php echo $row['LastName'] ?>
				 	</td>
				 	<td>
				 		<?php echo $row['Gender'] ?>
				 	</td>

                    <td class="result-bar" style= "width:<?=@round(($row['voteCount']/$total)*100)?>%">
                <?=@round(($row['voteCount']/$total)*100)?>%
				 	</td>

				 </tr>
                 <?php } ?>
			</tbody>
		</table>
         <?php } ?>
  </div>

</section>
</form>
</body>

<head>
<style>
                    /*
            ** Media Queries
            ***********************************************************/
            @media (max-width: 1000px) {
            .flex-grid {
                flex-direction: column;
            }

            .flex-grid > div {
            }
            }


            h1{
            font-size: 30px;
            color: #fff;
            text-transform: uppercase;
            font-weight: 300;
            text-align: center;
            margin-bottom: 15px;
            }
            table{
            width:100%;
            table-layout: fixed;
            }
            .tbl-header{
            background-color: black;
            opacity: 70%;
            }
            .tbl-content{
            height:300px;
            overflow-x:auto;
            background-color: black;
            opacity: 50%;
            margin-top: 0px;
            border: 1px solid rgba(255,255,255,0.3);
            align-items: left;z-index: 1;
            }
            th{
            padding: 20px 15px;
            text-align: center;
            font-weight: 500;
            font-size: 12px;
            color: #fff;
            text-transform: uppercase;
            }
            td{
            padding: 15px;
            
            text-align: center;
            vertical-align:middle;
            font-weight: 300;
            font-size: 12px;
            color: #fff;
            border-bottom: solid 1px rgba(255,255,255,0.1);
            }


            /* demo styles */

            @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
            body{
                background-size: cover;
                background-image: url("../Assets/Images/map.jpg");
                padding: 100px;
                background-position: center center;
                font-family: 'Roboto', sans-serif;
            }
            section{
            margin: 50px;
            }


            /* follow me template */
            .made-with-love {
            margin-top: 40px;
            padding: 10px;
            clear: left;
            text-align: center;
            font-size: 10px;
            font-family: arial;
            color: black;
            }
            .made-with-love i {
            font-style: normal;
            color: #F50057;
            font-size: 14px;
            position: relative;
            top: 2px;
            }
            .made-with-love a {
            color: black;
            text-decoration: none;
            }
            .made-with-love a:hover {
            text-decoration: underline;
            }


            /* for custom scrollbar for webkit browser*/

            ::-webkit-scrollbar {
                width: 6px;
            } 
            ::-webkit-scrollbar-track {
                -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5); 
            } 
            ::-webkit-scrollbar-thumb {
                -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5); 
            }

            .container-contact100-form-btn {
                width: 100%;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                padding-top: 17px;
            }
            
            .contact100-form-btn {
                font-family: Montserrat-Bold;
                font-size: 12px;
                color: white;
                line-height: 1.2;
                text-transform: uppercase;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 0 20px;
                min-width: 160px;
                height: 42px;
                border-radius: 21px;
                background: gray;
                box-shadow: 0 10px 20px 0px black;
                -moz-box-shadow: 0 10px 20px 0px black;
                -webkit-box-shadow: 0 10px 20px 0px black;
                -o-box-shadow: 0 10px 20px 0px black;
                -ms-box-shadow: 0 10px 20px 0px black;
            
                -webkit-transition: all 0.4s;
                -o-transition: all 0.4s;
                -moz-transition: all 0.4s;
                transition: all 0.4s;
  }
  
            .contact100-form-btn:hover {
                box-shadow: 0 10px 30px 0px rgba(51, 51, 51, 0.5);
                -moz-box-shadow: 0 10px 30px 0px rgba(51, 51, 51, 0.5);
                -webkit-box-shadow: 0 10px 30px 0px rgba(51, 51, 51, 0.5);
                -o-box-shadow: 0 10px 30px 0px rgba(51, 51, 51, 0.5);
                -ms-box-shadow: 0 10px 30px 0px rgba(51, 51, 51, 0.5);
            }

            .cent{
                position: absolute;
                left:54%;
            }
            .res{
                position: absolute;
                left:36%;
            }

            .result-bar {
            margin-top: 3px;
                display: flex;
                height: 7px;
                min-width: 4%;
                background-color: gray;
                border-radius: 5px;
                font-size: 11px;
                color: #FFFFFF;
                justify-content: center;
                align-items: center;
            }
</head>