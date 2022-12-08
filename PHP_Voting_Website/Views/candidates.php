<html>
<head>
    <link rel="stylesheet" type="text/css" href="../Styles/adminStyle.css">
    <script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
 <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
 <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
    <script>
        $("input[name='candidate']").change(function(){
    
        });
    </script>
    <style>
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


            body {
            }

            .searchBox {
                position: absolute;
                top: 10%;
                left: 20%;
                transform:  translate(-50%,50%);
                background: #2f3640;
                height: 40px;
                border-radius: 40px;
                padding: 10px;

            }

            .searchBox:hover > .searchInput {
                width: 240px;
                padding: 0 6px;
            }

            .searchBox:hover > .searchButton {
            background: white;
            color : #2f3640;
            }

            .searchButton {
                color: white;
                float: right;
                width: 40px;
                height: 40px;
                border-radius: 50%;
                background: #2f3640;
                display: flex;
                justify-content: center;
                align-items: center;
                transition: 0.4s;
            }

            .searchInput {
                border:none;
                background: none;
                outline:none;
                float:left;
                padding: 0;
                color: white;
                font-size: 16px;
                transition: 0.4s;
                line-height: 40px;
                width: 0px;

            }

            @media screen and (max-width: 620px) {
            .searchBox:hover > .searchInput {
                width: 150px;
                padding: 0 6px;
            }
            }

            .pagination {
                display: block;
                width:50%;
                margin: 0 auto;
                text-align: center;
                }


                .pagination a {
                color: white;

                padding: 8px 16px;
                text-decoration: none;
                }
                .pagination a.active {
                background-color: gray;
                color: white;
                border-radius: 5px;
                }
                .pagination a:hover:not(.active) {
                background-color: #ddd;
                color: black;
                border-radius: 5px;
            }
</style>

</head>
<body src = "../Assets/Images/map.jpg">
<section>  
<div class="searchBox">

<input class="searchInput"  id="search" type="text" name="" placeholder="Search">
<button class="searchButton" href="#">
    <i class="material-icons">
        search
    </i>
</button>
</div>
<h1>Vote For Your Favorite Candidate</h1>

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

            $results_per_page = 3;
            //function to query and select all events and return the result
            $number_of_results = $result->num_rows;
            $number_of_pages = ceil($number_of_results / $results_per_page);
            if (!isset($_GET['page'])) {
                $page = 1;
            } else {
                $page = $_GET['page'];
            }
            $this_page_first_result = ($page - 1) * $results_per_page;
            $conn = getConnection();

            $sql2 = "select candidate_id, FirstName, LastName, Gender from candidate LIMIT ?,?;";
            if ($st = $conn->prepare($sql2)) {
            $st->bind_param("dd", $this_page_first_result, $results_per_page);
            $st->execute();
            $result2 = $st->get_result();
            $st->close();
            disconnect($conn);
            
            }

            while($row = $result2->fetch_assoc()) {
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
            <td>
                <a href="view_profile.php?id=<?php echo $row["candidate_id"]; ?>" class="contact100-form-btn"name="view">View Profile</a>
            </td>
            </tr>
            <?php } ?>



    </tbody>
</table>            
<div class="pagination">
    <?php
    for ($page = 1; $page <= $number_of_pages; $page++) {
    if (!isset($_GET['page'])) {
    $variable = 1;
    } else {
        $variable = $_GET['page'];
    }

    if ($page == $variable) {
        echo '<a class="active" href="candidates.php?page=' . $page . '">' . $page . '</a> ';
    } else {
        echo '<a href="candidates.php?page=' . $page . '">' . $page . '</a> ';
    }
}
    ?>
</div>

  </div>

</section>

 <div class="cent"><button class="contact100-form-btn" name="vote">Vote</button></div>
 <div class="res"><button class="contact100-form-btn" name="result">View Result</button></div>
</form>
<div class="made-with-love">
  Vote
  <i>♥</i>
  <a target="_blank" href="">Vote</a>
</div>
<body>

<script>  
      $(document).ready(function(){  
           $('#search').keyup(function(){  
                search_table($(this).val());  
           });  
           function search_table(value){  
                $('#tableee tr').each(function(){  
                     var found = 'false';  
                     $(this).each(function(){  
                          if($(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0)  
                          {  
                               found = 'true';  
                          }  
                     });  
                     if(found == 'true')  
                     {  
                          $(this).show();  
                     }  
                     else  
                     {  
                          $(this).hide();  
                     }  
                });  
           }  
      });  

      
      
 </script>  
     
</html>