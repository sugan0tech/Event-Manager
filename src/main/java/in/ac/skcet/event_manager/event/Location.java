package in.ac.skcet.event_manager.event;

public enum Location {
    EXTERNAL,
    REMOTE,
    KRISHNA_HALL,
    BS01,
    BS02,
    BS03,
    BS04,
    SEMINAR_HALL,
    CONFERENCE_HALL,
    JB_LAB,
    AK_LAB;

    // Default value
    public static final Location DEFAULT = EXTERNAL;

    public static Location fromString(String text) {
        for (Location location : Location.values()) {
            if (location.name().equalsIgnoreCase(text)) {
                return location;
            }
        }
        return null;
    }
}
