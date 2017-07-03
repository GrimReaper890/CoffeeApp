<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	$name=$_POST['name'];
	$phone=$_POST['phone'];
	$username=$_POST['username'];
	$password=$_POST['password'];
	$type=$_POST['typeId'];
	require_once('dbconnect.php');
	$sql="INSERT INTO administrator(name,phone,username,password,type) VALUES('$name','$phone','$username','$password','$type')";
	if(mysqli_query($con,$sql)){
		echo"successfully registered";
		}
	else{
		echo"could not register";
		}	

}
?>