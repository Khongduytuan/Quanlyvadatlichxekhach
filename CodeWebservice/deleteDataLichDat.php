<?php
// file xóa thông tin lịch đặt
	require "dbCon.php";


	$ldID = $_POST['ldID'];

	// $ldID = "15";

	
	$querry = "DELETE FROM lichdat WHERE ldID = '$ldID'";
	


	if (mysqli_query($connect, $querry)) {
		echo "thanhcong";
			
	}else{
		echo "khongthanhcong";
	}
	


?>