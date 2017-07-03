<?php 
if($_SERVER['REQUEST_METHOD']=='POST'){
	$username=$_POST['pName'];
	$password=$_POST['ppassword'];
	$type=$_POST['typeId'];
//   die(json_encode($response));
   
   require_once('dbconnect.php');
  $query = " SELECT * FROM administrator WHERE username = '$username'and password='$password' and type ='$type'";
   $sql1=mysqli_query($con,$query);
   $row = mysqli_fetch_array($sql1);
    if (!empty($row)) { $response["success"] = 1; 
	$response["message"] = "You have been sucessfully login"; die(json_encode($response));
	 } 
	 else{ $response["success"] = 0; 
	 $response["message"] = "invalid username or password ";
	  die(json_encode($response)); } }
	  else{ $response["success"] = 0;
	   $response["message"] = " One or both of the fields are empty "; 
	   die(json_encode($response));
	    }
		
		 mysqli_close(); ?>
