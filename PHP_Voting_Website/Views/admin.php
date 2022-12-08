
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Admin | Voting System</title>
  <?php
	session_start();
    include ('../Database/Connection.php');
    $conn = getConnection();
    if(!isset($_SESSION['loggedin']))
    header('location:LoginForm.php');
    /*else if($_SESSION['loggedin']->getType()=='admin')*/
    if (!isset($_GET['page'])) {
        $table = 'users';
    } else
        $table = $_GET['page'];
    ?>



<meta content="" name="description">
<meta content="" name="keywords">
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<link rel="stylesheet" href="assets/font-awesome/css/all.min.css">


<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="assets/vendor/venobox/venobox.css" rel="stylesheet">
<link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link href="assets/DataTables/datatables.min.css" rel="stylesheet">


<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="assets/css/jquery-te-1.4.0.css">

<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/DataTables/datatables.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/jquery.easing/jquery.easing.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
<script src="assets/vendor/venobox/venobox.min.js"></script>
<script src="assets/vendor/waypoints/jquery.waypoints.min.js"></script>
<script src="assets/vendor/counterup/counterup.min.js"></script>
<script src="assets/vendor/owl.carousel/owl.carousel.min.js"></script>
<script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="assets/font-awesome/js/all.min.js"></script>
<script type="text/javascript" src="assets/js/jquery-te-1.4.0.min.js" charset="utf-8"></script>


</head>
<style>
	body{
        background: #80808045;
        background-image: url("../Assets/Images/map.jpg");
        background-size: cover;
        background-position: center ;
        background-repeat: no-repeat;
  }

    .pagination {
                display: block;
                width:50%;
                margin: 0 auto;
                text-align: center;
            }

    .pagination a {
        color: black;

        padding: 8px 16px;
        text-decoration: none;
    }

    .pagination a.active {
        background-color: #58555A;
        color: white;
        border-radius: 5px;
    }

    .pagination a:hover:not(.active) {
        background-color: #ddd;
        border-radius: 5px;
    }

</style>


	<style>
	.logo {
    margin: auto;
    font-size: 20px;
    background: white;
    padding: 5px 11px;
    border-radius: 50% 50%;
    color: #000000b3;
}
</style>
<body src="">
<nav class="navbar navbar-dark bg-dark fixed-top " style="padding:0;">
  <div class="container-fluid mt-2 mb-2">
  	<div class="col-lg-12">
  		<div class="col-md-1 float-left" style="display: flex;">
  			<div class="logo">
  				<i class="fa fa-poll-h"></i>
  			</div>
  		</div>
	  	<div class="col-md-2 float-right text-white">
	  		<a href="logout.php" class="text-white">Administrator <i class="fa fa-power-off"></i></a>
	    </div>
    </div>
  </div>
  
</nav>	


<nav id="sidebar" class='mx-lt-5 bg-dark' >
<div class="sidebar-list">
    <a href="admin.php?page=homee" class="nav-item nav-home"><span class='icon-field'><i class="fa fa-home"></i></span> Home</a>
    <a href="admin.php?page=users" class="nav-item nav-users"><span class='icon-field'><i class="fa fa-users"></i></span> Users</a>
    <a href="admin.php?page=msg" class="nav-item nav-users"><span class='icon-field'><i class="fa fa-message"></i></span> Messages</a>    
</div>

</nav>
<script>
    $('.nav-home').addClass('active');
</script>

 
</script>  <div class="toast" id="alert_toast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-body text-white">
    </div>
  </div>
  <main id="view-panel" >
    
  <?php
  
        if ($table == "homee") {
            ?>
    <div class="row">
		<div class="col-lg-12">
			<div class="card col-md-4 offset-2 bg-info float-left">
				<div class="card-body text-white">
                    
					<h4><b>Voters</b></h4>
					<hr>
					<span class="card-icon"><i class="fa fa-users"></i></span>
					<h3 class="text-right"><b><?php echo $conn->query('SELECT * FROM voters where userType = "voter" ')->num_rows ?></b></h3>
				</div>
			</div>
			<div class="card col-md-4 offset-2 bg-primary ml-4 float-left">
				<div class="card-body text-white">
					<h4><b>Voted</b></h4>
					<hr>
					<span class="card-icon"><i class="fa fa-user-tie"></i></span>
					<h3 class="text-right"><b><?php echo $conn->query('SELECT * FROM voters where userType = "voter" and status="VOTED"')->num_rows; }?></b></h3>
				</div>
			</div>
		</div>
	</div>
    <?php
    if ($table == "users") {
 
        ?>
        
        <div class="container-fluid">
            
            <div class="row">
            <div class="col-lg-12">
                    <button class="btn btn-primary float-right btn-sm" id="new_user"><i class="fa fa-plus"></i> New candidate</button>
            </div>
            </div>
            <br>
            <div class="row">
                <div class="card col-lg-12">
                    <div class="card-body">
                        <table class="table-striped table-bordered col-md-12">
                    <thead>
                        <tr>
                            <th class="text-center">#</th>
                            <th class="text-center">First Name</th>
                            <th class="text-center">Last Name</th>
                            <th class="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php

                             $sql = "SELECT candidate_id, FirstName, LastName FROM candidate order by FirstName asc;";

                             if ($stmt = $conn->prepare($sql)) {
                                $stmt->execute();
                                $result = $stmt->get_result();
                                $stmt->close();
                                disconnect($conn);
                            }
                             while($row= $result->fetch_assoc()):
                         ?>
                         <tr>
                             <td>
                                 <?php echo $row['candidate_id'] ?>
                             </td>
                             <td>
                                 <?php echo $row['FirstName'] ?>
                             </td>
                             <td>
                                 <?php echo $row['LastName'] ?>
                             </td>
                             <td>
                                 <center>
                                        <div class="btn-group">
                                          <button type="button" class="btn btn-primary">Action</button>
                                          <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <span class="sr-only">Toggle Dropdown</span>
                                          </button>
                                          <div class="dropdown-menu">
                                            <a class="dropdown-item edit_user" href="javascript:void(0)" data-id = '<?php echo $row['candidate_id'] ?>'>Edit</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item delete_user" href="javascript:void(0)" data-id = '<?php echo $row['candidate_id'] ?>'>Delete</a>
                                          </div>
                                        </div>
                                 </center>
                             </td>
                         </tr>
                        <?php endwhile;} ?>
                    </tbody>
                </table>
                    </div>
                </div>
            </div>
        
        </div>

