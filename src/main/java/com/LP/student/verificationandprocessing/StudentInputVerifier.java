package com.LP.student.verificationandprocessing;

public class StudentInputVerifier {
    static final String FIRST_NAME_FORMAT = "[a-zA-z]+((\\x27(?![\\x2d\\x27])|\\x2d(?![\\x2d\\x27])).?[a-zA-z]+(\\x27(?![\\x2d\\x27])|\\x2d(?![\\x2d\\x27]))?|(\\x27(?![\\x2d\\x27])|\\x2d(?![\\x2d\\x27])).?[a-zA-Z]+|[a-zA-z]+)+";
    static final String LAST_NAME_FORMAT = "(" + FIRST_NAME_FORMAT + "\\s?)+";
    static final String EMAIL_FORMAT = "([a-zA-Z\\d]+|([a-zA-Z\\d]+\\.?[a-zA-Z\\d]+))@[a-z\\d]+\\.[a-z\\d]+";
    static final String POINT_FORMAT = "\\d+";

    public boolean[] nameVerifier(String[] name) {
        boolean firstNameCheck = name[0].matches(FIRST_NAME_FORMAT);
        boolean lastNameCheck = name[1].matches(LAST_NAME_FORMAT);
        boolean emailCheck = name[2].matches(EMAIL_FORMAT);

        return new boolean[]{firstNameCheck, lastNameCheck, emailCheck};
    }

    public boolean pointsVerifier(String[] points) {
        for (int i = 1; i < points.length; i++) {
            if (!points[i].matches(POINT_FORMAT)) {
                return false;
            }
        }
        return true;
    }
}
