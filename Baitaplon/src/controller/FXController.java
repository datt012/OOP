//Trần Tiến Đạt
package controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import object.DonHang;
import object.DuongBo;
import object.HangKhong;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
//Hiện thực giao diện Initializable
public class FXController implements Initializable {
    //Khai báo các thành phần ứng dụng
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
    private TableColumn<DonHang, String> chonColumn;
    @FXML
    private TableColumn<DonHang, LocalDate> ngayColumn;
    @FXML
    private TableColumn<DonHang,String> idColumn;
    @FXML
    private TextField khachhangField;
    @FXML
    private TextField diachiField;
    @FXML
    private TextField sonhapvaoField;
    @FXML
    private CheckBox selectAll;
    @FXML
    private ComboBox<Integer> thangBox;
    @FXML
    private ComboBox<Integer> namBox;
    private final ObservableList<DonHang> listDonHang = FXCollections.observableArrayList();
    @Override
    //Phương thức khởi tạo initialize
    public void initialize(URL url, ResourceBundle rb) {
        DecimalFormat currency = new DecimalFormat("0.000đ");//Khởi tạo biến định dạng số thập phân
        //Đưa các thuộc tính phương thức của đối tượng vào tableview
        nguoinhanColumn.setCellValueFactory(new PropertyValueFactory<>("nguoiNhan"));
        nguoiguiColumn.setCellValueFactory(new PropertyValueFactory<>("nguoiGui"));
        diachiColumn.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        cannangColumn.setCellValueFactory(new PropertyValueFactory<>("canNang"));
        khoangcachColumn.setCellValueFactory(new PropertyValueFactory<>("khoangCach"));
        loaiColumn.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().loaiHang()));
        chonColumn.setCellValueFactory(new PropertyValueFactory<>("chon"));
        donhangTable.setItems(listDonHang);
        donhangTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        chiphiColumn.setCellValueFactory(o -> new SimpleStringProperty(currency.format(o.getValue().chiPhi())));
        ngayColumn.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        //Thiết lập ô select all
        selectAll.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            for (DonHang item : donhangTable.getItems()) {
                item.getChon().setSelected(selectAll.isSelected());
            }
        });
        idColumn.setCellValueFactory(o -> {
            DonHang item = o.getValue();
            int index = donhangTable.getItems().indexOf(item)+1;
            return new SimpleStringProperty(Integer.toString(index));
        });
        //Khởi tạo mảng tháng năm áp dụng cho chức năng cuối
        ObservableList<Integer> arrMonth = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        thangBox.setItems(arrMonth);
        ObservableList<Integer> arrYear = FXCollections.observableArrayList(2010,2011,2012,2013,2014,2015
                ,2016,2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        namBox.setItems(arrYear);
    }
    //Thiết lập nút add chức năng tạo mới đơn hàng
    public void addButton() throws IOException {
        //Tạo 1 cửa sổ mới để khởi tạo đơn hàng
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXadd.fxml"));
        Parent parent = fxmlLoader.load();
        AddController addController = fxmlLoader.getController();
        addController.setLoai();
        addController.setDonHang(listDonHang);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Add");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    //Thiết lập nút delete chức năng xóa đơn hàng
    public void deleteButton() {
        if (listDonHang.isEmpty()) {
            //Nếu đơn hàng trống thì khi ấn nút delete sẽ báo lỗi
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not delete order");
            alert.setContentText("The list is empty");
            alert.showAndWait();
        }
        else
        {
            ObservableList<DonHang> selectRow = FXCollections.observableArrayList();
            int count = 0;
            for (DonHang donHang : listDonHang) {
                if (donHang.getChon().isSelected()) {
                    selectRow.add(donHang);
                    count++;
                }
            }
            listDonHang.removeAll(selectRow);
            Alert alert;
            if (count != 0)
            {
                //Xóa xong các đơn hàng thì thông báo thành công
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Results");
                alert.setContentText("Delete the order successfully!");
            }
            else
            {
                //Nếu chưa chọn đơn hàng khi ấn nút delete sẽ thông báo lỗi
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Can not delete order");
                alert.setContentText("You have not selected an order");
            }
            alert.showAndWait();
        }
    }
    //Thiết lập nút update chức năng cập nhật đơn hàng
    public void updateButton() {
        try {
            if(listDonHang.isEmpty()) throw new IOException("Error"); //tạo ra ngoại lệ nếu ấn nút update khi đơn hàng trống
            else {
                //Tạo 1 cửa sổ mới để cập nhật đơn hàng
                DonHang DonHangSelected = donhangTable.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXupdate.fxml"));
                Parent parent = fxmlLoader.load();
                UpdateController updateController = fxmlLoader.getController();
                updateController.setDonHang(DonHangSelected);
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Update");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                donhangTable.refresh();
                donhangTable.getSelectionModel().clearSelection();
            }
        }catch(NullPointerException e){
            //Xử lí ngoại lệ khi chưa chọn đơn hàng
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not update order");
            alert.setContentText("You have not selected the order");
            alert.showAndWait();
        }catch(IOException e){
            //Xử lí ngoại lệ khi đơn hàng trống
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not update order");
            alert.setContentText("The list is empty");
            alert.showAndWait();
        }
    }
    //Thiết lập ô tìm kiếm
    public void searchField() {
        FilteredList<DonHang> filteredData = new FilteredList<>(listDonHang, p -> true);
        filteredData.predicateProperty().bind(Bindings.createObjectBinding(() ->
                        o -> (o.getNguoiNhan().contains(khachhangField.getText())
                                || o.getNguoiGui().contains(khachhangField.getText()))
                                && o.getDiaChi().contains(diachiField.getText())
                                && o.chiPhi() > parse(sonhapvaoField.getText()),
                khachhangField.textProperty(),
                diachiField.textProperty(),
                sonhapvaoField.textProperty()
        ));
        donhangTable.setItems(filteredData);
    }
    //Thiết lập nút statistic chức năng thống kê đơn hàng trong tháng
    public void statisticButton() {
        try {
            //Tạo 1 cửa sổ mới để thống kê đơn hàng
            FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("FXlist.fxml")));
            Parent parent = fxmlLoader.load();
            ListController listController = fxmlLoader.getController();
            ObservableList<DonHang> listDoanhthu = FXCollections.observableArrayList(thuNhap(thangBox.getSelectionModel().getSelectedItem(),
                    namBox.getSelectionModel().getSelectedItem()));
            listController.setDonHang(listDoanhthu);
            listController.setDoanhThu(listController.doanhThu());
            listController.setThangNam(thangBox.getSelectionModel().getSelectedItem(), namBox.getSelectionModel().getSelectedItem());
            listController.setSoLuong(listDoanhthu.size());
            listController.initialize();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("List");
            stage.setScene(scene);
            stage.showAndWait();
            thangBox.getSelectionModel().clearSelection();
            namBox.getSelectionModel().clearSelection();
        }catch(NullPointerException | IOException e){
            //Xử lí ngoại lệ khi chưa chọn tháng hoặc năm
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not create list order");
            alert.setContentText("You have not selected");
            alert.showAndWait();
        }
    }
    //Thiết lập nút open chức năng mở file chứa các đơn hàng
    public void handleOpen() throws IOException {
        donhangTable.setItems(listDonHang);
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open The Order");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(secondaryStage);
        if (file != null) {
            openFile(file);
        }
    }
    //Mở file đuôi txt,pdf
    private void openFile(File file) throws IOException {
        BufferedReader inWriter = new BufferedReader(new FileReader(file));
        String line;
        String[] array;
        while ((line = inWriter.readLine()) != null) {
            array = line.split("[:|]");
            DonHang donHang = null;
            if (array[13].trim().equalsIgnoreCase("Đường Bộ")) { //Upcasting
                donHang = new DuongBo(array[1].trim(), array[3].trim(), array[5].trim(), Double.parseDouble(array[7]), Double.parseDouble(array[9]), LocalDate.parse(array[11].replaceAll("\\s","")));
            }
            if (array[13].trim().equalsIgnoreCase("Hàng Không")) { //Upcasting
                donHang = new HangKhong(array[1].trim(), array[3].trim(), array[5].trim(), Double.parseDouble(array[7]), Double.parseDouble(array[9]), LocalDate.parse(array[11].replaceAll("\\s","")));
            }
            donhangTable.getItems().add(donHang);
        }
        inWriter.close();
    }
    //Thiết lập nút save chức năng lưu file chứa các đơn hàng
    public void handleSave(){
        try {
            if (listDonHang.isEmpty()) throw new IOException("Error");
            else {
                Stage secondaryStage = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save The Order");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                File file = fileChooser.showSaveDialog(secondaryStage);
                if (file != null) {
                    saveFile(donhangTable.getItems(), file);
                }
            }
        }catch(IOException e){
            //Xử lí ngoại lệ khi đơn hàng trống
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not save order");
            alert.setContentText("You have nothing to save");
            alert.showAndWait();
        }
    }
    //Lưu file đuôi txt,pdf
    private void saveFile(ObservableList<DonHang> observableList, File file) throws IOException {
        BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
        for (DonHang donHang : observableList) {
            outWriter.write(donHang.toString());
            outWriter.newLine();
        }
        outWriter.close();
    }
    //Thiết lập nút new tạo danh sách mới
    public void handleNew()
    {
        listDonHang.clear();
    }
    //Lọc ra các đơn hàng theo tháng năm
    private List<DonHang> thuNhap(int thang, int nam) {
        return listDonHang.stream().filter(o->o.getNgay().getMonthValue()==thang&&o.getNgay().getYear()==nam).collect(Collectors.toList());
    }
    //Chuyển đổi số dạng chuỗi sang double
    private double parse(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                return -1;
            }
        }
        else return 0;
    }
}































