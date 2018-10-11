
package canchaspz.util;

import canchaspz.model.Administrador;
import canchaspz.model.AdministradorDto;
import canchaspz.model.Cancha;
import canchaspz.model.CanchaDto;
import canchaspz.model.Equipo;
import canchaspz.model.EquipoDto;
import canchaspz.model.Reto;
import canchaspz.model.RetoDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


public class AppContext {

    private static AppContext INSTANCE = null;
    private static HashMap<String, Object> context = new HashMap<>();
    AdministradorDto admin;
    EquipoDto equipo;
    EquipoDto equiActual;
    Administrador adminEntity = new Administrador();
    Equipo equipoEntity = new Equipo();
    RetoDto retoActual;
    public List<Cancha> canchaList;
    public List<Reto> retoList;
    public List<Equipo> equipoList;
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    CanchaDto canchaActual=new CanchaDto();
    
    public EquipoDto getEquiActual() {
        return equiActual;
    }

    public void setEquiActual(EquipoDto equiActual) {
        this.equiActual = equiActual;
    }
     
    private AppContext() {
        cargarPropiedades();
    }

    public RetoDto getRetoActual() {
        return retoActual;
    }

    public void setRetoActual(RetoDto retoActual) {
        this.retoActual = retoActual;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AppContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppContext();
                }
            }
        }
    }

    public AdministradorDto getAdmin() {
        return admin;
    }

    public void setAdmin(AdministradorDto admin) {
        this.admin = admin;
    }

    public EquipoDto getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoDto equipo) {
        this.equipo = equipo;
    }

    public static AppContext getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }
    
    public void fillRetos() {
        retoList = new ArrayList();
         for(Cancha c : canchaList){
             for(Reto r : c.getRetoList()){
                 retoList.add(r);
             }
         }
    }
    
    public void fillCanchas() {
         EntityManager em = EntityManagerHelper.getInstance().getManager();
         Query qryCancha = em.createNamedQuery("Cancha.findAll",Cancha.class);
         canchaList = qryCancha.getResultList();
         fillRetos();
    }
    
    public void fillEquipos(){
        Query tmQry = em.createNamedQuery("Equipo.findAll", Equipo.class);
        equipoList = tmQry.getResultList();
        if(equipoList==null){
            equipoList = new ArrayList<>();
        }
    }
    
    public void updateDB() {
          
         Query qryUsuEqu = em.createNamedQuery("Equipo.findByEquUsu",Equipo.class);
         qryUsuEqu.setParameter("equUsu",equipo.getEquUsu());
         try {
             equipoEntity = (Equipo) qryUsuEqu.getSingleResult();
           
        } catch (NoResultException ex) {
            System.out.print(equipo.getEquUsu());
        }
        
         this.equipo = new EquipoDto(equipoEntity);
         Query qryCancha = em.createNamedQuery("Cancha.findAll",Cancha.class);
         canchaList = qryCancha.getResultList();
         fillRetos();
    }

    public List<Reto> getRetoList() {
        return retoList;
    }

    public void setRetoList(List<Reto> retoList) {
        this.retoList = retoList;
    }

    public List<Cancha> getCanchaList() {
        return canchaList;
    }

    public void setCanchaList(List<Cancha> canchaList) {
        this.canchaList = canchaList;
    }
    
    public CanchaDto getCanchaActual() {
        return canchaActual;
    }

    public void setCanchaActual(CanchaDto canchaActual) {
        this.canchaActual = canchaActual;
    }
    
    private void cargarPropiedades(){
//        try {
//            FileInputStream configFile;
//            configFile = new FileInputStream("config/properties.ini");
//            Properties appProperties = new Properties();
//            appProperties.load(configFile);
//            configFile.close();
////            if (appProperties.getProperty("propiedades.rutalog") != null) {
////                this.set("rutalog",appProperties.getProperty("propiedades.rutalog"));
////            }
//            if (appProperties.getProperty("propiedades.resturl") != null) {
//                this.set("resturl",appProperties.getProperty("propiedades.resturl"));
//            }
//        } catch (IOException io) {
//            System.out.println("Archivo de configuración no encontrado.");
//        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public Object get(String parameter){
        Object object = context.get(parameter);
        if(object == null && parameter.contains("Service"))
        {
            synchronized (AppContext.class) {
                object = context.get(parameter);
                if (object == null) {
                    try {
                        try {
                            object = Class.forName("unaplanilla2.service."+parameter).newInstance();
                            context.put(parameter, object);
                        } catch (InstantiationException | IllegalAccessException ex) {
                            java.util.logging.Logger.getLogger(AppContext.class.getName()).log(Level.SEVERE, "Creando Service [" + parameter + "].", ex);
                        }
                    } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(AppContext.class.getName()).log(Level.SEVERE, "Creando Service [" + parameter + "].", ex);
                    }
                }
            } 
        }        
        return object;
    }

    public void set(String nombre, Object valor) {
        context.put(nombre, valor);
    }

    public void delete(String parameter) {
        context.put(parameter, null);
    }

}
