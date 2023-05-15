package edu.umb.CS681.hw10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FileTest {
    public static void main(String[] args) {
        FileSystem fs;
        Directory root, apps, bin, home, pictures;
        File x, y, a, b, c;
        Link d, e;
        fs = FileSystem.getFileSystem();

        root = new Directory(null, "root", 0, LocalDateTime.now());
        fs.appendRootDirectory(root);   
        apps = new Directory(root, "apps", 0, LocalDateTime.now());
        x = new File(apps, "x", 10, LocalDateTime.now());

        bin = new Directory(root, "bin", 0, LocalDateTime.now());
        y = new File(bin, "y", 20, LocalDateTime.now());

        home = new Directory(root, "home", 0, LocalDateTime.now());
        c = new File(home, "c", 50, LocalDateTime.now());

        pictures = new Directory(home, "pictures", 0, LocalDateTime.now());

        a = new File(pictures, "a", 10, LocalDateTime.now());
        b = new File(pictures, "b", 20, LocalDateTime.now());

        d = new Link(root, "d", 0, LocalDateTime.now(), pictures);
        e = new Link(root, "e", 0, LocalDateTime.now(), x);

        Thread t1 = new Thread(() -> {
            List<Directory> directoryList = root.getSubDirectories();
            Boolean isFile = x.isFile();
            System.out.println(" Thread1- Is x a file: " + isFile);
        });

        Thread t2 = new Thread(() -> {
            List<Directory> directoryList1 = root.getSubDirectories();
            System.out.println(" Thread2: List of subdirectories of root " + directoryList1);

        });

        Thread t3 = new Thread(() -> {
            Boolean isLink = home.isLink();
            System.out.println(" Thread3- Is home a link: " + isLink);
        });

        Thread t4 = new Thread(() -> {
            LocalDateTime ctA = a.getCreationTime();
            System.out.println(" Thread4- Creation time of a " + ctA);

        });
    
        Thread t5 = new Thread(() -> {
            LocalDateTime ctB = b.getCreationTime();
            System.out.println(" Thread5- Creation time of b " + ctB);

        });

        Thread t6 = new Thread(() -> {
            FSElement targetD = d.getTarget();
            System.out.println(" Thread6- Get Target of D " + targetD);

        });

        Thread t7 = new Thread(() -> {
            FSElement targetE = e.getTarget();
            System.out.println(" Thread7- Get Target of E " + targetE);
            Directory pictureParent = pictures.getParent();
            System.out.println(" Thread7- Get Parent of Pictures " + pictureParent);

        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
    }
}
