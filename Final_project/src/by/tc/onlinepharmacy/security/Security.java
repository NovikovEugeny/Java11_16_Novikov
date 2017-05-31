package by.tc.onlinepharmacy.security;

import by.tc.onlinepharmacy.filter.SecurityFilter;

/**
 * The utility class that is used in the {@link SecurityFilter SecurityFilter}
 * to verify the compliance of the user's position and the command that he wants to execute.
 */
public class Security {

    /**
     * Verifies whether the command execution is allowed to the admin.
     *
     * @param command it is string that contains a command name
     * @return true if execution is allowed and false if not
     */
    public static boolean isAllowedToAdmin(String command) {
        boolean isAllowed = false;

        for (AdminCommand a : AdminCommand.values()) {
            if (command.equals(a.toString().toLowerCase())) {
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

    /**
     * Verifies whether the command execution is allowed to the client.
     *
     * @param command it is string that contains a command name
     * @return true if execution is allowed and false if not
     */
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

    /**
     * Verifies whether the command execution is allowed to the doctor.
     *
     * @param command it is string that contains a command name
     * @return true if execution is allowed and false if not
     */
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

    /**
     * Verifies whether the command execution is allowed to the pharmacist.
     *
     * @param command it is string that contains a command name
     * @return true if execution is allowed and false if not
     */
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

    /**
     * Verifies whether the command execution is allowed to the quest.
     *
     * @param command it is string that contains a command name
     * @return true if execution is allowed and false if not
     */
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