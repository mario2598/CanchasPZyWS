/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.logic.HourCard;
import canchaspz.logic.TeamCard;
import canchaspz.model.AdministradorDto;
import canchaspz.model.Cancha;
import canchaspz.model.CanchaDto;
import canchaspz.model.Equipo;
import canchaspz.model.EquipoDto;
import canchaspz.model.Match;
import canchaspz.model.MatchDto;
import canchaspz.service.MatchService;
import canchaspz.util.AppContext;
import canchaspz.util.DateUtil;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class AddMatchController extends DialogController implements Initializable {

    private class TeamOptions{
        StackPane teamSP;
        HBox teamOptions;
        JFXButton removeBtn;
    }
    
    //FXML Attributes
    @FXML
    private JFXButton dynamicBtn, removeBtn1, removeBtn2;
    @FXML
    private StackPane team1AP, team2AP;
    @FXML
    private Label scoreLbl;
    private JFXPopup popUp;
    
    //Attributes
    private ArrayList<TeamOptions> teamOptions;
    private MatchDto match;
    private CanchaDto field;
    private boolean reserving;
    private MatchService ms;
    private Integer selectingTeam;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ms = new MatchService();
        teamOptions = new ArrayList<>();
        TeamOptions teamOpt1 = new TeamOptions(), teamOpt2 = new TeamOptions();
        teamOpt1.teamSP = team1AP;
        teamOpt1.teamOptions = createTeamOptions(0);
        teamOpt1.removeBtn = removeBtn1;
        teamOpt2.teamSP = team2AP;
        teamOpt2.teamOptions = createTeamOptions(1);
        teamOpt2.removeBtn = removeBtn2;
        teamOptions.add(teamOpt1);
        teamOptions.add(teamOpt2);
    }    

    @Override
    public void initialize(){
        popUp = new JFXPopup();
        initPopUpMenu();
        removeBtn1.setVisible(false);
        removeBtn2.setVisible(false);
        scoreLbl.setVisible(false);
        this.field = ((HourCard) this.getCard()).getField();
        this.match = ((HourCard) this.getCard()).getMatch();
        this.match.setMatDisputados(false);
        if(((HourCard) this.getCard()).isAvailable()){
            this.reserving = true;
            initForBooking();
        }
        else{
            this.reserving = false;
            initForShowInfo();
        }
    }
    
    private void initForBooking(){
        dynamicBtn.setText("    RESERVAR    ");
        team2AP.getChildren().clear();
        team2AP.getChildren().add(teamOptions.get(1).teamOptions);
        if(AppContext.getInstance().getEquipo()!=null){
            Equipo team = new Equipo(AppContext.getInstance().getEquipo());
            match.setTeam1(team);
            team1AP.getChildren().clear();
            team1AP.getChildren().add(createTeamCard(team));
        } else {
            team1AP.getChildren().clear();
            team1AP.getChildren().add(teamOptions.get(0).teamOptions);
        }
        isRegistrable();
    }
    
    private void initForShowInfo(){
        dynamicBtn.setText("    CANCELAR RESERVA    ");
        team1AP.getChildren().clear();
        team1AP.getChildren().add(createTeamCard(match.getTeam1()));
        team2AP.getChildren().clear();
        team2AP.getChildren().add(createTeamCard(match.getTeam2()));
        isConcluted();
        isCancelable();
    }
    
    private Pane createTeamCard(Equipo team){
        if(team!=null){
            EquipoDto teamDto = new EquipoDto(team);
            TeamCard newCard = new TeamCard();
            newCard.setEquipo(teamDto);
            newCard.initCard();
            newCard.removeRipplerEffect();
            return newCard;
        } else {
            return createAnonymousLogo();
        }
    }

    @FXML
    private void addBtnAction(MouseEvent event){
        HourCard hc = ((HourCard) this.getCard());
        if(reserving){
            if(event.getButton().equals(MouseButton.PRIMARY)){
                match.setCanId(new Cancha(field));
                //Esta condicion persiste el partido en la base de datos, devolviendo true o false
                if(ms.persistMatch(match)){
                    ms.removeByID(match.getMatId()+1); // Se esta creando el mismo reto dos veces en la base de datos, motivo aun no encontrado, solucion temporal
                    Match entMatch = new Match(match);
                    //Agrego el partido en la cancha que esta en la card, como esta referenciado se guarda en la lista del app context de una vez
                    field.addMatch(entMatch);
                    //Evaluo si hay equipos en el partido, si los hay agrego el match en sus listas
                    if(match.getTeam1()!=null){
                        List<Equipo> teamList = AppContext.getInstance().equipoList;
                        ArrayList<Equipo> teamAList = new ArrayList<>(teamList);
                        Equipo team = teamAList.stream()
                                .filter(t -> t.getEquId().equals(entMatch.getEquId1().getEquId()))
                                .findFirst()
                                .get();
                        team.getMatchList1().add(entMatch);
                    }
                    if(match.getTeam2()!=null){
                        List<Equipo> teamList = AppContext.getInstance().equipoList;
                        ArrayList<Equipo> teamAList = new ArrayList<>(teamList);
                        Equipo team = teamAList.stream()
                                .filter(t -> t.getEquId().equals(entMatch.getEquId2().getEquId()))
                                .findFirst()
                                .get();
                        team.getMatchList().add(entMatch);
                    }
                    hc.setAvailable(false);
                    hc.retoDisponible(false);
                } else {
                    System.out.println("Error persistiendo el partido");
                }
                FlowController.getInstance().closeInternalDialog("INFOCANCHA");
            }
        } else {
            //Evento para cancelar reserva
            Match entMatch = field.getMatchArrayList().stream()
                    .filter(aux -> DateUtil.Date2LocalDate(aux.getMatDate()).equals(match.getMatDate())  && aux.getMatHora().equals(match.getMatHora()))
                    .findAny()
                    .get();
            if(ms.removeMatch(match)){
                //Elimino el partido del card y de la lista de partidos del appContext (lista referenciada, se elimina en ambos lugares)
                field.getMatchList().remove(entMatch);
                //Evaluo si hay logeado un administrador, si lo hay elimino el partido de la lista de canchas del admin
                AdministradorDto admin = AppContext.getInstance().getAdmin();
                if(admin!=null && field.getAdmId().getAdmId().equals(admin.getAdmId())){
                    ArrayList<Cancha> adminFieldList = new ArrayList<>(admin.getCanchaList());
                    Cancha adminField = adminFieldList.stream().filter(f -> f.getCanId().equals(field.getCanID())).findFirst().get();
                    adminField.getMatchList().remove(entMatch);
                }
                //Evaluo si el partido tenia equipos, si los tiene elimino el partido en ambos
                if(entMatch.getEquId1()!=null){
                        List<Equipo> teamList = AppContext.getInstance().equipoList;
                        ArrayList<Equipo> teamAList = new ArrayList<>(teamList);
                        Equipo team = teamAList.stream()
                                .filter(t -> t.getEquId().equals(entMatch.getEquId1().getEquId()))
                                .findFirst()
                                .get();
                        team.getMatchList1().remove(entMatch);
                }
                if(entMatch.getEquId2()!=null){
                    List<Equipo> teamList = AppContext.getInstance().equipoList;
                    ArrayList<Equipo> teamAList = new ArrayList<>(teamList);
                    Equipo team = teamAList.stream()
                            .filter(t -> t.getEquId().equals(entMatch.getEquId2().getEquId()))
                            .findFirst()
                            .get();
                    team.getMatchList().remove(entMatch);
                }
                hc.setAvailable(true);
//                System.out.println("Comparando el hourField con la fecha->"+entMatch.getMatDate()+", hora->"+entMatch.getMatHora());
//                System.out.println("Con los siguientes retos de la cancha " + field.getCanNombre());
//                for(Reto r : field.getRetoList()){
//                    System.out.println("Reto: fecha->"+r.getRetoFecha()
//                            +", horaIni->"+r.getRetoHoraIni()
//                            +", horaFin->"+r.getRetoHoraFin());
                if(field.getRetoArrayList().stream().anyMatch(dare -> dare.getRetoFecha().equals(entMatch.getMatDate()) 
                                                            && dare.getRetoHoraIni()<= entMatch.getMatHora() 
                                                            && dare.getRetoHoraFin()> entMatch.getMatHora())){
                    hc.retoDisponible(true);
                }
            }
            FlowController.getInstance().closeInternalDialog("INFOCANCHA");
        }
        event.consume();
    }
    
    private void teamOption1(Integer teamNumber){
        EquipoDto team = AppContext.getInstance().getEquipo();
        if(teamNumber.equals(0))
            match.setTeam1(new Equipo(team));
        else
            match.setTeam2(new Equipo(team));
        TeamCard newCard = new TeamCard();
        newCard.setEquipo(team);
        newCard.initCard();
        newCard.removeRipplerEffect();
        teamOptions.get(teamNumber).teamSP.getChildren().clear();
        teamOptions.get(teamNumber).teamSP.getChildren().add(newCard);
        teamOptions.get(teamNumber).removeBtn.setVisible(true);
        isRegistrable();
    }
    
    private void teamOption2(Integer teamNumber){
        //Falta opcion para mostrar lista de equipos
        selectingTeam = teamNumber;
        try{
            popUp.show(teamOptions.get(teamNumber).teamSP);
        } catch(NullPointerException ex){
            
        }
    }
    
    private void teamOption3(Integer teamNumber){
        teamOptions.get(teamNumber).teamSP.getChildren().clear();
        teamOptions.get(teamNumber).teamSP.getChildren().add(createAnonymousLogo());
        teamOptions.get(teamNumber).removeBtn.setVisible(true);
        isRegistrable();
    }
    
    @FXML
    private void removeTeam1(){
        removeTeam(0);
    }
    
    @FXML
    private void removeTeam2(){
        removeTeam(1);
    }
    
    private void removeTeam(Integer teamNumber){
        switch(teamNumber){
            case 0:
                match.setTeam1(null);
                break;
            case 1:
                match.setTeam2(null);
                break;
        }
        teamOptions.get(teamNumber).teamSP.getChildren().clear();
        teamOptions.get(teamNumber).teamSP.getChildren().add(teamOptions.get(teamNumber).teamOptions);
        teamOptions.get(teamNumber).removeBtn.setVisible(false);
        isRegistrable();
    }

    private void isRegistrable(){
        if(AppContext.getInstance().getEquipo()!=null)
            dynamicBtn.setVisible(teamOptions.get(1).removeBtn.isVisible());
        else
            dynamicBtn.setVisible(teamOptions.stream().allMatch(to -> to.removeBtn.isVisible()));
    }
    
    private void isCancelable(){
        boolean cancelable = false;
        AdministradorDto admin = AppContext.getInstance().getAdmin();
        if(admin!=null){
            if(field.getAdmId().getAdmId().equals(AppContext.getInstance().getAdmin().getAdmId()))
                cancelable = true;
        } else if(AppContext.getInstance().getEquipo()!=null){
            try{
                if(AppContext.getInstance().getEquipo().getEquId().equals(match.getTeam1().getEquId()) 
                || AppContext.getInstance().getEquipo().getEquId().equals(match.getTeam2().getEquId())){
                    LocalDate matchDate = match.getMatDate();
                    Long difD = ChronoUnit.DAYS.between(LocalDate.now(), matchDate);
                    LocalTime matchTime = LocalTime.of(Math.toIntExact(match.getMatHora()), 0);
                    Long difH = ChronoUnit.HOURS.between(LocalTime.now(), matchTime);
                    if(difD>0 || (difD==0 && difH>=3))
                        cancelable = true;
                }
            } catch(NullPointerException ex){
                cancelable = false;
            }
        }
        dynamicBtn.setVisible(cancelable);
    }
    
    private void isConcluted(){
        if(match.getMatDisputados()){
            scoreLbl.setText(String.valueOf(match.getMatMarcador1()) + " - " + String.valueOf(match.getMatMarcador2()));
            scoreLbl.setVisible(true);
        } else {
            scoreLbl.setVisible(false);
        }
    }
    
    //Solo inicializa componentes graficos
    private HBox createTeamOptions(Integer teamNumber){
        //Inicializo el HBox donde estan las opciones para agregar el segundo equipo
        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 0, 30, 0));
        hb.setSpacing(15);
        hb.setAlignment(Pos.CENTER);
        JFXButton option1 = new JFXButton(), option2 = new JFXButton(), option3 = new JFXButton();
