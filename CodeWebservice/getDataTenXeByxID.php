<?php
// file lấy thông tin tenXe từ xID
require "dbCon.php"; // Đảm bảo rằng bạn đã đúng đường dẫn tới file dbCon.php

$tenXe = $_GET['tenXe']; // Lấy dữ liệu từ tham số GET
// $tenXe = "Hùng Hùng";


$query = "SELECT xID FROM xe WHERE tenXe = '$tenXe'";

$data = mysqli_query($connect, $query);

if ($data && mysqli_num_rows($data) > 0) {
    $row = mysqli_fetch_assoc($data);
    $tenXe = $row['xID'];
    echo $tenXe; // Chỉ echo ra tên xe
} else {
    echo "";
}

mysqli_close($connect);
?>