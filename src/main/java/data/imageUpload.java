package data;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.util.Map;

public class imageUpload {
    public static void main(String[] args) {
        // Configure cloudinary account

        try {



            uploadImage(new File("/Users/arfat/Downloads/laptop.png"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String uploadImage(File image) throws Exception {
        // Configure cloudinary account
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "df6siehnq",
                "api_key", "895979429173171",
                "api_secret", "f7SpclQJ21OzdBCa5OuqIcb9ORQ"));

        Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
        System.out.println(uploadResult.get("url"));
        return uploadResult.get("url").toString();
    }


}

