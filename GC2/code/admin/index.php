<?php
session_start();
error_reporting(0);
include("dbconnection.php");
if(isset($_POST['login']))
{
$ret=mysqli_query($con,"SELECT * FROM admin WHERE name='".$_POST['username']."' and password='".$_POST['password']."'");
$num=mysqli_fetch_array($ret);
if($num>0)
{
$extra="home.php";
$_SESSION['alogin']=$_POST['username'];
$_SESSION['id']=$num['id'];
echo "<script>window.location.href='".$extra."'</script>";
exit();
}
else
{
$_SESSION['action1']="*Invalid username or password";
$extra="index.php";

echo "<script>window.location.href='".$extra."'</script>";
exit();
}
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<meta charset="utf-8" />
<title>CRM | Admin Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta content="" name="description" />
<meta content="" name="author" />
<link href="./../assets/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="./../assets/plugins/boostrapv3/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="./../assets/plugins/boostrapv3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
<link href="./../assets/plugins/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
<link href="./../assets/css/animate.min.css" rel="stylesheet" type="text/css"/>
<link href="./../assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="./../assets/css/responsive.css" rel="stylesheet" type="text/css"/>
<link href="./../assets/css/custom-icon-set.css" rel="stylesheet" type="text/css"/>

</head>
<body class="error-body no-top">
<div class="container">
  <div class="login-container">  
        <div class="col-md-5">
          <h2 class="text-center text-white"><strong>HAH-CRM - Admin Login Page</strong></h2>
          <hr>
        </div>
        <div class="col-md-5 "> 
		    <form id="login-form" class="login-form" action="" method="post">
         <p style="color: #F00"><?php echo $_SESSION['action1'];?><?php echo $_SESSION['action1']="";?></p>
         <div class="form-group">
          <label for="username" class="control-label">Username</label>
          <input type="text" class="form-control rounded-0" id="username" name="username" required="required">
         </div>
         <div class="form-group">
          <label for="password" class="control-label">Password</label>
          <input type="password" class="form-control rounded-0" id="password" name="password" required="required">
         </div>
        <div class="form-group text-center">
          <a href="./../">Back to Portal</a>
          <button class="btn btn-primary btn-cons pull-right" name="login" type="submit">Login</button>
        </div>
		  </form>
        </div>
     
    
  </div>
</div>
<script src="assets/plugins/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/plugins/pace/pace.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="assets/js/login.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/highcharts.js"></script>
	<script type="text/javascript" src="js/exporting.js"></script>	
</body>
</html>