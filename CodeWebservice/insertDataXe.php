<?php
// file add 1 đối tượng thông tin xe vào cơ sở dữ liệu
require "dbCon.php";

$tenXe = $_POST['tenXe'];
$bienSo = $_POST['bienSo'];
$soGhe = $_POST['soGhe'];

// $tenXe = "Hải Nam";
// $bienSo = "29D2-49253";
// $soGhe = "20";

$querry = "INSERT INTO xe VALUE(null, '$tenXe', '$bienSo', '$soGhe')";

if (mysqli_query($connect, $querry)) {
	echo "thanhcong";
}else{
	echo "khongthanhcong";
}

?>