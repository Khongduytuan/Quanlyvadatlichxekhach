<?php
// file xóa thông tin Xe và các đối tượng của các bảng khác ánh xạ tới xID đó
	require "dbCon.php";


	$xID = $_POST['xID'];

	// $xID = "2";

	$querrylotrinh = "DELETE FROM lotrinh WHERE xID = '$xID'";
	$querry = "DELETE FROM xe WHERE xID = '$xID'";
	$querryghe = "DELETE FROM ghe WHERE xID = '$xID'";


	if (mysqli_query($connect, $querrylotrinh)) {
		if (mysqli_query($connect, $querryghe)) {
			if (mysqli_query($connect, $querry)) {
			echo "thanhcong";
			}
			else{
				echo "khongthanhcong";
			}
		}
	}
	else{
		echo "loi";
	}


?>