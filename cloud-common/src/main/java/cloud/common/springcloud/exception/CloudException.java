
package cloud.common.springcloud.exception;

public class CloudException extends RuntimeException {

    public CloudException() {
    }

    public CloudException(String message) {
        super(message);
    }

    /**
     * 丢出一个异常
     *
     * @param message
     */
    public static void fail(String message) {
        throw new CloudException(message);
    }

}
