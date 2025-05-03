package com.nocode.main.infrastructure.adapter.externalservices;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.nocode.main.infrastructure.shared.exception.CloudinaryUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "folder", "commons",
                    "format", "webp",
                    "quality", "auto",
                    "transformation", new Transformation()
                            .width(800)
                            .height(800)
                            .crop("limit")
                            .generate()
            ));

            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            throw new CloudinaryUploadException("Error al subir imagen a Cloudinary: " + e.getMessage());
        }
    }

    public void deleteImage(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            throw new CloudinaryUploadException("Error al eliminar la imagen de Cloudinary: " + e.getMessage());
        }
    }


    public String extractPublicIdFromImageUrl(String imageUrl) {
        // Cloudinary image URL example: "https://res.cloudinary.com/demo/image/upload/v1614575737/sample.jpg"
        String[] urlParts = imageUrl.split("/");
        String fileNameWithExtension = urlParts[urlParts.length - 1];
        String publicId = fileNameWithExtension.split("\\.")[0]; // Remove the extension
        return publicId;
    }
}
