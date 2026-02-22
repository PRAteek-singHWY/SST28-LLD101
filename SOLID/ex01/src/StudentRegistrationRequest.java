public class StudentRegistrationRequest {
    public final String name;
    public final String email;
    public final String phone;
    public final String program;

    public StudentRegistrationRequest(String name, String email, String phone, String program) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.program = program;
    }
}
