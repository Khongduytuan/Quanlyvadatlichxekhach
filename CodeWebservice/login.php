<?php
// file đăng nhập
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

	    public function __construct($uID, 
	    	$hoTen, 
	    	$ngayThangNamSinh, 
	    	$diaChi, 
	    	$sdt, 
	    	$email, 
	    	$userName, 
	    	$passWord, 
	    	$role) {
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


	$userName = $_POST["userName"];
	$passWord = $_POST["passWord"];

	// $userName = "admin";
	// $passWord = "123456";


	if (strlen($userName) > 0 && strlen($passWord) > 0) {
		$mangUser = array();
		$querry = "SELECT * FROM user WHERE FIND_IN_SET('$userName', userName) AND FIND_IN_SET('$passWord', passWord)";
		$data = mysqli_query($connect, $querry);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($mangUser, new User($row['uID'], 
					$row['hoTen'], 
					$row['ngayThangNamSinh'], 
					$row['diaChi'], 
					$row['sdt'], 
					$row['email'], 
					$row['userName'], 
					$row['passWord'], 
					$row['role']));
			}
			if (count($mangUser) > 0) {
				echo json_encode($mangUser); 
			}
			else{
				echo "loi";
			}
		}
	}
	else{
		echo "null";
	}


?>
