/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ur_os;

import ur_os.system.SystemOS;
import ur_os.system.SimulationType;

/**
 *
 * @author super
 */
public class UR_OS {

    private static String VERSION = "0.0.5.3";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("************************************");
        System.out.println("         UR_OS V."+VERSION);
        System.out.println("************************************");
        
        
        
        SystemOS system = new SystemOS(SimulationType.MEMORY_MANAGEMENT);
        
        new Thread(system).start();
        
    }
    
}
