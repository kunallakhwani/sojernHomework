package com.sojern.takehome.scripting;

public class CompareVersions {

    /**
     * Given two version numbers,
     * @param version1
     * @param version2
     * @return 1 if version1 > version2
     * @return -1 if version1 < version2
     * @return 0 otherwise
     * @throws Exception
     */
    public int compare(String version1, String version2) throws Exception {
        if (version1 == null || version1.isEmpty() || version2 == null || version2.isEmpty())
            throw new Exception("Input is empty");

        if (version1.equals(version2))
            return 0;
        String[] version1Arr = version1.split("\\.");
        String[] version2Arr = version2.split("\\.");
        int len = Math.max(version1Arr.length, version2Arr.length);

        try {
            for (int i = 0; i < len; i++) {
                int v1ver = 0, v2ver = 0;
                if (version1Arr.length > i) v1ver = Integer.parseInt(version1Arr[i]);
                if (version2Arr.length > i) v2ver = Integer.parseInt(version2Arr[i]);
                if (v1ver == v2ver)
                    continue;
                if (v1ver > v2ver)
                    return 1;
                if (v1ver < v2ver)
                    return -1;
            }
        } catch (NumberFormatException e) {
            throw new Exception("Enter valid number");
        }
        return 0;
    }
}
