public interface EligibilityRule {
    String evaluateFailureReason(StudentProfile s);
}

class DisciplinaryRule implements EligibilityRule {
    @Override
    public String evaluateFailureReason(StudentProfile s) {
        if (s.disciplinaryFlag != LegacyFlags.NONE)
            return "disciplinary flag present";
        return null;
    }
}

class CgrRule implements EligibilityRule {
    private final double minCgr;

    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public String evaluateFailureReason(StudentProfile s) {
        if (s.cgr < minCgr)
            return "CGR below " + minCgr;
        return null;
    }
}

class AttendanceRule implements EligibilityRule {
    private final int minAttendancePct;

    public AttendanceRule(int minAttendancePct) {
        this.minAttendancePct = minAttendancePct;
    }

    @Override
    public String evaluateFailureReason(StudentProfile s) {
        if (s.attendancePct < minAttendancePct)
            return "attendance below " + minAttendancePct;
        return null;
    }
}

class CreditsRule implements EligibilityRule {
    private final int minCredits;

    public CreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public String evaluateFailureReason(StudentProfile s) {
        if (s.earnedCredits < minCredits)
            return "credits below " + minCredits;
        return null;
    }
}
