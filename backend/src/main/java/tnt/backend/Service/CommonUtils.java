package tnt.backend.Service;

public class CommonUtils {
    public static final String generateCode(String fullName) {
        String pathName[] = fullName.trim().split("\\s+");
        String firtLetter = pathName[0].substring(0, 1).toUpperCase();
        String lastLetter = pathName[pathName.length - 1].substring(0, 1).toUpperCase();
        String initials = firtLetter + lastLetter;
        int numberRandom = (int) (Math.random() * 10000);
        String code = initials + numberRandom;
        return code;
    }
}
