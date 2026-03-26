package Models;
import java.util.List;
import java.util.Map;

public class ModelTAS {

    public enum MAGMGROUP{
        MAGMGROUP_1("Ultrabasic"),
        MAGMGROUP_2("BasicLower"),
        MAGMGROUP_3("BasicMiddle"),
        MAGMGROUP_4("BasicUpper"),
        MAGMGROUP_5("IntermediateLower"),
        MAGMGROUP_6("IntermediateMiddle"),
        MAGMGROUP_7("IntermediateUpper"),
        MAGMGROUP_8("AcidLower"),
        MAGMGROUP_9("AcidMiddle"),
        MAGMGROUP_10("AcidUpper");

        private final String rockID;
        MAGMGROUP(String rockID){
            this.rockID=rockID;
        }
        public String getRockID() {
            return rockID;
        }

    }

    private static final Map<MAGMGROUP, List<String>> MAGMGROUP_VALUES = Map.ofEntries(
            Map.entry(MAGMGROUP.MAGMGROUP_1, List.of("3.2")),
            Map.entry(MAGMGROUP.MAGMGROUP_2, List.of("3.0")),
            Map.entry(MAGMGROUP.MAGMGROUP_3, List.of("3.0")),
            Map.entry(MAGMGROUP.MAGMGROUP_4, List.of("3.0")),
            Map.entry(MAGMGROUP.MAGMGROUP_5, List.of("2.8")),
            Map.entry(MAGMGROUP.MAGMGROUP_6, List.of("2.8")),
            Map.entry(MAGMGROUP.MAGMGROUP_7, List.of("2.8")),
            Map.entry(MAGMGROUP.MAGMGROUP_8, List.of("2.7")),
            Map.entry(MAGMGROUP.MAGMGROUP_9, List.of("2.7"))

    );
    public static List<String> getMagmGroupValues (MAGMGROUP magmgroup){
        return MAGMGROUP_VALUES.get(magmgroup);
    }
    public static MAGMGROUP[] getAllMagmgroups(){
        return MAGMGROUP.values();
    }
    public static String getValues(MAGMGROUP magmgroup, int index){
        List<String> values = MAGMGROUP_VALUES.get(magmgroup);
        if (values != null && index >= 0 && index < values.size()){
            return values.get(index);
        }
        return null;
    }
}
