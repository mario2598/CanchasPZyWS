/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chris
 */
@XmlRootElement(name = "MatchDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class MatchDto {
    //Attributes
    private Long matId;
    private Date matDate;
    private Integer matHora;
    private String matDisputado;
    private Integer matMarcador1;
    private Integer matMarcador2;
    private Integer matResultado;
    private Integer matCobro;
    private Integer equWin;
    private CanchaDto canId;
    private EquipoDto equId2;
    private EquipoDto equId1;
    
    //Constructors
    public MatchDto() {
        
    }
    
    public MatchDto(Match match) {
        if(match.getMatId() != null){
            this.matId = match.getMatId();
        }
        duplicarInfo(match);
    }
    
    //Methods
    public void duplicarInfo(Match match){
        this.matDate = match.getMatDate();
        this.matHora = match.getMatHora();
        this.matDisputado = match.getMatDisputado();
        this.matMarcador1 = match.getMatMarcador1();
        this.matMarcador2 = match.getMatMarcador2();
        this.matResultado = match.getMatResultado();
        this.matCobro = match.getMatCobro();
        this.equWin = match.getEquWin();
    }
    
    public void copiarSoloIDEquipos(Match match){
        this.equId1 = new EquipoDto();
        this.equId1.setEquId(match.getEquId1().getEquId());
        this.equId2 = new EquipoDto();
        this.equId2.setEquId(match.getEquId2().getEquId());
    }
    
    public void copiarSoloIdCancha(Match match){
        this.canId = new CanchaDto();
        this.canId.setCanId(match.getCanId().getCanId());
    }
    
    //Getters and Setters
    public Long getMatId() {
        return matId;
    }

    public void setMatId(Long matId) {
        this.matId = matId;
    }

    public Date getMatDate() {
        return matDate;
    }

    public void setMatDate(Date matDate) {
        this.matDate = matDate;
    }

    public Integer getMatHora() {
        return matHora;
    }

    public void setMatHora(Integer matHora) {
        this.matHora = matHora;
    }

    public String getMatDisputado() {
        return matDisputado;
    }

    public void setMatDisputado(String matDisputado) {
        this.matDisputado = matDisputado;
    }

    public Integer getMatMarcador1() {
        return matMarcador1;
    }

    public void setMatMarcador1(Integer matMarcador1) {
        this.matMarcador1 = matMarcador1;
    }

    public Integer getMatMarcador2() {
        return matMarcador2;
    }

    public void setMatMarcador2(Integer matMarcador2) {
        this.matMarcador2 = matMarcador2;
    }

    public Integer getMatResultado() {
        return matResultado;
    }

    public void setMatResultado(Integer matResultado) {
        this.matResultado = matResultado;
    }

    public Integer getMatCobro() {
        return matCobro;
    }

    public void setMatCobro(Integer matCobro) {
        this.matCobro = matCobro;
    }

    public Integer getEquWin() {
        return equWin;
    }

    public void setEquWin(Integer equWin) {
        this.equWin = equWin;
    }

    public CanchaDto getCanId() {
        return canId;
    }

    public void setCanId(CanchaDto canId) {
        this.canId = canId;
    }

    public EquipoDto getEquId2() {
        return equId2;
    }

    public void setEquId2(EquipoDto equId2) {
        this.equId2 = equId2;
    }

    public EquipoDto getEquId1() {
        return equId1;
    }

    public void setEquId1(EquipoDto equId1) {
        this.equId1 = equId1;
    }
    
}
