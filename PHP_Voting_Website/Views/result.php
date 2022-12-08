<body src = "../Assets/Images/map.jpg">
<form method="POST" action="submit_vote.php">
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
            <th class="text-center" >#Candidate_id</th>
            <th class="text-center">First Name</th>
            <th class="text-center">Last Name</th>
            <th class="text-center">Gender</th>
            <th class="text-center">Action</th>
            </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table id="tableee" cellpadding="0" cellspacing="0" border="0">
      <tbody>
      <?php
                    include ('../Database/Connection.php');
                    
                    $conn = getConnection();
                    $sql = "select candidate_id, FirstName, LastName, Gender from candidate;";
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
				 		<?php echo $row['candidate_id']."  "?><input type="radio" name= "candidate" value="<?php echo $row['FirstName']?>">
				 	</td>

				 	<td>
				 		<?php echo $row['FirstName'] ?>
				 	</td>
				 	<td>
				 		<?php echo $row['LastName'] ?>
				 	</td>
				 	<td>
				 		<?php echo $row['Gender'] ?>
				 	</td>

				 </tr>
                 <?php } ?>
			</tbody>
		</table>
        
  </div>

</section>
</form>
</body>


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
