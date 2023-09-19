<?php
// file cập nhật dữ liệu thông tin Xe
	require "dbCon.php";


	$gID = $_POST['gID'];
	$tenGhe = $_POST['tenGhe'];
	$loaiGhe = $_POST['loaiGhe'];
	$tinhTrang = $_POST['tinhTrang'];
	$xID = $_POST['xID'];

	// $gID = "6";
	// $tenGhe = "H6-1";
	// $loaiGhe = "Nằm";
	// $tinhTrang = "0";
	// $xID = "3";


	$querry = "UPDATE ghe
            SET tenGhe='$tenGhe', loaiGhe='$loaiGhe', tinhTrang='$tinhTrang', xID='$xID'
            WHERE gID='$gID'";


	if (mysqli_query($connect, $querry)) {
		echo "thanhcong";
	}
	else{
		echo "khongthanhcong";
	}





?>