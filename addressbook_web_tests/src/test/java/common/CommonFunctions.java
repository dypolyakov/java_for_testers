package common;

import model.ContactData;
import model.GroupData;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n) {
        Random random = new Random();
        Supplier<Integer> randomNumber = () -> random.nextInt(26);
        return Stream.generate(randomNumber)
                .limit(n)
                .map(number -> 'a' + number)
                .map(Character::toString)
                .collect(Collectors.joining());
    }

    public static String randomFile(String dir) {
        String[] fileNames = new File(dir).list();
        int index = new Random().nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }

    public static ContactData randomContact() {
        Random random = new Random();
        return new ContactData()
                .withFirstName(randomString(random.nextInt(25)))
                .withLastName(randomString(random.nextInt(25)))
                .withAddress(randomString(random.nextInt(25)))
                .withEmail(randomString(random.nextInt(25)))
                .withPhone(randomString(random.nextInt(25)));
    }

    public static GroupData randomGroup() {
        Random random = new Random();
        return new GroupData()
                .withName(randomString(random.nextInt(25)))
                .withHeader(randomString(random.nextInt(25)))
                .withFooter(randomString(random.nextInt(25)));
    }
}
