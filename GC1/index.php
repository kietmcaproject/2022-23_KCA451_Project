<?php
session_start();
error_reporting(0);
include('includes/dbconnection.php');
?>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <title>Campus Recruitment Management System|| Home Page</title>
   
    <script>
        addEventListener("load", function() {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link href="css/zoomslider.css" rel='stylesheet' type='text/css' />
    <link href="css/style6.css" rel='stylesheet' type='text/css' />
    <link href="css/style.css" rel='stylesheet' type='text/css' />
    <link href="css/fontawesome-all.css" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Dosis:200,300,400,500,600,700" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Quicksand:300,400,500,700" rel="stylesheet">
</head>

<body>
    <!-- banner-inner -->
    <div id="demo-1" data-zs-src='["images/1.jpg", "images/2.jpg","images/3.jpg", "images/4.jpg"]' data-zs-overlay="dots">
        <div class="demo-inner-content">
            <div class="header-top">
               <?php include_once('includes/header.php');?>
            </div>
            </div>
    </div>
    <!-- banner-text -->
 
    <!--/process-->
    <section class="banner-bottom-wthree pb-lg-5 pb-md-4 pb-3">
        <div class="container">
            <div class="inner-sec-w3ls py-lg-5  py-3">
			<!---728x90--->
                <h3 class="tittle text-center mb-lg-4 mb-3">
			
                    <span>Some Info</span>Latest Job flow-positions</h3>
					<!---728x90--->
                <div class="tabs mt-5">
                    <ul class="nav nav-pills my-4" id="pills-tab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Recent Jobs</a>
                        </li>
                       

                    </ul>
                    <div class="tab-content" id="pills-tabContent">
                        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                            <div class="menu-grids mt-4">
                                <div class="row t-in">
                                    <div class="col-lg-8 text-info-sec">
                                        <!--/job1-->

                                       
                                            <?php

                    
$ret=mysqli_query($con,"select tblcompany.ID as cid, tblcompany.CompanyName,tblvacancy.ID,tblvacancy.JobTitle,tblvacancy.NoofOpenings,tblvacancy.MonthlySalary,tblvacancy.JobLocation,tblvacancy.LastDate from tblcompany join tblvacancy on tblcompany.ID=tblvacancy.CompanyID order by rand() limit 6");
$cnt=1;
while ($row=mysqli_fetch_array($ret)) {

?> <div class="job-post-main row">
 <div class="col-md-9 job-post-info text-left">
<div class="job-post-icon">
<i class="fas fa-briefcase"></i>
</div>

<div class="job-single-sec">
<h4>
<?php echo $row['JobTitle'];?>
</h4>
<p class="my-2"><?php echo $row['CompanyName'];?></p>
<ul class="job-list-info d-flex">
<li>
<i class="fas fa-users"></i> <?php echo $row['NoofOpenings'];?></li>
<li>
<i class="fas fa-dollar-sign"></i> <?php echo $row['MonthlySalary'];?>/Month</li>
<li>
<i class="fas fa-map-marker-alt"></i><?php echo $row['JobLocation'];?></li>
<li><i class="fas fa-calendar"></i> <?php echo $row['LastDate'];?></li>
</ul>

</div>
<div class="clearfix"></div>
</div>
<div class="col-md-3 job-single-time text-right">
<span class="job-time">
 <a href="single-job-details.php?viewid=<?php echo $row['ID'];?>" class="aply-btn "> Details</span></a>

                                            </div>
                                            
                                        </div><br />
                                    <?php } ?>
                                   <a href="jobs-listed.php" class="aply-btn ">More..</a>
                             
                                    </div>
                                    <div class="col-lg-4 text-info-sec">
                                        <img src="images/job-1.jpg" alt=" " class="img-fluid" />
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                            <div class="menu-grids mt-4">
                                <div class="row t-in">
                                    <div class="col-lg-4 text-info-sec">
                                        <img src="images/job-2.jpg" alt=" " class="img-fluid" />
                                    </div>
                                    <div class="col-lg-8 text-info-sec">
                                        
 <?php

                    
$ret=mysqli_query($con,"select tblcompany.ID as cid, tblcompany.CompanyName,tblvacancy.ID,tblvacancy.JobTitle,tblvacancy.NoofOpenings,tblvacancy.MonthlySalary,tblvacancy.JobLocation,tblvacancy.LastDate from tblcompany join tblvacancy on tblcompany.ID=tblvacancy.CompanyID order by rand() limit 6");
$cnt=1;
while ($row=mysqli_fetch_array($ret)) {

?>
                                        <div class="job-post-main row">
                                            <div class="col-md-9 job-post-info text-left">
                                                <div class="job-post-icon">
                                                    <i class="fas fa-briefcase"></i>
                                                </div>
                                                <div class="job-single-sec">
                                                    <h4>
                                                       <?php echo $row['JobTitle'];?>
                                                    </h4>
                                                    <p class="my-2"><?php echo $row['CompanyName'];?></p>
                                                    <ul class="job-list-info d-flex">
                                                        <li>
                                                            <i class="fas fa-users"></i> <?php echo $row['NoofOpenings'];?></li>
                                                            <li>
                                                             <i class="fas fa-dollar-sign"></i> <?php echo $row['MonthlySalary'];?>/Month</li>
                                                        <li>
                                                            <i class="fas fa-map-marker-alt"></i><?php echo $row['JobLocation'];?></li>
                                                        
                                                        <li>
                                                            <i class="fas fa-calendar"></i> <?php echo $row['LastDate'];?></li>
                                                    </ul>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="col-md-3 job-single-time text-right">
                                                <span class="job-time">
                                                    <a href="single-job-details.php?viewid=<?php echo $row['ID'];?>" class="aply-btn "> Details</span>
                                                     <?php if($_SESSION['crmsuid']==""){?>
                                                 <a href="user/login.php" class="aply-btn ">Appy Now</a>
                                                <?php } ?>
                                            </div>
                                            
                                        </div><br /><?php } ?>
                                        
                               <a href="jobs-listed.php" class="aply-btn ">More..</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--//preocess-->

    <!--job -->
    <section class="banner-bottom-wthree mid py-lg-5 py-3">
        <div class="container">
            <div class="inner-sec-w3ls py-lg-5  py-3">
                <div class="mid-info text-center pt-3">
                    <h3 class="tittle text-center cen mb-lg-5 mb-3">
                        <span>Some Info</span>Make a Difference with Your Online Resume!</h3>
                    <p></p>
                    <div class="resume">
                        <a href="user/user-signup.php">
                            <i class="far fa-user"></i> Create Acccount</a>
                    </div>
                </div>

            </div>
        </div>
    </section>
    <!--//job -->


    <!--footer -->
   <?php include_once('includes/footer.php');?>
    <!-- //footer -->

    <!--/slider-->
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr-2.6.2.min.js"></script>
    <script src="js/jquery.zoomslider.min.js"></script>
    <!--//slider-->
    <!--search jQuery-->
    <script src="js/classie-search.js"></script>
    <script src="js/demo1-search.js"></script>
    <!--//search jQuery-->

    <script>
        $(document).ready(function() {
            $(".dropdown").hover(
                function() {
                    $('.dropdown-menu', this).stop(true, true).slideDown("fast");
                    $(this).toggleClass('open');
                },
                function() {
                    $('.dropdown-menu', this).stop(true, true).slideUp("fast");
                    $(this).toggleClass('open');
                }
            );
        });
    </script>
    <!-- //dropdown nav -->
    <!-- password-script -->
    <script>
        window.onload = function() {
            document.getElementById("password1").onchange = validatePassword;
            document.getElementById("password2").onchange = validatePassword;
        }

        function validatePassword() {
            var pass2 = document.getElementById("password2").value;
            var pass1 = document.getElementById("password1").value;
            if (pass1 != pass2)
                document.getElementById("password2").setCustomValidity("Passwords Don't Match");
            else
                document.getElementById("password2").setCustomValidity('');
            //empty string means no validation error
        }
    </script>
    <!-- //password-script -->

    <!-- stats -->
    <script src="js/jquery.waypoints.min.js"></script>
    <script src="js/jquery.countup.js"></script>
    <script>
        $('.counter').countUp();
    </script>
    <!-- //stats -->

    <!-- //js -->
    <script src="js/bootstrap.js"></script>
    <!--/ start-smoth-scrolling -->
    <script src="js/move-top.js"></script>
    <script src="js/easing.js"></script>
    <script>
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event) {
                event.preventDefault();
                $('html,body').animate({
                    scrollTop: $(this.hash).offset().top
                }, 900);
            });
        });
    </script>
    <script>
        $(document).ready(function() {
            /*
            						var defaults = {
            							  containerID: 'toTop', // fading element id
            							containerHoverID: 'toTopHover', // fading element hover id
            							scrollSpeed: 1200,
            							easingType: 'linear' 
            						 };
            						*/

            $().UItoTop({
                easingType: 'easeOutQuart'
            });

        });
    </script>
    <!--// end-smoth-scrolling -->
</body>

</html>