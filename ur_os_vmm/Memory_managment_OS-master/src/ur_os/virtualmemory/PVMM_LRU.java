/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.virtualmemory;

import java.util.LinkedList;

/**
 *
 * @author user
 */
public class PVMM_LRU extends ProcessVirtualMemoryManager{

    public PVMM_LRU(){
        type = ProcessVirtualMemoryManagerType.LRU;
    }
    
    @Override
    public int getVictim(LinkedList<Integer> memoryAccesses, int loaded) {
    LinkedList<Integer> temp = new LinkedList<Integer>(memoryAccesses); //copy of memoryAccesses
    LinkedList<Integer> recent = new LinkedList<Integer>(); //carries the order of recents
    LinkedList<Integer> pages = new LinkedList<Integer>(); //pages that are in frames
    
    temp.removeLast(); //In order to not count the page we have to add

    for(Integer m: temp) {
        //pages count part
        if(!pages.contains(m)) {
            pages.add(m);
        }
        if(pages.size() > loaded) {
            pages.remove(recent.getFirst());
            recent.removeFirst();
        }
        
        //recent part
        if(!recent.contains(m)) {
            recent.add(m);
        } else {
            recent.removeFirstOccurrence(m);
            recent.add(m);
        }
    }
    return recent.getFirst();
}
    
}
