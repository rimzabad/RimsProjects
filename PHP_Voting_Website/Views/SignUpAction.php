<?php
include('../Database/Connection.php');

if (isset($_POST['register'])) {

    $idnational = $_POST['idnumber'];
    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $c_password = $_POST['confirm'];

    if (!ValidateIfEmpty($firstname, $lastname,  $email, $idnational,$username , $password, $c_password)) {
        echo "<script type='text/javascript'>
        alert('PLEASE CHECK ENTERED VALUES!');
         window.location.replace('SignUpForm.php');
        </script>";
    } else {
        $conn = getConnection();
        $sql = "select * from voters where email LIKE '".$email."' or nationalid = $idnational;";
        $stmt = $conn->prepare($sql);
        $stmt->execute();
        $result = $stmt->get_result();
        $stmt->close();
        disconnect($conn);

        if ($result->num_rows === 0) {
            $conn = getConnection();
            $sql = "insert into voters (nationalid,firstname,lastname,email,username,password) values(?,?,?,?,?,?);";
            $stmt = $conn->prepare($sql);
            $stmt->bind_param("isssss", $idnational,$firstname,$lastname, $email, $username, $password);
            $stmt->execute();
            $stmt->close();
            disconnect($conn);
            echo "<script language='javascript'>
                window.location.replace('LoginForm.php');
                </script>";

        } else {
            echo "<script language='javascript'>
                alert('Email or National ID already in use. Please try again');
                window.location.replace('SignUpForm.php');
                </script>";
        }
    }
}

function ValidateIfEmpty( $firstname, $lastname, $email, $idnational, $username ,$password, $c_password) {
    if (empty($firstname) ||empty($lastname) || empty($username) || empty($firstname) || empty($email) ||  empty($password) || !isConfirmPassword($password, $c_password) || !validateEmail($email) || !validatePassword($password) ){
        return false;
    } else {
        return true;
    }
}

function validateEmail($email) {
    if (!preg_match(' /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/', $email))
        return false;
    return true;
}

function validatePassword($password) {
    if (!preg_match('/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/', $password))
        return false;
    return true;
}

function isConfirmPassword($password, $c_password) {
    if (strcmp($password, $c_password) == 0)
        return true;
    return false;
}
?>