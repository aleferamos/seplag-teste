package br.gov.seplag_api_teste.service;

import br.gov.seplag_api_teste.config.MinioPropertiesConfig;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioPropertiesConfig properties;

    private MinioClient getClient() {
        return MinioClient.builder()
                .endpoint(properties.getUrl())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }

    public void uploadBase64(String base64, String nomeArquivo) {
        try {
            byte[] dados = Base64.getDecoder().decode(base64);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(dados);

            MinioClient client = getClient();

            boolean bucketExists = client.bucketExists(BucketExistsArgs.builder()
                    .bucket(properties.getBucket()).build());

            if (!bucketExists) {
                client.makeBucket(MakeBucketArgs.builder()
                        .bucket(properties.getBucket()).build());
            }

            client.putObject(PutObjectArgs.builder()
                    .bucket(properties.getBucket())
                    .object(nomeArquivo)
                    .stream(inputStream, dados.length, -1)
                    .build());

        } catch (Exception e) {
            throw new InternalError("Erro ao enviar arquivo para o MinIO", e);
        }
    }

    public String gerarLinkTemporario(String nomeArquivo) {
        try {
            MinioClient client = getClient();

            return client.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(properties.getBucket())
                            .object(nomeArquivo)
                            .expiry(1, TimeUnit.MINUTES)
                            .build()
            );
        } catch (Exception e) {
            throw new InternalError("Erro ao gerar link temporário para o arquivo", e);
        }
    }
}
