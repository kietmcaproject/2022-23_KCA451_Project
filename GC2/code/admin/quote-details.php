<?php
session_start();
include("dbconnection.php");
include("checklogin.php");
check_login();
if(isset($_POST['remark']))
{
	$msg=mysqli_query($con,"update prequest set remark='".$_POST['adminremark']."' where id='".$_GET['id']."'");
	if($msg)
	{
	echo "<script>alert('Remark Updated'); location.replace(document.referrer)</script>";	
	}
}
?><!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<meta charset="utf-8" />
<title>Admin | Quote Details</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta content="" name="description" />
<meta content="" name="author" />
<link href="../assets/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="../assets/plugins/boostrapv3/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/plugins/boostrapv3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/plugins/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
<link href="../assets/css/animate.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/plugins/jquery-scrollbar/jquery.scrollbar.css" rel="stylesheet" type="text/css"/>
<link href="../assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="../assets/css/responsive.css" rel="stylesheet" type="text/css"/>
<link href="../assets/css/custom-icon-set.css" rel="stylesheet" type="text/css"/>
</head>
<body class="">
<?php include("header.php");?>

<div class="page-container row"> 
  
      <?php include("leftbar.php");?>
    
      <div class="clearfix"></div>
      <!-- END SIDEBAR MENU -->
    </div>
  </div>
  <div class="page-content"> 
    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
    <div id="portlet-config" class="modal hide">
      <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>Widget Settings</h3>
      </div>
      <div class="modal-body"> Widget settings form goes here </div>
    </div>

    <div class="clearfix"></div>
    <div class="content">                           
     <ul class="breadcrumb">
        <li>   
			<p>Home<p>  					 							
        </li>     				 
        <li><a href="#" class="active">Quote Details </a></li>                    
    </ul>
   	<div class="page-title">		
		<i class="icon-custom-left"></i>
		<h3>Quote Details</h3>	
	</div>
 	<?php
    $ret=mysqli_query($con,"select * from prequest where id='".$_GET['id']."'");
	while($row=mysqli_fetch_array($ret))
	{
	
	?>
      			<div class="row">
					<div class="col-md-12">
						    <div class="grid simple vertical green">
							<div class="grid-title no-border">
								<h4><?php echo $row['name'];?>'s Quote <span class="semi-bold">Details</span></h4>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="grid-body no-border">
								<div class="row-fluid ">
									    <div class="margin-bottom-20 margin-top-10">
											<strong>Name</strong>:
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php echo $row['name'];?><br>
                                            <strong>Email</strong>:
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php echo $row['email'];?><br>
                                            <strong>Contact no.</strong>:
											&nbsp;<?php echo $row['contactno'];?><br>
											<strong>Company</strong>:
											&nbsp;&nbsp;&nbsp;&nbsp;<?php echo $row['company'];?><br>
										</div>	
                                        <div class="margin-bottom-20 margin-top-10">
											<strong>Services:</strong><br>
											<div class="row">
												<?php if(!empty($row['services'])): ?>
												<?php foreach(json_decode(stripslashes($row['services'])) as $service): ?>
													<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
														<?= $service ?>
													</div>
												<?php endforeach; ?>
												<?php endif; ?>
											</div>
										</div>										 
										<div>
											<strong>Description</strong><br>
										<?php echo $row['query'];?>
										</div>
                                        <form name="remark" action="" method="post" enctype="multipart/form-data">
                                        <div>
											<strong>Remark</strong><br>
										<textarea name="adminremark" cols="70" rows="4"><?php echo $row['remark'];?></textarea><br /><br />
                                        <input type="submit" name="remark" value="Submit" />
										</div>
                                        </form>
								</div>
							</div>
						</div> 
					</div>
				</div>
      			<?php } ?>
				
			
				</div>					
			 </div>			
        </div>    	
	</div> 
  </div>  
  <!-- END PAGE --> 

</div>
<script src="../assets/plugins/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script src="../assets/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script> 
<script src="../assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="../assets/plugins/breakpoints.js" type="text/javascript"></script> 
<script src="../assets/plugins/jquery-unveil/jquery.unveil.min.js" type="text/javascript"></script>	
<script src="../assets/plugins/pace/pace.min.js" type="text/javascript"></script>
<script src="../assets/plugins/jquery-scrollbar/jquery.scrollbar.min.js" type="text/javascript"></script>
<script src="../assets/plugins/jquery-block-ui/jqueryblockui.js" type="text/javascript"></script>   
<script src="../assets/plugins/jquery-numberAnimate/jquery.animateNumbers.js" type="text/javascript"></script>
<script src="../assets/js/core.js" type="text/javascript"></script> 
<script src="../assets/js/chat.js" type="text/javascript"></script> 
<script src="../assets/js/demo.js" type="text/javascript"></script>  
</body>
</html>