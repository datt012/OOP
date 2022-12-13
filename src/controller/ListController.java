package controller;
import object.DonHang;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.DecimalFormat;
import java.time.LocalDate;
public class ListController {
    //Khai báo các thành phần trong cửa sổ
    @FXML
    private TableView<DonHang> donhangTable;
    @FXML
    private TableColumn<DonHang, String> nguoiguiColumn;
    @FXML
    private TableColumn<DonHang, String> nguoinhanColumn;
    @FXML
    private TableColumn<DonHang, String> diachiColumn;
    @FXML
    private TableColumn<DonHang, Double> cannangColumn;
    @FXML
    private TableColumn<DonHang, Double> khoangcachColumn;
    @FXML
    private TableColumn<DonHang, String> chiphiColumn;
    @FXML
    private TableColumn<DonHang, String> loaiColumn;
    @FXML
    private TableColumn<DonHang, LocalDate> ngayColumn;
    @FXML
    private TableColumn<DonHang, String> idColumn;
    @FXML
    private Label thunhapLabel;
    @FXML
    private Label thangnamLabel;
    @FXML
    private Label soluongLabel;
    private ObservableList<DonHang> listDonHang = FXCollections.observableArrayList();
    double tongDoanhThu;
    int thang, nam, soLuong;
    public void initialize() {
        DecimalFormat currency = new DecimalFormat("0.000đ");//Khởi tạo biến định dạng số thập phân
        //Đưa các thuộc tính phương thức của đối tượng vào tableview
        nguoiguiColumn.setCellValueFactory(new PropertyValueFactory<>("nguoiGui"));
        nguoinhanColumn.setCellValueFactory(new PropertyValueFactory<>("nguoiNhan"));
        diachiColumn.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        cannangColumn.setCellValueFactory(new PropertyValueFactory<>("canNang"));
        khoangcachColumn.setCellValueFactory(new PropertyValueFactory<>("khoangCach"));
        loaiColumn.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().loaiHang()));
        ngayColumn.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        chiphiColumn.setCellValueFactory(o -> new SimpleStringProperty(currency.format(o.getValue().chiPhi())));
        idColumn.setCellValueFactory(o -> {
            DonHang item = o.getValue();
            int index = donhangTable.getItems().indexOf(item) + 1;
            return new SimpleStringProperty(Integer.toString(index));
        });
        donhangTable.setItems(listDonHang);
        thunhapLabel.setText(currency.format(tongDoanhThu));
        thangnamLabel.setText("(" + thang + "/" + nam + ")" + " :");
        soluongLabel.setText(String.valueOf(soLuong));
    }
    public void setDonHang(ObservableList<DonHang> listDonHang) {
        this.listDonHang = listDonHang;
    }
    //Tính doanh thu trong tháng
    public double doanhThu() {
        double n = 0.0d;
        for (DonHang DonHang : listDonHang) {
            n += DonHang.chiPhi();
        }
        return n;
    }
    public void setDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }
    public void setThangNam(int thang, int nam) {
        this.thang = thang;
        this.nam = nam;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
