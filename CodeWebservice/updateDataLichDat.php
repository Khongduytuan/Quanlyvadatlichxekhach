<?php
// FILE XÁC NHẬN LỊCH ĐẶT CỦA ADMIN
	require "dbCon.php";

	$ldID = $_POST['ldID'];
	$tinhTrangXacNhan = $_POST['tinhTrangXacNhan'];

	// $ldID = "16";
	// $tinhTrangXacNhan = "1";


	



	// $ltID = "9";
	// $diemDau = "Hà Giang 1";
	// $diemCuoi = "Vĩnh Phúc 1";
	// $thoiGianXuatPhat = "2023-08-17 10:00:00";
	// $thoiGianToiNoi = "2023-08-17 15:00:00";
	// $xID = "3";

	$querry = "UPDATE lichdat SET tinhTrangXacNhan='$tinhTrangXacNhan' WHERE ldID='$ldID'";


	if (mysqli_query($connect, $querry)) {
		echo "thanhcong";
	}
	else{
		echo "khongthanhcong";
	}





?>