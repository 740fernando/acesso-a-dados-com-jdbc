 <h1> Acesso a banco de dados com JDBC</h1>
 
- Conhecer os principais recursos do JDBC na teoria e prática
- Elaborar a estrutura básica de um projeto com JDBC
- Implementar o padrão DAO manualmente com JDBC

<h2>Visão geral do JDBC</h2>
- JDBC (Java Database Connectivity): API padrão do Java para acesso a dados
<h3>Páginas oficiais: </h3>
- https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/
- https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html
  <h4> Pacotes: java.sql e javax.sql (API suplementar para servidores)</h4>
  
 <h2> Preparação do projeto no Eclipse </h2>
   Código fonte: https://github.com/acenelio/jdbc1
<h4>Checklist:</h4>

- Usando o MySQL Workbench, crie uma base de dados chamada "coursejdbc"
- Baixar o MySQL Java Connector
- Caso ainda não exista, criar uma User Library contendo o arquivo .jar do driver do MySQL
o Window -> Preferences -> Java -> Build path -> User Libraries
o Dê o nome da User Library de MySQLConnector
o Add external JARs -> (localize o arquivo jar)
- Criar um novo Java Project o Acrescentar a User Library MySQLConnector ao projeto

- Na pasta raiz do projeto, criar um arquivo "db.properties" contendo os dados de conexão:
                  <h5>- user=root</h5>
                  <h5>- password=root</h5>
                  <h5> - dburl=jdbc:mysql://localhost:3306/coursejdbc</h5>
                  <h5> - useSSL=false</h5>
- No pacote "db", criar uma exceção personalizada DbException
- No pacote "db", criar uma classe DB com métodos estáticos auxiliares
o Obter e fechar uma conexão com o banco
