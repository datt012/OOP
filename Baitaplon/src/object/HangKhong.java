//Trần Đình Kiến Giang
package object;
import java.time.LocalDate;
public class HangKhong extends DonHang {
    //Phương thức khởi tạo
    public HangKhong(String nguoiGui, String nguoiNhan, String diaChinhan, double khoangCach, double canNang, LocalDate ngay) {
        super(nguoiGui, nguoiNhan, diaChinhan, khoangCach, canNang, ngay);
    }
    //Overriding
    public double chiPhi() {
        final int PHIDV = 200000;
        return khoangCach * 100000 + canNang * 10000 + PHIDV;
    }
    //Overriding
    public String loaiHang(){
        return "Hàng Không";
    }
    //Overriding
    public String toString()
    {
        return "Tên người gửi: "+nguoiGui+" | "+"Tên người nhận: "+nguoiNhan+" | "+"Địa chỉ: "+diaChi+" | "+
                "Khoảng cách (km): "+khoangCach+" | "+"Cân nặng (kg): "+canNang+" | "+"Ngày giao hàng: "+ngay+" | "+
                "Loại hàng: "+loaiHang()+" | "+"Chi phi (đ): "+chiPhi();
    }
}