<?php 
if ($table == "msg") {

 ?>
 
 <div class="container-fluid">
     
     <div class="row">
     <div class="col-lg-12">
             <button class="btn btn-primary float-right btn-sm" id="new_user"><i class="fa fa-plus"></i> New candidate</button>
     </div>
     </div>
     <br>
     <div class="row">
         <div class="card col-lg-12">
             <div class="card-body">
                 <table class="table-striped table-bordered col-md-12">
             <thead>
                 <tr>
                     <th class="text-center">Name</th>
                     <th class="text-center">Phone Number</th>
                     <th class="text-center">Email</th>
                     <th class="text-center">Message</th>
                 </tr>
             </thead>
             <tbody>
                 <?php

                      $sql = "SELECT name,phone,email,message from contact ;";

                      if ($stmt = $conn->prepare($sql)) {
                         $stmt->execute();
                         $result = $stmt->get_result();
                         $stmt->close();
                         disconnect($conn);
                     }
                      while($row= $result->fetch_assoc()):
                  ?>
                  <tr>
                      <td>
                          <?php echo $row['name'] ?>
                      </td>
                      <td>
                          <?php echo $row['phone'] ?>
                      </td>
                      <td>
                          <?php echo $row['email'] ?>
                      </td>
                      <td>
                        <?php echo $row['message'] ?>
                      </td>
                  </tr>
                 <?php endwhile;} ?>
             </tbody>
         </table>
             </div>
         </div>
     </div>
 
 </div>
        <script>
            
        $('#new_user').click(function(){
            uni_modal('New Candidate','manage_candidates.php')
        })
        
        </script>

  </main>

  <div id="preloader"></div>
  <a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

<div class="modal fade" id="confirm_modal" role='dialog'>
    <div class="modal-dialog modal-md" role="document">
      <div class="modal-content">
        <div class="modal-header">
        <h5 class="modal-title">Confirmation</h5>
      </div>
      <div class="modal-body">
        <div id="delete_content"></div>
      </div>

      </div>
    </div>
  </div>
  <div class="modal fade" id="uni_modal" role='dialog'>
    <div class="modal-dialog modal-md" role="document">
      <div class="modal-content">
        <div class="modal-header">
        <h5 class="modal-title"></h5>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" name="submit" class="btn btn-primary" id='submit' onclick="$('#uni_modal form').submit()">Save</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
      </div>
      </div>
    </div>
  </div>
</body><?php?>
<script>
	window.start_load = function(){
    $('body').prepend('<di id="preloader2"></di>')
  }
  window.end_load = function(){
    $('#preloader2').fadeOut('fast', function() {
        $(this).remove();
      })
  }

  window.uni_modal = function($title = '' , $url=''){
    start_load()
    $.ajax({
        url:$url,
        error:err=>{
            console.log()
            alert("An error occured")
        },
        success:function(resp){
            if(resp){
                $('#uni_modal .modal-title').html($title)
                $('#uni_modal .modal-body').html(resp)
                $('#uni_modal').modal('show')
                end_load()
            }

        }
    })
}
window._conf = function($msg='',$func='',$params = []){
     $('#confirm_modal #confirm').attr('onclick',$func+"("+$params.join(',')+")")
     $('#confirm_modal .modal-body').html($msg)
     $('#confirm_modal').modal('show')
  }
   window.alert_toast= function($msg = 'TEST',$bg = 'success'){
      $('#alert_toast').removeClass('bg-success')
      $('#alert_toast').removeClass('bg-danger')
      $('#alert_toast').removeClass('bg-info')
      $('#alert_toast').removeClass('bg-warning')

    if($bg == 'success')
      $('#alert_toast').addClass('bg-success')
    if($bg == 'danger')
      $('#alert_toast').addClass('bg-danger')
    if($bg == 'info')
      $('#alert_toast').addClass('bg-info')
    if($bg == 'warning')
      $('#alert_toast').addClass('bg-warning')
    $('#alert_toast .toast-body').html($msg)
    $('#alert_toast').toast({delay:3000}).toast('show');
  }
  $(document).ready(function(){
    $('#preloader').fadeOut('fast', function() {
        $(this).remove();
      })
  })
</script>	

</html>