<?php
// file add 1 đối tượng thông tin lộ  vào cơ sở dữ liệu
require "dbCon.php";


$diemDau = $_POST['diemDau'];
$diemCuoi = $_POST['diemCuoi'];
$thoiGianXuatPhat = $_POST['thoiGianXuatPhat'];
$thoiGianToiNoi = $_POST['thoiGianToiNoi'];
$xID = $_POST['xID'];


$sqlCheckXe = "SELECT COUNT(*) as count FROM xe WHERE xID='$xID'";
$resultCheckXe = mysqli_query($connect, $sqlCheckXe);
$rowCheckXe = mysqli_fetch_assoc($resultCheckXe);

if ($rowCheckXe["count"] > 0) {
    // Truy vấn SQL để chèn dữ liệu mới vào bảng "lotrinh"
    $querry = "INSERT INTO lotrinh (ltID, diemDau, diemCuoi, thoiGianXuatPhat, thoiGianToiNoi, xID)
            VALUES (null, '$diemDau', '$diemCuoi', '$thoiGianXuatPhat', '$thoiGianToiNoi', '$xID')";

    if (mysqli_query($connect, $querry)) {
        echo "thanhcong";
    } else {
        echo "khongthanhcong";
    }
} else {
    echo "giatrixekhongtontai";
}


?>