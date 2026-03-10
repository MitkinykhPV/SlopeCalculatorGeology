package Models;
import java.util.List;
import java.util.Map;
//
public class ModelNomogramma {
//
    public enum GROUP {
        GROUP_1("Класс 1"),
        GROUP_2("Класс 2"),
        GROUP_3("Класс 3"),
        GROUP_4("Класс 4"),
        GROUP_5("Класс 5"),
        GROUP_6("Класс 6"),
        GROUP_7("Класс 7"),
        GROUP_8("Класс 8"),
        GROUP_9("Класс 9"),
        GROUP_10("Класс 10"),
        GROUP_11("Класс 11"),
        GROUP_12("Класс 12"),
        GROUP_13("Класс 13"),
        GROUP_14("Класс 14"),
        GROUP_15("Класс 15");

        private final String displayName;

        GROUP(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

    }
//
    // Статические карты для хранения значений каждой группы
    private static final Map<GROUP, List<String>> GROUP_VALUES = Map.ofEntries(
            Map.entry(GROUP.GROUP_1, List.of("Значение 1.1", "Значение 1.2")),
            Map.entry(GROUP.GROUP_2, List.of("Значение 1.2", "Значение 1.3")),
            Map.entry(GROUP.GROUP_3, List.of("Значение 1.3", "Значение 1.1")),
            Map.entry(GROUP.GROUP_4, List.of("Значение 1.1", "Значение 1.6")),
            Map.entry(GROUP.GROUP_5, List.of("Значение 1.4", "Значение 1.1")),
            Map.entry(GROUP.GROUP_6, List.of("Значение 1.3", "Значение 1.2")),
            Map.entry(GROUP.GROUP_7, List.of("Значение 1.2", "Значение 1.2")),
            Map.entry(GROUP.GROUP_8, List.of("Значение 1.4", "Значение 1.2")),
            Map.entry(GROUP.GROUP_9, List.of("Значение 1.7", "Значение 1.2")),
            Map.entry(GROUP.GROUP_10, List.of("Значение 1.2", "Значение 1.2")),
            Map.entry(GROUP.GROUP_11, List.of("Значение 1.5", "Значение 1.2")),
            Map.entry(GROUP.GROUP_12, List.of("Значение 1.7", "Значение 1.2")),
            Map.entry(GROUP.GROUP_13, List.of("Значение 1.2", "Значение 1.2")),
            Map.entry(GROUP.GROUP_14, List.of("Значение 1.3", "Значение 1.2")),
            Map.entry(GROUP.GROUP_15, List.of("Значение 1.2", "Значение 1.2"))
    );

    // Метод для получения значений группы
    public static List<String> getGroupValues(GROUP group) {
        return GROUP_VALUES.get(group);
    }

    // Метод для получения всех групп
    public static GROUP[] getAllGroups() {
        return GROUP.values();
    }

    // Метод для получения значения по группе и индексу
    public static String getValue(GROUP group, int index) {
        List<String> values = GROUP_VALUES.get(group);
        if (values != null && index >= 0 && index < values.size()) {
            return values.get(index);
        }
        return null;
    }
}
