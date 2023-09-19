<?php
// file lấy dữ liệu thông tin của các đối tượng ghe
require "dbCon.php";
	class Ghe {
	    public $gID;
	    public $tenGhe;
	    public $loaiGhe;
	    public $tinhTrang;
	    public $tenXe;

	    public function __construct($gID, $tenGhe, $loaiGhe, $tinhTrang, $tenXe) {
	        $this->gID = $gID;
	        $this->tenGhe = $tenGhe;
	        $this->loaiGhe = $loaiGhe;
	        $this->tinhTrang = $tinhTrang;
	        $this->tenXe = $tenXe;
	    }
	}

	$querry = "SELECT ghe.gID, ghe.tenGhe, ghe.loaiGhe, ghe.tinhTrang, xe.tenXe
        FROM ghe
        JOIN xe ON ghe.xID = xe.xID";

	$data = mysqli_query($connect, $querry);


	$gheArray = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($gheArray, new Ghe($row['gID'], $row['tenGhe'], $row['loaiGhe'], $row['tinhTrang'], $row['tenXe']));
		
	}
	echo json_encode($gheArray);


?>
