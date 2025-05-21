/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.virtualmemory;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juans.alvarado
 */

public class PVMM_MBR extends ProcessVirtualMemoryManager {
    private static final double DECAY = 0.9; //Exponential decay

    public PVMM_MBR() {
        type = ProcessVirtualMemoryManagerType.MBR;
    }

    @Override
    public int getVictim(LinkedList<Integer> memoryAccesses, int loaded) {
        LinkedList<Integer> temp = new LinkedList<>(memoryAccesses);
        temp.removeLast(); // Ignore the one to add

        LinkedList<Integer> pages = new LinkedList<>();
        Map<Integer, Integer> frequency = new HashMap<>();
        Map<Integer, Integer> lastUsed = new HashMap<>();
        int time = 0;

        for (Integer m : temp) {
            time++;

            
            if (!pages.contains(m)) {
                pages.add(m);
            }

            
            if (pages.size() > loaded) {
                Integer victim = getMinMomentum(frequency, lastUsed, pages, time);
                pages.remove(victim);
                frequency.remove(victim);
                lastUsed.remove(victim);
            }

            // Actualize frequency and last access
            frequency.put(m, frequency.getOrDefault(m, 0) + 1);
            lastUsed.put(m, time);
        }

        return getMinMomentum(frequency, lastUsed, pages, time);
    }

    private int getMinMomentum(Map<Integer, Integer> frequency, Map<Integer, Integer> lastUsed, LinkedList<Integer> pages, int currentTime) {
        double minMomentum = Double.MAX_VALUE;
        int victim = -1;

        for (Integer page : pages) {
            int freq = frequency.getOrDefault(page, 0);
            int age = currentTime - lastUsed.getOrDefault(page, 0);
            double momentum = freq * Math.pow(DECAY, age);

            if (momentum < minMomentum) {
                minMomentum = momentum;
                victim = page;
            }
        }

        return victim;
    }
    
    
}
