package br.com.senacrs.air.dao.impl_BD;

public class BDException extends RuntimeException {

    public BDException(String s) {
        super(s);
    }

    public BDException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BDException(Throwable throwable) {
        super(throwable);
    }
}
