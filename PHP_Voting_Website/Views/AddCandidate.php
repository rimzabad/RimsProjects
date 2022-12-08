<?php
include ('../Database/Connection.php');
include('userRequest.php');
session_start();

if (!isset($_SESSION['loggedin'])) {

	header('location : LoginForm.php');
}

	$first = $_POST["name"];
	$last = $_POST["username"];
	$gender = $_POST["password"];
	if(empty($id)){
	$conn = getConnection();
	$sql = "select * from candidate where FirstName LIKE '".$first."' and LastName LIKE '".$last."';";
	$stmt = $conn->prepare($sql);
	$stmt->execute();
	$result = $stmt->get_result();
	$stmt->close();
	disconnect($conn);

	if ($result->num_rows === 0) {
		$conn = getConnection();
		$sql = "insert into candidate(FirstName,LastName,Gender) values(?,?,?)";
		$stmt = $conn->prepare($sql);
		$stmt->bind_param("sss", $first, $last, $gender);
		
		$stmt->execute();
		$stmt->close();
		disconnect($conn);
		echo "<script language='javascript'>
			alert('Candidate Successfully Added!');
			window.location.replace('admin.php');
			</script>";
	}
	} else {
		echo "<script language='javascript'>
			alert('Something went wrong!');
			window.location.replace('admin.php');
			</script>";
	}
?>