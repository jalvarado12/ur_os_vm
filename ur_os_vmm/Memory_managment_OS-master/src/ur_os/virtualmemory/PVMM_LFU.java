/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.virtualmemory;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class PVMM_LFU extends ProcessVirtualMemoryManager{

    public PVMM_LFU(){
        type = ProcessVirtualMemoryManagerType.LFU;
    }
    
    @Override
    public int getVictim(LinkedList<Integer> memoryAccesses, int loaded) {
    LinkedList<Integer> temp = new LinkedList<Integer>(memoryAccesses); // copy of memory accesses
    LinkedList<Integer> pages = new LinkedList<Integer>(); // pages that are in frames
    Map<Integer, Integer> frequency = new HashMap<>(); // freq of the pages in memory accesses
    Integer victim = -1;
    temp.removeLast(); // In order to not count the page we have to add

    for (Integer m : temp) {
        if (!pages.contains(m)) {
            pages.add(m);
        }
        if (pages.size() > loaded) {
            LinkedList<Integer> temp2 = new LinkedList<Integer>(pages);
            temp2.removeLast(); //not count the element to add
            victim = minVictim(frequency, temp2);
            pages.remove(victim);
        }

        if (frequency.containsKey(m)) {
            frequency.put(m, frequency.get(m) + 1);
        } else {
            frequency.put(m, 1);
}
    }

    return minVictim(frequency, pages);
}

public int minVictim(Map<Integer, Integer> frequency, LinkedList<Integer> pages) {
    Integer min = Integer.MAX_VALUE;
    Integer victim = -1;

    //  min
    for (Integer p : pages) {
        if (frequency.get(p) < min) {
            min = frequency.get(p);
        }
    }

    // draw
    for (Integer p : pages) {
        if (frequency.get(p) == min) {
            victim = p;
            break;
        }
    }
    return victim;
}
    
}
