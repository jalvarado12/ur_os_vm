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
public class PVMM_MFU extends ProcessVirtualMemoryManager{

    public PVMM_MFU(){
        type = ProcessVirtualMemoryManagerType.MFU;
    }
    
    @Override
    public int getVictim(LinkedList<Integer> memoryAccesses, int loaded) {
    LinkedList<Integer> temp = new LinkedList<Integer>(memoryAccesses); // copy of memory accesses
    LinkedList<Integer> pages = new LinkedList<Integer>(); // pages that are in frames
    Map<Integer, Integer> frequency = new HashMap<>(); // freq of the pages in memory accesses
    Integer victim = -1;
    temp.removeLast(); // In order to not count the page we have to add

    for (Integer m : temp) {
        // pages count part
        if (!pages.contains(m)) {
            pages.add(m);
        }
        if (pages.size() > loaded) {
            LinkedList<Integer> temp2 = new LinkedList<Integer>(pages);
            temp2.removeLast(); // In order to not count the element to add
            victim = maxVictim(frequency, temp2);
            pages.remove(victim); // removing old victims
        }

        // adding the freq
        frequency.put(m, frequency.getOrDefault(m, 0) + 1);
    }

    return maxVictim(frequency, pages);
}

public int maxVictim(Map<Integer, Integer> frequency, LinkedList<Integer> pages) {
    Integer max = Integer.MIN_VALUE;
    Integer victim = -1;

    // searching max
    for (Integer p : pages) {
        if (frequency.get(p) > max) {
            max = frequency.get(p);
        }
    }

    // in order to select the first in (in case of draw)
    for (Integer p : pages) {
        if (frequency.get(p) == max) {
            victim = p;
            break;
        }
    }

    return victim;
}
    
}
