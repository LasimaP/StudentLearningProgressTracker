package com.LP.student.verificationandprocessing;

public class StudentInputProcessor {

    public String[] nameSplitter(String name) {
        String[] stringArray = name.split(" ");
        if (stringArray.length < 3) {
            return new String[] {" "};
        }

        String firstname;
        StringBuilder lastname = new StringBuilder();
        String email;
        firstname = stringArray[0];
        for (int i = 1; i < stringArray.length - 1; i++) {
            if (i != stringArray.length - 2) {
                lastname.append(stringArray[i]).append(" ");
            } else {
                lastname.append(stringArray[i]);
            }
        }

        email = stringArray[stringArray.length - 1];

        return new String[]{firstname, lastname.toString(), email};
    }

    public int[] pointSplitter(String[] points) {
        int[] pointsArray = new int[4];
        for (int i = 1; i < points.length; i++) {
            pointsArray[i - 1] = Integer.parseInt(points[i]);
        }
        return pointsArray;
    }
}
