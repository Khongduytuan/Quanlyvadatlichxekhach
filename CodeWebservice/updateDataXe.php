<?php
// file cập nhật dữ liệu thông tin Xe
	require "dbCon.php";

	$xID = $_POST['xID'];
	$tenXe = $_POST['tenXe'];
	$bienSo = $_POST['bienSo'];
	$soGhe = $_POST['soGhe'];


	$querry = "UPDATE xe SET tenXe = '$tenXe', bienSo = '$bienSo', soGhe ='$soGhe' WHERE xID = '$xID'";


	if (mysqli_query($connect, $querry)) {
		echo "thanhcong";
	}
	else{
		echo "khongthanhcong";
	}





?>