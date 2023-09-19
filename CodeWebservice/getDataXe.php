<?php
// file lấy dữ liệu thông tin của các đối tượng xe
require "dbCon.php";
	class Xe {
	    public $xID;
	    public $tenXe;
	    public $bienSo;
	    public $soGhe;

	    public function __construct($xID, $tenXe, $bienSo, $soGhe) {
	        $this->xID = $xID;
	        $this->tenXe = $tenXe;
	        $this->bienSo = $bienSo;
	        $this->soGhe = $soGhe;
	    }
	}


	$querry = "SELECT * FROM xe";

	$data = mysqli_query($connect, $querry);


	$xeArray = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($xeArray, new Xe($row['xID'], $row['tenXe'], $row['bienSo'], $row['soGhe']));
		
	}
	echo json_encode($xeArray);


?>
