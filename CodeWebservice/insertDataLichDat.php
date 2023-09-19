<?php
require "dbCon.php";

// Lấy thông tin từ đối tượng LichDat
$ltID = $_POST['ltID'];
$uID = $_POST['uID'];
$thoiGianDat = $_POST['thoiGianDat'];
$tinhTrangXacNhan = $_POST['tinhTrangXacNhan'];

// $ltID = "4";
// $uID = "2";
// $thoiGianDat = "2023-08-21 05:01:40";
// $tinhTrangXacNhan = "0";

// Kiểm tra sự tồn tại của ltID và uID (có thể thêm kiểm tra nếu cần)
// ...

// Truy vấn SQL để chèn dữ liệu mới vào bảng "lichdat"
$query = "INSERT INTO lichdat (ldID, ltID, uID, thoiGianDat, tinhTrangXacNhan)
          VALUES (null, '$ltID', '$uID', '$thoiGianDat', '$tinhTrangXacNhan')";

if (mysqli_query($connect, $query)) {
    echo "thanhcong";
} else {
    echo "khongthanhcong" . mysqli_error($connect);
}
?>