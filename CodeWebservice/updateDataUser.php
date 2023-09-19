<?php
// file cập nhật dữ liệu thông tin User đối với quyền người dùng thường 
	require "dbCon.php";

	$uID = $_POST['uID'];
	$hoTen = $_POST['hoTen'];
	$ngayThangNamSinh = $_POST['ngayThangNamSinh'];
	$diaChi = $_POST['diaChi'];
	$sdt = $_POST['sdt'];
	$email = $_POST['email'];
	// $uID = "2";
	// $hoTen = "Khong tuan 2";
	// $ngayThangNamSinh = "2023-12-01";
	// $diaChi = "Vĩnh Phúc 2";
	// $sdt = "012333333";
	// $email = "khongduytuan01122001@gmail.com";


		// public $uID;
	    // public $hoTen;
	    // public $ngayThangNamSinh;
	    // public $diaChi;
	    // public $sdt;
	    // public $email;
	    // public $userName;
	    // public $passWord;
	    // public $role;


	$querry = "UPDATE user SET hoTen = '$hoTen', ngayThangNamSinh = '$ngayThangNamSinh', diaChi ='$diaChi', sdt = '$sdt', email = '$email' WHERE uID = '$uID'";


	if (mysqli_query($connect, $querry)) {
		echo "thanhcong";
	}
	else{
		echo "khongthanhcong";
	}





?>