/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.virtualmemory;

import ur_os.system.SystemOS;


/**
 *
 * @author user
 */
public class SwapMemory {
    byte[] bytes;
    int size;
    
    public SwapMemory(){
        this(SystemOS.SWAP_MEMORY_SIZE);
    }
    
    public SwapMemory(int size){
        this.size = size;
        bytes = new byte[size];
        
        for (int i = 0; i<size;i++) {
            bytes[i] = (byte)0;
        }
        
    }
    
    public byte get(int i){
        return bytes[i];
    }
    
    public void set(int i, byte b){
        bytes[i]= b;
    }
    
    public int getSize(){
        return size;
    }
    
    public void showNotNullBytes(){
        for (int i = 0; i<size;i++) {
            if(bytes[i] != 0){
                System.out.println(""+i+" - "+bytes[i]+"\n");
            }
        }
    }
    
}
