/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.model.AdministradorDto;
import canchaspz.model.CanchaDto;
import canchaspz.util.AppContext;
import canchaspz.util.DateUtil;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;

/**
 * FXML Controller class
 *
 * @author robri
 */
public class ProfitReportController extends DialogController implements Initializable {

    @FXML
    private JFXDialogLayout root;
    @FXML
    private Label lblOccupedSpaces;
    @FXML
    private Label lblEmptySpaces;
    @FXML
    private Label lblProfits;
    @FXML
    private JFXDatePicker dpStartDate;
    @FXML
    private JFXDatePicker dpEndDate;
    
    private CanchaDto field;
    private AdministradorDto admin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.dpStartDate.setOnAction(event->{
            if(this.dpEndDate.getValue()!=null){
                if(this.field!=null)
                bindField();
                else
                   bindAdmin(); 
            }
                
        });
        this.dpEndDate.setOnAction(event->{
            if(this.dpStartDate.getValue()!=null){
                if(this.field!=null)
                bindField();
                else
                   bindAdmin(); 
            }
        });
    }    

    @Override
    public void initialize() {
        if(AppContext.getInstance().getCanchaActual()!=null)
         this.field=AppContext.getInstance().getCanchaActual();
        else
            this.admin=AppContext.getInstance().getAdmin();
         unbind();
    }

    @FXML
    private void exportToExcel(ActionEvent event) throws FileNotFoundException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        if(AppContext.getInstance().getCanchaActual()!=null)
        workbook.setSheetName(0, "Reporte Ganancias "+ AppContext.getInstance().getCanchaActual().getNombre());
        else
            workbook.setSheetName(0, "Reporte Ganancias "+ AppContext.getInstance().getAdmin().getAdmUsu());
        
        String[] data = new String[]{
            this.dpStartDate.getValue().toString(),
            this.dpEndDate.getValue().toString(),
            this.lblOccupedSpaces.getText(),
            this.lblEmptySpaces.getText(),
            this.lblProfits.getText()
        };
        
        String[] headers = new String[]{
            "fecha inicio",
            "fecha final",
            "Espacios ocupados",
            "Espacios vacíos",
            "monto recaudado"
        };
        
        CellStyle headerStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerStyle.setFont(font);

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        
        //crea la fila de cabecera
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(headers[i]);
        }
        
        //crea la segunda fila con datos
        HSSFRow dataRow = sheet.createRow(1);
        for(int i=0;i<data.length;i++){
            dataRow.createCell(i).setCellValue(data[i]);
        }
        
        for(int i=0;i<headers.length;i++){
           sheet.autoSizeColumn(i);
        }
        
        try (FileOutputStream file = new FileOutputStream("src/canchaspz/resources/reports/report"+AppContext.getInstance().getCanchaActual().getNombre()+".xls")) {
            workbook.write(file);
        }
    }
    
    private void bindField(){
        HashMap<String, SimpleStringProperty> info=this.field.getProfitReportInfoProp(DateUtil.LocalDate2Date(this.dpStartDate.getValue()), DateUtil.LocalDate2Date(this.dpEndDate.getValue()));
        this.lblEmptySpaces.textProperty().bind(info.get("emptySpaces"));
        this.lblOccupedSpaces.textProperty().bind(info.get("occupedSpaces"));
        this.lblProfits.textProperty().bind(info.get("earnedMoney"));
    }
    
    private void bindAdmin(){
        HashMap<String, SimpleStringProperty> info=this.admin.getProfitReportInfoProp(DateUtil.LocalDate2Date(this.dpStartDate.getValue()), DateUtil.LocalDate2Date(this.dpEndDate.getValue()));
        this.lblEmptySpaces.textProperty().bind(info.get("emptySpaces"));
        this.lblOccupedSpaces.textProperty().bind(info.get("occupedSpaces"));
        this.lblProfits.textProperty().bind(info.get("earnedMoney"));
    }
    
    private void unbind(){
        this.lblEmptySpaces.textProperty().unbind();
        this.lblOccupedSpaces.textProperty().unbind();
        this.lblProfits.textProperty().unbind();
    }
}
