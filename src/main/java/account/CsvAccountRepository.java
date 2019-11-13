package account;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvAccountRepository implements AccountRepository {
    private String filePath = "./transactions.csv";

    @Override
    public Result create(Transaction transaction) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(transaction.id + ","
                                + transaction.deposit + ", "
                                + transaction.withdrawal + ", "
                                + transaction.balance
                                + System.getProperty("line.separator"));
            fileWriter.close();
            return Result.Ok();
        } catch (IOException e) {
            return Result.Fail("Error writing file: " + e.getMessage());
        }
    }

    @Override
    public ResultOf<Transaction> read(Predicate<Transaction> p) {
            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                Transaction transaction =
                        stream.map(line -> {
                            String[] items = line.split(",");
                            return new Transaction(Integer.parseInt(items[0]), Double.parseDouble(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[3]));})
                        .filter(p)
                        .findFirst()
                        .get();
                return Result.Ok(Optional.of(transaction));
            } catch (IOException e) {
                return Result.Fail(null, "Error reading file: " + e.getMessage());
            }
    }

    @Override
    public Result update(Transaction transaction) {
        return Result.Fail("Not implemented");
    }

    @Override
    public Result delete(Transaction transaction) {
        return Result.Fail("Not implemented");
    }

    @Override
    public ResultOf<Collection<Transaction>> readAll() {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            Collection<Transaction> transactions =
                    stream.map(line -> {
                        String[] items = line.split(",");
                        return new Transaction(Integer.parseInt(items[0]), Double.parseDouble(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[2]));})
                            .collect(Collectors.toList());

            return Result.Ok(Optional.of(transactions));
        } catch (IOException e) {
            return Result.Fail(null, "Error reading file: " + e.getMessage());
        }
    }
}
