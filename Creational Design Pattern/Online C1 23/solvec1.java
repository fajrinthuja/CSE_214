class AuditLogger {
    static AuditLogger auditLogger = null;
    private AuditLogger() {

    }
    public static AuditLogger getInstance() {
        if(auditLogger == null) auditLogger = new AuditLogger();
        return auditLogger;
    }
}

public class solvec1{
    public static void main(String[] args) {
        AuditLogger studLogger = AuditLogger.getInstance();
        AuditLogger teachLogger = AuditLogger.getInstance();
        System.out.println(studLogger == teachLogger);
    }
}