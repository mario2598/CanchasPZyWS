package Model;

import Model.Cancha;
import Model.Equipo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-21T19:16:48")
@StaticMetamodel(Match.class)
public class Match_ { 

    public static volatile SingularAttribute<Match, Integer> matCobro;
    public static volatile SingularAttribute<Match, Integer> matMarcador2;
    public static volatile SingularAttribute<Match, Integer> matMarcador1;
    public static volatile SingularAttribute<Match, Integer> matHora;
    public static volatile SingularAttribute<Match, Equipo> equId1;
    public static volatile SingularAttribute<Match, String> matDisputado;
    public static volatile SingularAttribute<Match, Equipo> equId2;
    public static volatile SingularAttribute<Match, Long> matId;
    public static volatile SingularAttribute<Match, Cancha> canId;
    public static volatile SingularAttribute<Match, Integer> equWin;
    public static volatile SingularAttribute<Match, Date> matDate;
    public static volatile SingularAttribute<Match, Integer> matResultado;

}