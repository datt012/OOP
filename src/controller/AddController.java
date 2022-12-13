package controller;
import object.DonHang;
import object.DuongBo;
import object.HangKhong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class AddController
{
    //Khai báo các thành phần trong cửa sổ
    @FXML
    private TextField nguoiguiField;
    @FXML
    private TextField nguoinhanField;
    @FXML
    private TextField diachiField;
    @FXML
    private TextField khoangcachField;
    @FXML
    private TextField cannangField;
    @FXML
    private ComboBox<String> loaiBox;
    @FXML
    private DatePicker ngayPicker;
    private ObservableList <DonHang> listDonHang=FXCollections.observableArrayList();
    private final ObservableList<String> loaiHang=FXCollections.observableArrayList("Đường Bộ","Hàng Không");
    public void setLoai()
    {
        loaiBox.setItems(loaiHang);
    }
    private void themHangDuongBo() {
        DonHang hangDuongBo = new DuongBo(    //Upcasting
                nguoiguiField.getText(),
                nguoinhanField.getText(),
                diachiField.getText(),
                Double.parseDouble(khoangcachField.getText()),
                Double.parseDouble(cannangField.getText()),
                ngayPicker.getValue());
        listDonHang.add(hangDuongBo);
        nguoiguiField.clear();
        nguoinhanField.clear();
        diachiField.clear();
        khoangcachField.clear();
        cannangField.clear();
    }
    private void themHangHangKhong() {
        DonHang hangHangKhong = new HangKhong(   //Upcasting
                nguoiguiField.getText(),
                nguoinhanField.getText(),
                diachiField.getText(),
                Double.parseDouble(khoangcachField.getText()),
                Double.parseDouble(cannangField.getText()),
                ngayPicker.getValue());
        listDonHang.add(hangHangKhong);
        nguoiguiField.clear();
        nguoinhanField.clear();
        diachiField.clear();
        khoangcachField.clear();
        cannangField.clear();
    }
    //Thiết lập nút add chức năng thêm mới đơn hàng vừa tạo
    public void addButton(ActionEvent event) {
        try {
            if (Double.parseDouble(khoangcachField.getText()) <= 0 || Double.parseDouble(cannangField.getText()) <= 0)
                throw new ArithmeticException("Error"); //Tạo ra ngoại lệ khi cân nặng hoặc hàng hóa có giá trị nhâp vào <=0
            if(nguoiguiField.getText().isEmpty()||nguoinhanField.getText().isEmpty()||diachiField.getText().isEmpty())
                throw new NullPointerException("Error"); //Tạo ra ngoại lệ khi các trường để trống
            else {
                if (loaiBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Đường Bộ")) {
                    themHangDuongBo();
                }
                if (loaiBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Hàng Không")) {
                    themHangHangKhong();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Results:");
                alert.setContentText("Add the order successfully!");
                alert.showAndWait();
                closeStage(event);
            }
        }catch(NullPointerException e){
            //Xử lí ngoại lệ khi chưa nhập xong
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not add order");
            alert.setContentText("You have not entered complete data" + "\n" +
                    "Please continue enter");
            alert.showAndWait();
        }catch(NumberFormatException | ArithmeticException e){
            //Xử lí ngoại lệ khi nhập lỗi
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not add order");
            alert.setContentText("You entered the wrong data" + "\n" +
                    "Please re-enter");
            alert.showAndWait();
        }
    }
    public void setDonHang (ObservableList <DonHang> listDonHang) {
        this.listDonHang = listDonHang;
    }
    //Đóng cửa sổ
    private void closeStage (ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}