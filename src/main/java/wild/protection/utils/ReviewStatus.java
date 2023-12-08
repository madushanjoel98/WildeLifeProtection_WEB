package wild.protection.utils;

public enum ReviewStatus {
	Pending(0),
    Accept(1),
    Reject(2);

    private final int value;

    ReviewStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
