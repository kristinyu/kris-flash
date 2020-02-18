package com.kris.edu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtils.class);
    private static String ivParameterSpec = "3465281205462396";
    private static String key = "uQAZBjZrbshFp9Ix";
    private static String ivUserParameterSpec = "60d8065c65354d14";
    private static String keyUser = "ba54717b31914fdf";

    public AESUtils() {
    }

    public static String encript(String phone) {
        try {
            return assemble(phone, key, ivParameterSpec);
        } catch (Exception var2) {
            return null;
        }
    }

    public static String decript(String securityPhone) {
        try {
            return disassemble(securityPhone, key, ivParameterSpec);
        } catch (Exception var2) {
            return null;
        }
    }

    public static String encriptUser(String phone) {
        try {
            return assemble(phone, keyUser, ivUserParameterSpec);
        } catch (Exception var2) {
            return null;
        }
    }

    public static String decriptUser(String securityPhone) {
        try {
            return disassemble(securityPhone, keyUser, ivUserParameterSpec);
        } catch (Exception var2) {
            return null;
        }
    }

    public static String encriptCrm(String phone, String keyCrm, String ivCrmParameterSpec) {
        try {
            return assemble(phone, keyCrm, ivCrmParameterSpec);
        } catch (Exception var4) {
            return null;
        }
    }

    public static String decriptCrm(String securityPhone, String keyCrm, String ivCrmParameterSpec) {
        try {
            return disassemble(securityPhone, keyCrm, ivCrmParameterSpec);
        } catch (Exception var4) {
            return null;
        }
    }

    public static String longToEncript(Long data) {
        try {
            return data == null ? null : encript(String.valueOf(data));
        } catch (Exception var2) {
            return null;
        }
    }

    public static Long decriptToLong(String securityData) {
        try {
            String data = decript(securityData);
            return data == null ? null : Long.valueOf(data);
        } catch (Exception var2) {
            return null;
        }
    }

    private static String assemble(String sSrc, String sKey, String sIv) {
        try {
            if (sKey == null) {
                return null;
            } else if (sKey.length() != 16) {
                return null;
            } else {
                byte[] raw = sKey.getBytes();
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
                cipher.init(1, skeySpec, iv);
                byte[] encrypted = cipher.doFinal(sSrc.getBytes());
                return byte2hex(encrypted).toLowerCase();
            }
        } catch (Exception var8) {
            LOGGER.error("AESEncryptUtils assemble : error", var8);
            return null;
        }
    }

    private static String disassemble(String sSrc, String sKey, String sIv) {
        try {
            if (sKey == null) {
                return null;
            } else if (sKey.length() != 16) {
                return null;
            } else {
                byte[] raw = sKey.getBytes("ASCII");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
                cipher.init(2, skeySpec, iv);
                byte[] encrypted1 = hex2byte(sSrc);
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original);
            }
        } catch (Exception var9) {
            LOGGER.error("AESEncryptUtils disassemble : error", var9);
            return null;
        }
    }

    private static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        } else {
            int l = strhex.length();
            if (l % 2 == 1) {
                return null;
            } else {
                byte[] b = new byte[l / 2];

                for(int i = 0; i != l / 2; ++i) {
                    b[i] = (byte)Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
                }

                return b;
            }
        }
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";

        for(int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }

        return hs.toUpperCase();
    }
}
