<?php
// file add 1 đối tượng thông tin ghế vào cơ sở dữ liệu
require "dbCon.php";


$tenGhe = $_POST['tenGhe'];
$loaiGhe = $_POST['loaiGhe'];
$tinhTrang = $_POST['tinhTrang'];
$xID = $_POST['xID'];


// $tenGhe = "H6";
// $loaiGhe = "Ngồi";
// $tinhTrang = "1";
// $xID = "3";


$sqlCheckXe = "SELECT COUNT(*) as count FROM xe WHERE xID='$xID'";
$resultCheckXe = mysqli_query($connect, $sqlCheckXe);
$rowCheckXe = mysqli_fetch_assoc($resultCheckXe);

if ($rowCheckXe["count"] > 0) {
    // Truy vấn SQL để chèn dữ liệu mới vào bảng "lotrinh"
    $querry = "INSERT INTO ghe (gID, tenGhe, loaiGhe, tinhTrang, xID)
            VALUES (null, '$tenGhe', '$loaiGhe', '$tinhTrang', '$xID')";

    if (mysqli_query($connect, $querry)) {
        echo "thanhcong";
    } else {
        echo "khongthanhcong";
    }
} else {
    echo "giatrixekhongtontai";
}


?>