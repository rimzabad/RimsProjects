
<html>
<body src="../Assets/Images/map.jpg">
<?php
    include ('../Database/Connection.php');
    if (isset($_GET["candidate_id"])) {
        $id = $_GET["candidate_id"];
        
    $conn = getConnection();
    $sql = "select FirstName, LastName, Information from candidate where candidate_id= ?;";
    if ($stmt = $conn->prepare($sql)) {
        $stmt->bind_param("i", $id);
        $stmt->execute();
        $result = $stmt->get_result();
        $stmt->close();
        disconnect($conn);
    
    }
    while ($row = $result->fetch_assoc()) {
        ?>
        <div id="popup1" class="overlay">
            <div class="popup">
                <h2><?php $row['FirstName']." ". $row['LastName']?></h2>
                <a class="close" href="candidates.php">&times;</a>
                <div class="content">
                    <?php $row['Information'];}?>
                </div>
            </div>
        </div>
</body>
<?php } ?>        
<head>
            <style>

                .overlay {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                right: 0;
                background: rgba(0, 0, 0, 0.7);
                transition: opacity 500ms;
                visibility: hidden;
                opacity: 0;
                }
                .overlay:target {
                visibility: visible;
                opacity: 1;
                }

                .popup {
                margin: 70px auto;
                padding: 20px;
                background: #fff;
                border-radius: 5px;
                width: 30%;
                position: relative;
                transition: all 5s ease-in-out;
                }

                .popup h2 {
                margin-top: 0;
                color: #333;
                font-family: Tahoma, Arial, sans-serif;
                }
                .popup .close {
                position: absolute;
                top: 20px;
                right: 30px;
                transition: all 200ms;
                font-size: 30px;
                font-weight: bold;
                text-decoration: none;
                color: #333;
                }
                .popup .close:hover {
                color: #06D85F;
                }
                .popup .content {
                max-height: 30%;
                overflow: auto;
                }

                @media screen and (max-width: 700px){
                .box{
                    width: 70%;
                }
                .popup{
                    width: 70%;
                }
            }
            </style>
        </head>
</html>