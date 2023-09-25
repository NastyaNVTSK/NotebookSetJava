import java.util.*;

public class NotebookSet {
    private Set<Notebook> notebooks;

    public NotebookSet() {
        notebooks = new HashSet<>();
    }

    public void addNotebook(Notebook notebook) {
        notebooks.add(notebook);
    }

    public void filterNotebooks(Map<String, String> filterCriteria) {
        List<Notebook> filteredNotebooks = new ArrayList<>();

        for (Notebook notebook : notebooks) {
            boolean meetsCriteria = true;

            for (Map.Entry<String, String> entry : filterCriteria.entrySet()) {
                String criterion = entry.getKey();
                String value = entry.getValue();

                if (!notebook.meetsCriterion(criterion, value)) {
                    meetsCriteria = false;
                    break;
                }
            }

            if (meetsCriteria) {
                filteredNotebooks.add(notebook);
            }
        }

        if (filteredNotebooks.isEmpty()) {
            System.out.println("Нет ноутбуков, удовлетворяющих указанному фильтру.");
        } else {
            System.out.println("Найдены следующие ноутбуки, удовлетворяющие фильтру:");
            for (Notebook notebook : filteredNotebooks) {
                System.out.println(notebook);
            }
        }
    }

    private static class Notebook {
        private int ram;
        private int storage;
        private String os;
        private String color;

        public Notebook(int ram, int storage, String os, String color) {
            this.ram = ram;
            this.storage = storage;
            this.os = os;
            this.color = color;
        }

        public boolean meetsCriterion(String criterion, String value) {
            switch (criterion) {
                case "1":
                    
                    return Integer.parseInt(value) <= ram;
                case "2":
                    
                    return Integer.parseInt(value) <= storage;
                case "3":
                   
                    return value.equalsIgnoreCase(os);
                case "4":
                   
                    return value.equalsIgnoreCase(color);
                default:
                    return false;
            }
        }

        @Override
        public String toString() {
            return "Notebook{" +
                    "ram=" + ram +
                    ", storage=" + storage +
                    ", os='" + os + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        NotebookSet notebookSet = new NotebookSet();

        notebookSet.addNotebook(new Notebook(8, 256, "Windows", "Серый"));
        notebookSet.addNotebook(new Notebook(16, 512, "Mac OS", "Серебристый"));
        notebookSet.addNotebook(new Notebook(8, 256, "Windows", "Белый"));
        notebookSet.addNotebook(new Notebook(16, 256, "Windows", "Синий"));
        notebookSet.addNotebook(new Notebook(8, 512, "Linux", "Красный"));
        notebookSet.addNotebook(new Notebook(8, 512, "Windows", "Золотой"));
        notebookSet.addNotebook(new Notebook(16, 256, "Linux", "Черный"));
        notebookSet.addNotebook(new Notebook(8, 512, "Windows", "Серебристый"));
        notebookSet.addNotebook(new Notebook(16, 256, "Mac OS", "Черный"));
        notebookSet.addNotebook(new Notebook(8, 512, "Mac OS", "Черный"));

        try (Scanner scanner = new Scanner(System.in)) {
            Map<String, String> filterCriteria = new HashMap<>();
            boolean continueFiltering = true;

            while (continueFiltering) {
                System.out.println("Введите цифру, соответствующую необходимому критерию:");
                System.out.println("1. Фильтровать по ОЗУ");
                System.out.println("2. Фильтровать по объему ЖД");
                System.out.println("3. Фильтровать по операционной системе");
                System.out.println("4. Фильтровать по цвету");
                String criterion = scanner.nextLine();

                System.out.println("Введите минимальное значение для фильтра:");
                String value = scanner.nextLine();

                filterCriteria.put(criterion, value);

                System.out.println("Хотите добавить еще один критерий фильтрации? (да/нет)");
                String continueFilteringInput = scanner.nextLine();

                if (!continueFilteringInput.equalsIgnoreCase("да")) {
                    continueFiltering = false;
                }
            }

            notebookSet.filterNotebooks(filterCriteria);
        }
    }
}
