package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.lang.StringBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import org.omg.CORBA.portable.OutputStream;
import org.xmlpull.v1.XmlPullParserException;
import io.minio.MinioClient;
import io.minio.errors.MinioException;

/**
 * Created by alinka on 10.5.17.
 */

public class PutObject {
    ScoreList scores;
    public PutObject(ScoreList scoreList){
        scores = scoreList;
    }
    public void put()
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
        try {
            MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "BWU8MSL31F0NHIO75JFT",
                    "FCEYg9mdwbuCLsqCL85lA+LJRXX2WIL/auCRQBmd");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(scores);
            oos.flush();
            oos.close();
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            ByteArrayInputStream bais = new ByteArrayInputStream(scores.toString().getBytes("UTF-8"));
            minioClient.putObject("flayingblur", "scoretable", is, is.available(), "application/octet-stream");
            bais.close();
            System.out.println("scoretable is uploaded successfully");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}