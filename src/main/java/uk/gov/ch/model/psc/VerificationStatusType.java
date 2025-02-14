package uk.gov.ch.model.psc;

public enum VerificationStatusType {
    UNVERIFIED,
    VERIFIED,
    PENDING;

    int ordinal;     // ordinal will start from 1

    // cached instance used as optimization
    static final VerificationStatusType[] values = VerificationStatusType.values();

    static {
        // ordinal is initialized based on enum natural order
        int counter = 1;
        for (VerificationStatusType s: values)
            s.ordinal = counter++;
    }

    public static VerificationStatusType getTypeFromLong(final Long ordinal) {
        return ordinal != null ? values[ordinal.intValue() - 1] : null;
    }}