//        if(AppContext.getInstance().getEquipo()!=null){
//            //Boton para autocompletar datos del equipo logeado
//            option1.setPrefSize(50, 50);
//            option1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//            option1.setAlignment(Pos.CENTER);
//            option1.setFont(new Font(20));
//            option1.setTooltip(new Tooltip("Jugar contra nosotros mismos"));
//            FontAwesomeIconView selfIcon = new FontAwesomeIconView(FontAwesomeIcon.REFRESH);
//            selfIcon.setFill(Paint.valueOf("#bcbcbc"));
//            selfIcon.setSize("35");
//            selfIcon.setBoundsType(TextBoundsType.VISUAL);
//            option1.setGraphic(selfIcon);
//            option1.setOnMouseClicked(event -> {
//                if(event.getButton().equals(MouseButton.PRIMARY))
//                    teamOption1(teamNumber);
//                event.consume();
//            });
//            hb.getChildren().add(option1);
//        }
        //Boton para jugar contra equipo registrado en el sistema
        option2.setPrefSize(50, 50);
        option2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        option2.setAlignment(Pos.CENTER);
        option2.setFont(new Font(20));
        option2.setTooltip(new Tooltip("Jugar contra equipo registrado"));
        FontAwesomeIconView addIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
        addIcon.setFill(Paint.valueOf("#bcbcbc"));
        addIcon.setSize("35");
        addIcon.setBoundsType(TextBoundsType.VISUAL);
        option2.setGraphic(addIcon);
        option2.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY))
                teamOption2(teamNumber);
            event.consume();
        });
        hb.getChildren().add(option2);
        //Boton para jugar contra equipo no registrado en el sistema
        option3.setPrefSize(50, 50);
        option3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        option3.setAlignment(Pos.CENTER);
        option3.setFont(new Font(20));
        option3.setTooltip(new Tooltip("Jugar contra equipo desconocido"));
        FontAwesomeIconView unknownIcon = new FontAwesomeIconView(FontAwesomeIcon.QUESTION_CIRCLE);
        unknownIcon.setFill(Paint.valueOf("#bcbcbc"));
        unknownIcon.setSize("35");
        unknownIcon.setBoundsType(TextBoundsType.VISUAL);
        option3.setGraphic(unknownIcon);
        option3.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY))
                teamOption3(teamNumber);
            event.consume();
        });
        hb.getChildren().add(option3);
        return hb;
    }
    
    private Pane createAnonymousLogo(){
        Pane anonLogo = new Pane();
        anonLogo.setPrefSize(200, 240);
        ImageView IV = new ImageView("canchaspz/resources/questionMark.png");
        IV.setPreserveRatio(false);
        IV.setFitWidth(200);
        IV.setFitHeight(200);
        Label nameLabel = new Label();
        //Styling se quita cuando haya css
        nameLabel.setStyle("-fx-text-fill: black; -font-size: 20px;");
        nameLabel.setPrefSize(200, 40);
        nameLabel.setLayoutY(200);
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setText("Equipo No Registrado");
        anonLogo.getChildren().addAll(IV, nameLabel);
        return anonLogo;
    }
    
    private void initPopUpMenu(){
        ScrollPane scrollP = new ScrollPane();
        scrollP.setFitToHeight(true);
        scrollP.setFitToWidth(true);
        scrollP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollP.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollP.setPrefWidth(200);
        scrollP.setMaxHeight(250);
        
        VBox vb = new VBox();
        vb.setAlignment(Pos.TOP_CENTER);
        vb.setPrefWidth(200);
        
        scrollP.setContent(vb);
        (new ArrayList<>(AppContext.getInstance().equipoList)).stream().forEach(team -> {
            JFXButton teamBtn = new JFXButton();
            teamBtn.setText(team.getEquNombre());
            teamBtn.getStyleClass().add("popup-button");
            teamBtn.setFocusTraversable(false);
            teamBtn.setPrefSize(200, 50);
            teamBtn.setMinHeight(50);
            teamBtn.setOnAction(event -> {
                //Evento para rellenar datos del equipo
                Equipo entTeam = (new ArrayList<>(AppContext.getInstance().equipoList)).stream().filter(t -> t.getEquId().equals(team.getEquId())).findAny().get();
                EquipoDto dtoTeam = new EquipoDto(entTeam);
                if(selectingTeam.equals(0))
                    match.setTeam1(entTeam);
                else
                    match.setTeam2(entTeam);
                TeamCard newCard = new TeamCard();
                newCard.setEquipo(dtoTeam);
                newCard.initCard();
                newCard.removeRipplerEffect();
                teamOptions.get(selectingTeam).teamSP.getChildren().clear();
                teamOptions.get(selectingTeam).teamSP.getChildren().add(newCard);
                teamOptions.get(selectingTeam).removeBtn.setVisible(true);
                isRegistrable();
                popUp.hide();
                event.consume();
            });
            vb.getChildren().add(teamBtn);
        });
        popUp.setPopupContent(scrollP);
    }
    
}
