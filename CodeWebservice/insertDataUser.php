<?php
// file add 1 đối tượng thông tin xe vào cơ sở dữ liệu
require "dbCon.php";


$hoTen = $_POST["hoTen"];
$ngayThangNamSinh = $_POST["ngayThangNamSinh"];
$diaChi = $_POST["diaChi"];
$sdt = $_POST["sdt"];
$email = $_POST["email"];
$userName = $_POST["userName"];
$passWord = $_POST["passWord"];
$role = $_POST["role"];

// $hoTen = "Tuấn Tuấn";
// $ngayThangNamSinh = "2001-08-02";
// $diaChi = "Vĩnh Tường";
// $sdt = "022222222";
// $email = "tuantuan@gmail.com";
// $userName = "tuantuan";
// $passWord = "123456";
// $role = "0";


$querry = "INSERT INTO user VALUE(null, '$hoTen', '$ngayThangNamSinh', '$diaChi', '$sdt', '$email', '$userName', '$passWord', '$role')";

if (mysqli_query($connect, $querry)) {
	echo "thanhcong";
}else{
	echo "khongthanhcong";
}

?>