package by.tc.online_pharmacy.security;


public class Security {

    public static boolean isAllowedToClient(String command) {
        boolean isAllowed = false;

        for (ClientCommand c : ClientCommand.values()) {
            if (command.equals(c.toString().toLowerCase())) {
                isAllowed = true;
                break;
            }
        }

        for (CommonUserCommand c : CommonUserCommand.values()) {
            if (command.equals(c.toString().toLowerCase())) {
                isAllowed = true;
                break;
            }
        }

        return isAllowed;
    }

    public static boolean isAllowedToDoctor(String command) {
        boolean isAllowed = false;

        for (DoctorCommand d : DoctorCommand.values()) {
            if (command.equals(d.toString().toLowerCase())) {
                isAllowed = true;
                break;
            }
        }

        for (CommonUserCommand c : CommonUserCommand.values()) {
            if (command.equals(c.toString().toLowerCase())) {
                isAllowed = true;
                break;
            }
        }

        return isAllowed;
    }

    public static boolean isAllowedToPharmacist(String command) {
        boolean isAllowed = false;

        for (PharmacistCommand p : PharmacistCommand.values()) {
            if (command.equals(p.toString().toLowerCase())) {
                isAllowed = true;
                break;
            }
        }

        for (CommonUserCommand c : CommonUserCommand.values()) {
            if (command.equals(c.toString().toLowerCase())) {
                isAllowed = true;
                break;
            }
        }

        return isAllowed;
    }

    public static boolean isAllowedToGuest(String command) {
        boolean isAllowed = false;

        for (GuestCommand c : GuestCommand.values()) {
            if (command.equals(c.toString().toLowerCase())) {
                isAllowed = true;
                break;
            }
        }

        return isAllowed;
    }
}