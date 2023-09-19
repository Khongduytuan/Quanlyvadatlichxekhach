<?php
// file cập nhật dữ liệu thông tin Xe
	require "dbCon.php";

	$ltID = $_POST['ltID'];
	$diemDau = $_POST['diemDau'];
	$diemCuoi = $_POST['diemCuoi'];
	$thoiGianXuatPhat = $_POST['thoiGianXuatPhat'];
	$thoiGianToiNoi = $_POST['thoiGianToiNoi'];
	$xID = $_POST['xID'];



	// $ltID = "9";
	// $diemDau = "Hà Giang 1";
	// $diemCuoi = "Vĩnh Phúc 1";
	// $thoiGianXuatPhat = "2023-08-17 10:00:00";
	// $thoiGianToiNoi = "2023-08-17 15:00:00";
	// $xID = "3";

	$querry = "UPDATE lotrinh
            SET diemDau='$diemDau', diemCuoi='$diemCuoi', thoiGianXuatPhat='$thoiGianXuatPhat', thoiGianToiNoi='$thoiGianToiNoi', xID='$xID'
            WHERE ltID='$ltID'";


	if (mysqli_query($connect, $querry)) {
		echo "thanhcong";
	}
	else{
		echo "khongthanhcong";
	}





?>