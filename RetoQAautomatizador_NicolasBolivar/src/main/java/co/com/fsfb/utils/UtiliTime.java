package co.com.fsfb.utils;

import java.util.concurrent.Callable;

public class UtiliTime {

    public static Callable<Boolean> condicionExitosa(){
        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return null;
            }
        };
    }
}
