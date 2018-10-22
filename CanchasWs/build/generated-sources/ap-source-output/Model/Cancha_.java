package Model;

import Model.Administrador;
import Model.Match;
import Model.Reto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-21T18:13:06")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-21T11:54:56")
>>>>>>> mario/ws/jasper
@StaticMetamodel(Cancha.class)
public class Cancha_ { 

    public static volatile SingularAttribute<Cancha, Double> canLatitud;
    public static volatile SingularAttribute<Cancha, Administrador> admId;
    public static volatile ListAttribute<Cancha, Reto> retoList;
    public static volatile SingularAttribute<Cancha, Integer> canPrecioNoches;
    public static volatile SingularAttribute<Cancha, String> canNombre;
    public static volatile SingularAttribute<Cancha, String> canUrl;
    public static volatile SingularAttribute<Cancha, Long> canId;
    public static volatile SingularAttribute<Cancha, Double> canLongitud;
    public static volatile SingularAttribute<Cancha, Integer> canCierra;
    public static volatile SingularAttribute<Cancha, Integer> canCantJugadores;
    public static volatile SingularAttribute<Cancha, Integer> canTel;
    public static volatile SingularAttribute<Cancha, String> canDireccion;
    public static volatile SingularAttribute<Cancha, Integer> canPrecioDia;
    public static volatile ListAttribute<Cancha, Match> matchList;
    public static volatile SingularAttribute<Cancha, Integer> canAbre;

}