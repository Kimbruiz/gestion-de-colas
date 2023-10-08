package essalud.gob.pe.gestionatencionservice.util;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public abstract class RandomUuidCreator {

    private static final int RANDOM_VERSION = 4;

    /**
     * Returns a random-based UUID.
     *
     * It uses a thread local {@link SecureRandom}.
     *
     * @return a random-based UUID
     */
    public static UUID getRandomUuid() {
        return getRandomUuid(SecureRandomLazyHolder.THREAD_LOCAL_RANDOM.get());
    }

    /**
     * Returns a random-based UUID.
     *
     * It uses any instance of {@link Random}.
     *
     * @return a random-based UUID
     */
    public static UUID getRandomUuid(Random random) {

        long msb = 0;
        long lsb = 0;

        // (3) set all bit randomly
        if (random instanceof SecureRandom) {
            // Faster for instances of SecureRandom
            final byte[] bytes = new byte[16];
            random.nextBytes(bytes);
            msb = toNumber(bytes, 0, 8); // first 8 bytes for MSB
            lsb = toNumber(bytes, 8, 16); // last 8 bytes for LSB
        } else {
            msb = random.nextLong(); // first 8 bytes for MSB
            lsb = random.nextLong(); // last 8 bytes for LSB
        }

        // Apply version and variant bits (required for RFC-4122 compliance)
        msb = (msb & 0xffffffffffff0fffL) | (RANDOM_VERSION & 0x0f) << 12; // apply version bits
        lsb = (lsb & 0x3fffffffffffffffL) | 0x8000000000000000L; // apply variant bits

        // Return the UUID
        return new UUID(msb, lsb);
    }

    private static long toNumber(final byte[] bytes, final int start, final int length) {
        long result = 0;
        for (int i = start; i < length; i++) {
            result = (result << 8) | (bytes[i] & 0xff);
        }
        return result;
    }

    // Holds thread local secure random
    private static class SecureRandomLazyHolder {
        static final ThreadLocal<Random> THREAD_LOCAL_RANDOM = ThreadLocal.withInitial(SecureRandom::new);
    }

    public static String generateSpecial() {
        String uiid = RandomUuidCreator.getRandomUuid().toString();
        Date currentDate = new Date();

        StringBuilder sb = new StringBuilder()
                .append(StringUtilities.randomString(2))
                .append(DateUtilities.formatPart(currentDate,"dd"))
                .append(StringUtilities.randomString(2))
                .append(DateUtilities.formatPart(currentDate,"MM"))
                .append(StringUtilities.randomString(2))
                .append("-")
                .append(uiid)
                .append("-")
                .append(StringUtilities.randomString(2))
                .append(DateUtilities.formatPart(currentDate,"yy"))
                .append(StringUtilities.randomString(2))
                .append(DateUtilities.formatPart(currentDate,"HH"))
                .append(StringUtilities.randomString(2))
                .append("-")
                .append(StringUtilities.randomString(2))
                .append(DateUtilities.formatPart(currentDate,"mm"))
                .append(StringUtilities.randomString(2))
                .append(DateUtilities.formatPart(currentDate,"ss"))
                .append(StringUtilities.randomString(2));

        return sb.toString();
    }

    /**
     * For tests!
     */
    /*public static void main(String[] args) {

        System.out.println("// Using thread local `java.security.SecureRandom` (DEFAULT)");
        System.out.println("RandomUuidCreator.getRandomUuid()");
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.println(RandomUuidCreator.getRandomUuid());
        }

        System.out.println();
        System.out.println("// Using `java.util.Random` (FASTER)");
        System.out.println("RandomUuidCreator.getRandomUuid(new Random())");
        System.out.println();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            System.out.println(RandomUuidCreator.getRandomUuid(random));
        }
    }*/
}