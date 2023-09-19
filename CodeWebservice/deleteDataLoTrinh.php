<?php
// file xóa thông tin lộ trình
	require "dbCon.php";


	$ltID = $_POST['ltID'];

	// $ltID = "9";

	$querry = "DELETE FROM lotrinh WHERE ltID = '$ltID'";


	
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