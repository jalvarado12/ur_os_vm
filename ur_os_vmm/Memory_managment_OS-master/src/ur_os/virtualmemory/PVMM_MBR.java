package ur_os.virtualmemory;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class PVMM_WFV extends ProcessVirtualMemoryManager {
    private int waveCount = 0; // Para alternar la dirección de búsqueda

    public PVMM_WFV() {
        type = ProcessVirtualMemoryManagerType.WFV; // Necesitas agregar esta enumeración
    }

    @Override
    public int getVictim(LinkedList<Integer> memoryAccesses, int loaded) {
        LinkedList<Integer> temp = new LinkedList<>(memoryAccesses);
        temp.removeLast(); // Ignorar el que se va a agregar

        LinkedList<Integer> pages = new LinkedList<>();
        Map<Integer, Integer> lastUsed = new HashMap<>();
        int time = 0;

        for (Integer m : temp) {
            time++;

            if (!pages.contains(m)) {
                pages.add(m);
            }

            if (pages.size() > loaded) {
                Integer victim = getWaveVictim(pages, lastUsed, time);
                pages.remove(victim);
                lastUsed.remove(victim);
            }

            lastUsed.put(m, time);
        }

        waveCount++; // Alternar dirección para la próxima vez
        return getWaveVictim(pages, lastUsed, time);
    }

    private int getWaveVictim(LinkedList<Integer> pages, Map<Integer, Integer> lastUsed, int currentTime) {
        int n = pages.size();
        int center = n / 2;
        int direction = waveCount % 2 == 0 ? -1 : 1;

        int victim = -1;
        int maxAge = -1;

        for (int offset = 0; offset < n; offset++) {
            int index = center + direction * offset;

            // Alterna izquierda y derecha desde el centro
            if (direction == -1) index = center - offset;
            else index = center + offset;

            if (index < 0 || index >= n) continue;

            Integer page = pages.get(index);
            int age = currentTime - lastUsed.getOrDefault(page, 0);

            if (age > maxAge) {
                maxAge = age;
                victim = page;
            }
        }

        return victim;
    }
}
