package Model;

import Model.Match;
import Model.Reto;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-16T21:19:12")
@StaticMetamodel(Equipo.class)
public class Equipo_ { 

    public static volatile SingularAttribute<Equipo, BigDecimal> equId;
    public static volatile ListAttribute<Equipo, Reto> retoList;
    public static volatile SingularAttribute<Equipo, String> equUsu;
    public static volatile SingularAttribute<Equipo, Reto> retoId;
    public static volatile ListAttribute<Equipo, Reto> retoList1;
    public static volatile SingularAttribute<Equipo, String> equUrl;
    public static volatile SingularAttribute<Equipo, BigInteger> equPts;
    public static volatile SingularAttribute<Equipo, String> equNomJug2;
    public static volatile ListAttribute<Equipo, Match> matchList1;
    public static volatile SingularAttribute<Equipo, String> equNomJug1;
    public static volatile SingularAttribute<Equipo, String> equPassword;
    public static volatile SingularAttribute<Equipo, String> equNombre;
    public static volatile SingularAttribute<Equipo, BigInteger> equTelJug1;
    public static volatile ListAttribute<Equipo, Match> matchList;
    public static volatile SingularAttribute<Equipo, BigInteger> equTelJug2;

}