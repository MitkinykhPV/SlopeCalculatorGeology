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
            Map.entry(GROUP.GROUP_1, List.of("1.85", "2.7")),
            Map.entry(GROUP.GROUP_2, List.of("1.8", "2.7")),
            Map.entry(GROUP.GROUP_3, List.of("1.7", "2.65")),
            Map.entry(GROUP.GROUP_4, List.of("1.6", "2.6")),
            Map.entry(GROUP.GROUP_5, List.of("1.6", "2.6")),
            Map.entry(GROUP.GROUP_6, List.of("1.5", "2.7")),
            Map.entry(GROUP.GROUP_7, List.of("1.4", "2.8")),
            Map.entry(GROUP.GROUP_8, List.of("1.3", "2.9")),
            Map.entry(GROUP.GROUP_9, List.of("1.2", "1.8")),
            Map.entry(GROUP.GROUP_10, List.of("1.2", "2.8")),
            Map.entry(GROUP.GROUP_11, List.of("1.3", "1.5")),
            Map.entry(GROUP.GROUP_12, List.of("1.05", "1.3")),
            Map.entry(GROUP.GROUP_13, List.of("1.3", "1.4")),
            Map.entry(GROUP.GROUP_14, List.of("1.1", "1.4")),
            Map.entry(GROUP.GROUP_15, List.of("1.2", "1.7"))
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
