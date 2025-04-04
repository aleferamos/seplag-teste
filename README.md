// TODO dados inscrição

# Seplag Teste

# Instruções para executação

- Primeiramente Verifique se a máquina possui Docker se não tiver instale. https://www.docker.com
- Clone este projeto, após a clonagem entre na pasta scripts fica assim seplag-teste\scripts, digite (docker-compose up -d) essa é a saída esperada.

```bash
[+] Running 5/5
 ✔ Network scripts_default      Created                                                                                                                                                                                                                          0.3s 
 ✔ Volume "scripts_pgdata"      Created                                                                                                                                                                                                                          0.0s 
 ✔ Volume "scripts_minio_data"  Created                                                                                                                                                                                                                          0.0s 
 ✔ Container minio              Started                                                                                                                                                                                                                          1.0s 
 ✔ Container postgres           Started
```
- Após isso já temos o banco de dados e o minio subidos, com o usuario e senha já inseridos para poder fazer autenticação.
- Confirme que o banco está up com as seguintes credenciais. Pode estar conectando em algum software de conexões com banco, indico o DBeaver. https://dbeaver.io
```bash
Host: localhost
Banco de dados: meubanco
Usuario: admin
Senha: admin123
```
- Confirme que o minio está up também, basta acessar http://localhost:9001, e entrar com as credencias
```bash
Username: admin
Password: admin123
```
- Após isso basta instalar o java ou open jdk 23. https://www.oracle.com/br/java/technologies/downloads/#java23
- Após a instalação insira a variável de ambiente JAVA_HOME=C:\Program Files\Java\jdk-23 e atualizar o Path com o bin dessa pasta C:\Program Files\Java\jdk-23\bin e fica da seguinte forma.
  ![image](https://github.com/user-attachments/assets/59a2a0e4-52c1-42a6-abcf-fa94cc32ca9f)

- Agora basta voltar um diretorio cd .. e digitar o comando (./mvnw clean package -DskipTests) voce verá uma saída como
```bash
[INFO] --- jar:3.4.2:jar (default-jar) @ seplag-api-teste ---
[INFO] Building jar: C:\Users\AcostaCell\Documents\pessoal\seplag-teste\target\seplag-api-teste-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot:3.2.4:repackage (repackage) @ seplag-api-teste ---
[INFO] Replacing main artifact C:\Users\AcostaCell\Documents\pessoal\seplag-teste\target\seplag-api-teste-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\AcostaCell\Documents\pessoal\seplag-teste\target\seplag-api-teste-0.0.1-SNAPSHOT.jar.original
[INFO]
[INFO] --- spring-boot:3.2.4:repackage (default) @ seplag-api-teste ---
[INFO] Replacing main artifact C:\Users\AcostaCell\Documents\pessoal\seplag-teste\target\seplag-api-teste-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\AcostaCell\Documents\pessoal\seplag-teste\target\seplag-api-teste-0.0.1-SNAPSHOT.jar.original
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.502 s
[INFO] Finished at: 2025-04-03T21:00:11-04:00
[INFO] ------------------------------------------------------------------------
```
