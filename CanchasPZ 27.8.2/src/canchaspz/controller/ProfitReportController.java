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
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialogLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;


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
    //private String[] data;
    private String url;
    private CanchaDto field;
    private AdministradorDto admin;
    @FXML
    private StackPane dialogsPane;
    
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private CellStyle headerStyle;
    private CellStyle style;
    private CellStyle footerStyle;
    private File directory;
    @FXML
    private JFXButton exportButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this.exportButton.setDisable(true);
        
        this.dpStartDate.setOnAction(event->{
            if(this.dpEndDate.getValue()!=null){
                if(this.field!=null)
                bindField();
                //bindPrueba();
                
                else 
                   bindAdmin();
                    //bindPrueba();
                this.exportButton.setDisable(false);
            }
                
        });
        this.dpEndDate.setOnAction(event->{
            
            if(this.dpStartDate.getValue()!=null){
                if(this.field!=null)
                bindField();
                //bindPrueba();
                else
                   bindAdmin();
                    //bindPrueba();
                this.exportButton.setDisable(false);
            }
        });
        
        openDirectory();
    }    

    @Override
    public void initialize() {
        this.exportButton.setDisable(true);
        //esto estaba comentado para pruebas
        if(AppContext.getInstance().getCanchaActual()!=null)
         this.field=AppContext.getInstance().getCanchaActual();
        else
            this.admin=AppContext.getInstance().getAdmin();
         unbind();
        //this.field=new CanchaDto();//todo prueba
    }

    @FXML
    private void exportToExcel(ActionEvent event) throws FileNotFoundException, IOException {
        
        //obtiene las fechas del date picker
        Date d1= DateUtil.LocalDate2Date(dpStartDate.getValue());
        Date d2= DateUtil.LocalDate2Date(dpEndDate.getValue());
        
        //valida que la primera sea mayor a la segunda
        if(d1!=null&&d2!=null&&d1.before(d2)){
            if(AppContext.getInstance().getCanchaActual()!=null)
            url=AppContext.getInstance().getCanchaActual().getNombre()+".xls";//obtiene nombre del archivo según lacanchaDto
        else
            url="prueba.xls"; //nombre prueba
        
        openFile();//abre el archivo a partir del directorio + el nombre del archivo(url)
        
        //crea los diferentes estilos para los tipos de celdas
        createCellStyles();
        
        //crea fila de cabecera
        createHeadersRow();
        
        //crea los detalles por día entre dos fechas
        createDetailRows();
        
        
        //crea última fila (totales)
        createLastRow();
        
        saveFile();
        //openFile();
        this.exportButton.setDisable(true);
        }
        else{
            String title = "Reporte de ganancias";
            String body = "Verificar las fechas";
            FlowController.getInstance().showInfoDialog(title, body, null, this.dialogsPane);
        }
        
    }
    
    private void bindField(){
        HashMap<String, SimpleStringProperty> info=this.field.getProfitReportInfoProp(DateUtil.LocalDate2Date(this.dpStartDate.getValue()), DateUtil.LocalDate2Date(this.dpEndDate.getValue()));
        this.lblEmptySpaces.textProperty().bind(info.get("emptySpaces"));
        this.lblOccupedSpaces.textProperty().bind(info.get("occupedSpaces"));
        this.lblProfits.textProperty().bind(info.get("earnedMoney"));
    }
    
    private void bindPrueba(){
        this.lblEmptySpaces.textProperty().bind(new SimpleStringProperty("0"));
        this.lblOccupedSpaces.textProperty().bind(new SimpleStringProperty("10"));
        this.lblProfits.textProperty().bind(new SimpleStringProperty("50000"));
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
    
    /**
     * carga los datos para la fila de totales
     * @param fieldD
     * @return 
     */
    private String[] loadFinalData(){
        String[] data = new String[]{
            "Total",
            this.lblOccupedSpaces.getText(),
            this.lblEmptySpaces.getText(),
            this.lblProfits.getText()
        };
        return data;
    }
    
    /**
     * crea los estilos para los tres tipos de celdas (cabecera,general,total)
     */
    private void createCellStyles(){
        //fuente
        HSSFFont hFont = workbook.createFont();
        hFont.setBold(true);
        hFont.setItalic(true);
        hFont.setColor(HSSFColor.WHITE.index);
        
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setItalic(true);
        //font.setColor(HSSFColor.AQUA.index);
        
        //estilo cabecera
        headerStyle = workbook.createCellStyle();//crea estilo
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(HSSFColor.AQUA.index);//color de relleno
        headerStyle.setFillPattern (FillPatternType.SOLID_FOREGROUND );
        headerStyle.setFont(hFont);//aplica fuente
        headerStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerStyle.setBorderTop(BorderStyle.MEDIUM);
        headerStyle.setLocked(true);
        headerStyle.setBottomBorderColor(HSSFColor.WHITE.index);
        headerStyle.setTopBorderColor(HSSFColor.WHITE.index);
        
        //estilo otras celdas (falta aplicar)
        style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);//aplica fuente
        style.setLocked(true);
        
        //estilo fila totales (última fila)
        footerStyle = workbook.createCellStyle();
        footerStyle.setFont(font);//aplica fuente
    }
    
    /**
     * crea una nueva fila
     * @param row índice de fila a crear
     * @param data arreglo de datos a introducir
     * @param style estilo de celda a aplicar
     */
    private void createRow(Integer row,String[] data,CellStyle style){
        HSSFRow dataRow = sheet.createRow(row);
        for(int i=0;i<data.length;i++){
            HSSFCell cell = dataRow.createCell(i);//defines an Excel cell addressed in reference to a row.
            cell.setCellStyle(style);//aplica estilo
            cell.setCellValue(data[i]);//rellena con datos
            //dataRow.createCell(i).setCellValue(data[i]);
        }
    }
    
    private void createInfoRow(String[] data){
        HSSFRow emptyRow = sheet.createRow(0);//crea una nueva fila
        HSSFCell fCell = emptyRow.createCell(0);//defines an Excel cell addressed in reference to a row.
        fCell.setCellStyle(footerStyle);//aplica estilo
        fCell.setCellValue("                              ");//rellena con datos
            
        HSSFRow dataRow;
        for(int i=1;i<=data.length;i++){
            dataRow = sheet.createRow(i);//crea una nueva fila
            HSSFCell cell = dataRow.createCell(1);//defines an Excel cell addressed in reference to a row.
            cell.setCellStyle(footerStyle);//aplica estilo
            cell.setCellValue(data[i-1]);//rellena con datos
        }
    }
    
    private void createHeadersRow(){
        //cabeceras de columnas
        String[] headers = new String[]{
            "fecha",
            "Espacios ocupados",
            "Espacios vacíos",
            "monto recaudado"
        };
        
        String[] infoData = new String[]{
            "Reporte de Ganancias",
            "fecha inicio: "+this.dpStartDate.getValue().toString(),
            "fecha final: "+this.dpEndDate.getValue().toString(),
        };
        
        //información del reporte
        createInfoRow(infoData);
        //fila de cabeceras
        createRow(sheet.getLastRowNum()+2,headers,headerStyle);
        
        //celdas se ajustan al tamaño adecuado para el contenido
        for(int i=0;i<headers.length;i++){
           sheet.autoSizeColumn(i);
        }
    }
    
    private void createDetailRows(){
        //ArrayList<String[]> array = field.getDayReportPrueba(DateUtil.LocalDate2Date(dpStartDate.getValue()),DateUtil.LocalDate2Date(dpEndDate.getValue()));
        ArrayList<String[]> array = field.getDayReport(DateUtil.LocalDate2Date(dpStartDate.getValue()),DateUtil.LocalDate2Date(dpEndDate.getValue()));
        for(String[] data:array){
            createRow(sheet.getLastRowNum()+1,data,style);
        }
    }
    
    private void createLastRow(){
        String[] fData = loadFinalData();
        createRow(sheet.getLastRowNum()+1,fData,headerStyle);
    }
    
    /**
     * guarda el archivo de excel
     * @param workbook 
     */
    private void saveFile(){
        try{
            //antes crea directorio
            //luego se basa en ese directorio + url para intentar guardar
            FileOutputStream file = new FileOutputStream(directory+"\\" + url);
            workbook.write(file);//escribe con la url a partir del directorio
            
            String title = "Reporte de ganancias";
            String body = "El reporte se ha generado,\nlo puedes encontrar en mis documentos";
            FlowController.getInstance().showInfoDialog(title, body, null, this.dialogsPane);
            showFile(directory + "\\" + url);
        } catch(IOException ex){
            String title = "Reporte de ganancias";
            String body = "Ha ocurrido un error generando el reporte de ganancias, intentalo mas tarde";
            FlowController.getInstance().showInfoDialog(title, body, null, this.dialogsPane);
            System.out.println("Ha ocurrido un error generando el reporte de ganancias,\nintentalo mas tarde");
        }
    }
    
    /**
     * abre el directorio y si no existe lo crea
     */
    private void openDirectory(){
        directory = new File(System.getProperty("user.home") + "\\Documents\\CanchasPZ\\Reportes");
            if (!directory.exists()){
                directory.mkdirs();
            }//crea directorio
    }
    
    /**
     * averigua si el archivo de la ruta definida ya existía para abrirlo y si no existe,lo crea
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void openFile() throws FileNotFoundException, IOException{
        File xlsx = new File(directory+"\\" + url);
        String dates=dpStartDate.getValue().toString()+" a "+dpEndDate.getValue().toString();
        //verifica si ya existía o tiene que crear uno nuevo
        if (xlsx.isFile() && xlsx.exists()) {
                FileInputStream is = new FileInputStream(xlsx);
                workbook = new HSSFWorkbook(is);
                sheet=workbook.getSheetAt(0);//obtiene la primera hoja del workbook que ya existía
                sheet = workbook.createSheet(dates);//hoja excel
                createCellStyles();
                     
		} else {
                    workbook = new HSSFWorkbook();//Documento excel nuevo(2007 o posterior)
                    sheet = workbook.createSheet(dates);//hoja excel
                    createCellStyles();
                    
		}
    }
    
    /**
     * hace que windows abra el archivo
     * @param dir 
     */
    private void showFile(String dir){
        try{ 
            File path = new File (dir);
            Desktop.getDesktop().open(path);
     
        }catch(IOException e){
            System.out.println("error ruta");
            e.printStackTrace();
        }
    }

}
