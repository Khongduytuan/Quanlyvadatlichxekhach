<?php
// file xóa thông tin lộ trình
	require "dbCon.php";


	$uID = $_POST['uID'];

	// $uID = "5";

	$querry = "DELETE FROM user WHERE uID = '$uID'";


	if (mysqli_query($connect, $querry)) {
        $affectedRows = mysqli_affected_rows($connect);
        if ($affectedRows > 0) {
            echo "thanhcong";
        } else {
            echo "loiid";
        }
    } else {
        echo "khongthanhcong";
    }





?>