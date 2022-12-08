<?php
function getConnection(){
    $servername = "localhost";
    $dbusername = "root";
    $dbpassword = "";
    $dbname = "finalproject";
    try {
    $conn = new mysqli($servername, $dbusername, $dbpassword, $dbname) or die("Connection Failed: %s\n" . $conn->error);
    return $conn;
    } catch (PDOException $ex) {
        echo "Connection Failed: " . $ex->getMessage();
    }
}

function disconnect($conn) {
    $conn->close();
}
?>