<?php
// file lấy thông tin người dùng với role = 0 (tức là người dùng thường)
require "dbCon.php"; // Đảm bảo bạn đã import file kết nối CSDL

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

$role = $_POST["role"]; 
	// $role = "0"; 

if (strlen($role) > 0) {
    $mangUser = array();
    $query = "SELECT * FROM user WHERE FIND_IN_SET('$role', role)";
    $data = mysqli_query($connect, $query);

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
        } else {
            echo "Không tìm thấy User có role $role";
        }
    } else {
        echo "Có lỗi xảy ra khi truy vấn CSDL.";
    }
} else {
    echo "Vui lòng cung cấp giá trị role.";
}
?>