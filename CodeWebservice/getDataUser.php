<?php
// file lấy dữ liệu thông tin của các đối tượng xe
require "dbCon.php";
	class User {
	    public $uID;
	    public $hoTen;
	    public $ngayThangNamSinh;
	    public $diaChi;
	    public $sdt;
	    public $email;
	    public $userName;
	    public $passWord;
	    public $role;

	    public function __construct($uID, $hoTen, $ngayThangNamSinh, $diaChi, $sdt, $email, $userName, $passWord, $role) {
	        $this->uID = $uID;
	        $this->hoTen = $hoTen;
	        $this->ngayThangNamSinh = $ngayThangNamSinh;
	        $this->diaChi = $diaChi;
	        $this->sdt = $sdt;
	        $this->email = $email;
	        $this->userName = $userName;
	        $this->passWord = $passWord;
	        $this->role = $role;
	    }
	}


	$querry = "SELECT * FROM user";

	$data = mysqli_query($connect, $querry);


	$userArray = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($userArray, new User($row['uID'], 
			$row['hoTen'], 
			$row['ngayThangNamSinh'], 
			$row['diaChi'], 
			$row['sdt'],
			$row['diaChi'],
			$row['userName'],
			$row['passWord'],
			$row['role']));
		
	}
	echo json_encode($userArray);


?>
