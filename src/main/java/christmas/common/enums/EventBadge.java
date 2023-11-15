package christmas.common.enums;

public enum EventBadge {
    SANTA(20_000, "산타"),
    TREE(10_000, "트리"),
    STAR(5_000, "별"),
    NONE(0, "없음");

    private final int threshold;
    private final String badge;

    EventBadge(int threshold, String badge) {
        this.threshold = threshold;
        this.badge = badge;
    }

    public static String determineEventBadge(int totalDiscount) {
        for (EventBadge badge : EventBadge.values()) {
            if (totalDiscount >= badge.getThreshold()) {
                return badge.getBadge();
            }
        }
        return NONE.getBadge();
    }

    public int getThreshold() {
        return threshold;
    }

    public String getBadge() {
        return badge;
    }
}
