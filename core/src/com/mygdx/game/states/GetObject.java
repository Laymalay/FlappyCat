package com.mygdx.game.states;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import org.xmlpull.v1.XmlPullParserException;
import io.minio.MinioClient;
import io.minio.errors.MinioException;

/**
 * Created by alinka on 10.5.17.
 */

public class GetObject {
    public ScoreList get()
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
        ScoreList scoreList = new ScoreList();
        try {
            MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "BWU8MSL31F0NHIO75JFT",
                                                        "FCEYg9mdwbuCLsqCL85lA+LJRXX2WIL/auCRQBmd");
            minioClient.statObject("flayingblur", "scoretable");
            InputStream stream = minioClient.getObject("flayingblur", "scoretable");
            ObjectInputStream oin = new ObjectInputStream(stream);
            scoreList = (ScoreList) oin.readObject();
            oin.close();
            stream.close();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return scoreList;
    }
}