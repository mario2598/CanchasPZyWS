package Model;

import Model.Cancha;
import Model.Equipo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-16T21:19:12")
@StaticMetamodel(Match.class)
public class Match_ { 

    public static volatile SingularAttribute<Match, BigInteger> matCobro;
    public static volatile SingularAttribute<Match, BigInteger> matMarcador2;
    public static volatile SingularAttribute<Match, BigInteger> matMarcador1;
    public static volatile SingularAttribute<Match, BigInteger> matHora;
    public static volatile SingularAttribute<Match, Equipo> equId1;
    public static volatile SingularAttribute<Match, String> matDisputado;
    public static volatile SingularAttribute<Match, Equipo> equId2;
    public static volatile SingularAttribute<Match, BigDecimal> matId;
    public static volatile SingularAttribute<Match, Cancha> canId;
    public static volatile SingularAttribute<Match, BigInteger> equWin;
    public static volatile SingularAttribute<Match, Date> matDate;
    public static volatile SingularAttribute<Match, BigInteger> matResultado;

}