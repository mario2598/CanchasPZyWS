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
    public Long matId;
    public Date matDate;
    public Integer matHora;
    public String matDisputado;
    public Integer matMarcador1;
    public Integer matMarcador2;
    public Integer matResultado;
    public Integer matCobro;
    public Integer equWin;
    public CanchaDto canId;
    public EquipoDto equId2;
    public EquipoDto equId1;
    
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
        if(match.getEquId1()!=null){
            this.equId1 = new EquipoDto();
            this.equId1.equId = (match.getEquId1().getEquId());
        } else {
            this.equId1 = null;
        }
        if(match.getEquId2()!=null){
            this.equId2 = new EquipoDto();
            this.equId2.equId = (match.getEquId2().getEquId());
        } else {
            this.equId2 = null;
        }
    }
    
    public void copiarSoloIdCancha(Match match){
        this.canId = new CanchaDto(match.getCanId());
    }
    
}
