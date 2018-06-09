package br.com.senacrs.air.negocio;

/**
 *
 * @author lhries
 */
public class NegocioException extends Exception {

    public NegocioException(String s) {
        super(s);
    }

    public NegocioException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NegocioException(Throwable throwable) {
        super(throwable);
    }
}
