package controller;
import object.DonHang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class UpdateController
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
    private DatePicker ngayPicker;
    private DonHang donHang;
    public void setDonHang(DonHang donHang) {
        //Thay đổi các thuộc tính
        this.donHang=donHang;
        nguoiguiField.setText(donHang.getNguoiGui());
        nguoinhanField.setText(donHang.getNguoiNhan());
        diachiField.setText(donHang.getDiaChi());
        khoangcachField.setText(Double.toString(donHang.getKhoangCach()));
        cannangField.setText(Double.toString(donHang.getCanNang()));
        ngayPicker.setValue(donHang.getNgay());
    }
    //Thiết lập nút update thay đổi đơn hàng
    public void updateButton(ActionEvent event) {
        try {
            if(Double.parseDouble(khoangcachField.getText()) <= 0 || Double.parseDouble(cannangField.getText()) <= 0)
                throw new ArithmeticException("Error");//Tạo ra ngoại lệ khi cân nặng hoặc hàng hóa có giá trị nhâp vào <=0
            if(nguoiguiField.getText().isEmpty()||nguoinhanField.getText().isEmpty()||diachiField.getText().isEmpty())
                throw new NullPointerException("Error");//Tạo ra ngoại lệ khi các trường để trống
            else
            {
                donHang.setNguoiGui(nguoiguiField.getText());
                donHang.setNguoiNhan(nguoinhanField.getText());
                donHang.setDiaChi(diachiField.getText());
                donHang.setKhoangCach(Double.parseDouble(khoangcachField.getText()));
                donHang.setCanNang(Double.parseDouble(cannangField.getText()));
                donHang.setNgay(ngayPicker.getValue());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Results");
                alert.setContentText("Update the order successfully!");
                alert.showAndWait();
                closeStage(event);
            }
        }catch(NullPointerException e) {
            //Xử lí ngoại lệ khi chưa nhập xong
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can not update order");
            alert.setContentText("You have not entered complete data" + "\n" +
                    "Please continue enter");
            alert.showAndWait();
        }catch(NumberFormatException | ArithmeticException e) {
            //Xử lí ngoại lệ khi nhập lỗi
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can not update order");
            alert.setContentText("You entered the wrong data" + "\n" +
                    "Please re-enter");
            alert.showAndWait();
        }
    }
    //Đóng cửa sổ
    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
