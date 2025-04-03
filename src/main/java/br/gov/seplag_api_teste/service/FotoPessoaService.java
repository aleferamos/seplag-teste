package br.gov.seplag_api_teste.service;

import br.gov.seplag_api_teste.entity.FotoPessoa;
import br.gov.seplag_api_teste.repository.FotoPessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FotoPessoaService {
    private final FotoPessoaRepository repository;
    private final MinioService minioService;

    @Value("${minio.bucket}")
    private String bucket;

    public List<FotoPessoa> salvar(List<FotoPessoa> fotoPessoas) {
        fotoPessoas.forEach(foto -> {
            var fotoAsBase64 = foto.getFotoAsBase64();

            if (!ObjectUtils.isEmpty(fotoAsBase64)) {
                var hashFoto = gerarHashDaImagemBase64(fotoAsBase64);
                var nomeFoto = gerarNomeDoArquivo(foto, hashFoto);
                foto.setHash(hashFoto);
                foto.setBucket(bucket);
                foto.setNomeArquivo(nomeFoto);
                minioService.uploadBase64(fotoAsBase64, nomeFoto);
            }

            foto.setData(new Date());
        });

        return repository.saveAll(fotoPessoas);
    }

    private String gerarNomeDoArquivo(FotoPessoa fotoPessoa, String hash) {
        return String.format(
                "%d/%s.%s",
                fotoPessoa.getPessoa().getId(),
                hash,
                detectarExtensaoPorBytes(Base64.getDecoder().decode(fotoPessoa.getFotoAsBase64()))
        );
    }

    private String gerarHashDaImagemBase64(String base64) {
        try {
            byte[] imagemBytes = Base64.getDecoder().decode(base64);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(imagemBytes);

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();

        } catch (Exception e) {
            throw new InternalError("Erro ao gerar hash da imagem", e);
        }
    }

    private String detectarExtensaoPorBytes(byte[] bytes) {
        if (bytes == null || bytes.length < 4) {
            throw new IllegalArgumentException("Arquivo muito pequeno ou nulo");
        }

        if (bytes[0] == (byte) 0xFF && bytes[1] == (byte) 0xD8) {
            return "jpg";
        }

        if (bytes[0] == (byte) 0x89 && bytes[1] == (byte) 0x50 &&
                bytes[2] == (byte) 0x4E && bytes[3] == (byte) 0x47) {
            return "png";
        }

        if (bytes[0] == (byte) 0x47 && bytes[1] == (byte) 0x49 &&
                bytes[2] == (byte) 0x46) {
            return "gif";
        }

        if (bytes[0] == (byte) 0x25 && bytes[1] == (byte) 0x50 &&
                bytes[2] == (byte) 0x44 && bytes[3] == (byte) 0x46) {
            return "pdf";
        }

        if (bytes[0] == (byte) 0x42 && bytes[1] == (byte) 0x4D) {
            return "bmp";
        }

        if (isAsciiText(bytes)) {
            return "txt";
        }

        throw new IllegalArgumentException("Tipo de arquivo não reconhecido");
    }

    private boolean isAsciiText(byte[] bytes) {
        for (byte b : bytes) {
            if (b < 0x09 || (b > 0x0D && b < 0x20)) {
                return false;
            }
        }
        return true;
    }

    List<FotoPessoa> buscarFotosDeUmaPessoa(Long id){
        return repository.findAllByPessoaId(id);
    }
}
