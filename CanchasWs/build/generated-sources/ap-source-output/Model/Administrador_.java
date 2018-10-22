package Model;

import Model.Cancha;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-21T18:13:06")
@StaticMetamodel(Administrador.class)
public class Administrador_ { 

    public static volatile SingularAttribute<Administrador, Long> admId;
    public static volatile SingularAttribute<Administrador, String> admUsu;
    public static volatile ListAttribute<Administrador, Cancha> canchaList;
    public static volatile SingularAttribute<Administrador, String> admPassword;

}