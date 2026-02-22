import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private final RawInputParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository db, RawInputParser parser, StudentValidator validator,
            OnboardingPrinter printer) {
        this.db = db;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        StudentRegistrationRequest req = parser.parse(raw);
        List<String> errors = validator.validate(req);

        if (!errors.isEmpty()) {
            printer.printValidationErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, req.name, req.email, req.phone, req.program);

        db.save(rec);

        printer.printSuccess(rec, db.count());
    }
}
