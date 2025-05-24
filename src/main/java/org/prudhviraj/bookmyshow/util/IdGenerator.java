package org.prudhviraj.bookmyshow.util;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;


@Service
public class IdGenerator {
    private static final char[] ID_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static final int SUFFIX_LENGTH = 8;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String NAME_PAD_CHAR = "X";

    public String generateBookingId() {
        return "BPR-" + NanoIdUtils.randomNanoId().toUpperCase();
    }

}
