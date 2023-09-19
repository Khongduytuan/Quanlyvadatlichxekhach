<?php
// tạo ra số ghế từ số ghế từ bảng xe
require "dbCon.php"; // Đảm bảo bạn đã import file kết nối CSDL
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

    // $xID = $_POST["xID"]; // Lấy thông tin xID từ dữ liệu post
    // $soGhe = $_POST["soGhe"]; // Lấy thông tin số lượng ghế từ dữ liệu post
    // $loaiGhe = $_POST["loaiGhe"];
    // $tinhTrang = $_POST["tinhTrang"];

    $xID = "10"; 
    $soGhe = "15"; 
    $loaiGhe = "Nằm";
    $tinhTrang = "0";



    $sqlCheckXe = "SELECT COUNT(*) as count FROM xe WHERE xID='$xID'";
    $resultCheckXe = mysqli_query($connect, $sqlCheckXe);
    $rowCheckXe = mysqli_fetch_assoc($resultCheckXe);

    if ($xID && $soGhe > 0) {
        for ($i = 1; $i <= $soGhe; $i++) {
            if ($rowCheckXe["count"] <= 0) {
                // Truy vấn SQL để chèn dữ liệu mới vào bảng "lotrinh"
                echo "giatrixekhongtontai";
                break;
            } else {
                $querry = "INSERT INTO ghe (gID, tenGhe, loaiGhe, tinhTrang, xID)
                        VALUES (null, '$i', '$loaiGhe', '$tinhTrang', '$xID')";

                if (!mysqli_query($connect, $querry)) {
                    echo "khongthanhcong";
                    break;
                }
            }
            
        }
        echo "thanhcong";

    }
?>
