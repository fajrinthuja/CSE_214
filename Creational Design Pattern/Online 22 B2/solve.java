interface Notification {
    void notifyUser();
}

class SMS implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("notified by sms");
    }
}

class Email implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("notified by email");
    }
}

class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Notifeid by email");
    }
}

class nothing implements Notification {
    @Override
    public void notifyUser() {
        // TODO Auto-generated method stub
        
    }
}

class NotificationFactory {
    private String str;
    NotificationFactory(String s) {
        str = s;
    }
    public Notification create() {
        if(str.equals("SMS")) return new SMS();
        if(str.equals("Email")) return new Email();
        if(str.equals("Push Notification")) return new PushNotification();
        return new nothing();
    }
}

public class solve {
    public static void main(String[] args) {
        NotificationFactory nf = new NotificationFactory("SMS");
        Notification n = nf.create();
        n.notifyUser();
    }
}
