package account;

public class Validator {
    public static Result validate(Double value) {
        if (value == null)
            return Result.Fail("Value is null");

        if(value < 0)
            return Result.Fail("Value is less than zero");

        return Result.Ok();
    }
}
