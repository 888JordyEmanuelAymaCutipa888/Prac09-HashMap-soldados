package fp.lab9;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author jordy
 */
public class VideoJuego {

    
    public static void main(String[] args) {
        
        //HashMap para el tablero
        HashMap <Integer, HashMap <Integer,Soldado> > ejercitos = new HashMap<>();
        
        //Ejércitos
        HashMap <Integer, Soldado> ejercito1 = new HashMap<>();
        HashMap <Integer, Soldado> ejercito2 = new HashMap<>();
        
        //Soldado activo: estado= true
        for (int i = 0; i < 10; i++) {
            ejercitos.put(i ,new HashMap<>());
            for (int j = 0; j < 10; j++) {
                ejercitos.get(i).put(j, new Soldado());
                ejercitos.get(i).get(j).setEstado(false);
                ejercitos.get(i).get(j).setNombre("---------------");
            }
        }
        //
        CrearTableroyEjercito(ejercitos, ejercito1, "E1");
        CrearTableroyEjercito(ejercitos, ejercito2, "E2");
        Imprimir(ejercitos);
        
        System.out.println("\n\n********** EJERCITO 2 **********\n\n");
        soldadoConMayorVida(ejercito2);
        ImprimirPorCreación(ejercito2);
        similarSelección(ejercito2);
        System.out.println("PROMEDIO DE VIDA: "+promedioVida(ejercito2));
        
        System.out.println("\n\n********** EJERCITO 1 **********\n\n");
        soldadoConMayorVida(ejercito1);
        ImprimirPorCreación(ejercito1);
        similarSelección(ejercito1);
        System.out.println("PROMEDIO DE VIDA: "+promedioVida(ejercito1));
     
        
        
        System.out.println("");
        if (promedioVida(ejercito2)>promedioVida(ejercito1)) {
            
            System.out.println("EL EQUIPO 2 GANA LA BATALLA");
            
        }
        else if(promedioVida(ejercito2)==promedioVida(ejercito1)){
            System.out.println("NINGUN EQUIPO GANA");
        }
        else{
            System.out.println("EL EQUIPO 1 GANA LA BATALLA");
        }
    }
    
    //Crea y retorna un nuevo ejercito y lo ubica en el tablero
    //Recibe el tablero, un ejercito y el nombre del ejercito
    
    public static void CrearTableroyEjercito
        (HashMap <Integer, HashMap <Integer,Soldado>> ejercitos,HashMap <Integer, Soldado> ejercito, String nomEjercito){
            
        int fil,col,vida;
        String nombre;
        
        // Cantidad de ejercito
        
        int cantidad= (int) (Math.random()*10+10);
        
        
        //Crea el ejercito
        
        for (int i = 0; i < cantidad; i++) {
            
            //Permite que no se repitan posiciones
            do{
                fil = (int) (Math.random()*9+0);
                col = (int) (Math.random()*9+0);
            }
            //
            while(ejercitos.get(fil).get(col).getEstado());
            //
            
            vida = (int) (Math.random()*5+1);
            nombre = nomEjercito+"-soldado"+(i+1);
            Soldado sol = new Soldado();
            
            sol.setNombre(nombre);
            sol.setColumna(col);
            sol.setFila(fil);
            sol.setVida(vida);
            sol.setEstado(true);
            //Agrega un soldado al nuevo ejercito, además lo coloca en el tablero
            ejercito.put(i, sol);
            ejercitos.get(fil).put(col, sol);
            //
        }
    }
    
    public static void Imprimir(HashMap <Integer, HashMap <Integer,Soldado>> ejercitos){
        
        
        for (Map.Entry<Integer, HashMap <Integer,Soldado>> filas : ejercitos.entrySet()) {
                  
            for (Map.Entry<Integer,Soldado> columnas: filas.getValue().entrySet()) {
                //para soldados inactivos
                if(columnas.getValue().getVida() ==0){
                    System.out.print(columnas.getValue().getNombre()+"         ");
                }
                //para soldados activos
                else{
                    System.out.print(columnas.getValue().getNombre()+"("+columnas.getValue().getVida()+")"+"          ");
                }
            }
            System.out.println("\n\n");
        }
        
    }
    
    public static void soldadoConMayorVida(HashMap <Integer, Soldado> ejercito){
        
        int mayorvida=0, identificador=0;
        
        for (Map.Entry <Integer, Soldado> ejer : ejercito.entrySet()) {
            if(mayorvida < ejer.getValue().getVida()){
                mayorvida = ejer.getValue().getVida();
                identificador = ejer.getKey();
            }
        }
        
        System.out.println("El soldado con mayor vida \n"+ejercito.get(identificador));
        
        
    }
    public static void ImprimirPorCreación(HashMap <Integer, Soldado> ejercito){
        
        System.out.println("\nEJÉRCITO POR ORDEN DE CREACIÓN");
        
        for (Map.Entry<Integer, Soldado> ejer : ejercito.entrySet()) {
            
            System.out.println(ejer.getValue());
            
        }
        System.out.println("\n");
    }
    
    public static int promedioVida(HashMap <Integer, Soldado> ejercito){
        
        int sumatotal=0, prom;
        
        for (Map.Entry<Integer, Soldado> e : ejercito.entrySet()) {
            
            sumatotal += e.getValue().getVida();
        }
        prom=(sumatotal/ejercito.size());
        return prom;
    }
    
    public static void similarSelección(HashMap <Integer, Soldado> ejercito){
        
        HashMap <Integer, Soldado> rankingfalso = (HashMap <Integer, Soldado>) ejercito.clone();
        HashMap <Integer, Soldado> ranking = new HashMap<>();
        
        int identificador=0, a;
        a= rankingfalso.size();
        
        System.out.println("\n***ESTE ES EL RANKING(similar Seleccion)***\n");
        
        for (int i = 0; i < a; i++) {
            Soldado aux = new Soldado();
            for (Map.Entry <Integer, Soldado> obtenermayor : rankingfalso.entrySet()) {
                
                if(aux.getVida()<= obtenermayor.getValue().getVida() 
                  && !ranking.containsKey(obtenermayor.getKey())){
                    
                    aux = obtenermayor.getValue();
                    identificador = obtenermayor.getKey();
                    
                }
            }
            ranking.putIfAbsent(identificador, aux);
            System.out.println(rankingfalso.get(identificador));
            rankingfalso.remove(identificador);
        }
        System.out.println("finish");
        
        
        //AL PARECER EL RAKING VERDADERO SE DESORDENA POR ELLO LO IMPRIMO DIRECTAMENTE
        //MIENTRAS SE REALIZA EL TRASPASO DE ELEMENTOS
        
        
        //ESTO IMPRIMIRÍA EL RANKING PERO SE DESORDENA
        /*
        ranking.entrySet().forEach(ranz -> {
            System.out.println(ranz.getValue()+"\n");
        });
        */
    }
    //NO HE PODIDO ENCONTRAR LA PORMA DE OBTENER UNA POSICIÓN EN UN--
    //HASHMAP PARA ELABORAR LA BUSQUEDA BINARIA
    
}
