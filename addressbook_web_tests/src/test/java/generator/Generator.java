package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.CommonFunctions;
import model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;


    public static void main(String[] args) throws IOException {
        Generator generator = new Generator();

        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);

        generator.run();
    }

    private void run() throws IOException {
        Object data = generate();
        save(data);
    }

    private Object generate() {
        if (type.equals("groups")) {
            return generateGroups();
        } else if (type.equals("contacts")) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateContacts() {
        return null;
    }

    private Object generateGroups() {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10))
            );
        }
        return groups;
    }

    private void save(Object data) throws IOException {
        if (format.equals("json")) {
            // Создание маппера для сериализации
            ObjectMapper mapper = new ObjectMapper();

            // Включение режима pretty print
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            // Создание json файла
            // output - название файла заданное в аргументах,
            // data - класс или список классов, которые будут сериализованы (преобразованы в json формат)
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}