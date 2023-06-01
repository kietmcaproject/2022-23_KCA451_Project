<?php
session_start();
error_reporting(0);

include('includes/dbconnection.php');
?>
<!DOCTYPE html>
<html lang="zxx">

<head>
	<title>Campus Recruitment Management System|| Contact Us</title>
	
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
		<li class="breadcrumb-item active">About Us</li>
	</ol>
	<!-- banner-text -->
	<!-- contact -->
	<section class="banner-bottom-wthree pt-lg-5 pt-md-3 pt-3">
		<div class="inner-sec-w3ls pt-md-5 pt-md-3 pt-3">
		<!---728x90--->
			<h3 class="tittle text-center mb-lg-5 mb-3">
				<span>Get More info</span>About Us</h3>
				<!---728x90--->
				<?php 
 $query=mysqli_query($con,"select * from  tblpage where PageType='aboutus'");
 while ($row=mysqli_fetch_array($query)) {


 ?>
				<div class="container">
						<div class="address row mb-5">
							<div class="col-lg-12 address-grid">
								<div class="row address-info">
								
									<div class="col-md-9 address-right text-left">
										<h6 class="ad-info text-uppercase mb-2"><?php  echo $row['PageTitle'];?></h6>
										<p> <?php  echo $row['PageDescription'];?>
		
										</p>
									</div>
								</div>
							</div>
							
							
						</div>
					</div>
					<?php } ?>
		
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