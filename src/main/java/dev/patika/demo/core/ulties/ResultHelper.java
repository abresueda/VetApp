package dev.patika.demo.core.ulties;

import dev.patika.demo.core.result.Result;
import dev.patika.demo.core.result.ResultData;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        //Her veri eklendiğinde created olduğuysa bu mesajı döndürür.
        return new ResultData<>(true, Message.CREATED, "201", data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, Message.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Message.OK, "200", data);
    }

    public static Result ok() {
        return new Result(true, Message.OK, "200");
    }

    public static Result notFoundError(String msg) {
        return new Result(false, msg, "404");
    }

    public static Result error(String msg) {
        return new Result(false, msg, "400");
    }
}
