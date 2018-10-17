package Model;

import Model.Administrador;
import Model.Match;
import Model.Reto;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-16T21:19:12")
@StaticMetamodel(Cancha.class)
public class Cancha_ { 

    public static volatile SingularAttribute<Cancha, BigInteger> canLatitud;
    public static volatile SingularAttribute<Cancha, Administrador> admId;
    public static volatile ListAttribute<Cancha, Reto> retoList;
    public static volatile SingularAttribute<Cancha, BigInteger> canPrecioNoches;
    public static volatile SingularAttribute<Cancha, String> canNombre;
    public static volatile SingularAttribute<Cancha, String> canUrl;
    public static volatile SingularAttribute<Cancha, BigDecimal> canId;
    public static volatile SingularAttribute<Cancha, BigInteger> canLongitud;
    public static volatile SingularAttribute<Cancha, BigInteger> canCierra;
    public static volatile SingularAttribute<Cancha, BigInteger> canCantJugadores;
    public static volatile SingularAttribute<Cancha, BigInteger> canTel;
    public static volatile SingularAttribute<Cancha, String> canDireccion;
    public static volatile SingularAttribute<Cancha, BigInteger> canPrecioDia;
    public static volatile ListAttribute<Cancha, Match> matchList;
    public static volatile SingularAttribute<Cancha, BigInteger> canAbre;

}