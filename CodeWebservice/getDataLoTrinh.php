<?php
// file lấy dữ liệu thông tin của các đối tượng lotrinh
require "dbCon.php";
	class LoTrinh {
	    public $ltID;
	    public $diemDau;
	    public $diemCuoi;
	    public $thoiGianXuatPhat;
	    public $thoiGianToiNoi;
	    public $tenXe;

	    public function __construct($ltID, $diemDau, $diemCuoi, $thoiGianXuatPhat, $thoiGianToiNoi, $tenXe) {
	        $this->ltID = $ltID;
	        $this->diemDau = $diemDau;
	        $this->diemCuoi = $diemCuoi;
	        $this->thoiGianXuatPhat = $thoiGianXuatPhat;
	        $this->thoiGianToiNoi = $thoiGianToiNoi;
	        $this->tenXe = $tenXe;
	    }
	}
	$querry = "SELECT lotrinh.ltID, lotrinh.diemDau, lotrinh.diemCuoi, lotrinh.thoiGianXuatPhat, lotrinh.thoiGianToiNoi, xe.tenXe
        FROM lotrinh
        JOIN xe ON lotrinh.xID = xe.xID";
	$data = mysqli_query($connect, $querry);
	$lotrinhArray = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($lotrinhArray, new LoTrinh($row['ltID'], $row['diemDau'], $row['diemCuoi'], $row['thoiGianXuatPhat'],$row['thoiGianToiNoi'], $row['tenXe']));
		
	}
	echo json_encode($lotrinhArray);

?>
