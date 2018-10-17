package Model;

import Model.Cancha;
import Model.Equipo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-16T21:19:12")
@StaticMetamodel(Reto.class)
public class Reto_ { 

    public static volatile SingularAttribute<Reto, BigInteger> retoHoraFin;
    public static volatile SingularAttribute<Reto, BigInteger> retoHoraIni;
    public static volatile SingularAttribute<Reto, Equipo> equipo1Id;
    public static volatile SingularAttribute<Reto, Equipo> equipo2Id;
    public static volatile SingularAttribute<Reto, BigDecimal> retoId;
    public static volatile SingularAttribute<Reto, String> retoCompleto;
    public static volatile ListAttribute<Reto, Equipo> equipoList;
    public static volatile SingularAttribute<Reto, Date> retoFecha;
    public static volatile SingularAttribute<Reto, BigInteger> retoNivel;
    public static volatile SingularAttribute<Reto, Cancha> canchaId;

}