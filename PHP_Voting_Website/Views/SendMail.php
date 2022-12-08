<?php
use PHPMailer\PHPMailer\PHPMailer;

if(isset($_POST['name']) && isset($_POST['email'])){
    require_once 'PHPMailer/SMTP.php';
    require_once 'PHPMailer/Exception.php';
    require_once 'PHPMailer/PHPMailer.php';

    $name = $_POST['name'];
    $email = $_POST['email'];
    $message = $_POST['message'];
    $phone = $_POST['phone'];

    $mail = new PHPMailer(true);
    try{
        $mail->SMTPOptions = array(
        'ssl' => array(
        'verify_peer' => false,
        'verify_peer_name' => false,
        'allow_self_signed' => true)
        );
        
    $mail->isSMTP();
    $mail->Host = 'smtp.gmail.com';
    $mail->SMTPAuth= true;
    $mail->Username = 'voteeforlebanonn@gmail.com';
    $mail->Password ='Vote$123leb';
    $mail->SMTPSecure ="ssl";
    $mail->Port = '465';
    $mail->setFrom('voteeforlebanonn@gmail.com'); 
    $mail->addAddress($email); 
    $mail->isHTML (true);
    $mail->Subject ='Voting System';
    $mail->Body = '<h2> Dear '.$name." </h2>".'<p> Your message has been recieved. <br> Regards</p>';
    $mail->send();

    include ('../Database/Connection.php');
    $conn = getConnection();
    $sql = "insert into contact(name,phone,email,message) values(?,?,?,?)";
    if($stmt = $conn->prepare($sql)){
    $stmt->bind_param("ssss", $name, $phone, $email, $message);
    $stmt->execute();
    $stmt->close();
    disconnect($conn);
    echo "<script language='javascript'>
        alert('okeh!');
        window.location.replace('ContactForm.php');
        </script>";}
    } catch (Exception $e){
        echo $e->getMessage();
    }

    echo "<script language='javascript'>
    window.location.replace('ContactForm.php');
    </script>";
}



?>