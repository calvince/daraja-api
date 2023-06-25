package dev.vexla.mpesaDaraja.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;


@Slf4j
public class Helper {
    //value to be converted to base 64

    public static String toBase64(String value) {
        byte[] data = value.getBytes(StandardCharsets.ISO_8859_1);
        return Base64.getEncoder().encodeToString(data);
    }

    public static String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    //encrypt password using cipher and certificate
    @SneakyThrows
    public static String getSecurityCredential(String b2cInitiatorPassword) {
        String encryptedPassword;
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] input = b2cInitiatorPassword.getBytes();
            Resource resource = new ClassPathResource("cert.cer");
            InputStream inputStream = resource.getInputStream();

            FileInputStream fileInputStream = new FileInputStream(resource.getFile());
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCPadding", "BC");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
            PublicKey key = certificate.getPublicKey();
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] cipherText = cipher.doFinal(input);

            //convert the cipherText into a String with the help of Base64
            encryptedPassword = Base64.getEncoder().encodeToString(cipherText).trim();
            return encryptedPassword;
        } catch (NoSuchProviderException | NoSuchAlgorithmException | IllegalBlockSizeException | CertificateException |
                 InvalidKeyException | NoSuchPaddingException
                 | BadPaddingException | FileNotFoundException e) {
            log.error(String.format("Error generating security credentials ->%s", e.getLocalizedMessage()));
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
