<?php
session_start();
error_reporting(0);

include('includes/dbconnection.php');
?>
<!DOCTYPE html>
<html lang="zxx">

<head>
	<title>Campus Recruitment Management System|| Job Listed</title>
	
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
	<link href="css/style6.css" rel='stylesheet' type='text/css' />
	<link href="css/style.css" rel='stylesheet' type='text/css' />
	<link href="css/fontawesome-all.css" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Dosis:200,300,400,500,600,700" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Quicksand:300,400,500,700" rel="stylesheet">
</head>


<body>
	<!-- banner-inner -->
	<div id="demo-1" class="page-content">
		<div class="dotts">
			<div class="header-top">
			 <?php include_once('includes/header.php');?>
			</div>
			<!--/banner-info-w3layouts-->
			<div class="banner-info-w3layouts text-center">
			</div>
			<!--//banner-info-w3layouts-->
		</div>
	</div>
	<ol class="breadcrumb justify-content-left">
		<li class="breadcrumb-item">
			<a href="index.php">Home</a>
		</li>
		<li class="breadcrumb-item active">Jobs Listed</li>
	</ol>
	<!-- banner-text -->
	<!-- contact -->
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

//Getting default page number
        if (isset($_GET['pageno'])) {
            $pageno = $_GET['pageno'];
        } else {
            $pageno = 1;
        }
        // Formula for pagination
        $no_of_records_per_page = 6;
        $offset = ($pageno-1) * $no_of_records_per_page;
        $total_pages_sql = "SELECT COUNT(*) FROM tblvacancy";
$ret1=mysqli_query($con,"select COUNT(*) from  tblvacancy");
$total_rows = mysqli_fetch_array($ret1)[0];
$total_pages = ceil($total_rows / $no_of_records_per_page);
$sql = "SELECT * FROM tblvacancy LIMIT $offset, $no_of_records_per_page";

                    
$ret=mysqli_query($con,"select tblcompany.ID as cid, tblcompany.CompanyName,tblvacancy.ID,tblvacancy.JobTitle,tblvacancy.NoofOpenings,tblvacancy.MonthlySalary,tblvacancy.JobLocation,tblvacancy.LastDate from tblcompany join tblvacancy on tblcompany.ID=tblvacancy.CompanyID LIMIT $offset, $no_of_records_per_page");
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
                                                        
                                                        <li>
                                                            <i class="fas fa-calendar"></i> <?php echo $row['LastDate'];?></li>
                                                    </ul>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="col-md-3 job-single-time text-right">
                                                <span class="job-time">
    <a href="single-job-details.php?viewid=<?php echo $row['ID'];?>" class="aply-btn "> Details</span></a>

                                            </div>
                                            
                                        </div><br /><?php } ?>
                                   
                             
                                    </div>

                                    <div class="col-lg-4 text-info-sec">
                                        <img src="images/job-1.jpg" alt=" " class="img-fluid" />
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div align="center">
    <ul class="pagination" >
        <li><a href="?pageno=1"><strong>First></strong></a></li>
        <li class="<?php if($pageno <= 1){ echo 'disabled'; } ?>">
            <a href="<?php if($pageno <= 1){ echo '#'; } else { echo "?pageno=".($pageno - 1); } ?>"><strong style="padding-left: 10px">Prev></strong></a>
        </li>
        <li class="<?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
            <a href="<?php if($pageno >= $total_pages){ echo '#'; } else { echo "?pageno=".($pageno + 1); } ?>"><strong style="padding-left: 10px">Next></strong></a>
        </li>
        <li><a href="?pageno=<?php echo $total_pages; ?>"><strong style="padding-left: 10px">Last</strong></a></li>
    </ul>
</div>
                   
                    </div>
                </div>
            </div>
        </div>
    </section>
	<!-- //contact -->
	<!---728x90--->
	<!--footer -->
	 <?php include_once('includes/footer.php');?>
	<!-- //footer -->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!--//slider-->
	<!--search jQuery-->
	<script src="js/classie-search.js"></script>
	<script src="js/demo1-search.js"></script>
	<!--//search jQuery-->

	<script>
		$(document).ready(function () {
			$(".dropdown").hover(
				function () {
					$('.dropdown-menu', this).stop(true, true).slideDown("fast");
					$(this).toggleClass('open');
				},
				function () {
					$('.dropdown-menu', this).stop(true, true).slideUp("fast");
					$(this).toggleClass('open');
				}
			);
		});
	</script>
	<!-- //dropdown nav -->
	<!-- password-script -->
	<script>
		window.onload = function () {
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
	<!-- //js -->
	<script src="js/bootstrap.js"></script>
	<!--/ start-smoth-scrolling -->
	<script src="js/move-top.js"></script>
	<script src="js/easing.js"></script>
	<script>
		jQuery(document).ready(function ($) {
			$(".scroll").click(function (event) {
				event.preventDefault();
				$('html,body').animate({
					scrollTop: $(this.hash).offset().top
				}, 900);
			});
		});
	</script>
	<script>
		$(document).ready(function () {
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