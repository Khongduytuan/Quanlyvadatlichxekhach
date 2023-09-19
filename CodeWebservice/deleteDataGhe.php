<?php
// file xóa thông tin lộ trình
	require "dbCon.php";


	$gID = $_POST['gID'];

	// $gID = "5";

	$querry = "DELETE FROM ghe WHERE gID = '$gID'";


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