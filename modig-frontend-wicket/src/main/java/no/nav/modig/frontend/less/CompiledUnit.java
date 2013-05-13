package no.nav.modig.frontend.less;

import org.apache.wicket.util.time.Time;

/**
 * Unit containing the compiled css as a byte array and last modified time of the source.
 *
 * Immutable.
 *
 * NOT A PART OF THE PUBLIC API
 */
final class CompiledUnit {
    private static int count = 0;

    private final byte[] compiledBytes;
    private final Time compiledTime;
    private final int version;

    CompiledUnit() {
        this(new byte[]{}, Time.START_OF_UNIX_TIME);
    }

    CompiledUnit(byte[] compiledBytes, Time compiledTime) {
        this.compiledBytes = compiledBytes;
        this.compiledTime = compiledTime;
        version = ++count;
    }

    public byte[] getCompiledBytes() {
        return compiledBytes;
    }

    public Time getCompiledTime() {
        return compiledTime;
    }

    public int getVersion() {
        return version;
    }

    public static void resetCount() {
        count = 0;
    }
}
