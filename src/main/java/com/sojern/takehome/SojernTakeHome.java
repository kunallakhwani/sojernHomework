package com.sojern.takehome;

import com.sojern.takehome.scripting.CompareVersions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SojernTakeHome {
    public static void main(String[] args) {
        SpringApplication.run(SojernTakeHome.class, args);

        // Compare two version numbers
        CompareVersions cv = new CompareVersions();
        Scanner sc = new Scanner(System.in);
        System.out.println("*****Enter 'e' to exit at any time*****");
        while(true) {
            System.out.println("**Compare versions**");
            System.out.print("Enter version 1 (or exit): ");
            String version1 = sc.nextLine();
            if (version1.equals("e")) {
                System.out.println("Exiting version comparison...");
                break;
            }
            System.out.print("Enter version 2 (or exit): ");
            String version2 = sc.nextLine();
            if (version2.equals("e")) {
                System.out.println("Exiting version comparison...");
                break;
            }
            int compResult = 0;
            try {
                compResult = cv.compare(version1, version2);
                String sign = "=";
                if (compResult == 1)
                    sign = ">";
                else if (compResult == -1)
                    sign = "<";
                System.out.println(version1 + " " + sign + " " + version2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
