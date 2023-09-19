<?php
// FILE LẤY DỮ LIỆU LỊCH ĐẶT CHO ADMIN GỒM TẤT CÁC ĐỐI TƯỢNG
require "dbcon.php";
	class LichDat {
		public $ldID;
		public $thoiGianDat;
	    public $tinhTrangXacNhan;
	    public $diemDau;
	    public $diemCuoi;
	    public $thoiGianXuatPhat;
	    public $thoiGianToiNoi;
	    public $tenXe;
	    public $hoTen;
	    

	    public function __construct($ldID,$thoiGianDat, $tinhTrangXacNhan, $diemDau, $diemCuoi, $thoiGianXuatPhat, $thoiGianToiNoi,$tenXe, $hoTen) {
	        $this->ldID = $ldID;
	        $this->thoiGianDat = $thoiGianDat;
	        $this->tinhTrangXacNhan = $tinhTrangXacNhan;
	        $this->diemDau = $diemDau;
	        $this->diemCuoi = $diemCuoi;
	        $this->thoiGianXuatPhat = $thoiGianXuatPhat;
	        $this->thoiGianToiNoi = $thoiGianToiNoi;
	        $this->tenXe = $tenXe;
	        $this->hoTen = $hoTen;
	    }
	}

	// $uID = $_POST['uID'];
	// $uID = "2";

	$querry = "SELECT lichdat.ldID, lichdat.thoiGianDat, lichdat.tinhTrangXacNhan, lotrinh.diemDau, lotrinh.diemCuoi, lotrinh.thoiGianXuatPhat, lotrinh.thoiGianToiNoi, xe.tenXe, user.hoTen 
		FROM lichdat 
		INNER JOIN lotrinh ON lichdat.ltID = lotrinh.ltID 
		INNER JOIN xe ON lotrinh.xID = xe.xID 
		INNER JOIN user ON lichdat.uID = user.uID";
	
	$data = mysqli_query($connect, $querry);
	$lichDatArray = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($lichDatArray, new LichDat($row['ldID'], $row['thoiGianDat'], $row['tinhTrangXacNhan'], $row['diemDau'],$row['diemCuoi'], $row['thoiGianXuatPhat'], $row['thoiGianToiNoi'], $row['tenXe'], $row['hoTen']));
		
	}
	echo json_encode($lichDatArray);

?>


