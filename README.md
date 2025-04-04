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
- Após isso basta rodar o comando (java -jar .\target\seplag-api-teste-0.0.1-SNAPSHOT.jar) na pasta raiz, ou seja, nessa que você já está e verá uma saída como esta.
```bash
2025-04-03T21:01:46.196-04:00  INFO 12928 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-04-03T21:01:46.200-04:00  INFO 12928 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-04-03T21:01:46.731-04:00  WARN 12928 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-04-03T21:01:47.073-04:00  INFO 12928 --- [           main] eAuthenticationProviderManagerConfigurer : Global AuthenticationManager configured with AuthenticationProvider bean with name authenticationProvider
2025-04-03T21:01:47.074-04:00  WARN 12928 --- [           main] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with an AuthenticationProvider bean. UserDetailsService beans will not be used by Spring Security for automatically
 configuring username/password login. Consider removing the AuthenticationProvider bean. Alternatively, consider using the UserDetailsService in a manually instantiated DaoAuthenticationProvider. If the current configuration is intentional, to turn off this warning, increase the logging level of 'org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer' to ERROR
2025-04-03T21:01:47.179-04:00  INFO 12928 --- [           main] o.s.d.j.r.query.QueryEnhancerFactory     : Hibernate is in classpath; If applicable, HQL parser will be used.
2025-04-03T21:01:49.128-04:00  INFO 12928 --- [           main] o.s.v.b.OptionalValidatorFactoryBean     : Failed to set up a Bean Validation provider: jakarta.validation.NoProviderFoundException: Unable to create a Configuration, because no Jakarta Bean Validation provider could be found. Add a provider like Hibernate Validator (RI) to your classpath.
2025-04-03T21:01:49.560-04:00  INFO 12928 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/seplag-api'
2025-04-03T21:01:49.575-04:00  INFO 12928 --- [           main] b.g.s.SeplagApiTesteApplication          : Started SeplagApiTesteApplication in 9.406 seconds (process running for 10.12)
```
- Após isso a ai já está operante, agora basta acessar o swagger para testar os end-points (http://localhost:8080/seplag-api/swagger-ui/index.html), verá uma saída como essa.
![image](https://github.com/user-attachments/assets/628ff20d-559f-404d-b4cd-613341435eeb)


# Conteúdo Swagger
Vejamos, temos os CRUD de Servidor Efetivo, Unidade, Lotação e Servidor Temporario. E tambem temos os end-points que foi solicitado no processo seletivo oque aparece nessa imagem.
![image](https://github.com/user-attachments/assets/d0d4c589-af52-4bd8-a793-a274f626d605)

POST
/endpoints-edital/upload-fotos - esse end point vamos utilizar para subir fotografias de uma pessoa para utilizar, primeiramente terá que criar uma pessoa então podemos usar o end-point.

POST
/servidor-efetivo/salvar - esse end point serve para salvar um servidor efetivo junto com a pessoa dele, então primeiro vamos fazer a autenticação .

POST
/auth/login

Basta clicar em (try it out) foi configurado já com o usuario e senha do usuario que foi inserido no banco.
![image](https://github.com/user-attachments/assets/ec7e5a8f-6094-4fa7-a484-c121b0d772ac)

Será gerado um json parecido com este
```bash
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W10sInN1YiI6InNlcGxhZ0BlbWFpbC5jb20iLCJpYXQiOjE3NDM3Mjg5NjMsImV4cCI6MTc0MzcyOTI2M30.1eLvzqrzaemqcggnglTpoDper6W3x9q0YLiNIdyAI00",
  "expiresIn": 300000,
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZXBsYWdAZW1haWwuY29tIiwiaWF0IjoxNzQzNzI4OTYzLCJleHAiOjE3NDQzMzM3NjN9.fE0vPDnJMld26OFnVzIxhaUgx-GnyaMRDtLzjNshZ5s"
}
```

Basta copiar o token e inserir no campo de login do swagger
![image](https://github.com/user-attachments/assets/361f25fc-71ec-4cb3-aed3-d4da61b1555c)
![image](https://github.com/user-attachments/assets/1fdb701a-d77f-437b-af85-4eaa44911d68)

- Após isso você já está autenticado agora vamos voltar pro end point de cadastro de pessoa

POST
/servidor-efetivo/salvar

![image](https://github.com/user-attachments/assets/085d81fe-139e-47f3-9d1d-21cb29b86336)

Caso de certo você verá essa imagem

![image](https://github.com/user-attachments/assets/a84376f8-c671-4a0a-8470-e0e4080d634a)

