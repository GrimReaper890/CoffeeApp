<?php 
if($_SERVER['REQUEST_METHOD']=='POST'){

    $Semester = $_POST["cr_semster"];
    // $Section = $_POST["cr_section"];
    $Type = $_POST["cr_type"];
    $Program = $_POST["cr_program"];
    $cRName = $_POST["cr_name"];
    $Session = $_POST["cr_session"];
    $password = $_POST["cr_password"];

 require_once('dbconnect.php');
    $query = " SELECT * FROM bs_class_details WHERE Semester  = '$Semester' and
   Type ='$Type' and Program ='$Program' and Session = '$Session' and
   cr_name = '$cRName' and cr_pass =  '$password' ";

   $sql1=mysqli_query($con,$query);

   $row = mysqli_fetch_array($sql1);
   
    if (!empty($row)) { $response["success"] = 1; 
	$response["message"] = "You have been sucessfully login"; die(json_encode($response));
	 } 
	 else{ $response["success"] = 0; 
	 $response["message"] = "invalid username or password ";
	  die(json_encode($response)); } } else{ $response["success"] = 0;
	   $response["message"] = " One or both of the fields are empty "; 
	   die(json_encode($response));
	    }
		 mysqli_close(); 
        
         ?>