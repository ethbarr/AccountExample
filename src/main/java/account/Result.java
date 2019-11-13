package account;

import java.util.Optional;

public class Result
{
    public boolean IsSuccess;
    public String Error;
    public boolean IsFailure() {return !IsSuccess;}

    protected Result(boolean isSuccess, String error)
    {
        if (isSuccess && error != "")
            throw new RuntimeException();
        if (!isSuccess && error == "")
            throw new RuntimeException();

        IsSuccess = isSuccess;
        Error = error;
    }

    public static Result Fail(String message)
    {
        return new Result(false, message);
    }

    public static Result Ok()
    {
        return new Result(true, "");
    }

    public static Result Combine(Result[] results)
    {
        for (Result result : results)
        {
            if (result.IsFailure())
                return result;
        }

        return Ok();
    }

    public static <T> ResultOf<T> Fail(T defaultValue, String message)
    {
        return new ResultOf<T>((Optional<T>) defaultValue, false, message);
    }

    public static <T> ResultOf<T> Ok(Optional<T> value)
    {
        return new ResultOf<T>(value, true, "");
    }

}


