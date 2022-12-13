//Trần Đình Kiến Giang
package object;
import java.time.LocalDate;
public class DuongBo extends DonHang {
    //Phương thức khởi tạo
    public DuongBo(String nguoiGui, String nguoiNhan, String diaChinhan, double khoangCach, double canNang, LocalDate ngay) {
        super(nguoiGui, nguoiNhan, diaChinhan, khoangCach, canNang,ngay);
    }
    //Overriding
    public double chiPhi() {
        return khoangCach * 20000 + canNang * 5000;
    }
    //Overriding
    public String loaiHang(){
        return "Đường Bộ";
    }
    //Overriding
    public String toString()
    {
        return "Tên người gửi: "+nguoiGui+" | "+"Tên người nhận: "+nguoiNhan+" | "+"Địa chỉ: "+diaChi+" | "+
                "Khoảng cách (km): "+khoangCach+" | "+"Cân nặng (kg): "+canNang+" | "+"Ngày giao hàng: "+ngay+" | "+
                "Loại hàng: "+loaiHang()+" | "+"Chi phi (đ): "+chiPhi();
    }
}