<html xmlns=“http://www.w3.org/1999/xhtml”>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="  ">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>Vote For Lebanon</title>
    <link rel="stylesheet" type="text/css" href="../Styles/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/bfd69857a9.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>

</head>
<body src="../Assets/Images/map.jpg">
<section class="about" id="about" >    
 
    <div class="row">
        <div class="col50">
            <h2 class="title"><span>S</span>eed of <span>C</span>hange</h2>
            <p style="font-size: 20px;font-family:  Garamond;">Given Lebanon’s ongoing economic and social crises,
             Lebanese expats see hope in the 2022 parliamentary election, set to take place on March 27.<br>
             Lebanon’s parliamentary election will be the first held since the popular uprising in late 2019, when hundreds of thousands took to the streets demanding an end to the entrenched 
             political and economic patronage system, blamed for multiple dire crises engulfing the tiny Mediterranean country.<br>
             A spiraling economy has plunged three-quarters of Lebanese into poverty as their local currency has lost some 93 percent of its value. Medicine and electricity are in short supply, while food prices have skyrocketed due to inflation. The economic meltdown, coinciding with 
             the coronavirus outbreak, has posed the worst threat to Lebanon’s stability since the 1975-1990 civil war, observers say.<br>
             <br>“To be eligible you need to believe in the unity of Lebanon,” said Khatib, “the exclusivity of arms for the state, fight corruption. 
             People can say “I represent the thawra and I want to run. Anyone might get 5 votes, 10,000 votes or 100,000 votes.”
<br>
</p>
        </div>
        <div class="col50">
            <div class="imgBx">
                <div class="w3-content w3-section" style="max-width:400px">
                    <img class="mySlides" src="../Assets/Images/mapp.jpg" style="width:100%">
                    <img class="mySlides" src="../Assets/Images/vote.jpg" style="width:100%">
                    <img class="mySlides" src="../Assets/Images/flag.jpg" style="width:100%">
                    <img class="mySlides" src="../Assets/Images/vote1.png" style="width:100%">
                    <img class="mySlides" src="../Assets/Images/mapleb.jpg" style="width:100%">
                    <img class="mySlides" src="../Assets/Images/vote2.jpg" style="width:100%">
                    
                  </div>
                  
                  <script>
                  var myIndex = 0;
                  carousel();
                  function carousel() {
                    var i;
                    var x = document.getElementsByClassName("mySlides");
                    for (i = 0; i < x.length; i++) {
                      x[i].style.display = "none";  
                    }
                    myIndex++;
                    if (myIndex > x.length) {myIndex = 1}    
                    x[myIndex-1].style.display = "block";  
                    setTimeout(carousel, 2000); 
                  }
                  </script>
            </div>
        </div>

    </div>


</section> 

<script>
$(function(){
  $("#nav-placeholder").load("navbar.php");
});
</script>

</body>
</html>