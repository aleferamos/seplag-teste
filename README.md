// TODO dados inscrição

# Seplag Teste

# Instruções para executação

- Primeiramente Verifique se a máquina possui Docker se não tiver instale. https://www.docker.com
- Clone este projeto, após a clonagem entre na pasta scripts fica assim seplag-teste\scripts, digite docker-compose up -d essa é a saída esperada.
docker-compose up -d

```bash
[+] Running 5/5
 ✔ Network scripts_default      Created                                                                                                                                                                                                                          0.3s 
 ✔ Volume "scripts_pgdata"      Created                                                                                                                                                                                                                          0.0s 
 ✔ Volume "scripts_minio_data"  Created                                                                                                                                                                                                                          0.0s 
 ✔ Container minio              Started                                                                                                                                                                                                                          1.0s 
 ✔ Container postgres           Started
```
- Após isso já temos o banco de dados e o minio subidos, com o usuario e senha já inseridos para poder fazer autenticação.
- Confirme que o banco está subido com as seguintes credenciais.
```bash
Host - localhost
Banco de dados - meubanco
Usuario - admin
Senha - admin123
```
