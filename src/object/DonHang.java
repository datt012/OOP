//Trần Đình Kiến Giang
package object;
import javafx.scene.control.CheckBox;
import java.time.LocalDate;
public abstract class DonHang {
    protected String nguoiNhan;
    protected String nguoiGui;
    protected String diaChi;
    protected double khoangCach;
    protected double canNang;
    protected CheckBox chon;
    protected LocalDate ngay;
    public DonHang(String nguoiGui,String nguoiNhan,String diaChinhan,double khoangCach,double canNang,LocalDate ngay)
    {
        this.nguoiGui=nguoiGui;
        this.nguoiNhan=nguoiNhan;
        this.diaChi=diaChinhan;
        this.khoangCach=khoangCach;
        this.canNang=canNang;
        this.chon=new CheckBox();
        this.ngay=ngay;
    }
    public String getNguoiNhan()
    {
        return nguoiNhan;
    }
    public void setNguoiNhan(String nguoiNhan)
    {
        this.nguoiNhan=nguoiNhan;
    }
    public String getNguoiGui()
    {
        return nguoiGui;
    }
    public void setNguoiGui(String nguoiGui)
    {
        this.nguoiGui=nguoiGui;
    }
    public String getDiaChi()
    {
        return diaChi;
    }
    public void setDiaChi(String diaChinhan)
    {
        this.diaChi=diaChinhan;
    }
    public double getKhoangCach() { return khoangCach; }
    public void setKhoangCach(double khoangCach)
    {
        this.khoangCach=khoangCach;
    }
    public double getCanNang()
    {
        return canNang;
    }
    public void setCanNang(double canNang)
    {
        this.canNang=canNang;
    }
    public CheckBox getChon() { return chon;}
    public LocalDate getNgay() { return ngay; }
    public void setNgay(LocalDate ngay) { this.ngay=ngay;}
    abstract public double chiPhi();
    abstract public String loaiHang();
    abstract public String toString();
}
