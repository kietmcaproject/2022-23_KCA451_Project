<?php
session_start();
include("dbconnection.php");
include("checklogin.php");
check_login();
error_reporting(0);
if(isset($_POST['submit']))
{
	$name=$_POST['name'];
	$email=$_POST['email'];
	$contact=$_POST['contact'];
	$company=$_POST['company'];
	$services=addslashes(mysqli_real_escape_string($con, json_encode($_POST['services'])));
	$other=$_POST['other'];
	$query=$_POST['query'];
	$pd=date('Y-m-d');
mysqli_query($con,"insert into prequest(name,email,contactno,company,services,others,query,posting_date) values('$name','$email','$contact','$company','$services','$other','$query','$pd')");
echo "<script>alert('Query received. We will contact you soon.');</script>";  
echo "<script>window.location.href='get-quote.php'</script>";
}
?>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<meta charset="utf-8" />
<title>CRM | Request Quote</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta content="" name="description" />
<meta content="" name="author" />

<link href="assets/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="assets/plugins/boostrapv3/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/boostrapv3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/animate.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/jquery-scrollbar/jquery.scrollbar.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/responsive.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/custom-icon-set.css" rel="stylesheet" type="text/css"/>
</head>
<body class="">
<?php include("header.php");?>
<div class="page-container row-fluid">
	<?php include("leftbar.php");?>
	<div class="clearfix"></div>
    <!-- END SIDEBAR MENU --> 
  </div>
  </div>
  <!-- END SIDEBAR --> 
  <!-- BEGIN PAGE CONTAINER-->
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
		<div class="page-title">	
			<h3>Quote Request Form</h3>
             <div class="row">
                        <div class="col-md-12">
                            
                            <form class="form-horizontal" method="post" enctype="multipart/form-data">
                            <div class="panel panel-default">
                             
                                <div class="panel-body">
                                    <p>Please click below mention services of your interest to receive quotation for the same:</p>
                                </div>
                                <div class="panel-body bg-white">                                                                        
                                    
                                    <div class="row">
                                        
                                        <div class="col-md-6">
 <?php
$uid=$_SESSION['id'];
$query=mysqli_query($con,"select email,name,mobile from user where id='$uid'");
while($rw=mysqli_fetch_array($query)){
      ?>                                          

    <div class="form-group">
     <label class="col-md-3 text-left text-left control-label">Name </label>
    <div class="col-md-9">                                            
    <div class="input-group">
        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
         <input type="text" name="name" class="form-control" value="<?php echo $rw['name'];?>" readonly="true" />
          </div>                                            
                                                
        </div>
       </div>
                                            
        <div class="form-group">                                        
   <label class="col-md-3 text-left text-left control-label">Contact no</label>
  <div class="col-md-9 col-xs-12">
  <div class="input-group">
   <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
    <input type="text" name="contact" class="form-control" value="<?php echo $rw['mobile'];?>" readonly="true"/>
     </div>            
       </div>
     </div>
                                      
                                            
                                            
<div class="form-group">
    <label class="col-md-3 text-left text-left control-label">Service Required :</label>
    <div  class="col-md-9">   
        <?php for($i = 1; $i <= 5; $i++): ?>
        <div class="form-group">
            <label class="check">
            <input type="checkbox" class="icheckbox" name="services[]" value="Service/Support Needed 10<?= $i ?>"/> Service/Support Needed 10<?= $i ?></label>
        </div>
        <?php endfor ?>
    </div>
</div>
        
        
        
    </div>
                                        <div class="col-md-6">
                                            
                                            <div class="form-group">                                        
                                                <label class="col-md-3 text-left text-left control-label">Email</label>
                                                <div class="col-md-9">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="email" name="email" class="form-control" value="<?php echo $rw['email'];?>" readonly="true">                                            
                                                    </div>
                                                   
                                                </div>
                                            </div>
                                            
                                               <div class="form-group">                                        
                                                <label class="col-md-3 text-left text-left control-label">Company</label>
                                                <div class="col-md-9">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                                                        <input type="text" name="company" class="form-control datepicker" value="" required>                                            
                                                    </div>
                                                   
                                                </div>
                                            </div>
                                       
                                            
                                            <div class="form-group">
                                                <div class="col-md-9">  
                                                <?php for($i = 6; $i <= 10; $i++): ?>
                                                    <div class="form-group">
                                                        <label class="check">
                                                        <input type="checkbox" class="icheckbox" name="services[]" value="Service/Support Needed 10<?= $i ?>"/> Service/Support Needed 10<?= $i ?></label>
                                                    </div>
                                                    <?php endfor ?>
                                                </div>
                                            </div>
                                            
                                        </div>
                                        <div class="col-md-12">
                                         <div class="form-group">
                                                <label class="col-md-1 text-left text-left control-label">Message</label>
                                                <div class="col-md-11 col-xs-12">                                            
                                                    <textarea class="form-control" rows="5" name="query" required></textarea>
                                                </div>
                                            </div></div>
                                        
                                    </div>
   <?php } ?>   
                                </div>
                                <div class="panel-footer">
                                    <button class="btn btn-default">Clear Form</button>                                    
                                    <input value="Submit" type="submit" name="submit" class="btn btn-primary pull-right">
                                </div>
                            </div>
                            </form>
                            
                        </div>
                    </div>            
                                    
                                    
                                   
                                   
                                    
                               
                                    
                                    
                                      
             
            	
		</div>
    </div>
  </div>
<!-- BEGIN CHAT --> 

 </div>
<script src="assets/plugins/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script src="assets/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script> 
<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="assets/plugins/breakpoints.js" type="text/javascript"></script> 
<script src="assets/plugins/jquery-unveil/jquery.unveil.min.js" type="text/javascript"></script> 
<script src="assets/plugins/jquery-block-ui/jqueryblockui.js" type="text/javascript"></script> 
<script src="assets/plugins/jquery-scrollbar/jquery.scrollbar.min.js" type="text/javascript"></script>
<script src="assets/plugins/pace/pace.min.js" type="text/javascript"></script>  
<script src="assets/plugins/jquery-numberAnimate/jquery.animateNumbers.js" type="text/javascript"></script>
<script src="assets/js/core.js" type="text/javascript"></script> 
<script src="assets/js/chat.js" type="text/javascript"></script> 
<script src="assets/js/demo.js" type="text/javascript"></script> 

</body>
</html>