<!--FONTS-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Sora:wght@200&display=swap" rel="stylesheet">

<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" style="box-shadow: 0px 3px 4px rgba(225, 225, 225, .6); font-family: 'Sora', sans-serif;">
  <div class="container-fluid"> 
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#defaultNavbar1">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.php" style="padding-left: 50px; font-size:25px; color:white; font-family: 'Sora', sans-serif">
          Jobvio.in
      </a> 
    </div>
      
      
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="defaultNavbar1" style="padding-right:50px;">
     
    
        <ul class="nav navbar-nav navbar-right ">
        <li><a href="index.php">Jobs</a></li>
        	<?php 
                
               if (session_status() == PHP_SESSION_NONE) {
               session_start();
}
                if(isset($_SESSION['login_user']))   // Checking whether the session is already there or not if 
                              // true then header redirect it to the home page directly 
 {
                    $myusername = $_SESSION['login_user'];
   echo ' <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">'.$myusername.'<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="seekerAccount.php">My Profile</a></li>
             <li><a href="AppliedJobs.php">Jobs Applied</a></li>
             <li><a href="logout.php">Logout</a></li>
       
          </ul>
        </li>';
 }
 if(isset($_SESSION['login_employer']))   // Checking whether the employer session is already there or not if 
                              // true then header redirect it to the home page directly 
 {
                    $myusername = $_SESSION['login_employer'];
   echo ' <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">'.$myusername.'<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="employerAccount.php">My Account</a></li>
             <li><a href="postjob.php">Post a job</a></li>
              <li><a href="ViewApplicants.php">View Applications</a></li>
            <li><a href="logout.php">Logout</a></li>
       
          </ul>
        </li>';
 }
 elseif(!isset($_SESSION['login_employer']) && !isset($_SESSION['login_user']))
     {
   
    echo '<li><a id="loginAnchor" href="#" data-toggle="modal" data-target="#myEmployerModal">
  Sign In 
</a></li>';
     
 }  ?>
       
        <li><a href="#contactus">Contact Us</a></li>
      </ul>
          
    </div>
    <!-- /.navbar-collapse --> 
  </div>
  <!-- /.container-fluid --> 
</nav>